package com.kancho.config.fcm

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FcmConfiguration {
    @Bean
    fun kanchoFirebaseApp(): FirebaseApp {

        val options = FirebaseOptions
                .Builder()
                .setCredentials(GoogleCredentials.fromStream(ClassPathResource("kancho-byeolbyeol.json").inputStream))
                .build()

        return FirebaseApp.initializeApp(options, "Kancho")
    }
}