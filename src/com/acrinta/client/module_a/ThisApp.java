package com.acrinta.client.module_a;

import com.acrinta.client.login.request.LoginService;
import com.acrinta.client.login.request.LoginServiceAsync;
import com.acrinta.client.module_a.view.ThisAppShell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ThisApp implements EntryPoint {
	ThisAppShell shell = GWT.create(ThisAppShell.class);
	LoginServiceAsync loginService = GWT.create(LoginService.class);

	public void onModuleLoad() {
		RootLayoutPanel rootLayoutPanel_1 = RootLayoutPanel.get();

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		rootLayoutPanel_1.add(verticalPanel);
		rootLayoutPanel_1.setWidgetTopHeight(verticalPanel, 0.0, Unit.PX, 37.0, Unit.PX);
		rootLayoutPanel_1.setWidgetLeftWidth(verticalPanel, 661.0, Unit.PX, 133.0, Unit.PX);
		verticalPanel.add(shell);

		shell.getLogoutLink().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginService.logout(new AsyncCallback() {
					public void onFailure(Throwable caught) {
					}

					public void onSuccess(Object result) {
						Window.alert("Log out complete");
					}
				});
				Window.Location.assign("/Default.html");
			}
		});

		shell.getContent().setWidget(new HTML(""));
		shell.getLogoutLink().setDirectionEstimator(true);
		shell.getLogoutLink().setName("logout");
		shell.getLogoutLink().setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ClientForm clientForm = new ClientForm();
		rootLayoutPanel_1.add(clientForm);
		rootLayoutPanel_1.setWidgetLeftWidth(clientForm, 18.0, Unit.PX, 477.0, Unit.PX);
		rootLayoutPanel_1.setWidgetTopHeight(clientForm, 82.0, Unit.PX, 417.0, Unit.PX);
		clientForm.setWidth("253px");
	}
}
