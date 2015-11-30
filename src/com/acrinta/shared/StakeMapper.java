package com.acrinta.shared;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface StakeMapper {
	
	@Insert ("INSERT INTO stakes (raceType,winnerHorse1,bookmakerProfit,allStakesSumm) VALUES (#{param1},#{param2},#{param3},#{param4})")
	void setRaceResult(int raceType, int winnerHorse1, int bookmakerProfit, int allStakesSumm);
	
	@Select ("SELECT raceType, winnerHorse1, bookmakerProfit, allStakesSumm FROM stakes ORDER BY raceID DESC LIMIT #{param1}, 5")
	List<Result> getRaceData (int raceId);
}
