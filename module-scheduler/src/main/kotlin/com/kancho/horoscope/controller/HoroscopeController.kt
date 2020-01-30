package com.kancho.horoscope.controller

import com.kancho.horoscope.application.HoroscopeService
import org.springframework.stereotype.Component

@Component
class HoroscopeController(private val horoscopeService: HoroscopeService) {

    fun horoscopeCrawling() = horoscopeService.designateHoroscope()

}