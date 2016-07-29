package com.teamset.colcap.model;

import java.math.BigDecimal;

public class CapitalReqCalculator {

	public static BigDecimal calculate(BigDecimal currentExpVal, BigDecimal currentCollVal, BigDecimal haircutPct, BigDecimal fxHaircutPct) {
		BigDecimal capReq = currentExpVal.multiply(BigDecimal.ONE.add(haircutPct)).subtract(currentCollVal.multiply(BigDecimal.ONE.subtract(haircutPct).subtract(fxHaircutPct)));

		return BigDecimal.ZERO.compareTo(capReq) >= 0 ? BigDecimal.ZERO : capReq;
	}

	public static void main(String[] args) {
		BigDecimal currentExpVal = new BigDecimal("100000");
		BigDecimal currentCollVal = new BigDecimal("80000");
		BigDecimal haircutPct = new BigDecimal("0.03");
		BigDecimal fxHaircutPct = new BigDecimal("0.034");

		System.out.println(calculate(currentExpVal, currentCollVal, haircutPct, fxHaircutPct));
	}

}