package com.kancho.push.application

import com.kancho.constellation.Constellation
import com.kancho.horoscope.Horoscope
import com.kancho.horoscope.application.HoroscopeDesignation
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

class HoroscopeDesignationTest {

    private lateinit var horoscopeDesignation: HoroscopeDesignation


    @Before
    fun set_up() {
        horoscopeDesignation = HoroscopeDesignation()
    }

    @Test
    fun 크롤링_데이터를_이용하여_운세_생성() {
        val constellation: Constellation = Constellation.AQUARIUS
        val horoscope: Horoscope =
                horoscopeDesignation.designate(constellation, "www.naver.com")

        Assertions.assertThat(horoscope.constellation).isEqualTo(constellation)
    }

}