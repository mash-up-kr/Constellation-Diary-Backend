package com.kancho.daily.application;

import com.kancho.common.constant.ReqTimeZone;
import com.kancho.common.exception.FailAuthenticationException;
import com.kancho.common.util.TimeCalculate;
import com.kancho.daily.domain.Diary;
import com.kancho.daily.domain.DiaryRepository;
import com.kancho.daily.dto.ResDailyQuestionDto;
import com.kancho.daily_question.DailyQuestion;
import com.kancho.daily_question.DailyQuestionRepository;
import com.kancho.user.User;
import com.kancho.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DailyQuestionService {

    private final static String COMMON_QUESTION = "오늘의 운세를\n확인 해보세요";
    private final static String DAILY_QUESTION = "오늘 하루,\n어떠셨나요?";

    private final UserRepository userRepository;
    private final DailyQuestionRepository dailyQuestionRepository;
    private final DiaryRepository diaryRepository;
    private final DailyQuestionMapper dailyQuestionMapper;

    public ResDailyQuestionDto getDailyQuestions(Long id, Date date, ReqTimeZone reqTimeZone) {
        LocalDateTime nowDateTime = TimeCalculate.covertLocalDateTime(date);
        LocalDate nowLocalDate = TimeCalculate.covertLocalDate(nowDateTime, reqTimeZone);
        LocalDateTime startTime = TimeCalculate.createStartTime(nowDateTime, reqTimeZone);
        LocalDateTime endTime = TimeCalculate.createEndTime(nowDateTime, reqTimeZone);

        User user = userRepository.findById(id)
                .orElseThrow(FailAuthenticationException::new);

        Optional<Diary> diary = diaryRepository.findByUsersIdAndDateBetween(id, startTime, endTime);
        if (diary.isPresent()) {
            return dailyQuestionMapper.toResDailyQuestionDto(diary.get());
        }

        if (user.isPreviousQuestionTime(nowDateTime.toLocalTime(), TimeCalculate.getDeadLine(reqTimeZone))) {
            return dailyQuestionMapper.toResDailyQuestionDto(COMMON_QUESTION);
        }

        DailyQuestion dailyQuestion = dailyQuestionRepository.findByDate(nowLocalDate)
                .orElse(DailyQuestion.builder().content(DAILY_QUESTION).date(nowLocalDate).build());
        return dailyQuestionMapper.toResDailyQuestionDto(dailyQuestion.getContent());
    }
}




