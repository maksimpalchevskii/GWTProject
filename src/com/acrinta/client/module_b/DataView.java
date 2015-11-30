package com.acrinta.client.module_b;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class DataView extends Composite {

	public DataView() {

		Grid grid = new Grid(4, 5);
		grid.setStyleName("w3-bordered tr,.w3-table-all tr");
		grid.setBorderWidth(1);
		
		initWidget(grid);

		// TODO ADD Race Type from DB!
		Label lblNewLabel_1 = new Label("Race Type");
		grid.setWidget(0, 0, lblNewLabel_1);
		grid.setText(1, 0, "Race Type 1");
		grid.setText(2, 0, "Race Type 2");
		grid.setText(3, 0, "Race Type 3");

		// TODO ADD winners from DB!
		Label lblNewLabel_2 = new Label("Winners");
		grid.setWidget(0, 1, lblNewLabel_2);
		grid.setText(1, 1, "Winners 1");
		grid.setText(2, 1, "Winners 2");
		grid.setText(3, 1, "Winners 3");

		// TODO ADD Total Summ from DB
		Label lblNewLabel_3 = new Label("Total money");
		grid.setWidget(0, 2, lblNewLabel_3);
		grid.setText(1, 2, "Summ 1");
		grid.setText(2, 2, "Summ 2");
		grid.setText(3, 2, "Summ 3");

		Label lblNewLabel_4 = new Label("Bookmaker's profit");
		grid.setWidget(0, 3, lblNewLabel_4);
		grid.setText(1, 3, "Profit 1");
		grid.setText(2, 3, "Profit 2");
		grid.setText(3, 3, "Profit 3");
	//	grid.setVisible(, false);

		Label lblNewLabel_5 = new Label("Remove / Edit");
		grid.setWidget(0, 4, lblNewLabel_5);

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		grid.setWidget(1, 4, verticalPanel);
		verticalPanel.setSize("95px", "95px");

		TextButton txtbtnNewButton = new TextButton("Edit");
		verticalPanel.add(txtbtnNewButton);
		txtbtnNewButton.setWidth("60px");

		TextButton txtbtnNewButton1 = new TextButton("Delete");
		verticalPanel.add(txtbtnNewButton1);
		txtbtnNewButton1.setWidth("60px");

		VerticalPanel verticalPanel_1 = new VerticalPanel();
		verticalPanel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		grid.setWidget(2, 4, verticalPanel_1);
		verticalPanel_1.setSize("95px", "95px");
		TextButton txtbtnNewButton3 = new TextButton("Edit");

		verticalPanel_1.add(txtbtnNewButton3);
		txtbtnNewButton3.setWidth("60px");
		TextButton txtbtnNewButton4 = new TextButton("Delete");
		verticalPanel_1.add(txtbtnNewButton4);
		txtbtnNewButton4.setWidth("60px");

		VerticalPanel verticalPanel_2 = new VerticalPanel();
		verticalPanel_2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		grid.setWidget(3, 4, verticalPanel_2);
		verticalPanel_2.setSize("95px", "95px");
		TextButton txtbtnNewButton5 = new TextButton("Edit");
		verticalPanel_2.add(txtbtnNewButton5);
		txtbtnNewButton5.setWidth("60px");

		TextButton txtbtnNewButton6 = new TextButton("Delete");
		verticalPanel_2.add(txtbtnNewButton6);
		txtbtnNewButton6.setWidth("60px");
	}
}
