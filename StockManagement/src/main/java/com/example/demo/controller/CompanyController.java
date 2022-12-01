package com.example.demo.controller;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.CompanyAlreadyExistException;
import com.example.demo.model.Company;
import com.example.demo.model.Stock;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.CompanyService;
import com.example.demo.service.StockService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController

@RequestMapping("api/v1.0/market")
public class CompanyController {
	
    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private StockService stockService;
    
    @PostMapping("/company/register")
    public ResponseEntity<?> addCompanyDetails(@RequestBody Company company) throws CompanyAlreadyExistException
    {
    if(company==null)
    {
    	return new ResponseEntity<String>("No data found", HttpStatus.BAD_REQUEST);
    }
    if(company.getCompanyCEO()==null || company.getCompanyName()==null || company.getCompanyTurnover()==null || company.getCompanyTurnover() <100000000 || company.getCompanyWebsite()==null || company.getStockExchange()==null)
    {
    	
    	return new ResponseEntity<String>("Some data missing", HttpStatus.BAD_REQUEST);
    }
    	if(companyService.addCompanyDetails(company)!=null)
    	{
    		return ResponseHandler.generateResponse("Company added successfully", HttpStatus.CREATED, company);
    	}
		return new ResponseEntity<String>("Company Details already Exist", HttpStatus.CONFLICT);
    	
    }
	
    
    @GetMapping("/company/info/{companyCode}")
    public ResponseEntity<?> getCompanyById(@PathVariable int companyCode)
	{
		Company company=companyService.getCompanyById(companyCode);
		if(company!=null)
		{
			
			CacheControl cacheObj= CacheControl.maxAge(24, TimeUnit.HOURS);
			return ResponseEntity.ok().cacheControl(cacheObj)
	            	.body(ResponseHandler.generateResponse("Data retrieved for Company", HttpStatus.OK, company));
		}
		else
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
    
    @GetMapping("/company/info/getAll")
    public ResponseEntity<?> getCompanyDetails()
	{
    	List<Company> companyList=companyService.getCompanyDetails();
    	if(companyList!=null)
    	{
            for(Company c:companyList)
            {
            	Set<Stock> stockSet=stockService.getStockList(c.getCompanyCode());
            	c.setStockList(stockSet);
            }
			            
            CacheControl cacheObj= CacheControl.maxAge(24, TimeUnit.HOURS);
            return ResponseEntity.ok().cacheControl(cacheObj)
            	.body(ResponseHandler.generateResponse("Data of all Companies",HttpStatus.OK , companyList));
		}
		return new ResponseEntity<String>("Company Details does not Exist", HttpStatus.NO_CONTENT);
    	
	}
    
    @Hidden
    @DeleteMapping("/company/delete/{companyCode}")
    public ResponseEntity<?> deleteCompany(@PathVariable int companyCode)
	{
  
    	if(companyService.deleteCompany(companyCode) & stockService.deleteStock(companyCode))
    {
    	return new ResponseEntity<String>("Company Detail deleted", HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   
	}
    
    @PutMapping("/company/put/{companyCode}")
    public ResponseEntity<?> updateCompany(@PathVariable int companyCode, @RequestBody Company company)
    {
    	if(company.getCompanyCode() !=companyCode)
    	{
    		return new ResponseEntity<String>("Company Codes not matched", HttpStatus.BAD_REQUEST);
    	}
    	if(companyService.getCompanyById(companyCode)!=null)
    	{
    	boolean flag=companyService.updateCompany(company);
    	if(flag==true)
    	{
    		return new ResponseEntity<String>("Company Detail updated", HttpStatus.OK);
    	}
    	}
    	return new ResponseEntity<String>("Company Detail not present", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
