package com.leanpay.Leanpay.dto;

import java.io.Serializable;

public class MonthlyAccountDetailedDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int month;
	private double paymentAmount;
	private double principalAmount;
	private double interestAmount;
	private double balanceOwed;

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

	public double getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(double principalAmount) {
		this.principalAmount = principalAmount;
	}

	public double getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(double interestAmount) {
		this.interestAmount = interestAmount;
	}

	public double getBalanceOwed() {
		return balanceOwed;
	}

	public void setBalanceOwed(double balanceOwed) {
		this.balanceOwed = balanceOwed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
