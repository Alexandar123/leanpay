package com.leanpay.Leanpay.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.leanpay.Leanpay.entity.LoanCalculatorResponse;
import com.leanpay.Leanpay.entity.MonthlyAccount;

@Service
public class CalculateLoanPaymentService {

	public LoanCalculatorResponse calculateMonthlyInstallment(float amount, int numOfMonths,
			float monthlyInterestPercent) {
		double i = monthlyInterestPercent / 100 / 12;
		double iRounded = round(i, 6);

		double above = amount * iRounded * Math.pow((1 + iRounded), numOfMonths);
		double belowe = Math.pow((1 + iRounded), numOfMonths) - 1;
		double installment = above / belowe;

		double totalAmount = (above / belowe) * numOfMonths;
		double interest = totalAmount - amount;

		List<MonthlyAccount> monthlyInstallments = new ArrayList<>();

		for (int count = 0; count < numOfMonths; count++) {
			monthlyInstallments.add(new MonthlyAccount((count + 1), round(installment, 2)));
		}

		LoanCalculatorResponse response = new LoanCalculatorResponse();
		response.setAmount(amount);
		response.setTotalAmount(round(totalAmount, 2));
		response.setInterestAmount(round(interest, 2));
		response.setItems(monthlyInstallments);
		return response;
	}

	private static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
