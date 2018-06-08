package pl.coderslab.app;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * {@link OddsCalculator} is a class containing various methods for generating odds of football matches.
 * All methods assume that we have data regarding strengths of football teams. Strength is a value from 0 to 1,
 * and is expected to be a value calculated for each team basing of earlier games e.g. 0.6 - means that a team scores
 * at least 1 goal in 60% matches (form historical.
 *
 * Some of this class methods include time parameter and current scores - for live bets.
 *
 * Statistical equations found at: https://stats.stackexchange.com/questions/168411/probability-of-a-team-scoring-a-goal
 * Implemented and adjusted by Karol Nurzynski
 *
 * TO DO: Change from double to BigDecimal
 */
@Component
public class OddsCalculator {

    /**
     * This method calculates the probability of goals from 0 to 4 goals,
     * based on team strength (probability of scoring at least 1 goal) and redistributes this probability into 5 events
     * using Poisson model - see {@link OddsCalculator} description
     *
     * @param strengthA is a strength of a team, which is the probability that team scores 1 or more goals.
     *                  Strength should be less than 1 and more than 0.
     * @return is the list of probailities of scoring 0, 1, 2, 3 and 4(or more) goals by the team
     */

    public static List<Double> calcProbabilityOfGoals(double strengthA) {
        double lambdaAB = -Math.log(1.0-strengthA);

        double probabilityAB;

        List<Double> listProbabilityAB = new ArrayList<>();

        double sumAB = 0;

        for (int i=0;i<4;i++) {
            probabilityAB = ((Math.exp(-lambdaAB))*(Math.pow(lambdaAB,i)))/ArithmeticUtils.factorial(i);
            listProbabilityAB.add(probabilityAB);
            sumAB += probabilityAB;
        }

        listProbabilityAB.add(1-sumAB);

        return listProbabilityAB;
    }

    //R


    /**
     * This method returns a map o results, where key is in format ab
     * (a = goals scored by b; b = goals scored by a) and values represents the probability of this event
     *
     * @param strengthA is the probability that teamA scores at least 1 goal (received values from 0 to 1)
     * @param strengthB is the probability that teamB scores at least 1 goal (received values from 0 to 1)
     * @return is a map of results e.g. key = 01, means game result teamA 0 : teamB 1;
     *          values is the probability of this result
     */
    public static Map<String,Double> calculateMapOfResultProbabilityAB (double strengthA, double strengthB) {

        List<Double> listProbabilityAscoresB = calcProbabilityOfGoals(strengthA);
        List<Double> listProbabilityBscoresA = calcProbabilityOfGoals(strengthB);

        Map<String,Double> mapOfResultProbability = new HashMap<>();

        for (int i=0;i<4;i++) {
            for(int j=0;j<=4;j++) {
                String resultCode = String.valueOf(i) + String.valueOf(j);
                double probabilityAiBj = listProbabilityAscoresB.get(i)*listProbabilityBscoresA.get(j);
                mapOfResultProbability.put(resultCode, probabilityAiBj);
            }
        }

        return mapOfResultProbability;
    }

    /**
     *
     * @param strengthA is the probability that teamA scores at least 1 goal (received values from 0 to 1)
     * @param strengthB strengthB is the probability that teamB scores at least 1 goal (received values from 0 to 1)
     * @return is a 3-element list, where first is the probability than team A wins, 2 - team B wins, 3 - draw
     */
    public static List<Double> calculateProbabilityAwinsBwinsDraw (double strengthA, double strengthB) {

        List<Double> listProbabilityAscoresB = calcProbabilityOfGoals(strengthA);
        List<Double> listProbabilityBscoresA = calcProbabilityOfGoals(strengthB);

        double aWinsSum = 0;
        double bWinsSum = 0;
        double drawSum = 0;

        for (int i=0;i<4;i++) {
            for(int j=0;j<=4;j++) {
                double probabilityAiBj = listProbabilityAscoresB.get(i)*listProbabilityBscoresA.get(j);

                if (i>j) {
                    aWinsSum += probabilityAiBj;
                } else if (j>i) {
                    bWinsSum += probabilityAiBj;
                } else {
                    drawSum += probabilityAiBj;
                }
            }
        }

        double totalProbabilities = aWinsSum + bWinsSum + drawSum;

        double aWinsProbability = aWinsSum / totalProbabilities;
        double bWinsProbability = bWinsSum / totalProbabilities;
        double drawProbability = 1.0 - aWinsProbability - bWinsProbability;

        if (drawProbability<0) {
            drawProbability = 0;
        }

        List<Double> listOfProbabilitiesAwinsBwinsDraw = new ArrayList<>();
        listOfProbabilitiesAwinsBwinsDraw.add(aWinsProbability);
        listOfProbabilitiesAwinsBwinsDraw.add(bWinsProbability);
        listOfProbabilitiesAwinsBwinsDraw.add(drawProbability);

        return listOfProbabilitiesAwinsBwinsDraw;
    }

    /**
     * This method is similar to {@link OddsCalculator#calculateProbabilityAwinsBwinsDraw(double, double)} but this one
     *  includes to additional parameters - time and current game result (score A and score B)
     *
     * @param strengthA is the probability that teamA scores at least 1 goal (received values from 0 to 1)
     * @param strengthB strengthB is the probability that teamB scores at least 1 goal (received values from 0 to 1
     * @param timeToEndRatio is the ration of time left, where 1 is full time (normally 90 minutes); 0.5 would mean 45minutes left
     * @param scoreA is number of goals scored by team A for which probability is calculated
     * @param scoreB is number of goals scored by team B for which probability is calculated
     * @return is a 3-element list of probabilities, where first is the probability that team A wins, second - team B wins,
     *          third - draw
     */
    public static List<Double> calculateProbabilityAwinsBwinsDrawInclTimeAndCurrentResult (double strengthA, double strengthB, double timeToEndRatio, int scoreA, int scoreB) {

        List<Double> listProbabilityAscoresBInclTime = calcProbabilityOfGoals(strengthA*timeToEndRatio);
        List<Double> listProbabilityBscoresAInclTime = calcProbabilityOfGoals(strengthB*timeToEndRatio);

        List<Double> listProbabilityAscoresB = new ArrayList<>();
        List<Double> listProbabilityBscoresA = new ArrayList<>();

        listProbabilityAscoresB.add(Double.valueOf(scoreA));
        listProbabilityAscoresB.addAll(listProbabilityAscoresBInclTime);

        listProbabilityBscoresA.add(Double.valueOf(scoreB));
        listProbabilityBscoresA.addAll(listProbabilityBscoresAInclTime);

        double aWinsSum = 0;
        double bWinsSum = 0;
        double drawSum = 0;

        for (int i=0;i<4;i++) {
            for(int j=0;j<=4;j++) {
                double probabilityAiBj = listProbabilityAscoresB.get(i)*listProbabilityBscoresA.get(j);

                if (i>j) {
                    aWinsSum += probabilityAiBj;
                } else if (j>i) {
                    bWinsSum += probabilityAiBj;
                } else {
                    drawSum += probabilityAiBj;
                }
            }
        }

        double totalProbabilities = aWinsSum + bWinsSum + drawSum;

        double aWinsProbability = aWinsSum / totalProbabilities;
        double bWinsProbability = bWinsSum / totalProbabilities;
        double drawProbability = 1.0 - aWinsProbability - bWinsProbability;

        if (drawProbability<0) {
            drawProbability = 0;
        }

        List<Double> listOfProbabilitiesAwinsBwinsDraw = new ArrayList<>();
        listOfProbabilitiesAwinsBwinsDraw.add(aWinsProbability);
        listOfProbabilitiesAwinsBwinsDraw.add(bWinsProbability);
        listOfProbabilitiesAwinsBwinsDraw.add(drawProbability);

        return listOfProbabilitiesAwinsBwinsDraw;
    }

    //Method helping to calc ration of time left from LocalTime

    /**
     * This is a method helping in calculation of time to end of game ratio.
     *
     * @param totalTime is total game time, for football mathces normally 90 minutes
     * @param leftTime is time left to game end - e.g.45 minutes left
     * @return is the ration of time to end e.g. 45/90 = 0.5
     */
    public static double calcTimeToEndRation (LocalTime totalTime, LocalTime leftTime) {
        double secondsTotal = totalTime.getHour()*3600 + totalTime.getMinute()*60 + totalTime.getSecond();
        double secondsLeft = leftTime.getHour()*3600 + leftTime.getMinute()*60 + leftTime.getSecond();

        if (secondsLeft!=0) {
            return secondsLeft / secondsTotal;
        } else {
            return 0;
        }
    }

}
