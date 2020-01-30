package com.kancho.dailyquestion.controller

import com.kancho.dailyquestion.application.DailyQuestionService
import org.springframework.stereotype.Component

@Component
class DailyQuestionController(private val dailyQuestionService: DailyQuestionService) {
    fun designateDailyQuestion() = dailyQuestionService.designateDailyQuestion()
}