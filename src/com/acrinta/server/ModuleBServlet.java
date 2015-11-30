package com.acrinta.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acrinta.shared.UserDto;

public class ModuleBServlet  extends AdvancedServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//set up response
		res.setContentType("text/html");
		res.setCharacterEncoding("UTF-8");
		
		//set up our response
		//if the user is already authenticated => append appropriate module js
		PrintWriter page = res.getWriter();
		
		
		// user helper from AdvancedServlet to get local current_user if it exists
		if(((UserDto)req.getSession().getAttribute("current_user"))==null){
			//this guy needs to login..
			//respond with the login page (login.js)
			page.append("<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"><link type=\"text/css\" rel=\"stylesheet\" href=\"w3.css\"><script type=\"text/javascript\" src=\"../login/login.nocache.js\"></script></head><body></body></html>");
		}else{
			//the user is authenticated
			//respond with the host page (ModuleA.js) for module a
			page.append("<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"><link type=\"text/css\" rel=\"stylesheet\" href=\"w3.css\"><script type=\"text/javascript\" src=\"../module_b/module_b.nocache.js\"></script></head><body></body></html>");
		}
	}
}
