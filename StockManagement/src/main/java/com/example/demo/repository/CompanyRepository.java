package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Company;

@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company,Integer>{

	//@Modifying
	//@Query(value="delete stockList from Company c where c.companyCode= :companyCode")
	//public void deleteStockList(int companyCode) ;
}
