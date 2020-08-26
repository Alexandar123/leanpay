package com.leanpay.Leanpay.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "monthly_account")
public class MonthlyAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int month;
	private double paymentAmount;

	public MonthlyAccount() {
	}

	public MonthlyAccount(int month, double paymentAmount) {
		super();
		this.month = month;
		this.paymentAmount = paymentAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "MonthlyAccount [id=" + id + ", month=" + month + ", paymentAmount=" + paymentAmount + "]";
	}

}
