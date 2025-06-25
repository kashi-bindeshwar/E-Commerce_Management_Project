package com.bindeshwar.bindeshwarmart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.UsersCategory;

@Repository
public interface userCategoryRepository extends CrudRepository<UsersCategory, Long> {

	List<UsersCategory> findByName(String name);

}
