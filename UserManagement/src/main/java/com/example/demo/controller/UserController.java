package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/getUserById")
//	public ResponseEntity<?> getUserById(@RequestBody int userId)
	public ResponseEntity<?> getUserById(@RequestParam int userId)
	{
		User user=userService.getUserById(userId);
		if(user!=null)
		{
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<String>("User not present", HttpStatus.NO_CONTENT);
		
	}
}
