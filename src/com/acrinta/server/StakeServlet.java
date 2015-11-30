package com.acrinta.server;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.acrinta.client.module_a.StakeService;
import com.acrinta.shared.Result;
import com.acrinta.shared.Stake;
import com.acrinta.shared.StakeMapper;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class StakeServlet extends RemoteServiceServlet implements StakeService {

	static final Logger logger = LogManager.getLogger(StakeServlet.class.getName());

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	final static double bookmakerPart = 0.25;
	private static int horse;
	private static int winnerHorse1;
	private static int winnerHorse2;
	private static int winnerHorse3;
	private static double winSumm;
	private static float winnersSumm;
	private static int allStakesSumm;
	private static SqlSessionFactory sqlSessionFactory;

	static {
		try {
			Reader resourceReader = Resources.getResourceAsReader("resources/config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceReader);
			sqlSessionFactory.getConfiguration().addMapper(StakeMapper.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int sendStake(long uuid, int raceType, int horseNumber, int summ) throws IllegalArgumentException {

		// TODO logic
		Stake stake = new Stake();
		stake.setId(uuid);
		stake.setHorseNumber(horseNumber);
		stake.setSumm(summ);

		List<Stake> stakeSummary = new ArrayList<Stake>();
		List<Stake> winnerStake = new ArrayList<Stake>();

		stakeSummary.add(stake);
		winnerStake.add(stake);

		// Getting winner horse
		getWinner(raceType);

		// adding 30 other stakes with horse Number, bounds of which are
		// specified by raceType

		for (int i = 0; i < 30; i++) {
			Random rand = new Random();
			switch (raceType) {
			case 1:
				horse = 1 + rand.nextInt(4);
				break;
			case 2:
				horse = 1 + rand.nextInt(6);
				break;
			case 3:
				horse = 1 + rand.nextInt(8);
				break;
			}
			stake.setId(uuid);
			stake.setHorseNumber(horse);
			int newStakeSumm = summ / 2 + summ / 5 * rand.nextInt(10);
			stake.setSumm(newStakeSumm);
			stakeSummary.add(stake);
			allStakesSumm = allStakesSumm + newStakeSumm;
			
			

			if (stake.getHorseNumber() == winnerHorse1) {
				Stake winnerStakeEntity = new Stake();
				winnerStakeEntity.setHorseNumber(winnerHorse1);
				winnerStakeEntity.setSumm(newStakeSumm);
				winnerStake.add(winnerStakeEntity);
				winnersSumm = winnersSumm + newStakeSumm;
			}

			if (stake.getHorseNumber() == winnerHorse2) {
				Stake winnerStakeEntity = new Stake();
				winnerStakeEntity.setHorseNumber(winnerHorse2);
				winnerStakeEntity.setSumm(newStakeSumm);
				winnerStake.add(winnerStakeEntity);
				winnersSumm = winnersSumm + newStakeSumm;
			}

			if (stake.getHorseNumber() == winnerHorse3) {
				Stake winnerStakeEntity = new Stake();
				winnerStakeEntity.setHorseNumber(winnerHorse3);
				winnerStakeEntity.setSumm(newStakeSumm);
				winnerStake.add(winnerStakeEntity);
				winnersSumm = winnersSumm + newStakeSumm;
			}
		}

		logger.info("\n" + allStakesSumm + " allStakesSumm");
		logger.info(winnersSumm + " winnersSumm");

		float coefficient = allStakesSumm / winnersSumm;

		logger.info("coefficient = " + coefficient);

		switch (raceType) {

		case 1:
			if (horseNumber == winnerHorse1) {
				winSumm = summ * coefficient * (1.00 - bookmakerPart);
				logger.info("Your stake was " + summ + ", You win " + (int) winSumm + " excellent job!");
				break;
			} else {
				logger.info("Sorry that happened, you win nothing : (");
				break;
			}

		case 2:
			if (horseNumber == winnerHorse2 | horseNumber == winnerHorse1) {
				if (horseNumber == winnerHorse1) {
					winSumm = summ * coefficient * (1.00 - bookmakerPart);
					logger.info("Your stake was " + summ + ", You win " + (int) winSumm + " excellent job!");
					break;
				} else {
					winSumm = summ * coefficient * (1.00 - bookmakerPart) / 1.2;
					logger.info("Your stake was " + summ + ", You win " + (int) winSumm + " excellent job!");
					break;
				}
			} else {
				logger.info("Sorry that happened, you win nothing : (");
				break;
			}

		case 3:
			if (horseNumber == winnerHorse3 | horseNumber == winnerHorse2 | horseNumber == winnerHorse1) {
				if (horseNumber == winnerHorse1) {
					winSumm = summ * coefficient * (1.00 - bookmakerPart);
					logger.info("Your stake was " + summ + ", You win " + (int) winSumm + " excellent job!");
					break;
				}
				if (horseNumber == winnerHorse2) {
					winSumm = summ * coefficient * (1.00 - bookmakerPart) / 1.2;
					logger.info("Your stake was " + summ + ", You win " + (int) winSumm + " excellent job!");
					break;
				} else {
					winSumm = summ * coefficient * (1.00 - bookmakerPart) / 1.5;
					logger.info("Your stake was " + summ + ", You win " + (int) winSumm + " excellent job!");
					break;
				}
			} else {
				logger.info("Sorry that happened, you win nothing : (" + winnersSumm);
				break;
			}
		}

		int bookmakerProfit = (int) (allStakesSumm * bookmakerPart);

		// TODO all this stuff is going to DB
		// TODO add Date for DB table DATE and even maybe ID as long UUID

		setRaceResultToDB(raceType, winnerHorse1, bookmakerProfit, allStakesSumm);

		// @return 0 if you lost
		// @return winSumm if you won

		if (winSumm == 0.0) {
			return 0;
		} else {
			int result = (int) winSumm;
			winSumm = 0.0;
			return result;
		}
	}

	private void setRaceResultToDB(Integer raceType, Integer winnerHorse1, Integer bookmakerProfit,
			Integer allStakesSumm) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			StakeMapper mapper = session.getMapper(StakeMapper.class);

			mapper.setRaceResult(raceType, winnerHorse1, bookmakerProfit, allStakesSumm);
			session.commit();
		} finally {
			session.close();
		}
	}

	public static void getWinner(int raceType) {
		Random rnd = new Random();

		switch (raceType) {
		case 1:
			winnerHorse1 = 1 + rnd.nextInt(4);
			logger.info("horse " + winnerHorse1 + " wins");
			break;
		case 2:
			winnerHorse1 = 1 + rnd.nextInt(6);
			winnerHorse2 = 1 + rnd.nextInt(6);
			while (winnerHorse2 == winnerHorse1) {
				winnerHorse2 = 1 + rnd.nextInt(6);
			}
			logger.info("horse " + winnerHorse1 + " wins");
			logger.info("horse " + winnerHorse2 + " came second");
			break;
		case 3:
			winnerHorse1 = 1 + rnd.nextInt(8);
			winnerHorse2 = 1 + rnd.nextInt(8);
			winnerHorse3 = 1 + rnd.nextInt(8);
			while (winnerHorse2 == winnerHorse1) {
				winnerHorse2 = 1 + rnd.nextInt(8);
			}
			while (winnerHorse3 == winnerHorse1 | winnerHorse3 == winnerHorse2) {
				winnerHorse3 = 1 + rnd.nextInt(8);
			}
			logger.info("horse " + winnerHorse1 + " wins");
			logger.info("horse " + winnerHorse2 + " came second");
			logger.info("horse " + winnerHorse3 + " came third");
			break;
		}
	}

	@Override
	public List<Result> getRaceResultfromDB(int raceId) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			StakeMapper mapper = session.getMapper(StakeMapper.class);
			List<Result> result = mapper.getRaceData(raceId);
			// org.apache.log4j.BasicConfigurator.configure();
			mapper.getRaceData(raceId);
			logger.info(" " + raceId + " " + " result size" + result.size());
			return result;
		} finally {
			session.close();
		}
	}
}
