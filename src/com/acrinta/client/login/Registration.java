package com.acrinta.client.login;

import java.util.Random;

import com.acrinta.client.login.request.RegistrationService;
import com.acrinta.client.login.request.RegistrationServiceAsync;
import com.acrinta.shared.RegistrationToken;
import com.acrinta.shared.UserDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sun.org.apache.bcel.internal.generic.POP;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.SimplePanel;

public class Registration extends Composite {
	private TextBox login;
	private PasswordTextBox password;
	private PasswordTextBox repeatPassword;
	private SubmitButton submitButton;
	private long uuid;

	private final RegistrationServiceAsync registrationService = GWT.create(RegistrationService.class);

	public TextBox getLogin() {
		return login;
	}

	public void setLogin(TextBox login) {
		this.login = login;
	}

	public TextBox getPassword() {
		return password;
	}

	public void setPassword(PasswordTextBox password) {
		this.password = password;
	}

	public TextBox getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(PasswordTextBox repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public Registration() {

		final FormPanel formPanel = new FormPanel();
		formPanel.setStyleName("gwt-Label-Login");
		formPanel.setMethod(FormPanel.METHOD_POST);
		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanel.setAction("/myFormHandler");
		initWidget(formPanel);
		formPanel.setSize("203px", "247px");

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setStyleName("gwt-Label-Login");
		formPanel.setWidget(verticalPanel);
		verticalPanel.setSize("205px", "246px");

		Label lblRegistrationForm = new Label("Registration form");
		lblRegistrationForm.setStyleName("h3");
		verticalPanel.add(lblRegistrationForm);
		
		final SimplePanel simplePanel = new SimplePanel();
		verticalPanel.add(simplePanel);

		VerticalPanel verticalPanel_1 = new VerticalPanel();
		verticalPanel.add(verticalPanel_1);

		Label lblChooseLogin = new Label("Choose login:");
		lblChooseLogin.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblChooseLogin.setStyleName("gwt-Label-Login");
		verticalPanel_1.add(lblChooseLogin);
		lblChooseLogin.setSize("178px", "19px");

		login = new TextBox();
		login.setAlignment(TextAlignment.CENTER);
		login.setStyleName("gwt-Label-Login");
		verticalPanel_1.add(login);
		login.setWidth("203px");

		VerticalPanel verticalPanel_3 = new VerticalPanel();
		verticalPanel.add(verticalPanel_3);

		Label lblYourPassword = new Label("Your password:");
		lblYourPassword.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblYourPassword.setStyleName("gwt-Label-Login");
		verticalPanel_3.add(lblYourPassword);
		lblYourPassword.setSize("178px", "19px");

		password = new PasswordTextBox();
		password.setStyleName("gwt-Label-Login");
		verticalPanel_3.add(password);
		password.setWidth("203px");

		VerticalPanel verticalPanel_2 = new VerticalPanel();
		verticalPanel.add(verticalPanel_2);

		Label lblRepeatPassword = new Label("Repeat password:");
		lblRepeatPassword.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblRepeatPassword.setStyleName("gwt-Label-Login");
		verticalPanel_2.add(lblRepeatPassword);

		repeatPassword = new PasswordTextBox();
		repeatPassword.setStyleName("gwt-Label-Login");
		verticalPanel_2.add(repeatPassword);
		repeatPassword.setWidth("202px");

		submitButton = new SubmitButton();
		submitButton.setText("Register!");
		submitButton.setStyleName("gwt-Label-Login");
		verticalPanel.add(submitButton);
		verticalPanel.setCellHorizontalAlignment(submitButton, HasHorizontalAlignment.ALIGN_RIGHT);

		formPanel.addSubmitHandler(new SubmitHandler() {
			public void onSubmit(SubmitEvent event) {
				if (getPassword().getText().equals(getRepeatPassword().getValue()) & !getLogin().getText().equals("")
						& !getPassword().getValue().equals("")) {
					submit();
				} else
					Window.alert("Complete form first! \nProvide login details and make sure that passwords are same");
			}

			private void submit() {
				Random rand = new Random();
				uuid = rand.nextLong();
				registrationService.submit(getLogin().getText(), getPassword().getValue(),
						new AsyncCallback<RegistrationToken>() {
					public void onFailure(Throwable caught) {
						simplePanel.setWidget(new HTML("That... was a mistake! \n" + caught.getMessage()));
						Window.alert("That... was a mistake! \n" + caught.getMessage());
					}

					public void onSuccess(RegistrationToken token) {
						if (token == null) {
							simplePanel.setWidget(new HTML("???? Success registration with null ???"));
							Window.alert("???? Success registration with null ???");
						} else
							simplePanel.setWidget(new HTML("Registration Succesfull! Please login with this credentials"));
							Window.alert("Registration Succesfull! Please login with this credentials");
							formPanel.getParent().getParent().setVisible(false);
					}
				});
			}
		});
	}

}
