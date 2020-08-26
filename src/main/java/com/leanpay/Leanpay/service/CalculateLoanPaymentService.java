package com.leanpay.Leanpay.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.leanpay.Leanpay.dto.DetailedResponseDto;
import com.leanpay.Leanpay.dto.MonthlyAccountDetailedDTO;

@Service
public class CalculateLoanPaymentService {

	/**
	 * 
	 * @param principal          - loan withdrawal amount
	 * @param numOfMonths        - number of months to raise a loan
	 * @param annualInterestRate - monthly interest
	 * @return response - total amount, total interest and monthly installment
	 *         amount
	 */
	public DetailedResponseDto calculateMonthlyInstallmentDetailed(double principal, double annualInterestRate,
			int numMonths) {

		double interestPaid, principalPaid, newBalance;
		double monthlyInterestRate, monthlyPayment;
		int month;

		// Output monthly payment and total payment
		// principalAMount + interestAmount
		monthlyInterestRate = annualInterestRate / 12;
		monthlyPayment = monthlyPayment(principal, monthlyInterestRate, numMonths);

		DetailedResponseDto response = new DetailedResponseDto();
		response.setAmount(principal);
		response.setTotalAmount(round(monthlyPayment * numMonths, 2));
		response.setInterestAmount(round(response.getTotalAmount() - principal, 2));

		List<MonthlyAccountDetailedDTO> monthlyDetailedInstallments = new ArrayList<>();
		
		for (month = 1; month <= numMonths; month++) {
			// Compute amount paid and new balance for each payment period
			interestPaid = principal * (monthlyInterestRate / 100);
			principalPaid = monthlyPayment - interestPaid;
			newBalance = principal - principalPaid;

			MonthlyAccountDetailedDTO tmp = new MonthlyAccountDetailedDTO();
			tmp.setMonth(month);
			tmp.setPaymentAmount(round(principalPaid + interestPaid, 2));
			tmp.setPrincipalAmount(round(principalPaid, 2));
			tmp.setInterestAmount(round(interestPaid, 2));
			tmp.setBalanceOwed(round(newBalance, 2));

			// Update the balance
			principal = newBalance;
			monthlyDetailedInstallments.add(tmp);

		}

		response.setItems(monthlyDetailedInstallments);

		return response;
	}

	/**
	 * @param loanAmount
	 * @param monthlyInterestRate in percent
	 * @param numberOfYears
	 * @return the amount of the monthly payment of the loan
	 */
	static double monthlyPayment(double loanAmount, double monthlyInterestRate, int numberOfMonths) {
		monthlyInterestRate /= 100;
		return loanAmount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfMonths));
	}

	/**
	 * helper method for rounding a decimal number to n digits
	 * 
	 * @param value  - decimal value
	 * @param places - number of decimal places
	 * @return a number rounded to n decimal places
	 */
	private static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
