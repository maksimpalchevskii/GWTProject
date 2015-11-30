package com.acrinta.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RegistrationToken implements IsSerializable {
	
	private long id;
	private String name;
	private String password;
	
	public long getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPassword(String email){
		this.password = email;
	}
	
}
