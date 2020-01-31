package com.kancho.push.controller

import com.kancho.push.application.PushService
import org.springframework.stereotype.Component
import java.time.LocalTime

@Component
class PushController(private val pushService: PushService) {

    fun sendHoroscopePushAlarm(nowTime: LocalTime) = pushService.sendHoroscopePushAlarm(nowTime)

    fun sendDailyQuestionPushAlarm(nowTime: LocalTime) = pushService.sendDailyQuestionPushAlarm(nowTime)
}