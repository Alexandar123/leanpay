package com.leanpay.Leanpay.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leanpay.Leanpay.entity.MonthlyAccount;

@Repository
public interface MonthlyAccountRepo extends CrudRepository<MonthlyAccount, Long> {

}
