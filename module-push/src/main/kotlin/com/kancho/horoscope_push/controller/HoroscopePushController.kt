package com.kancho.horoscope_push.controller

import com.kancho.horoscope_push.application.HoroscopePushService
import org.springframework.stereotype.Component
import java.time.LocalTime

@Component
class HoroscopePushController(private val horoscopePushService: HoroscopePushService) {

    fun sendHoroscopePushAlarm(nowTime: LocalTime) = horoscopePushService.sendHoroscopePushAlarm(nowTime)

}