package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.CompanyAlreadyExistException;
import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private CompanyRepository companyRepo;

	@Override
	public Company getCompanyById(int companyCode) {
		Optional<Company> company=companyRepo.findById(companyCode);
		if(company.isPresent())
		    return company.get();
		else
			return null;
	}

	@Override
	public List<Company> getCompanyDetails() {
		List<Company> companyList=companyRepo.findAll();
		if(companyList!=null)
		{
			return companyList;
		}
		return null;
	}

	@Override
	public Company addCompanyDetails(Company company) throws CompanyAlreadyExistException {
		Optional<Company> existedCompany=companyRepo.findById(company.getCompanyCode());
		if(existedCompany.isPresent())
		{
			throw new CompanyAlreadyExistException();
		}
		else
		{
			return companyRepo.saveAndFlush(company);
		}
		
	}

	@Override
	public boolean deleteCompany(int companyCode) {
		if(companyCode!=0)
		{
			//companyRepo.deleteStockList(companyCode);
			companyRepo.deleteById(companyCode);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateCompany(Company company) {
		Optional<Company> companyObj=companyRepo.findById(company.getCompanyCode());
		if(companyObj.isPresent())
		{
			Company companyObj2=companyObj.get();
			companyObj2.setCompanyCEO(company.getCompanyCEO());
			companyObj2.setCompanyName(company.getCompanyName());
			companyObj2.setCompanyTurnover(company.getCompanyTurnover());
			companyObj2.setCompanyWebsite(company.getCompanyWebsite());
			companyObj2.setStockExchange(company.getStockExchange());
			companyRepo.saveAndFlush(companyObj2);
			return true;			
		}
		return false;  
	}

}
