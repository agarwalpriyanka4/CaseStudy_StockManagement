package com.example.demo.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object Obj)
	{
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("Display Message", message);
		map.put("Http Status", status);
		map.put("Data",Obj );
		return new ResponseEntity<Object>(map,status);
		
		
	}

}
