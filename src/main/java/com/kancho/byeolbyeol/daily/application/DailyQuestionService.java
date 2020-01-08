package com.kancho.byeolbyeol.daily.application;

import com.kancho.byeolbyeol.common.util.TimeCalculate;
import com.kancho.byeolbyeol.daily.domain.daily_question.DailyQuestion;
import com.kancho.byeolbyeol.daily.domain.daily_question.DailyQuestionRepository;
import com.kancho.byeolbyeol.daily.domain.diary.Diary;
import com.kancho.byeolbyeol.daily.domain.diary.DiaryRepository;
import com.kancho.byeolbyeol.daily.dto.ResDailyQuestionDto;
import com.kancho.byeolbyeol.daily.exception.NotFoundQuestionException;
import com.kancho.byeolbyeol.user.domain.user.User;
import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import com.kancho.byeolbyeol.common.exception.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DailyQuestionService {

    private final static String COMMON_QUESTION = "오늘의 운세를\n확인 해보세요";

    private final UserRepository userRepository;
    private final DailyQuestionRepository dailyQuestionRepository;
    private final DiaryRepository diaryRepository;
    private final DailyQuestionMapper dailyQuestionMapper;

    public ResDailyQuestionDto getDailyQuestions(Long id) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDate nowLocalDate = TimeCalculate.covertKstLocalDate(nowDateTime);
        LocalDateTime startTime = TimeCalculate.createStartKstTime(nowDateTime);
        LocalDateTime endTime = TimeCalculate.createEndKstTime(nowDateTime);

        User user = userRepository.findById(id)
                .orElseThrow(NotFoundUserException::new);

        Optional<Diary> diary = diaryRepository.findByUsersIdAndDateBetween(id, startTime, endTime);
        if (diary.isPresent()) {
            return dailyQuestionMapper.toResHomeViewDto(diary.get());
        }

        if (user.isPreviousQuestionTime(nowDateTime.toLocalTime(), TimeCalculate.getKstDeadLine())) {
            return dailyQuestionMapper.toResHomeViewDto(COMMON_QUESTION);
        }

        DailyQuestion dailyQuestion = dailyQuestionRepository.findByDate(nowLocalDate)
                .orElseThrow(NotFoundQuestionException::new);
        return dailyQuestionMapper.toResHomeViewDto(dailyQuestion.getContent());
    }
}




