package com.leanpay.Leanpay.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "installment_request")
public class LoanCalculatorRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "amount")
	private float amount;
	@Column(name = "months")
	private int numberOfMonths;
	@Column(name = "percent")
	private float monthlyInterestPercent;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "lc_response_fk")
	private LoanCalculatorResponse lc_response;

	public LoanCalculatorRequest() {
	}

	public LoanCalculatorRequest(Long id, float amount, int numberOfMonths, float monthlyInterestPercent,
			LoanCalculatorResponse loanCalculatorResponse) {
		super();
		this.id = id;
		this.amount = amount;
		this.numberOfMonths = numberOfMonths;
		this.monthlyInterestPercent = monthlyInterestPercent;
		this.lc_response = loanCalculatorResponse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LoanCalculatorResponse getLc_response() {
		return lc_response;
	}

	public void setLc_response(LoanCalculatorResponse lc_response) {
		this.lc_response = lc_response;
	}

	@Override
	public String toString() {
		return "LoanCalculatorRequest [id=" + id + ", amount=" + amount + ", numberOfMonths=" + numberOfMonths
				+ ", monthlyInterestPercent=" + monthlyInterestPercent + ", loanCalculatorResponse=" + lc_response
				+ "]";
	}

}