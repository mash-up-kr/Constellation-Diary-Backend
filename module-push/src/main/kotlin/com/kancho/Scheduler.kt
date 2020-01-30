package com.kancho

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Scheduler() {

    @Scheduled(cron = "0 0 14 * * *")
    fun startJob() {
    }
}