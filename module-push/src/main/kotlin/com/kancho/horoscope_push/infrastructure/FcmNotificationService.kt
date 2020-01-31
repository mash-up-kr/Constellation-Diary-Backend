package com.kancho.horoscope_push.infrastructure

import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import com.kancho.horoscope_push.application.NotificationService
import org.springframework.stereotype.Component

import java.util.ArrayList

import com.google.firebase.messaging.SendResponse
import mu.KLogging


@Component

class FcmNotificationService(private val firebaseApp: FirebaseApp) : NotificationService {
    companion object : KLogging()

    override fun send(tokens: List<String>, title: String, message: String) {
        val message = MulticastMessage.builder()
                .putData("title", title)
                .putData("body", message)
                .addAllTokens(tokens)
                .build()

        val response = FirebaseMessaging.getInstance(firebaseApp).sendMulticast(message)
        if (response.failureCount > 0) {
            val responses: List<SendResponse> = response.responses
            val failedTokens: MutableList<String> = ArrayList<String>()
            for (i in responses.indices) {
                if (!responses[i].isSuccessful) {
                    failedTokens.add(tokens[i])
                }
            }
            logger.error { "Fail FCM Users : $failedTokens" }
        }
    }
}

