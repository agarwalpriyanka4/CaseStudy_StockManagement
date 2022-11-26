package com.example.demo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService{

	@Autowired
	private StockRepository stockRepo;
	@Override
	public Set<Stock> getStockList(int companyCode) {
		Set<Stock> stockList=stockRepo.getStockList(companyCode);
		return stockList;
	}

	@Override
	public boolean addStock(Stock stock) {
		if(stockRepo.saveAndFlush(stock)!=null)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteStock(int companyCode) {
		stockRepo.deleteStock(companyCode);
		return true;
	}

}
