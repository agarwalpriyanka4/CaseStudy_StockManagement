package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("auth/v1/user")
public class AuthenticationController {
	private Map<String,String> mapObj=new HashMap<String,String>();
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user)
	{
		User userObj=userService.addUser(user);
		if(user!= null)
		{
			return new ResponseEntity<User>(userObj, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("User already exist", HttpStatus.CONFLICT);
	}
	
	public String generateToken(String username, String password) throws ServletException,Exception
	{
		String jwtToken="";
		if(username==null || password==null)
		{
			throw new ServletException("Please enter valid username and password");
		}
		boolean flag= userService.validateUser(username, password);
		
		if(flag)
		{
			jwtToken=Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+60000000)).signWith(SignatureAlgorithm.HS256, "Secret Key").compact();
		}
		else
		{
			throw new ServletException("Invalid Credentials");
		}
		return jwtToken;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> LoginUser(@RequestBody User user)
	{
		try
		{
			String getToken = generateToken(user.getUsername(),user.getPassword());
			mapObj.put("message", "User logged in successfully");
			mapObj.put("Token", getToken);
		}
		catch(Exception e)
		{
			mapObj.put("message", "User not logged in successfully");
			mapObj.put("Token", null);
		}
		return new ResponseEntity<>(mapObj,HttpStatus.OK);
	}   

}
