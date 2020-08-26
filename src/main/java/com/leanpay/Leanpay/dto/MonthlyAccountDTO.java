package com.leanpay.Leanpay.dto;

import java.io.Serializable;

public class MonthlyAccountDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int month;
	private double paymentAmount;

	/**
	 * 
	 * @param month - number of months
	 * @param paymentAmount - monthly loan amount
	 */
	public MonthlyAccountDTO(int month, double paymentAmount) {
		super();
		this.month = month;
		this.paymentAmount = paymentAmount;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

}
