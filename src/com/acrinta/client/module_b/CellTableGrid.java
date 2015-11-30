package com.acrinta.client.module_b;

import java.util.LinkedList;
import java.util.List;

import com.acrinta.client.module_a.StakeService;
import com.acrinta.client.module_a.StakeServiceAsync;
import com.acrinta.shared.Result;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

public class CellTableGrid extends Composite {

	LinkedList<Row> list = new LinkedList<Row>();

	private final StakeServiceAsync stakeService = GWT.create(StakeService.class);

	public CellTableGrid() {
		
		
		final CellTable<Row> table = new CellTable<Row>();
		TextColumn<Row> firstColumn = new TextColumn<Row>() {
			@Override
			public String getValue(Row object) {
				return object.firstColumn;
			}
		};
		table.addColumn(firstColumn, "header one");

		TextColumn<Row> secondColumn = new TextColumn<Row>() {
			@Override
			public String getValue(Row object) {
				return object.secondColumn;
			}
		};
		table.addColumn(secondColumn, "header two");

		TextColumn<Row> thirdColumn = new TextColumn<Row>() {
			@Override
			public String getValue(Row object) {
				return object.thirdColumn;
			}
		};
		table.addColumn(thirdColumn, "header three");

		TextColumn<Row> fourthColumn = new TextColumn<Row>() {
			@Override
			public String getValue(Row object) {
				return object.fourthColumn;
			}
		};
		table.addColumn(fourthColumn, "header four");

		// getRows(1);
		table.setRowCount(20);
		final ListDataProvider<Row> dataProvider = new ListDataProvider<Row>(getRows(1));
		dataProvider.addDataDisplay(table);

		final SingleSelectionModel<Row> selectionModel = new SingleSelectionModel<Row>();
		table.setSelectionModel(selectionModel);

		Button btn = new Button("delete entry");
		btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Row selected = selectionModel.getSelectedObject();
				if (selected != null) {
					dataProvider.getList().remove(selected);
				}
			}
		});

		RootPanel.get().add(table);
		RootPanel.get().add(btn);

		// Window.alert("list = " + list.size());

	}

	private class Row {
		private String firstColumn;
		private String secondColumn;
		private String thirdColumn;
		private String fourthColumn;

		public Row(String firstColumn, String secondColumn, String thirdColumn, String fourthColumn) {
			this.firstColumn = firstColumn;
			this.secondColumn = secondColumn;
			this.thirdColumn = thirdColumn;
			this.fourthColumn = fourthColumn;

		}
	}

	protected LinkedList<Row> getRows(int raceId) {
		stakeService.getRaceResultfromDB(raceId, new AsyncCallback<List<Result>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(List<Result> result) {
				// getList();
				for (int i = 0; i < result.size(); i++) {
					list.add(new Row(Integer.toString(result.get(i).getRaceType()),
					Integer.toString(result.get(i).getWinnerHorse1()),
					Float.toString(result.get(i).getBookmakerProfit()),
					Float.toString(result.get(i).getFullStakeSumm())));
					
					Window.alert("Race type = " + Integer.toString(result.get(i).getRaceType()));
					Window.alert("Winner Horse " + Integer.toString(result.get(i).getWinnerHorse1()));
					Window.alert("Get Full Stake Summ " + Float.toString(result.get(i).getFullStakeSumm()));
					Window.alert("bookmaker Profit " + Float.toString(result.get(i).getBookmakerProfit()));
					
					Window.alert(list.toString());
				}
			}
		});
		return list;
	}
}
