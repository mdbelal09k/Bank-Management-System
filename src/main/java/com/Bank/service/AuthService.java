package com.Bank.service;

import java.util.Optional;

import com.Bank.entity.User;
import com.Bank.entity.enums.Role;

/**
 * Handles authentication and user-related business logic.
 */
public interface AuthService {

    /**
     * Register a new user (Admin creates user)
     */
	
	User register(String username,String password,Role role);
	
	 /**
     * Login using username & password
     */
	Optional<User>login(String username,String password);

    /**
     * Validate user role (RBAC)
     */
	boolean authorize(User user,Role urquiredRole);
	
	/**
    * Change user password
    */
    void changePassword(Long userId,String oldPassword,String newPasssword);
    

    /**
     * Delete user (Admin only)
     */
    void deleteUser(Long userId);
    
    /**
     * Check if username already exists
     */
    boolean usernameExists(String username);
    
    
	
	

	
}
