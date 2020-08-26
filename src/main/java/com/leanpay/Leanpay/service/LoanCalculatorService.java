package com.leanpay.Leanpay.service;

import com.leanpay.Leanpay.dto.LoanCalculatorRequestDto;
import com.leanpay.Leanpay.entity.LoanCalculatorRequest;

public interface LoanCalculatorService {
	
	LoanCalculatorRequest save(LoanCalculatorRequestDto lcRequestDto);

}
