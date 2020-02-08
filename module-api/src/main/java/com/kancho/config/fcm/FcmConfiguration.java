package com.kancho.config.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FcmConfiguration {
    @Bean
    public FirebaseApp kanchoFirebaseApp() throws IOException {

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("kancho-byeolbyeol.json").getInputStream()))
                .build();
        return FirebaseApp.initializeApp(options, "Kancho");
    }
}
