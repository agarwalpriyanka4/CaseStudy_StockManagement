package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.demo.model.Company;

public class CompanyTest {
	
	@Test
	public void test01()
	{

	Company companyObj=Mockito.mock(Company.class);
	companyObj.setCompanyCode(1);
	Integer mockCompanyCode=companyObj.getCompanyCode();
	when(companyObj.getCompanyCode()).thenReturn(mockCompanyCode);
		assertEquals(companyObj.getCompanyCode(),mockCompanyCode);
	}
	
	@Test
	public void test02()
	{

	Company companyObj=Mockito.mock(Company.class);
	companyObj.setCompanyTurnover(90000000);
	Integer mockCompanyTurnover=companyObj.getCompanyTurnover();
	when(companyObj.getCompanyTurnover()).thenReturn(mockCompanyTurnover);
		assertEquals(companyObj.getCompanyTurnover(),mockCompanyTurnover);
	}
		
		
	@Test
	public void test03()
	{

	Company companyObj=Mockito.mock(Company.class);
	companyObj.setCompanyCEO("Peter");
	String mockCompanyCEO=companyObj.getCompanyCEO();
	when(companyObj.getCompanyCEO()).thenReturn(mockCompanyCEO);
		assertEquals(companyObj.getCompanyCEO(),mockCompanyCEO);
	
	}
	@Test
	public void test04()
	{
		Company companyObj=Mockito.mock(Company.class);
		companyObj.setCompanyName("HAL");
		String mockCompanyName=companyObj.getCompanyName();
		when(companyObj.getCompanyName()).thenReturn(mockCompanyName);
		assertEquals(companyObj.getCompanyName(),mockCompanyName);
		}
	
	@Test
	public void test05()
	{
		Company companyObj=Mockito.mock(Company.class);
		companyObj.setCompanyWebsite("abc@abc.com");
		String mockCompanywebsite=companyObj.getCompanyWebsite();
		when(companyObj.getCompanyWebsite()).thenReturn(mockCompanywebsite);
		assertEquals(companyObj.getCompanyWebsite(),mockCompanywebsite);
		}
	
	@Test
	public void test06()
	{
		Company companyObj=Mockito.mock(Company.class);
		companyObj.setStockExchange("BSE");
		String mockCompanyexchange=companyObj.getStockExchange();
		when(companyObj.getStockExchange()).thenReturn(mockCompanyexchange);
		assertEquals(companyObj.getStockExchange(),mockCompanyexchange);
		}
}
