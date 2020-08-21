package com.leanpay.Leanpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leanpay.Leanpay.entity.LoanCalculatorRequest;
import com.leanpay.Leanpay.entity.LoanCalculatorResponse;
import com.leanpay.Leanpay.repo.LoanCalculatorRequestRepo;
import com.leanpay.Leanpay.service.CalculateLoanPaymentService;

@RestController
public class LoanCalculatorController {

	private final CalculateLoanPaymentService service;

	private LoanCalculatorRequestRepo loanCalculatorRequestRepo;

	@Autowired
	public LoanCalculatorController(CalculateLoanPaymentService service,
			LoanCalculatorRequestRepo loanCalculatorRequestRepo) {
		this.service = service;
		this.loanCalculatorRequestRepo = loanCalculatorRequestRepo;
	}

	@PostMapping("/installment-plan")
	public LoanCalculatorResponse calculateInstallmentPlan(@RequestBody LoanCalculatorRequest request) {

		float amount = request.getAmount();
		int numOfMonths = request.getNumberOfMonths();
		float monthlyInterestPercent = request.getMonthlyInterestPercent();

		LoanCalculatorResponse response = service.calculateMonthlyInstallment(amount, numOfMonths,
				monthlyInterestPercent);

		request.setLc_response(response);

		loanCalculatorRequestRepo.save(request);

		return response;

	}

}
