package com.kancho.push.application

import com.kancho.user.User
import com.kancho.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalTime

@Service
class PushService(private val notificationService: NotificationService,
                  private val userRepository: UserRepository) {

    companion object {
        private const val TITLE = "별별일기"
        private const val HOROSCOPE_TEXT = "오늘의 운세를 확인해보세요."
        private const val QUESTION_TEXT = "오늘 하루는 어떠셨나요?"
        private const val HOROSCOPE_TYPE = "HOROSCOPE"
        private const val QUESTION_TYPE = "QUESTION"
        private const val NOTIFICATION_ON = true
        private const val MAX_SIZE = 500
    }

    fun sendHoroscopePushAlarm(nowTime: LocalTime) {

        val users: MutableList<User> = userRepository.findByHoroscopeAlarmFlagAndHoroscopeTime(NOTIFICATION_ON, nowTime)
        val userSize: Int = users.size - 1

        var tokens: MutableList<String> = mutableListOf()

        for ((index, user) in users.withIndex()) {
            tokens.add(user.fcmToken)
            if (tokens.size == MAX_SIZE || userSize == index) {
                notificationService.send(tokens, TITLE, HOROSCOPE_TEXT, HOROSCOPE_TYPE)
                tokens = mutableListOf()
            }
        }
    }

    fun sendDailyQuestionPushAlarm(nowTime: LocalTime) {
        val users: MutableList<User> = userRepository.findByQuestionAlarmFlagAndQuestionTime(NOTIFICATION_ON, nowTime)
        val userSize: Int = users.size - 1

        var tokens: MutableList<String> = mutableListOf()

        for ((index, user) in users.withIndex()) {
            tokens.add(user.fcmToken)
            if (tokens.size == MAX_SIZE || userSize == index) {
                notificationService.send(tokens, TITLE, QUESTION_TEXT, QUESTION_TYPE)
                tokens = mutableListOf()
            }
        }

    }
}