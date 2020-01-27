package com.kancho.byeolbyeol.daily.application;

import com.kancho.byeolbyeol.common.constant.ReqTimeZone;
import com.kancho.byeolbyeol.common.exception.FailAuthenticationException;
import com.kancho.byeolbyeol.common.util.TimeCalculate;
import com.kancho.byeolbyeol.daily.domain.daily_question.DailyQuestion;
import com.kancho.byeolbyeol.daily.domain.daily_question.DailyQuestionRepository;
import com.kancho.byeolbyeol.daily.domain.diary.Diary;
import com.kancho.byeolbyeol.daily.domain.diary.DiaryRepository;
import com.kancho.byeolbyeol.daily.dto.ResDailyQuestionDto;
import com.kancho.byeolbyeol.user.domain.user.User;
import com.kancho.byeolbyeol.user.domain.user.UserRepository;
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




