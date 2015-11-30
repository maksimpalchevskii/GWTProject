package com.acrinta.client.login.request;

import com.acrinta.shared.RegistrationToken;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("registration.rpc")
public interface RegistrationService extends RemoteService{ 
	RegistrationToken submit(String name, String password);
}
