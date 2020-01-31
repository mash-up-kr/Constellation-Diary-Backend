package com.kancho.horoscope_push.application

interface NotificationService {
    fun send(tokens: List<String>, title: String, message: String)
}