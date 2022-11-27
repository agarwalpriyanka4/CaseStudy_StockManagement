package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired 
	private UserRepository userRepo;
	@Override
	public List<User> getAllUsers() {
		List<User> userList= userRepo.findAll();
		if(userList!=null & userList.size()>0)
		{
		return userList;
		}
		return null;
	}

	@Override
	public User addUser(User user) {
		if(user!=null)
		{
			return userRepo.saveAndFlush(user);
		}
		return null;
	}
	

	@Override
	public boolean validateUser(String username, String password) {
		User user= userRepo.validateUser(username, password);
		if(user!=null)
		{
			return true;
		}
		return false;
	}

	@Override
	public User getUserById(int userId) {
	Optional<User> user=userRepo.findById(userId);
	if(user.isPresent())
	{
		return user.get();
	}
		return null;
	}

}
