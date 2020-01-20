package com.kancho.byeolbyeol.daily.application;

import com.kancho.byeolbyeol.common.user_context.UserInfo;
import com.kancho.byeolbyeol.common.util.TimeCalculate;
import com.kancho.byeolbyeol.daily.dto.*;
import com.kancho.byeolbyeol.daily.domain.diary.Diary;
import com.kancho.byeolbyeol.daily.domain.diary.DiaryRepository;
import com.kancho.byeolbyeol.daily.exception.IsExceedWriteDiaryException;
import com.kancho.byeolbyeol.daily.exception.IsNotTheWriterException;
import com.kancho.byeolbyeol.daily.exception.NotFoundDiaryException;
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

    public ResDiariesDto getDiaries(UserInfo userInfo, Integer year, Integer month) {
        LocalDateTime startDate = TimeCalculate.createStartKstDate(year, month);
        LocalDateTime endDate = TimeCalculate.createEndKstDate(year, month);
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
                                .date(TimeCalculate.covertDate(diary.getDate()))
                                .build())
                        .collect(Collectors.toList());

        return ResDiariesDto.builder()
                .diaries(resSimpleDiaryDtos)
                .build();
    }

    public void writeDiary(UserInfo userInfo, ReqWriteDiaryDto reqWriteDiaryDto) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime startTime = TimeCalculate.createStartKstTime(nowDateTime);
        LocalDateTime endTime = TimeCalculate.createEndKstTime(nowDateTime);

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

    public ResDiaryDto getDiary(UserInfo userInfo, Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(NotFoundDiaryException::new);

        if (diary.isNotTheWriter(userInfo.getId())) {
            throw new IsNotTheWriterException();
        }

        return ResDiaryDto.builder()
                .id(diary.getId())
                .date(TimeCalculate.covertDate(diary.getDate()))
                .title(diary.getTitle())
                .content(diary.getContent())
                .horoscopeId(diary.getHoroscopeId())
                .build();
    }

    @Transactional
    public ResDiaryDto modifyDiary(UserInfo userInfo, Long diaryId, ReqModifyDiaryDto reqModifyDiaryDto) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(NotFoundDiaryException::new);

        if (diary.isNotTheWriter(userInfo.getId())) {
            throw new IsNotTheWriterException();
        }

        diary.modify(reqModifyDiaryDto.getTitle(), reqModifyDiaryDto.getContent());

        return ResDiaryDto.builder()
                .id(diary.getId())
                .date(TimeCalculate.covertDate(diary.getDate()))
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
