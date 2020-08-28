package com.leanpay.Leanpay.controller;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leanpay.Leanpay.dto.DetailedResponseDto;
import com.leanpay.Leanpay.dto.LoanCalculatorRequestDto;
import com.leanpay.Leanpay.dto.MonthlyAccountDTO;
import com.leanpay.Leanpay.dto.ResponseDTO;
import com.leanpay.Leanpay.service.CalculateLoanPaymentService;
import com.leanpay.Leanpay.service.LoanCalculatorService;

@RestController
public class LoanCalculatorController {

	static Logger log = LoggerFactory.getLogger(LoanCalculatorController.class);

	private final CalculateLoanPaymentService service;
	private LoanCalculatorService loanCalculatorService;

	@Autowired
	public LoanCalculatorController(CalculateLoanPaymentService service, LoanCalculatorService loanCalculatorService) {
		this.service = service;
		this.loanCalculatorService = loanCalculatorService;
	}

	@PostMapping("/installment-plan")
	public ResponseDTO calculateInstallmentPlan(@RequestBody LoanCalculatorRequestDto request) {

		double amount = request.getAmount();
		int numOfMonths = request.getNumberOfMonths();
		double monthlyInterestPercent = request.getMonthlyInterestPercent();

		DetailedResponseDto response = service.calculateMonthlyInstallmentDetailed(amount, monthlyInterestPercent, numOfMonths);
		
		request.setLcDetailedResponseDto(response);
		
		loanCalculatorService.save(request);
		
		ResponseDTO responseDto = new ResponseDTO();
		responseDto.setAmount(response.getAmount());
		responseDto.setInterestAmount(response.getInterestAmount());
		responseDto.setTotalAmount(response.getTotalAmount());
		responseDto.setItems(response.getItems()
				.stream()
				.map(responseDetailed -> 
						new MonthlyAccountDTO(
								responseDetailed.getMonth(),
								responseDetailed.getPaymentAmount()))
				.collect(Collectors.toList()));

		log.debug("Request body: {}", request);
		return responseDto;

	}

	@PostMapping("/installment-plan-detailed")
	public DetailedResponseDto calculateInstallmentPlanDetailed(@RequestBody LoanCalculatorRequestDto request) {

		float principal = request.getAmount();
		int numOfMonths = request.getNumberOfMonths();
		float annualInterestRate = request.getMonthlyInterestPercent();

		DetailedResponseDto dtoResponse = service.calculateMonthlyInstallmentDetailed(principal, annualInterestRate,
				numOfMonths);

		request.setLcDetailedResponseDto(dtoResponse);
		loanCalculatorService.save(request);

		log.debug("Request body: {}", request);
		return dtoResponse;

	}
}
