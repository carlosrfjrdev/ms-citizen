package com.thinkhack.geopolis.mscitizen.main;

public class LevelTestMain {

	private static final double BASE_XP = 500;
	private static final double EXPONENT = 3.022f;

	public void test() {
	    for (int i = 0; i < 100; i++) {
	        double levelXp = calcXpForLevel(i);
	        double max = calculateFullTargetXp(i);
	        System.out.println(String.format("Level: %3d, xp for the next level: %10.2f, next level xp: %10.2f", i, levelXp, max));
	    }
	    System.out.println("====================");
	    int xp = 1982;
	    System.out.println("For " + xp + " xp  calculated level is " + calculateLevel(xp));
	}

	private static double calcXpForLevel(int level) {
	    return BASE_XP + (BASE_XP * Math.pow(level, EXPONENT));
	}

	private static double calculateFullTargetXp(int level) {
	    double requiredXP = 0;
	    for (int i = 0; i <= level; i++) {
	        requiredXP += calcXpForLevel(i);
	    }
	    return requiredXP;
	}

	private static int calculateLevel(double xp) {
	    int level = 0;
	    double maxXp = calcXpForLevel(0);
	    do {
	        maxXp += calcXpForLevel(++level);
	    } while (maxXp < xp);
	    return level;
	}

}
