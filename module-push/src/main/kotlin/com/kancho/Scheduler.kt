package com.kancho

import com.kancho.push.controller.PushController
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalTime

@Component
@Profile("horoscope")
class HoroscopeScheduler(val pushController: PushController) {

    @Scheduled(cron = "* */1 * * * *")
    fun startHoroscopeJob() = pushController.sendHoroscopePushAlarm(LocalTime.now())

}

@Component
@Profile("question")
class QuestionSchedulerTwo(val pushController: PushController) {

    @Scheduled(cron = "* */1 * * * *")
    fun startQuestionJob() = pushController.sendDailyQuestionPushAlarm(LocalTime.now())

}