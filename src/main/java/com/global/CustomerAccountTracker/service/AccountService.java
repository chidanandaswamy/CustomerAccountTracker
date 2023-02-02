package com.global.CustomerAccountTracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.CustomerAccountTracker.Exception.RecordNotFoundException;
import com.global.CustomerAccountTracker.bean.AccountBean;
import com.global.CustomerAccountTracker.dao.AccountDao;
@Service
public class AccountService {
	
	@Autowired
	AccountDao ad;
	
	@Autowired
	CustomerService cs;
	
	public AccountBean GetAccDetailsByAccNum(Long accountNumber) throws RecordNotFoundException
	{
		Optional<AccountBean> account=ad.findByAccountNumber(accountNumber);
		if(account.isPresent()) 
		{
			return account.get();
		}
		else
		{
			throw new RecordNotFoundException("No Customer record exist for given Account Number :"+accountNumber);
		}
	}

}
	
	

