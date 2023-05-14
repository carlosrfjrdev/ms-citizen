package com.thinkhack.geopolis.mscitizen;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.thinkhack.geopolis.mscitizen.service.LevelService;

@SpringBootTest
class LevelTest {

	
	@Test
	void levelTest() {
		LevelService service = new LevelService();
		
		System.out.println(service.getLevelByXp(523100L));
		
		
		
	}

}
