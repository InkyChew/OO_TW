package com.models;

public class VVIPLevel implements Discounter {
	public double getDiscount(int amount){
		if(amount > 100) {
			return 0.0;
		} else {
			return 0.5;
		}
	}
}
