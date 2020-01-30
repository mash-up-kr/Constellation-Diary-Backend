package com.kancho.daily.application;

import com.kancho.common.constant.ReqTimeZone;
import com.kancho.common.user_context.UserInfo;
import com.kancho.common.util.TimeCalculate;
import com.kancho.daily.domain.Diary;
import com.kancho.daily.domain.DiaryRepository;
import com.kancho.daily.dto.*;
import com.kancho.daily.exception.IsExceedWriteDiaryException;
import com.kancho.daily.exception.IsNotTheWriterException;
import com.kancho.daily.exception.NotFoundDiaryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public ResDiariesDto getDiaries(UserInfo userInfo, Integer year, Integer month, ReqTimeZone reqTimeZone) {
        LocalDateTime startDate = TimeCalculate.createStartDate(year, month, reqTimeZone);
        LocalDateTime endDate = TimeCalculate.createEndDate(year, month, reqTimeZone);
        List<Diary> diaries =
                diaryRepository.findByUsersIdAndDateGreaterThanEqualAndDateLessThan(userInfo.getId(), startDate, endDate);

        if (diaries.isEmpty()) {
            return ResDiariesDto.builder()
                    .diaries(new ArrayList<>())
                    .build();
        }

        List<ResSimpleDiaryDto> resSimpleDiaryDtos =
                diaries.stream()
                        .map(diary -> ResSimpleDiaryDto.builder()
                                .id(diary.getId())
                                .title(diary.getTitle())
                                .date(TimeCalculate.covertDate(diary.getDate(), reqTimeZone))
                                .build())
                        .collect(Collectors.toList());

        return ResDiariesDto.builder()
                .timeZone(reqTimeZone.getValue())
                .diaries(resSimpleDiaryDtos)
                .build();
    }

    public void writeDiary(UserInfo userInfo, ReqWriteDiaryDto reqWriteDiaryDto, ReqTimeZone reqTimeZone) {
        LocalDateTime nowDateTime = TimeCalculate.covertLocalDateTime(reqWriteDiaryDto.getDate());
        LocalDateTime startTime = TimeCalculate.createStartTime(nowDateTime, reqTimeZone);
        LocalDateTime endTime = TimeCalculate.createEndTime(nowDateTime, reqTimeZone);

        Optional<Diary> restrictDiary =
                diaryRepository.findByUsersIdAndDateBetween(userInfo.getId(), startTime, endTime);

        if (restrictDiary.isPresent()) {
            throw new IsExceedWriteDiaryException();
        }

        Diary diary = Diary.builder()
                .content(reqWriteDiaryDto.getContent())
                .date(nowDateTime)
                .title(reqWriteDiaryDto.getTitle())
                .horoscopeId(reqWriteDiaryDto.getHoroscopeId())
                .userId(userInfo.getId())
                .build();

        diaryRepository.save(diary);
    }

    public ResDiaryDto getDiary(UserInfo userInfo, Long diaryId, ReqTimeZone reqTimeZone) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(NotFoundDiaryException::new);

        if (diary.isNotTheWriter(userInfo.getId())) {
            throw new IsNotTheWriterException();
        }

        return ResDiaryDto.builder()
                .id(diary.getId())
                .timeZone(reqTimeZone.getValue())
                .date(TimeCalculate.covertDate(diary.getDate(), reqTimeZone))
                .title(diary.getTitle())
                .content(diary.getContent())
                .horoscopeId(diary.getHoroscopeId())
                .build();
    }

    @Transactional
    public ResDiaryDto modifyDiary(UserInfo userInfo, Long diaryId,
                                   ReqModifyDiaryDto reqModifyDiaryDto, ReqTimeZone reqTimeZone) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(NotFoundDiaryException::new);

        if (diary.isNotTheWriter(userInfo.getId())) {
            throw new IsNotTheWriterException();
        }

        diary.modify(reqModifyDiaryDto.getTitle(), reqModifyDiaryDto.getContent());

        return ResDiaryDto.builder()
                .id(diary.getId())
                .timeZone(reqTimeZone.getValue())
                .date(TimeCalculate.covertDate(diary.getDate(), reqTimeZone))
                .title(diary.getTitle())
                .content(diary.getContent())
                .horoscopeId(diary.getHoroscopeId())
                .build();
    }

    public void deleteDiary(UserInfo userInfo, Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(NotFoundDiaryException::new);

        if (diary.isNotTheWriter(userInfo.getId())) {
            throw new IsNotTheWriterException();
        }

        diaryRepository.delete(diary);
    }

}
