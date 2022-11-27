package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.demo.filter.JWTFilter;

@SpringBootApplication
public class UserManagementApplication {
	
	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean filterBean=new FilterRegistrationBean();
		filterBean.setFilter(new JWTFilter());
		filterBean.addUrlPatterns("/api/v1/user/*");
		return filterBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

}
