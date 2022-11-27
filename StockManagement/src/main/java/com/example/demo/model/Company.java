package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Company {

	@Id
	private Integer companyCode;
	
	
	private String companyName;
	private String companyCEO;
	private Integer companyTurnover;
	private String companyWebsite;
	private String stockExchange;
	
	private Float latestStockPrice;
	
	@OneToMany(targetEntity=Stock.class)
	private Set<Stock> stockList;
	
	//@Transient
	//private List<String> errorList=new ArrayList<String>();
	public Integer getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(Integer companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCEO() {
		return companyCEO;
	}

	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}

	public Integer getCompanyTurnover() {
		return companyTurnover;
	}

	public void setCompanyTurnover(Integer companyTurnover) {
		this.companyTurnover = companyTurnover;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public Float getLatestStockPrice() {
		return latestStockPrice;
	}

	public void setLatestStockPrice(Float latestStockPrice) {
		this.latestStockPrice = latestStockPrice;
	}

	public Set<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(Set<Stock> stockList) {
		this.stockList = stockList;
	}

	
	
	
}
