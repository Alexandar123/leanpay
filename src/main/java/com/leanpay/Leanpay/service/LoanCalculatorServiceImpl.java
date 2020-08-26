package com.leanpay.Leanpay.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leanpay.Leanpay.dto.DetailedResponseDto;
import com.leanpay.Leanpay.dto.LoanCalculatorRequestDto;
import com.leanpay.Leanpay.dto.MonthlyAccountDetailedDTO;
import com.leanpay.Leanpay.entity.LoanCalculatorRequest;
import com.leanpay.Leanpay.entity.LoanCalculatorResponse;
import com.leanpay.Leanpay.entity.MonthlyAccountDetailed;
import com.leanpay.Leanpay.repo.LoanCalculatorRequestRepo;

@Service
public class LoanCalculatorServiceImpl implements LoanCalculatorService {

	private LoanCalculatorRequestRepo loanRequestRepo;

	@Autowired
	public LoanCalculatorServiceImpl(LoanCalculatorRequestRepo loanRequestRepo) {
		this.loanRequestRepo = loanRequestRepo;
	}

	@Override
	public LoanCalculatorRequest save(LoanCalculatorRequestDto lcRequestDto) {
		return this.loanRequestRepo.save(
				new LoanCalculatorRequest(
						lcRequestDto.getAmount(),
						lcRequestDto.getNumberOfMonths(),
						lcRequestDto.getMonthlyInterestPercent(),
						convertToEntity(
								lcRequestDto.getLcDetailedResponseDto()
								)));
	}

	/**
	 * A helper method that converts a DTO (Data Transfer Object) to an Entity
	 * 
	 * @param lcResponseDto - an object that is returned to the user
	 * @return entity of LoanCalculatorResponse object
	 */
	private LoanCalculatorResponse convertToEntity(DetailedResponseDto lcResponseDto) {
		return new LoanCalculatorResponse(
				lcResponseDto.getAmount(),
				lcResponseDto.getTotalAmount(),
				lcResponseDto.getInterestAmount(),
				convertToEntityAccount(lcResponseDto.getItems()));
	}

	/**
	 * A helper method that converts a DTO (Data Transfer Object) to an
	 * MonthlyAccount Entity
	 * 
	 * @param accountDTOs - a list of dto objects that are transformed into an
	 *                    entity
	 * @return entity of list MonthlyAccount objects
	 */
	private List<MonthlyAccountDetailed> convertToEntityAccount(List<MonthlyAccountDetailedDTO> accountDTOs) {
		List<MonthlyAccountDetailed> listOfMonthlyAcc = new ArrayList<>();
		for (int i = 0; i < accountDTOs.size(); i++) {
			listOfMonthlyAcc.add(
					new MonthlyAccountDetailed(
							accountDTOs.get(i).getPrincipalAmount(),
							accountDTOs.get(i).getInterestAmount(),
							accountDTOs.get(i).getBalanceOwed(),
							accountDTOs.get(i).getPaymentAmount(),
							accountDTOs.get(i).getMonth()));
		}
		return listOfMonthlyAcc;
	}
}
