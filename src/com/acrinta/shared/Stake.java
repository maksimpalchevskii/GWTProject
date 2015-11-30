package com.acrinta.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Stake implements IsSerializable {
	private long id;
	private int horseNumber;
	private int summ;
	private int raceType;
	
	
	public long getId(){
		return id;
	}
	
	public int getRaceType(){
		return raceType;
	}
	
	public int getHorseNumber(){
		return horseNumber;
	}
	
	public int getSumm(){
		return summ;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public void setRaceType(int raceType){
		this.raceType = raceType;
	}
	
	public void setHorseNumber(Integer horseNumber){
		this.horseNumber = horseNumber;
	}
	
	public void setSumm(double winSumm){
		this.summ = (int) winSumm;
	}
}
