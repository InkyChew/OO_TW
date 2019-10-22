package com.models;

public class VIPLevel implements Discounter {
	public double getDiscount(int amount){
		if(amount > 1000) {
			return 0.0;
		} else {
			return 0.8;
		}
	}
}
