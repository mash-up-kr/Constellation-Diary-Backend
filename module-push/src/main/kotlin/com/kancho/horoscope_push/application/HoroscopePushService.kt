package com.kancho.horoscope_push.application

import com.kancho.user.User
import com.kancho.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalTime

@Service
class HoroscopePushService(private val notificationService: NotificationService,
                           private val userRepository: UserRepository) {
    fun sendHoroscopePushAlarm(nowTime: LocalTime) {

        val users: MutableList<User> = userRepository.findByHoroscopeAlarmFlagAndHoroscopeTime(true, nowTime)

        var tokens: MutableList<String> = mutableListOf()
        for (user in users) {
            if (tokens.size == 500) {
                notificationService.send(tokens, "별별일기", "오늘의 운세를 확인해보세요.")
                tokens = mutableListOf()
            } else {
                tokens.add(user.fcmToken)
            }

        }
    }
}