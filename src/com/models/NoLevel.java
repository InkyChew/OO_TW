package com.models;

public class NoLevel implements Discounter {
	public double getDiscount(int amount){
		if(amount > 10000) {
			return 0.0;
		} else {
			return 1.0;
		}
	}
}
