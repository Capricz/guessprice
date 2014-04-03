package com.hackathon.guessprice.common;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Utils {
	
	public static Double calculatePercent(BigInteger regionCount, int countUsers) {
		BigDecimal a = BigDecimal.valueOf(regionCount.doubleValue());
		BigDecimal b = BigDecimal.valueOf(countUsers);
		return a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/*public static Double calculatePercent(Integer regionCount, int countUsers) {
		BigDecimal a = BigDecimal.valueOf(regionCount);
		BigDecimal b = BigDecimal.valueOf(countUsers);
		return a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}*/
	
	/*public static void main(String[] args) {
		Integer a = Integer.valueOf(2);
		int b = 3;
		System.out.println(Utils.calculatePercent(a, b));
	}*/

}
