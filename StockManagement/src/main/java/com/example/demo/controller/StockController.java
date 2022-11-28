package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Company;
import com.example.demo.model.Stock;
import com.example.demo.service.CompanyService;
import com.example.demo.service.StockService;

@RestController
@RequestMapping("api/v1.0/market")
public class StockController {
	
	@Autowired 
	private StockService stockService;
	
	@Autowired 
	private CompanyService companyService;
	
	@PostMapping("/stock/add/{companyCode}")
	public ResponseEntity<?> addStock(@PathVariable int companyCode,@RequestBody Stock stock)
	{
				
		Company existObj=companyService.getCompanyById(companyCode);
		if(companyCode!=stock.getCompanyCode_fk())
		{
			return new ResponseEntity<String>("Invalid company codes combination", HttpStatus.BAD_REQUEST);
		}
		if(existObj!=null)
		{
			existObj.setLatestStockPrice(stock.getStockPrice());
			//stock.setCompanyCode_fk(companyCode);
			if(companyService.updateCompany(existObj)& stockService.addStock(stock))
			{
				//Set<Stock> stockSet=(stockService.getStockList(companyCode));
				//stockSet.add(stock);
				//existObj.setStockList(stockSet);
				//companyService.updateCompany(existObj);
				return new ResponseEntity<String>("Stock Details added and company also updated.", HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<String>("Stock Details not added", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
