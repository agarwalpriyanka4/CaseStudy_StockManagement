package com.example.demo;

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

import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.CompanyService;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyServiceTest {

	@Mock
	private CompanyRepository companyRepo;
	
	@InjectMocks
	private CompanyService companyService;
	
	private MockMvc mockmvc;
	
	 @BeforeEach
	  public void initialise()
	{
		 MockitoAnnotations.openMocks(this);
		 mockmvc=MockMvcBuilders.standaloneSetup(companyService).build();
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
	 }
}
