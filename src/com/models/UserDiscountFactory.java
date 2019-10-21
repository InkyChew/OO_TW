package com.models;

public class UserDiscountFactory implements DiscounterFactory {
	int level;
	UserDiscountFactory(int l){
		this.level = l;
	}
	public Discounter createLevelDiscounter(){
		Discounter userLevel;
		switch (level){
			case 1:
				userLevel = new VIPLevel();
				break;
			case 2:
				userLevel = new VVIPLevel();
				break;
			default:
				userLevel = new NoLevel();
		}
		return userLevel;
	}
}
