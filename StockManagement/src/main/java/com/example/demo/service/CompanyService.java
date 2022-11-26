package com.example.demo.service;

import java.util.List;

import com.example.demo.exceptions.CompanyAlreadyExistException;
import com.example.demo.model.Company;

public interface CompanyService {

	public Company getCompanyById(int companyCode);
	
	public List<Company> getCompanyDetails();
	
	public Company addCompanyDetails(Company company) throws CompanyAlreadyExistException;
	
	public boolean deleteCompany(int companyCode);
	
	public boolean updateCompany(Company company);
	
	
}
