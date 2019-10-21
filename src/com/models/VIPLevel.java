package com.models;

public class VIPLevel implements Discounter {
	public double getDiscount(double discount){
		discount = 0.8;
		return  discount;
	}
}
