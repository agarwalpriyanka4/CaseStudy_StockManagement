package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

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
	
	public String generateToken(String username, String password)
	{
		return password;
		
	}

}
