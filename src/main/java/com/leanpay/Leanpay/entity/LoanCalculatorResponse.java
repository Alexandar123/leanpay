package com.leanpay.Leanpay.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "installment_response")
public class LoanCalculatorResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	private float amount;
	@Column(name = "total_amount")
	private double totalAmount;
	@Column(name = "interest_amount")
	private double interestAmount;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "monthly_acc_fk", referencedColumnName = "id")
	private List<MonthlyAccount> items;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "lc_response")
	private LoanCalculatorRequest loanCalculatorRequest;

	public LoanCalculatorResponse() {
	}

	public LoanCalculatorResponse(Long id, float amount, double totalAmount, double interestAmount,
			List<MonthlyAccount> items) {
		super();
		this.id = id;
		this.amount = amount;
		this.totalAmount = totalAmount;
		this.interestAmount = interestAmount;
		this.items = items;
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

	public List<MonthlyAccount> getItems() {
		return items;
	}

	public void setItems(List<MonthlyAccount> items) {
		this.items = items;
	}

	public LoanCalculatorRequest getLoanCalculatorRequest() {
		return loanCalculatorRequest;
	}

	public void setLoanCalculatorRequest(LoanCalculatorRequest loanCalculatorRequest) {
		this.loanCalculatorRequest = loanCalculatorRequest;
	}

	@Override
	public String toString() {
		return "LoanCalculatorResponse [id=" + id + ", amount=" + amount + ", totalAmount=" + totalAmount
				+ ", interestAmount=" + interestAmount + ", items=" + 5 + "]";
	}

}
