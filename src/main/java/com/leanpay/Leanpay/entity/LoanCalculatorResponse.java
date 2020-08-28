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

import lombok.experimental.Accessors;

@Entity
@Table(name = "installment_response")
@Accessors(chain = true)
public class LoanCalculatorResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double amount;
	@Column(name = "total_amount")
	private double totalAmount;
	@Column(name = "interest_amount")
	private double interestAmount;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "monthly_detailed_acc_fk", referencedColumnName = "id")
	private List<MonthlyAccountDetailed> items;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "lc_response")
	private LoanCalculatorRequest loanCalculatorRequest;

	public LoanCalculatorResponse() {
	}

	public LoanCalculatorResponse(double amount, double totalAmount, double interestAmount,
			List<MonthlyAccountDetailed> detailedItems) {
		super();
		this.amount = amount;
		this.totalAmount = totalAmount;
		this.interestAmount = interestAmount;
		this.items = detailedItems;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LoanCalculatorRequest getLoanCalculatorRequest() {
		return loanCalculatorRequest;
	}

	public void setLoanCalculatorRequest(LoanCalculatorRequest loanCalculatorRequest) {
		this.loanCalculatorRequest = loanCalculatorRequest;
	}

	public List<MonthlyAccountDetailed> getDetailedItems() {
		return items;
	}

	public void setDetailedItems(List<MonthlyAccountDetailed> detailedItems) {
		this.items = detailedItems;
	}

	@Override
	public String toString() {
		return "LoanCalculatorResponse [id=" + id + ", amount=" + amount + ", totalAmount=" + totalAmount
				+ ", interestAmount=" + interestAmount + ", items=" + items + ", detailedItems=" + items
				+ ", loanCalculatorRequest=" + loanCalculatorRequest + "]";
	}

}
