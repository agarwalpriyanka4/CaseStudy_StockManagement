package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.exceptions.CompanyAlreadyExistException;
import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.CompanyService;
import com.example.demo.service.CompanyServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyServiceTest {

	@Mock
	private CompanyRepository companyRepo;
	
	@InjectMocks
	private CompanyServiceImpl companyServiceImpl;
	
	private MockMvc mockmvc;
	
	 @BeforeEach
	  public void initialise()
	{
		 MockitoAnnotations.openMocks(this);
		 mockmvc=MockMvcBuilders.standaloneSetup(companyServiceImpl).build();
	}
	 
	 private List<Company> companyList=new ArrayList<Company>();
	 
	 @Test
	 public void getCompanyDetails()
	 {
		 Company company=new Company();
		 company.setCompanyCode(1);
			company.setCompanyCEO("Peter");
			company.setCompanyName("ABC");
			company.setCompanyTurnover(150000000);
			company.setCompanyWebsite("abc@def.com");
			company.setStockExchange("NSE");
			
			companyList.add(company);
			when(companyRepo.findAll()).thenReturn(companyList);
			List<Company> list1=companyServiceImpl.getCompanyDetails();
		assertEquals(companyList,list1);
	 }
	 
	 @Test
	 public void getCompanyDetailsFail()
	 {
	 when(companyRepo.findAll()).thenReturn(null);
	 List<Company> list2=companyServiceImpl.getCompanyDetails();
	 assertNull(list2);
	 }
	 
	 @Test
	 public void addCompany() throws CompanyAlreadyExistException 
	 {
		 Company company=new Company();
		 company.setCompanyCode(1);
			company.setCompanyCEO("Peter");
			company.setCompanyName("ABC");
			company.setCompanyTurnover(150000000);
			company.setCompanyWebsite("abc@def.com");
			company.setStockExchange("NSE");
			when(companyRepo.saveAndFlush(company)).thenReturn(company);
			Company actual=companyServiceImpl.addCompanyDetails(company);
			assertEquals(company,actual);
	 }
	 
	 @Test
	 public void addCompanyFail() throws CompanyAlreadyExistException 
	 {
		 Company company=new Company();
		 when(companyRepo.saveAndFlush(company)).thenReturn(null);
			Company actual=companyServiceImpl.addCompanyDetails(company);
			assertNull(actual);
	 }
	 
	 
}
