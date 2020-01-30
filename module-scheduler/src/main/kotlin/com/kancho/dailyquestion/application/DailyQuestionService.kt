package com.kancho.dailyquestion.application

import com.kancho.daily_question.DailyQuestion
import com.kancho.daily_question.DailyQuestionRepository
import com.kancho.dailyquestion.domain.question.Question
import com.kancho.dailyquestion.domain.question.QuestionRepository
import com.kancho.util.calculateDate
import com.kancho.util.randIndex
import org.springframework.stereotype.Service

@Service
class DailyQuestionService(private val questionRepository: QuestionRepository,
                           private val dailyQuestionRepository: DailyQuestionRepository) {


    fun designateDailyQuestion() {
        val questions: MutableList<Question> = questionRepository.findAll()

        val question: Question = randQuestion(questions)

        val dailyQuestion = DailyQuestion.builder()
                .content(question.content)
                .date(calculateDate())
                .build()

        dailyQuestionRepository.save(dailyQuestion)
    }

    private fun randQuestion(questions: MutableList<Question>): Question =
            questions[randIndex(questions.size)]

}