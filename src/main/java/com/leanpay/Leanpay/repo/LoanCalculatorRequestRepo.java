package com.leanpay.Leanpay.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leanpay.Leanpay.entity.LoanCalculatorRequest;

@Repository
public interface LoanCalculatorRequestRepo extends CrudRepository<LoanCalculatorRequest, Long> {

}
