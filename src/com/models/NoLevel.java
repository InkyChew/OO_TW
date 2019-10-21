package com.models;

public class NoLevel implements Discounter {
	public double getDiscount(double discount){
		discount = 0.0;
		return  discount;
	}
}
