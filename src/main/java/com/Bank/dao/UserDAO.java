package com.Bank.dao;

import java.util.List;
import java.util.Optional;

/*
 * DAO interface for User entity
 *	handle databse operation releted to usrs.
 */

import com.Bank.entity.User;
import com.Bank.entity.enums.Role;
public interface UserDAO {
	 	///-> CREATE NEW USER
	 	
	 	void save(User user);
	 	///-> UPDATE
	 	void update(User user);
	 	///-> DELETE
	 	void delete(Long userId);
	 	///-> FIND BY USER ID
	 	Optional<User> findById(Long userId);
	 	///-> find user by username(username for login)
	 	Optional<User> findByUsername(String username);
	 	///-> get by user
	 	List<User> findAll();
	 	///-> get users by role (ADMIN /TELLER/ CUSTOMER)
	 	List<User> findByRoll(Role role);
	 	
	 	///-> check if usrname already exists
	 	
	 	boolean existsByUsername(String username);

	 
}


