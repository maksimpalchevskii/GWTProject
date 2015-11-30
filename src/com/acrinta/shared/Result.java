package com.acrinta.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Result implements IsSerializable {
	
	private int raceType;
	private float  allStakesSumm;
	private int winnerHorse1;
	private int winnerHorse2;
	private int winnerHorse3;
	private float bookmakerProfit;
	private int winSumm;
	
	public int getRaceType() {
		return raceType;
	}
	public void setRaceType(int raceType) {
		this.raceType = raceType;
	}
	public float getFullStakeSumm() {
		return allStakesSumm;
	}
	public void setaAllStakesSumm(float allStakesSumm) {
		this.allStakesSumm = allStakesSumm;
	}
	public int getWinnerHorse1() {
		return winnerHorse1;
	}
	public void setWinnerHorse1(int winnerHorse1) {
		this.winnerHorse1 = winnerHorse1;
	}
	public int getWinnerHorse2() {
		return winnerHorse2;
	}
	public void setWinnerHorse2(int winnerHorse2) {
		this.winnerHorse2 = winnerHorse2;
	}
	public int getWinnerHorse3() {
		return winnerHorse3;
	}
	public void setWinnerHorse3(int winnerHorse3) {
		this.winnerHorse3 = winnerHorse3;
	}
	public float getBookmakerProfit() {
		return bookmakerProfit;
	}
	public void setBookmakerProfit(float bookmakerProfit) {
		this.bookmakerProfit = bookmakerProfit;
	}
	public int getWonSumm() {
		return winSumm;
	}

	public void setWonSumm(int winSumm) {
		this.winSumm = winSumm;
	}
	
}
