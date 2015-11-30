package com.acrinta.client.module_a;

import java.util.List;

import com.acrinta.shared.Result;
import com.acrinta.shared.Stake;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("stake")
public interface StakeService extends RemoteService {
	int sendStake(long uuid, int raceType, int horseNumber, int summ) throws IllegalArgumentException;
	List<Result> getRaceResultfromDB (int raceId);
}

