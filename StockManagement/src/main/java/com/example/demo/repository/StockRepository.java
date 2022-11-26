package com.example.demo.repository;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Stock;

@Repository
@Transactional
public interface StockRepository  extends JpaRepository<Stock,Integer>{
	
	@Query(value="select stk from Stock stk where stk.companyCode_fk= :companyCode")
	public Set<Stock> getStockList(int companyCode);
	
	@Modifying
	@Query(value="delete from Stock where companyCode_fk= :companyCode")
	public void deleteStock(int companyCode);

}
