package com.acrinta.server;

import java.util.Random;

import com.acrinta.client.login.request.RegistrationService;
import com.acrinta.shared.RegistrationToken;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RegistrationServlet extends RemoteServiceServlet implements RegistrationService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long uuid;

	public RegistrationToken submit(String name, String password) {
		Random rand = new Random();
		uuid = rand.nextLong();
		RegistrationToken token = new RegistrationToken();
		token.setId(uuid);
		token.setName(name);
		token.setPassword(password);
		return token;
	}
}
