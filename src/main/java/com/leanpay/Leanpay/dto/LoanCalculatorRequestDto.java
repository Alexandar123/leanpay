package com.leanpay.Leanpay.dto;

import java.io.Serializable;

public class LoanCalculatorRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private float amount;
	private int numberOfMonths;
	private float monthlyInterestPercent;
	private DetailedResponseDto lcDetailedResponseDto;

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getNumberOfMonths() {
		return numberOfMonths;
	}

	public void setNumberOfMonths(int numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}

	public float getMonthlyInterestPercent() {
		return monthlyInterestPercent;
	}

	public void setMonthlyInterestPercent(float monthlyInterestPercent) {
		this.monthlyInterestPercent = monthlyInterestPercent;
	}

	public DetailedResponseDto getLcDetailedResponseDto() {
		return lcDetailedResponseDto;
	}

	public void setLcDetailedResponseDto(DetailedResponseDto lcDetailedResponseDto) {
		this.lcDetailedResponseDto = lcDetailedResponseDto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
