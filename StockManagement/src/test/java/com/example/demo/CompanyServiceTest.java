package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
			List<Company> list1=companyServiceImpl.getCompanyDetails();//actual
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
	 
	 @Test
	 public void getCompanyById()
	 {
		 Company company=new Company();
		 company.setCompanyCode(1); //expected
			company.setCompanyCEO("Peter");
			company.setCompanyName("ABC");
			company.setCompanyTurnover(150000000);
			company.setCompanyWebsite("abc@def.com");
			company.setStockExchange("NSE");
			doReturn(Optional.of(company)).when(companyRepo).findById(company.getCompanyCode());
			Company actual=companyServiceImpl.getCompanyById(company.getCompanyCode());
			assertEquals(company,actual);
			
	 }
	 
	 @Test
	 public void getCompanyByIdFail()
	 {
		 Company company=new Company();
		 company.setCompanyCode(1);
			doReturn(Optional.of(company)).when(companyRepo).findById(company.getCompanyCode());
			Company actual=companyServiceImpl.getCompanyById(company.getCompanyCode());
			assertEquals(companyRepo.findById(company.getCompanyCode()).get(),actual);
			
	 }
	 
	 @Test
	 public void deleteCompany()
	 {
		 Company company=new Company();
		 company.setCompanyCode(1); //expected
			company.setCompanyCEO("Peter");
			company.setCompanyName("ABC");
			company.setCompanyTurnover(150000000);
			company.setCompanyWebsite("abc@def.com");
			company.setStockExchange("NSE");
			
			boolean actual=companyServiceImpl.deleteCompany(company.getCompanyCode());
			assertTrue(actual);	
	 }
	 
	 @Test
	 public void deleteCompanyFail()
	 {
		 Company company=new Company();
		 company.setCompanyCode(1); 
			boolean actual=companyServiceImpl.deleteCompany(company.getCompanyCode());
			assertTrue(actual);
	 }
	 
	 @Test
	 public void updateCompany()
	 {
		 Company company=new Company();
		 company.setCompanyCode(1); 
			company.setCompanyCEO("Peter");
			company.setCompanyName("ABC");
			company.setCompanyTurnover(150000000);
			company.setCompanyWebsite("abc@def.com");
			company.setStockExchange("NSE");
			doReturn(Optional.of(company)).when(companyRepo).findById(company.getCompanyCode());
			when(companyRepo.saveAndFlush(company)).thenReturn(company);
			boolean actual=companyServiceImpl.updateCompany(company);
			assertTrue(actual);	
	 }
	 
	 @Test
	 public void updateCompanyFail()
	 {
		 Company company=new Company();
		 doReturn(Optional.of(company)).when(companyRepo).findById(company.getCompanyCode());
			when(companyRepo.saveAndFlush(company)).thenReturn(company);
			boolean actual=companyServiceImpl.updateCompany(company);
			assertTrue(actual);	
	 }
}
