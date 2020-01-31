package com.kancho

import com.kancho.push.controller.PushController
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalTime

@Component
class Scheduler(val pushController: PushController) {

    @Scheduled(cron = "0 */1 * * * *")
    fun startHoroscopeJob() {
        pushController.sendHoroscopePushAlarm(LocalTime.now())
    }

    @Scheduled(cron = "0 */1 * * * *")
    fun startQuestionJob() {
        pushController.sendDailyQuestionPushAlarm(LocalTime.now())
    }
}