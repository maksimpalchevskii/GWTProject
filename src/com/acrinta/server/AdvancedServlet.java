package com.acrinta.server;

import com.acrinta.shared.UserDto;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AdvancedServlet  extends RemoteServiceServlet{

	public UserDto getUser(){
		return (UserDto)getThreadLocalRequest().getSession().getAttribute("current_user");
	}
	
	public void setUser(UserDto user){
		getThreadLocalRequest().getSession().setAttribute("current_user", user);
	}
}
