package com.acrinta.server;

import java.util.Random;

import com.acrinta.client.login.request.LoginService;
import com.acrinta.client.login.request.RegistrationService;
import com.acrinta.shared.UserDto;

public class AuthServlet extends AdvancedServlet implements LoginService {

	private long uuid;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void logout() {
		setUser(null);
	}

	public UserDto isAuthenticated() {
		return getUser(); // helper method from AdvancedServlet
	}
	
	public UserDto authenticate(String email, String password) {
		//check to see if the email exist in the datastore
		//check passwords
		// *** Test Code ****
		UserDto user = new UserDto();
		if(email.contains("user") || email.contains("admin")){
			user.setEmail(email);
			user.setId(12);
			user.setName("NameFromDb");
			
			//set the current_user for this session
			setUser(user);//see AdvncedServlet (Parent Class)
			
		}else{
			user = null;//force showLogin
		}
		
		return user;
	}

}
