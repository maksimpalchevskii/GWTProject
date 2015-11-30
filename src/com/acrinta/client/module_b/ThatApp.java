package com.acrinta.client.module_b;

import java.util.LinkedList;
import java.util.List;

import org.apache.tools.ant.types.selectors.ExtendSelector;

import com.acrinta.client.login.request.LoginService;
import com.acrinta.client.login.request.LoginServiceAsync;
import com.acrinta.client.module_a.StakeService;
import com.acrinta.client.module_a.StakeServiceAsync;
import com.acrinta.client.module_b.view.ThatAppShell;
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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

public class ThatApp implements EntryPoint{
	
	LinkedList<Result> list = new LinkedList<>();
	private final StakeServiceAsync stakeService = GWT.create(StakeService.class);
	ThatAppShell shell = GWT.create(ThatAppShell.class);
	LoginServiceAsync loginService = GWT.create(LoginService.class);

	//CellTableGrid cellTableGrid = new CellTableGrid();
	private CellTable<Result> resultTable;

	public void onModuleLoad(){
		resultTable = new CellTable<>();
		resultTable.setWidth("100%", true);
		resultTable.setPageSize(20);
		//CellTable<>(); table = new CellTable<>();
		TextColumn<Result> firstColumn = new TextColumn<Result>() {
			@Override
			public String getValue(Result object) {
				return Integer.toString(object.getRaceType());
			}
		};
		resultTable.addColumn(firstColumn, "Race type");

		TextColumn<Result> secondColumn = new TextColumn<Result>() {
			@Override
			public String getValue(Result object) {
				return Integer.toString(object.getWinnerHorse1());
			}
		};
		resultTable.addColumn(secondColumn, "Winner Horse");

		TextColumn<Result> thirdColumn = new TextColumn<Result>() {
			@Override
			public String getValue(Result object) {
				return Integer.toString((int)object.getFullStakeSumm());
			}
		};
		resultTable.addColumn(thirdColumn, "All stakes summary");

		TextColumn<Result> fourthColumn = new TextColumn<Result>() {
			@Override
			public String getValue(Result object) {
				return Integer.toString((int)object.getBookmakerProfit());
			}
		};
		resultTable.addColumn(fourthColumn, "Bookmaker profit");


		resultTable.setRowCount(20);
		
		getRows(0);

		final ListDataProvider<Result> dataProvider = new ListDataProvider<Result>();
		dataProvider.addDataDisplay(resultTable);
	//	Window.alert("dataprovider " + dataProvider.toString());

		final SingleSelectionModel<Result> selectionModel = new SingleSelectionModel<Result>();
		resultTable.setSelectionModel(selectionModel);

		
		
		Button btn = new Button("delete entry");
		btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Result selected = selectionModel.getSelectedObject();
				if (selected != null) {
					dataProvider.getList().remove(selected);
										
					getRows(0);
				}
			}
		});
		
		RootPanel.get().add(resultTable);
		RootPanel.get().add(btn);	
	}


	protected void getRows(int raceId) {
		stakeService.getRaceResultfromDB(raceId, new AsyncCallback<List<Result>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(List<Result> result) {
					resultTable.setRowCount(result.size());
					resultTable.setRowData(0, result);	
			}
		});
	}
}

