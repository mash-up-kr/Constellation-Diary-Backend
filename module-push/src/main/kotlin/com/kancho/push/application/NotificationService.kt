package com.kancho.push.application

import org.springframework.scheduling.annotation.Async

interface NotificationService {
    @Async("pushSenderThreadPool")
    fun send(tokens: List<String>, title: String, body: String)
}

