package com.acrinta.client.login;

import com.acrinta.client.login.request.LoginService;
import com.acrinta.client.login.request.LoginServiceAsync;
import com.acrinta.client.login.view.LoginView;
import com.acrinta.client.module_a.resource.Resource;
import com.acrinta.shared.UserDto;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Login implements EntryPoint {
	private LoginView view = GWT.create(LoginView.class);
	private Resource resource = GWT.create(Resource.class);
	private final LoginServiceAsync loginService = GWT.create(LoginService.class);

	// MYBATIS CODE BLOCK
	/*
	 * String resources = "/LoginNew/war/mybatis-config.xml"; InputStream
	 * inputStream = null; try { inputStream =
	 * Resources.getResourceAsStream(resources); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * SqlSessionFactory sqlSessionFactory = new
	 * SqlSessionFactoryBuilder().build(inputStream);
	 */

	public void onModuleLoad() {

		RootLayoutPanel.get().add(new Image(resource.ajax_loader6()));

		// let's do a quick check via rpc to see if this user is
		// already authenticated

		loginService.isAuthenticated(new AsyncCallback<UserDto>() {
			public void onFailure(Throwable caught) {
				// damn something really went wrong
			}

			public void onSuccess(UserDto user) {
				if (user == null) {
					// show login screen
					showLogin();
				} else {
					// the user already has a session
					if (isModuleB()) {
						Window.Location.assign("/module_b/");
					} else {
						// redirect them to default module (A)
						Window.Location.assign("/module_a/");
					}

				}
			}
		});
	}

	public void showLogin() {
		SubmitHandler handler = new SubmitHandler();
		view.getSubmitButton().addClickHandler(handler);
		view.getEmailBox().addKeyUpHandler(handler);
		view.getPasswordBox().addKeyUpHandler(handler);
		view.getRegisterLink().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PopupPanel pop = new PopupPanel(true);
				Registration registrationForm = new Registration();
				pop.setWidget(registrationForm);
				pop.center();
			}
		});
		RootLayoutPanel.get().clear();
		RootLayoutPanel.get().add(view);
	}

	// Declare a Handler for either the Submit Button Click
	// or the KeyUp for Enter on either of the two text boxes

	class SubmitHandler implements ClickHandler, KeyUpHandler {
		public void onClick(ClickEvent event) {
			authenticate();
		}

		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				authenticate();
			}
		}
	}

	public void authenticate(){
		disableLogin();
		loginService.authenticate(view.getEmailValue(), view.getPasswordValue(), new AsyncCallback<UserDto>(){
			public void onFailure(Throwable caught) {
				//damn
			}
			
			public void onSuccess(UserDto user) {
				if(user == null){
					//we can take this as a bad attempt
					//add exceptions or other checks for better UX
					//we will just enable the form for another try..
					view.getMessage().setWidget(new HTML("Wrong! try Email: user / admin"));
					enableLogin();
				} else if (user.getEmail().equals("admin")) {
				//	if(isModuleB()) {
						Window.Location.assign("/module_b/");
					}
					 else if (user.getEmail().equals("user")) {
						//redirect them to default module (A)
						Window.Location.assign("/module_a/");
					}
				}
		});
	}
	
	// helper to see if we are in module_b
	public boolean isModuleB() {
		return Window.Location.getHref().contains("/module_b/");
	}

	public void disableLogin() {
		view.getMessage().clear();
		view.getSubmitButton().setText("Checking...");
		view.getSubmitButton().setEnabled(false);
		view.getEmailBox().setEnabled(false);
		view.getPasswordBox().setEnabled(false);
		view.getRegisterLink().setEnabled(false);
	}

	public void enableLogin() {
		view.getSubmitButton().setText("Login");
		view.getSubmitButton().setEnabled(true);
		view.getEmailBox().setEnabled(true);
		view.getPasswordBox().setEnabled(true);
		view.getRegisterLink().setEnabled(true);
	}
}