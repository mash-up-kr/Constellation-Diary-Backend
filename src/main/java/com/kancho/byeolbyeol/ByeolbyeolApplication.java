package com.kancho.byeolbyeol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ByeolbyeolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ByeolbyeolApplication.class, args);
    }

}
