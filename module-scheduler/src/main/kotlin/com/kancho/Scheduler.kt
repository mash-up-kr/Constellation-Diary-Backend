package com.kancho

import com.kancho.dailyquestion.controller.DailyQuestionController
import com.kancho.horoscope.controller.HoroscopeController
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Scheduler(private val horoscopeController: HoroscopeController,
                private val dailyQuestionController: DailyQuestionController) {

    @Scheduled(cron = "0 0 14 * * *")
    fun startJob() {
        horoscopeController.horoscopeCrawling()
        dailyQuestionController.designateDailyQuestion()
    }
}