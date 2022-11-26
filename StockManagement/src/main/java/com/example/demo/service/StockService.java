package com.example.demo.service;

import java.util.Set;

import com.example.demo.model.Stock;

public interface StockService {
	
	public Set<Stock> getStockList(int companyCode);
	
	public boolean addStock(Stock stock);
	
	public boolean deleteStock(int companyCode);

}
