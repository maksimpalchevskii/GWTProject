package com.acrinta.client.login.request;

import com.acrinta.shared.RegistrationToken;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RegistrationServiceAsync {
	void submit(String name, String password, AsyncCallback<RegistrationToken> asyncCallback);
}
