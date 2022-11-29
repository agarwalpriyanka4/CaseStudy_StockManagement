package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.CompanyController;
import com.example.demo.exceptions.CompanyAlreadyExistException;
import com.example.demo.model.Company;
import com.example.demo.service.CompanyService;
import com.example.demo.service.CompanyServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyControllerTest {
	@Mock
	private CompanyServiceImpl companyServiceImpl;
	
	@InjectMocks
	private CompanyController companyController;
	
	private MockMvc mockmvc;
	
	 @BeforeEach
	  public void initialise()
	{
		 MockitoAnnotations.openMocks(this);
		 mockmvc=MockMvcBuilders.standaloneSetup(companyController).build();
	}
private List<Company> companyList=new ArrayList<Company>();
	 
	/* @Test
	 public void getCompanyDetails() throws Exception
	 {
		 Company company=new Company();
		 company.setCompanyCode(1);
			company.setCompanyCEO("Peter");
			company.setCompanyName("ABC");
			company.setCompanyTurnover(150000000);
			company.setCompanyWebsite("abc@def.com");
			company.setStockExchange("NSE");
			
			companyList.add(company);
			when(companyServiceImpl.getCompanyDetails()).thenReturn(companyList);
			List<Company> list1=companyServiceImpl.getCompanyDetails();
		assertEquals(companyList,list1);
		
		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/info/getAll")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	 } */

 @Test
 public void addCompanyDetails() throws Exception
 {
	 Company company=new Company();
	 company.setCompanyCode(1);
		company.setCompanyCEO("Peter");
		company.setCompanyName("ABC");
		company.setCompanyTurnover(150000000);
		company.setCompanyWebsite("abc@def.com");
		company.setStockExchange("NSE");
		//when(companyServiceImpl.addCompanyDetails(any())).thenReturn(company);
		when(companyServiceImpl.addCompanyDetails(company)).thenReturn(company);
		Company actual=companyServiceImpl.addCompanyDetails(company);
		assertEquals(company,actual);
		mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/register").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(company))).andExpect(MockMvcResultMatchers.status().isCreated());
		
 }
}
