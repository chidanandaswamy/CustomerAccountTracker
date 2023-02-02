package com.global.CustomerAccountTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.global.CustomerAccountTracker.Exception.RecordNotFoundException;
import com.global.CustomerAccountTracker.bean.AccountBean;
import com.global.CustomerAccountTracker.service.AccountService;

@RestController
@RequestMapping("/accountDetails")
public class AccountController {
	
	@Autowired
	AccountService as;
	
	@RequestMapping(value="/{accountNumber}", method=RequestMethod.GET) 
	public AccountBean GetAccDetailsByAccNum(@PathVariable("accountNumber") Long accountNumber) throws RecordNotFoundException {
		return as.GetAccDetailsByAccNum(accountNumber);
	}

}
