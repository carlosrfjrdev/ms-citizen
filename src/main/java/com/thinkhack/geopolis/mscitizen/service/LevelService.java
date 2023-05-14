package com.thinkhack.geopolis.mscitizen.service;

import org.springframework.stereotype.Service;

@Service
public class LevelService {
	
	private static final double BASE_XP = 500;
	private static final double EXPONENT = 3.022f;
	
	public int getLevelByXp(Long experience) {
		
		 int level = 0;
		    double maxXp = calcXpForLevel(0);
		    do {
		        maxXp += calcXpForLevel(++level);
		    } while (maxXp < experience);
		    return level;
	} 
	public int getMaxXPForNextLevel(int level) {
		
		return calculateFullTargetXp(level);
		
	}
	
	private double calcXpForLevel(int level) {
	    return BASE_XP + (BASE_XP * Math.pow(level, EXPONENT));
	}
	
	
	private int calculateFullTargetXp(int level) {
	    int requiredXP = 0;
	    for (int i = 0; i <= level; i++) {
	        requiredXP += calcXpForLevel(i);
	    }
	    return requiredXP;
	}

}
