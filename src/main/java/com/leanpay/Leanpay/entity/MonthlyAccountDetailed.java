package com.leanpay.Leanpay.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detailed_payment_amount")
public class MonthlyAccountDetailed extends MonthlyAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double principalAmount;
	private double interestAmount;
	private double balanceOwed;

	public MonthlyAccountDetailed() {
	}

	public MonthlyAccountDetailed(double principalAmount, double interestAmount, double balanceOwed, double paymentAmount, int month) {
		super();
		super.setPaymentAmount(paymentAmount);
		super.setMonth(month);
		this.principalAmount = principalAmount;
		this.interestAmount = interestAmount;
		this.balanceOwed = balanceOwed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "MonthlyAccountDetailed [id=" + id + ", principalAmount=" + principalAmount + ", interestAmount="
				+ interestAmount + ", balanceOwed=" + balanceOwed + "]";
	}

}
