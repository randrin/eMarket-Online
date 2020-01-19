package com.eMarket.online.utils;

public class EmarketUtils {

	public static int percentReductuon(double unitPriceNew, double unitPriceSold) {
		
		return (int) Math.ceil(100 - ((unitPriceSold / unitPriceNew) * 100));
	}
}
