package com.bindeshwar.bindeshwarmart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.Users;
import com.bindeshwar.bindeshwarmart.repository.UsersRepository;

@Service
public class UsersService {
	@Autowired
	UsersRepository repository;
	
	public void saveUser(Users users) 
	{
		repository.save(users);
	}
	
	
	public List<Users> getAllUsers() {
		return repository.findAll();
	}
	
	public Users getUserByUsernameAndPassword(Users users)
	{
		return repository.findByUsername(users.getUsername(), users.getPassword());
	}
	
	//this is for admin
	public Users getUserByUsernamePasswordAndCate(Users users)
	{
		System.out.println("email "+users.getUsername());
		System.out.println("email "+users.getPassword());
		return repository.findByUsernameAndCategory(users.getUsername(), users.getPassword(),users.getUsercategory().getId());
	}
	
	//this is for customers
	public Users getUserByEmailPasswordAndCate(Users users)
	{
		if (users.getUsercategory()!=null) {
			return repository.findByEmailAndCategory(users.getEmail(), users.getPassword(),users.getUsercategory().getId());
		}else {
			return null;
		}
	}
	
	//checking if email exist
	public Users getUserByEmail(Users users)
	{
		return repository.findByEmail(users.getEmail());
	}
	
	public Users getUserById(Long userid) {
        Optional<Users> userOptional = repository.findById(userid);
        return userOptional.orElse(null);
    } 
	
	public Long countTotalUsers() {
		return repository.countTotalUsers();
	}
	
}
