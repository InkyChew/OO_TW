package com.models;

public class VVIPLevel implements Discounter {
	public double getDiscount(double discount){
		discount = 0.5;
		return  discount;
	}
}
