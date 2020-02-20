package com.kancho.push.application

import com.kancho.user.User
import com.kancho.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalTime

@Service
class PushService(private val notificationService: NotificationService,
                  private val userRepository: UserRepository) {
    fun sendHoroscopePushAlarm(nowTime: LocalTime) {

        val users: MutableList<User> = userRepository.findByHoroscopeAlarmFlagAndHoroscopeTime(true, nowTime)
        val userSize: Int = users.size - 1

        var tokens: MutableList<String> = mutableListOf()

        for ((index, user) in users.withIndex()) {
            if (tokens.size == 500 || userSize == index) {
                notificationService.send(tokens, "별별일기", "오늘의 운세를 확인해보세요.")
                tokens = mutableListOf()
            } else {
                tokens.add(user.fcmToken)
            }
        }
    }

    fun sendDailyQuestionPushAlarm(nowTime: LocalTime) {
        val users: MutableList<User> = userRepository.findByQuestionAlarmFlagAndQuestionTime(true, nowTime)
        val userSize: Int = users.size - 1

        var tokens: MutableList<String> = mutableListOf()

        notificationService.send(tokens, "별별일기", "오늘 하루는 어떠셨나요?")
        for ((index, user) in users.withIndex()) {
            if (tokens.size == 500 || userSize == index) {
                tokens = mutableListOf()
            } else {
                tokens.add(user.fcmToken)
            }
        }

    }
}