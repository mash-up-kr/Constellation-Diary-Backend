package com.kancho.byeolbyeol.home.application;

import com.kancho.byeolbyeol.common.util.TimeCalculate;
import com.kancho.byeolbyeol.horoscope.domain.constellation.Constellation;
import com.kancho.byeolbyeol.horoscope.domain.constellation.ConstellationRepository;
import com.kancho.byeolbyeol.daily_question.domain.DailyQuestion;
import com.kancho.byeolbyeol.daily_question.domain.DailyQuestionRepository;
import com.kancho.byeolbyeol.diary.domain.Diary;
import com.kancho.byeolbyeol.diary.domain.DiaryRepository;
import com.kancho.byeolbyeol.home.dto.ResHomeViewDto;
import com.kancho.byeolbyeol.home.exception.NotFoundHoroscopeException;
import com.kancho.byeolbyeol.home.exception.NotFoundQuestionException;
import com.kancho.byeolbyeol.horoscope.domain.horoscope.Horoscope;
import com.kancho.byeolbyeol.horoscope.domain.horoscope.HoroscopeRepository;
import com.kancho.byeolbyeol.user.domain.user.User;
import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import com.kancho.byeolbyeol.common.exception.NotFoundConstellationException;
import com.kancho.byeolbyeol.common.exception.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final static String COMMON_QUESTION = "오늘의 운세를\n확인 해보세요";

    private final UserRepository userRepository;
    private final DailyQuestionRepository dailyQuestionRepository;
    private final DiaryRepository diaryRepository;
    private final HoroscopeRepository horoscopeRepository;
    private final ConstellationRepository constellationRepository;
    private final HomeMapper homeMapper;

    public ResHomeViewDto getHomeView(Long id) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDate nowLocalDate = TimeCalculate.covertKstLocalDate(nowDateTime);
        LocalDateTime startTime = TimeCalculate.createStartKstTime(nowDateTime);
        LocalDateTime endTime = TimeCalculate.createEndKstTime(nowDateTime);

        User user = userRepository.findById(id)
                .orElseThrow(NotFoundUserException::new);

        Constellation constellation = constellationRepository.findById(user.getConstellationsId())
                .orElseThrow(NotFoundConstellationException::new);

        Horoscope horoscope =
                horoscopeRepository.findByConstellationsIdAndDate(constellation.getId(), nowLocalDate)
                        .orElseThrow(NotFoundHoroscopeException::new);

        Optional<Diary> diary = diaryRepository.findByUsersIdAndDateBetween(id, startTime, endTime);
        if (diary.isPresent()) {
            return homeMapper.toResHomeViewDto(diary.get(), horoscope, constellation);
        }

        if (user.isPreviousQuestionTime(nowDateTime.toLocalTime(), TimeCalculate.KST_NINE)) {
            return homeMapper.toResHomeViewDto(COMMON_QUESTION, horoscope, constellation);
        }

        DailyQuestion dailyQuestion = dailyQuestionRepository.findByDate(nowLocalDate)
                .orElseThrow(NotFoundQuestionException::new);
        return homeMapper.toResHomeViewDto(dailyQuestion.getContent(), horoscope, constellation);
    }
}




