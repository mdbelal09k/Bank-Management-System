package com.Bank.serviceImpl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.hibernate.grammars.hql.HqlParser.IsEmptyPredicateContext;

import com.Bank.dao.UserDAO;
import com.Bank.dao.Impl.UserDAOImpl;
import com.Bank.entity.User;
import com.Bank.entity.enums.Role;
import com.Bank.service.AuthService;

/*
 * Handle authentitication & user-related bussiness logic.
 * 
 */
public class AuthServiceImpl implements AuthService {

	private final UserDAOImpl userDAO = new UserDAOImpl();

//	  ====================================
//			 REGISTER NEW USER 
//     ===================================
	@Override
	public User register(String username, String password, Role role) {

		if (userDAO.existsByUsername(username)) {
			throw new RuntimeException("Username already exists.");
		}

		String hashedPassword = hashPassword(password);

		User user = new User();
		userDAO.save(user);

		return user;
	}

//	  ============================================
//	     LOGING 
//	  ==========================================

	private String hashPassword(String password) {
		return password;

	}

	@Override
	public Optional<User> login(String username, String password) {
		Optional<User> userOptional = userDAO.findByUsername(username);

		if (userOptional.isEmpty()) {
			return Optional.empty();
		}

		String hashInputPassword = hashPassword(password);

		if (hashInputPassword.isEmpty()) {
			return Optional.empty();
		}
		return userOptional;
	}
	// =========================
	// Role Authorization
	// =========================

	@Override
	public boolean authorize(User user, Role urquiredRole) {

		return user.getRole().equals(urquiredRole);

	}
	// =========================
	// Change Password
	// =========================

	@Override
	public void changePassword(Long userId, String oldPassword, String newPasssword) {
		User user = userDAO.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));

		if (!user.getPassword().equals(hashPassword(oldPassword))) {
			throw new RuntimeException("old password incorrect");
		}

		user.setPassword(hashPassword(newPasssword));
		userDAO.update(user);
	}

	// =========================
	// Delete User
	// =========================
	@Override
	public void deleteUser(Long userId) {
		userDAO.delete(userId);

	}

	@Override
	public boolean usernameExists(String username) {
		// TODO Auto-generated method stub
		return userDAO.existsByUsername(username);
	}
	// =========================
	// Password Hashing (SHA-256)
	// =========================

	private String hashPassword1(String password) {
		
		
		try {
			MessageDigest digest=MessageDigest.getInstance("SHA-256");
			byte[] hash=digest.digest(password.getBytes(StandardCharsets.UTF_8));
			
	StringBuilder hexString =new StringBuilder();
	
	for(byte b:hash) {
		String hex=Integer.toHexString( 0xff & b);
		if(hex.length()==1) hexString.append('0');
		hexString.append(hex);
	}
	return password;
	
		}catch(Exception e) {
			throw new RuntimeException("Error hashing password");
		}
	
		
	}
}
