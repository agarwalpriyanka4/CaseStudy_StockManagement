package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;
@DataJpaTest
@AutoConfigureMockMvc
public class CompanyRepositoryTest {
	
@Autowired
private CompanyRepository companyRepo;


Company company=new Company();

  @BeforeEach
  public void initialise()
{
	 company.setCompanyCode(1);
	company.setCompanyCEO("Peter");
	company.setCompanyName("ABC");
	company.setCompanyTurnover(150000000);
	company.setCompanyWebsite("abc@def.com");
	company.setStockExchange("NSE");
}

  @Test
  public void SaveCompanySuccess() throws Exception
  {
	  Company obj=null;
	  companyRepo.saveAndFlush(company);
	  
	  obj=companyRepo.findById(company.getCompanyCode()).get();
	  
	  assertEquals(company.getCompanyWebsite(),obj.getCompanyWebsite());
  }
	  
	  
  }




