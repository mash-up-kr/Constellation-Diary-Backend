package com.kancho.horoscope.application


import com.kancho.constellation.Constellation
import com.kancho.horoscope.Horoscope
import com.kancho.horoscope.constant.Exercise
import com.kancho.horoscope.constant.Numeral
import com.kancho.horoscope.constant.Stylist
import com.kancho.horoscope.constant.Word
import com.kancho.util.calculateDate
import com.kancho.util.randIndex
import org.springframework.stereotype.Component

@Component
class HoroscopeDesignation {
    fun designate(constellation: Constellation, content: String): Horoscope =
            Horoscope.builder()
                    .constellation(constellation)
                    .date(calculateDate())
                    .content(content)
                    .stylist(randStylist())
                    .numeral(randNumber())
                    .word(randWord())
                    .exercise(randExercise())
                    .build()

    private fun randWord(): Word =
            Word.values()[randIndex(Word.values().size)]

    private fun randExercise(): Exercise =
            Exercise.values()[randIndex(Exercise.values().size)]

    private fun randStylist(): Stylist =
            Stylist.values()[randIndex(Stylist.values().size)]

    private fun randNumber(): Numeral =
            Numeral.values()[randIndex(Numeral.values().size)]

}