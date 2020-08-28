package com.leanpay.Leanpay.dto;

import java.io.Serializable;
import java.util.List;

import lombok.experimental.Accessors;

@Accessors(chain = true)
public class DetailedResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private double amount;
	private double totalAmount;
	private double interestAmount;
	private List<MonthlyAccountDetailedDTO> items;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(double interestAmount) {
		this.interestAmount = interestAmount;
	}

	public List<MonthlyAccountDetailedDTO> getItems() {
		return items;
	}

	public void setItems(List<MonthlyAccountDetailedDTO> items) {
		this.items = items;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
