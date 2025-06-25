package com.bindeshwar.bindeshwarmart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.UsersCategory;
import com.bindeshwar.bindeshwarmart.repository.userCategoryRepository;

@Service
public class userCategoryService {
	@Autowired
	userCategoryRepository usercatrepository;
	
	public UsersCategory getUserById(Long id) {
		Optional<UsersCategory> optionalUser = usercatrepository.findById(id);
		return optionalUser.orElse(null);
	}
	
	public List<UsersCategory> findByName(String name) {
		return usercatrepository.findByName(name);
	}

	public void save(UsersCategory category) {
		usercatrepository.save(category);
	}
}

