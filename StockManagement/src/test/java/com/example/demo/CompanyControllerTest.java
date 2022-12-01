package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.CompanyController;
import com.example.demo.controller.StockController;
import com.example.demo.exceptions.CompanyAlreadyExistException;
import com.example.demo.model.Company;
import com.example.demo.model.Stock;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.CompanyService;
import com.example.demo.service.CompanyServiceImpl;
import com.example.demo.service.StockServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyControllerTest {
	@Mock
	private CompanyServiceImpl companyServiceImpl;
	@Mock
	private StockServiceImpl stockServiceImpl;
	
	@Mock
	private CompanyRepository companyRepo;
	@InjectMocks
	private CompanyController companyController;
	
	@InjectMocks
	private StockController stockController;
	
	@Autowired
	private MockMvc mockmvc;
	
	 @BeforeEach
	  public void initialise()
	{
		 MockitoAnnotations.openMocks(this);
		 mockmvc=MockMvcBuilders.standaloneSetup(companyController).build();
	}
private List<Company> companyList=new ArrayList<Company>();
	 
	@Test
	 public void getCompanyDetails() throws Exception
	 {
		 Company company=new Company();
		 company.setCompanyCode(1);
			company.setCompanyCEO("Peter");
			company.setCompanyName("ABC");
			company.setCompanyTurnover(150000000);
			company.setCompanyWebsite("abc@def.com");
			company.setStockExchange("NSE");
			
			Stock stock=new Stock();
			stock.setCompanyCode_fk(1);
			stock.setStockPrice(24f);
			stock.setTimestamp(new Date());
			stock.setTransactionId(1);
			
			Set<Stock> stockList=new HashSet<Stock>();
			stockList.add(stock);
			company.setStockList(stockList);
			
			companyList.add(company);
			when(companyServiceImpl.getCompanyDetails()).thenReturn(companyList);
			when(stockServiceImpl.getStockList(company.getCompanyCode())).thenReturn(stockList);
			List<Company> list1=companyServiceImpl.getCompanyDetails();
		assertEquals(companyList,list1);
		
		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/info/getAll")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	 } 
	
	
/*	@Test
	 public void getCompanyDetailsFail() throws Exception
	 {
		 Company company=new Company();
			
			Stock stock=new Stock();
			
			Set<Stock> stockList=new HashSet<Stock>();
			stockList.add(stock);
			company.setStockList(stockList);
			
			companyList.add(company);
			doReturn(Optional.of(company)).when(companyRepo).findById(company.getCompanyCode());
			when(companyServiceImpl.getCompanyDetails()).thenReturn(companyList);
			when(stockServiceImpl.getStockList(company.getCompanyCode())).thenReturn(stockList);
			List<Company> list1=companyServiceImpl.getCompanyDetails();
		assertNull(list1);
		
		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/info/getAll")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNoContent());
	 } */

 @Test
 public void addCompanyDetails() throws Exception
 {
	 Company company=new Company();
	 company.setCompanyCode(1);
		company.setCompanyCEO("Peter");
		company.setCompanyName("ABC");
		company.setCompanyTurnover(100000009);
		company.setCompanyWebsite("abc@def.com");
		company.setStockExchange("NSE");
		when(companyServiceImpl.addCompanyDetails(any())).thenReturn(company);
		//when(companyServiceImpl.addCompanyDetails(company)).thenReturn(company);
		Company actual=companyServiceImpl.addCompanyDetails(company);
		assertEquals(company,actual);
		//System.out.println(new ObjectMapper().writeValueAsString(company));
		//mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/register").contentType(MediaType.APPLICATION_JSON)
		//		.content(new ObjectMapper().writeValueAsString(company))).andExpect(MockMvcResultMatchers.status().isCreated());
		
		mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(company))).
		andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
		
 }
 
 @Test
 public void addCompanyDetailsFails() throws Exception
 {
	 Company company=new Company();
	 company.setCompanyCode(1);
		company.setCompanyCEO("Peter");
		company.setCompanyName("ABC");
		company.setCompanyTurnover(100000009);
		company.setCompanyWebsite("abc@def.com");
		company.setStockExchange("NSE");
		when(companyServiceImpl.addCompanyDetails(any())).thenReturn(null);
		//when(companyServiceImpl.addCompanyDetails(company)).thenReturn(company);
		Company actual=companyServiceImpl.addCompanyDetails(company);
		assertNull(actual);
		System.out.println(new ObjectMapper().writeValueAsString(company));
		//mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/register").contentType(MediaType.APPLICATION_JSON)
		//		.content(new ObjectMapper().writeValueAsString(company))).andExpect(MockMvcResultMatchers.status().isCreated());
		
		mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/register").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(company))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isConflict());
		
 }
 
 /*@Test
 public void deleteCompany() throws Exception
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
		
		mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/delete")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isConflict());
		
 }
 */
}
