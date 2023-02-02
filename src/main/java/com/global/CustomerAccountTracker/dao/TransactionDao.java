package com.global.CustomerAccountTracker.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.global.CustomerAccountTracker.bean.TransactionBean;



@Repository
public interface TransactionDao extends CrudRepository<TransactionBean, Integer> {
	
	

}