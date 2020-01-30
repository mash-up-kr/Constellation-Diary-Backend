package com.kancho.horoscope.application

interface HoroscopeCrawling {

    fun crawling(url: String): String

}