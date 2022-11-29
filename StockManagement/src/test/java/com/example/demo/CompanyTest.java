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
	companyObj.setCompanyCEO("Peter");
	String mockCompany=companyObj.getCompanyCEO();
	when(companyObj.getCompanyCEO()).thenReturn(mockCompany);
		assertEquals(companyObj.getCompanyCEO(),mockCompany);
	
	}
	@Test
	public void test02()
	{
		Company companyObj=Mockito.mock(Company.class);
		companyObj.setCompanyName("HAL");
		String mockCompanyName=companyObj.getCompanyName();
		when(companyObj.getCompanyName()).thenReturn(mockCompanyName);
		assertEquals(companyObj.getCompanyName(),mockCompanyName);
		}
}
