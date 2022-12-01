package com.example.demo;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.filter.JWTFilter;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
//@SecurityScheme(type = SecuritySchemeType.HTTP, name = "bearerAuth", in = SecuritySchemeIn.HEADER)
//@OpenAPIDefinition(info = @Info(title = "Apply Default Global SecurityScheme in springdoc-openapi", version = "1.0.0"), security = { @SecurityRequirement(name = "bearerAuth") })
@SpringBootApplication
public class StockManagementApplication {
	
	@Bean
    public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean filterBean=new FilterRegistrationBean();
		filterBean.setFilter(new JWTFilter());
		filterBean.addUrlPatterns("/api/v1.0/market/*");
		return filterBean;
	} 
	
	@Configuration
	class OpenApiConfig
	{
		@Bean
		public OpenAPI customconfig()
		{
			final String securitySchemeName="bearerAuth";
			 return new OpenAPI().addSecurityItem(new SecurityRequirement()
					 .addList(securitySchemeName))
					 .components(new Components().addSecuritySchemes(securitySchemeName, new SecurityScheme()
					 .name(securitySchemeName).type(SecurityScheme.Type.HTTP)
					 .scheme("bearer").bearerFormat("JWT")));
							 
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(StockManagementApplication.class, args);
		
	}

}
