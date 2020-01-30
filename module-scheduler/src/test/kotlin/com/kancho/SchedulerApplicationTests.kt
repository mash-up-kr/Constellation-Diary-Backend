package com.kancho

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [SchedulerApplication::class])
@ActiveProfiles("test")
class SchedulerApplicationTests {

	@Test
	fun contextLoads() {
	}

}
