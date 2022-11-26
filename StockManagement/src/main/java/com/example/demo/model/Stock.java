package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stock {

	@Id
	@GeneratedValue
	private Integer transactionId;
	private Float stockPrice;
	private Date timestamp;
	private Integer companyCode_fk;
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public Float getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(Float stockPrice) {
		this.stockPrice = stockPrice;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getCompanyCode_fk() {
		return companyCode_fk;
	}
	public void setCompanyCode_fk(Integer companyCode_fk) {
		this.companyCode_fk = companyCode_fk;
	}
	
	
}
