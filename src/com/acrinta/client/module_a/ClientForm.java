package com.acrinta.client.module_a;

import java.io.IOException;
import java.io.Reader;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.acrinta.shared.StakeMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.VerticalPanel;


public class ClientForm extends Composite {

	private final StakeServiceAsync stakeService = GWT.create(StakeService.class);
	private IntegerBox summ;
	private long uuid;
	ListBox horseNum = new ListBox();
	ListBox raceType = new ListBox();
	FormPanel form = new FormPanel();
	VerticalPanel base = new VerticalPanel();
	SimplePanel simplePanel = new SimplePanel();

	public IntegerBox getSumm() {
		return summ;
	}
	

	public ClientForm() {

		Random rand = new Random();
		uuid = rand.nextLong();

		// Create a FormPanel and point it at a service.
		//final VerticalPanel base = new VerticalPanel();
		initWidget(base);
		base.setSize("870px", "535px");

		//final SimplePanel simplePanel = new SimplePanel();
		simplePanel.setStyleName("h3");
		base.add(simplePanel);

		//FormPanel form = new FormPanel();
		base.add(form);
		base.setCellHorizontalAlignment(form, HasHorizontalAlignment.ALIGN_CENTER);
		form.setSize("384px", "233px");
		form.setStyleName("gwt-Label-Login");
		form.setAction("/myFormHandler");

		// Because we're going to add a FileUpload widget, we'll need to set the
		// form to use the POST method, and multipart MIME encoding.
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		// Create a panel to hold all of the form widgets.
		VerticalPanel panel = new VerticalPanel();
		panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		panel.setStyleName("gwt-Label-Login");
		form.setWidget(panel);
		panel.setSize("211px", "206px");

		Label lblNewLabel_2 = new Label("Please, land your stake!");
		lblNewLabel_2.setStyleName("h3");
		panel.add(lblNewLabel_2);

		VerticalPanel verticalPanel_2 = new VerticalPanel();
		panel.add(verticalPanel_2);

		// Create a ListBox, giving it a name and some values to be associated
		// with
		// its options.

		verticalPanel_2.add(raceType);
		verticalPanel_2.setCellHorizontalAlignment(raceType, HasHorizontalAlignment.ALIGN_CENTER);
		raceType.setStyleName("gwt-Label-Login");
		raceType.setName("listBoxFormElement");
		raceType.addItem("Set race type:");
		raceType.addItem("Simple race (4 horses)", "simpleRaceValue");
		raceType.addItem("Big Challenge (6 horses, 2 winners)", "bigChallengeValue");
		raceType.addItem("Ultra Race (10 horses, 3 winners)", "ultraChallengeValue");
		raceType.setWidth("160px");

		VerticalPanel verticalPanel = new VerticalPanel();
		panel.add(verticalPanel);

		Label lblNewLabel = new Label("Predict winner:");
		verticalPanel.add(lblNewLabel);
		verticalPanel.setCellHorizontalAlignment(lblNewLabel, HasHorizontalAlignment.ALIGN_CENTER);
		lblNewLabel.setSize("178px", "19px");
		lblNewLabel.setStyleName("gwt-Label-Login");

		verticalPanel.add(horseNum);
		verticalPanel.setCellHorizontalAlignment(horseNum, HasHorizontalAlignment.ALIGN_CENTER);
		horseNum.setStyleName("gwt-Label-Login");
		horseNum.setName("Choose horse");
		
		horseNum.addItem("Predict winning horse");
		horseNum.addItem("1", "horse1");
		horseNum.addItem("2", "horse2");
		horseNum.addItem("3", "horse3");
		horseNum.addItem("4", "horse4");
		horseNum.addItem("5", "horse5");
		horseNum.addItem("6", "horse6");
		horseNum.addItem("7", "horse7");
		horseNum.addItem("8", "horse8");
		horseNum.addItem("9", "horse9");
		horseNum.addItem("10", "horse10");
		
		for (int i = 10; i > 1; i--) {
			horseNum.getElement().<SelectElement> cast().getOptions().getItem(i).setDisabled(true);
		}

		raceType.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				if (raceType.getSelectedIndex() == 1) {
					horseNum.setSelectedIndex(1);
					for (int i = 10; i > 4; i--) {
						horseNum.getElement().<SelectElement> cast().getOptions().getItem(i).setDisabled(true);
					}
					for (int i = 4; i > 1; i--) {
						horseNum.getElement().<SelectElement> cast().getOptions().getItem(i).setDisabled(false);
					}
				}
				if (raceType.getSelectedIndex() == 2) {
					horseNum.setSelectedIndex(1);
					for (int i = 10; i > 6; i--) {
						horseNum.getElement().<SelectElement> cast().getOptions().getItem(i).setDisabled(true);
					}
					for (int i = 6; i > 1; i--) {
						horseNum.getElement().<SelectElement> cast().getOptions().getItem(i).setDisabled(false);
					}
				}
				if (raceType.getSelectedIndex() == 3) {
					horseNum.setSelectedIndex(1);
					for (int i = 10; i > 1; i--) {
						horseNum.getElement().<SelectElement> cast().getOptions().getItem(i).setDisabled(false);
					}
				}
			}
		});

		raceType.setWidth("160px");

		VerticalPanel verticalPanel_1 = new VerticalPanel();
		panel.add(verticalPanel_1);

		Label lblNewLabel_1 = new Label("Specify summ:");
		verticalPanel_1.add(lblNewLabel_1);
		verticalPanel_1.setCellHorizontalAlignment(lblNewLabel_1, HasHorizontalAlignment.ALIGN_CENTER);
		lblNewLabel_1.setStyleName("gwt-Label-Login");
		summ = new IntegerBox();
		verticalPanel_1.add(summ);
		verticalPanel_1.setCellHorizontalAlignment(summ, HasHorizontalAlignment.ALIGN_CENTER);
		summ.setStyleName("gwt-Label-Login");
		SubmitButton submitButton = new SubmitButton();

		submitButton.setText("Fire Stake!");
		submitButton.setStyleName("gwt-Label-Login");
		panel.add(submitButton);
		panel.setCellHorizontalAlignment(submitButton, HasHorizontalAlignment.ALIGN_RIGHT);

		form.addSubmitHandler(new SubmitHandler() {
			public void onSubmit(SubmitEvent event) {
				if (horseNum.getSelectedIndex() == 0 | summ.getText().equals("") | raceType.getSelectedIndex() == 0) {
					Window.alert("Specify Horse number and Stake summ");
				} else {
					submit(raceType.getSelectedIndex(), horseNum.getSelectedIndex(), summ.getValue());
					// Window.alert("Stake accepted");
				}
				// TODO add several actions
				raceType.setItemSelected(0, true);
				horseNum.setTabIndex(0);
				summ.setValue(null);
			}
		});
	}

	protected void submit(int raceType, int horseNumber, Integer summ) {
		stakeService.sendStake(uuid, raceType, horseNumber, summ, new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO That ... was a mistake!
				Window.alert("Stake rejected " + caught.getMessage());
			}

			@Override
			public void onSuccess(Integer result) {
				if (result != 0) {
				Window.alert("Stake accepted, " + "you win " + result + " dollars");
				}
				else Window.alert("You failed in predicting horse, sorry :( Better luck next time");
			}
		});
	}
}