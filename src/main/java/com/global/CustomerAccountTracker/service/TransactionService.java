package com.global.CustomerAccountTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.CustomerAccountTracker.bean.AccountBean;
import com.global.CustomerAccountTracker.bean.TransactionBean;
import com.global.CustomerAccountTracker.dao.AccountDao;
import com.global.CustomerAccountTracker.dao.TransactionDao;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class TransactionService {
	
	@Autowired
	TransactionDao td;
	
	@Autowired
	AccountDao ad;
	 
	public String AmountTransfer(Long accountNumber,TransactionBean tb,AccountBean ab)
	{
		java.util.Optional<AccountBean> transaction = ad.findByAccountNumber(accountNumber);
		String result="";
		if(transaction.isPresent())
		{
			tb.setAmount(tb.getAmount());
			tb.setBeneficiaryAccountNumber(tb.getBeneficiaryAccountNumber());
			tb.setBeneficiaryName(tb.getBeneficiaryName());
			AccountBean acc=transaction.get();
			if(acc.getBalanceAmount()>tb.getAmount())
			{
				tb.setBalanceAmount(acc.getBalanceAmount()-tb.getAmount());
				td.save(tb);
				acc.setBalanceAmount(acc.getBalanceAmount()-tb.getAmount());
				ad.save(acc);
				result=result+ "Transaction Successfull";
			}
			else
			{
				result=result+ "Max amount available for transaction is "+acc.getBalanceAmount();
			}
		return result;
		}
		else
		{
			return "Account Number Not Found";
		}
	}
	
}
