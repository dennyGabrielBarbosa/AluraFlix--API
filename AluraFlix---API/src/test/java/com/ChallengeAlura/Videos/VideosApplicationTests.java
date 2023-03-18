package com.ChallengeAlura.Videos;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.videos.VideosApplication;

@SpringBootTest(classes = VideosApplication.class)
class VideosApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
