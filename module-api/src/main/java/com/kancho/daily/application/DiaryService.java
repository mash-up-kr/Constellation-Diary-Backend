package com.kancho.daily.application;

import com.kancho.common.constant.ReqTimeZone;
import com.kancho.common.user_context.UserInfo;
import com.kancho.common.util.MonthRange;
import com.kancho.common.util.TimeCalculate;
import com.kancho.daily.domain.Diary;
import com.kancho.daily.domain.DiaryRepository;
import com.kancho.daily.dto.request.ReqDiariesDto;
import com.kancho.daily.dto.request.ReqModifyDiaryDto;
import com.kancho.daily.dto.request.ReqWriteDiaryDto;
import com.kancho.daily.dto.response.*;
import com.kancho.daily.exception.IsExceedWriteDiaryException;
import com.kancho.daily.exception.IsNotTheWriterException;
import com.kancho.daily.exception.NotFoundDiaryException;
import com.kancho.daily.exception.IsEmptyDiariesException;
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
    private final DiaryCountService diaryCountService;

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
                                .date(TimeCalculate.covertDate(diary.getDate()))
                                .build())
                        .collect(Collectors.toList());

        return ResDiariesDto.builder()
                .timeZone(reqTimeZone.getValue())
                .diaries(resSimpleDiaryDtos)
                .build();
    }

    public ResDiaryDto writeDiary(UserInfo userInfo, ReqWriteDiaryDto reqWriteDiaryDto, ReqTimeZone reqTimeZone) {
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

        diary = diaryRepository.save(diary);

        return ResDiaryDto.builder()
                .id(diary.getId())
                .content(diary.getContent())
                .date(TimeCalculate.covertDate(diary.getDate()))
                .horoscopeId(diary.getHoroscopeId())
                .title(diary.getTitle())
                .timeZone(reqTimeZone.getValue())
                .build();
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
                .date(TimeCalculate.covertDate(diary.getDate()))
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

    public void deleteDiaries(UserInfo userInfo, ReqDiariesDto reqDiariesDto) {
        List<Diary> diaries = diaryRepository.findAllByIdInAndUsersId(reqDiariesDto.getDiaryIds(), userInfo.getId());
        if (diaries.isEmpty()) {
            throw new IsEmptyDiariesException();
        }

        diaryRepository.deleteAll(diaries);

    }

    public ResCountDiariesDto countDiaries(UserInfo userInfo, ReqTimeZone reqTimeZone, Integer year) {
        List<ResCountYearDiaryDto> resCountYearDiaryDtos = new ArrayList<>();

        for (int i = -2; i <= 2; i++) {
            List<MonthRange> month = TimeCalculate.createRangeMonth(year + i, reqTimeZone);
            ResCountYearDiaryDto resCountYearDiaryDto = diaryCountService.countDiaries(userInfo.getId(), month);
            resCountYearDiaryDto.addInfo(year + i);
            resCountYearDiaryDtos.add(resCountYearDiaryDto);
        }

        return ResCountDiariesDto.builder()
                .timeZone(reqTimeZone.getValue())
                .diaries(resCountYearDiaryDtos)
                .build();
    }
}
