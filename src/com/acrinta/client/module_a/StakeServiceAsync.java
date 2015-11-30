package com.acrinta.client.module_a;

import java.util.List;

import com.acrinta.shared.Result;
import com.acrinta.shared.Stake;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface StakeServiceAsync {
	void sendStake(long uuid, int raceType, int horseNumber, int summ, AsyncCallback<Integer> callback);
	
	void getRaceResultfromDB(int raceId, AsyncCallback<List<Result>> callback);
}