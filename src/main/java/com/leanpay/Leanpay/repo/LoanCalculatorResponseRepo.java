package com.leanpay.Leanpay.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leanpay.Leanpay.entity.LoanCalculatorResponse;

@Repository
public interface LoanCalculatorResponseRepo extends CrudRepository<LoanCalculatorResponse, Long>{

}
