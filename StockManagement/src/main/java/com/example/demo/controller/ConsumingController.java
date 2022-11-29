package com.example.demo.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.model.UserDTO;

@RestController
@RequestMapping("/consumer")
public class ConsumingController {

	@PostMapping("/getLogin")
	public ResponseEntity<?> consumeLogin(@RequestBody UserDTO user) throws Exception
	{
		//@RequestBody UserDto user
		RestTemplate restTemplate=new RestTemplate();
		String baseUrl="http://localhost:8081/auth/v1/user/login";
		ResponseEntity<Map<String,String>> response= null;
		
		try
		{
			
			/*HashMap<String,String> request = new HashMap<String,String>();
			request.put("username", user.getUsername());
			request.put("password", user.getPassword());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);*/
			//HttpEntity<String> entity = new HttpEntity<String>(user, headers);
		//	HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
	 //        response=restTemplate.postForEntity(baseUrl, HttpMethod.POST, String.class)
			response= restTemplate.exchange(baseUrl,HttpMethod.POST, getHeaders(user),
					    new ParameterizedTypeReference<Map<String, String>>() {});
		//	response=restTemplate.postForEntity(baseUrl, entity, String.class);
					return new ResponseEntity<>(response,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Response object is null", HttpStatus.NO_CONTENT);
		
	}
	private static HttpEntity<UserDTO> getHeaders(UserDTO user) throws Exception
	{
		HttpHeaders headers =new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Access-Control-Allow-Origin", "*");
		return new HttpEntity<UserDTO>(user,headers);
		
	}

} 
