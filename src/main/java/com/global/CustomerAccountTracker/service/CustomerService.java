package com.global.CustomerAccountTracker.service;

import java.util.*;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.CustomerAccountTracker.Exception.RecordNotFoundException;
import com.global.CustomerAccountTracker.bean.AccountBean;
import com.global.CustomerAccountTracker.bean.CustomerBean;
import com.global.CustomerAccountTracker.dao.AccountDao;
import com.global.CustomerAccountTracker.dao.CustomerDao;

import antlr.collections.List;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao cd;
	
	@Autowired
	private AccountDao ad;
	
	
	public String CreateCustomer(CustomerBean cb, AccountBean ab) 
	{
		long accountNumber=Math.abs(generateRandom(12));
			cb.setAccountNumber(accountNumber);
			ab.setAccountNumber(accountNumber);
			ab.setBalanceAmount(10000);
			cd.save(cb);
			ad.save(ab);
			return "Customer Added Successfully";	
	}
	
	
	
	public String UpdateCustomer(CustomerBean cb) throws RecordNotFoundException
	{
		Optional<CustomerBean> customer=cd.findByaccountNumber(cb.getAccountNumber());
		if(customer.isPresent()) 
		{
			CustomerBean bean=customer.get();
			bean.setName(cb.getName());
			bean.setGender(cb.getGender());
			bean.setAadharNo(cb.getAadharNo());
			bean.setContact(cb.getContact());
			bean.setEmail(cb.getEmail());
			bean.setAccountType(cb.getAccountType());
			cd.save(bean);
			return "Updated Successfully";
		}
		else 
		{
			return "Account Number not found";	
		}	
	}
	
	
//	public List<CustomerBean> GetAllCustomers()
//	{
////		List<CustomerBean> list = (List<CustomerBean>) cd.findAll();
////		if(list.size()>0) 
////		{	
////			return list;
////		}
////		else 
////		{
////			return new ArrayList<CustomerBean>();
////		}		
//		List<CustomerBean> leads = cd.findAll();
//		return leads;
//	}
	
	
	public CustomerBean GetCustomerByAccNum(Long accountNumber) throws RecordNotFoundException 
	{
		Optional<CustomerBean> customer=cd.findByaccountNumber(accountNumber);
		if(customer.isPresent()) 
		{
			return customer.get();
		}
		else 
		{
			throw new RecordNotFoundException("No Customer record exist for given Account Number :"+accountNumber);	 
		}
	}
	
	@Transactional
	public String DeleteCustomerByAccNum(Long accountNumber) 
	{
		Optional<CustomerBean> customer=cd.findByaccountNumber(accountNumber);
		if(customer.isPresent()) 
		{
			cd.deleteByAccountNumber(accountNumber);
			
			return "Customer Deleted";
		}
		else 
		{
			return "No Customer record exist for given Account Number";
		}	
	}
	

	public long generateRandom(int length) 
	{
		Random random=new Random();
		char[] digits=new char[length];
		digits[0]=(char)(random.nextInt(9) +'1');
		for(int i=1;i<length;i++) 
		{
			digits[i]=(char)(random.nextInt(10) + '0');
		}
		return Long.parseLong(new String(digits));
	}
	
}