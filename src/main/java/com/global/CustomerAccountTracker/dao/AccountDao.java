package com.global.CustomerAccountTracker.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.global.CustomerAccountTracker.bean.AccountBean;

@Repository
public interface AccountDao extends CrudRepository<AccountBean, Integer> {
	
	Optional<AccountBean> findByAccountNumber(long accountNumber);
}