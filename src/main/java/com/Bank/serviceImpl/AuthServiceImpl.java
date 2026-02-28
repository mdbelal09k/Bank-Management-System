
package com.Bank.serviceImpl;
import java.util.Optional;



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

	private String hashPassword(String password) {
	// TODO Auto-generated method stub
	return null;
}

	@Override
	public Optional<User> login(String username, String password) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean authorize(User user, Role urquiredRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void changePassword(Long userId, String oldPassword, String newPasssword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean usernameExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}
}
