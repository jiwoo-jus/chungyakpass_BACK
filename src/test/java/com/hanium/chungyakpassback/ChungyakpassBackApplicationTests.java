package com.hanium.chungyakpassback;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootTest
class ChungyakpassBackApplicationTests {

	@Test
	void contextLoads() {
	}

}
