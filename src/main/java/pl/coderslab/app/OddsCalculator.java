package pl.coderslab.app;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Statistical equations found at: https://stats.stackexchange.com/questions/168411/probability-of-a-team-scoring-a-goal
// Implemented and adjusted by Karol Nurzynski
// TO DO: Change from double to BigDecimal

@Component
public class OddsCalculator {

    //Calculates probability that team A (with strength A) scores 0,1,2 or 3(and more) goals. Return is a list of probability summing to 1.
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

    //Returns a map o results, where key is in format ab (a = goals scored by b; b = goals scored by a) and values represents the probability of this event
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

    //Timetodenratio 0.3 means 0.3xnormal time is left
    public static List<Double> calculateProbabilityAwinsBwinsDrawInclTimeAndCurrentResult (double strengthA, double strengthB, double timeToEndRatio, int scoreA, int scoreB) {

        List<Double> listProbabilityAscoresBInclTime = calcProbabilityOfGoals(strengthA*timeToEndRatio);
        List<Double> listProbabilityBscoresAInclTime = calcProbabilityOfGoals(strengthB*timeToEndRatio);

        List<Double> listProbabilityAscoresB = new ArrayList<>();
        List<Double> listProbabilityBscoresA = new ArrayList<>();

        listProbabilityAscoresB.add(Double.valueOf(scoreA));
        listProbabilityAscoresB.addAll(listProbabilityAscoresBInclTime);

        listProbabilityBscoresA.add(Double.valueOf(scoreB));
        listProbabilityBscoresA.addAll(listProbabilityBscoresAInclTime);

//        for (Double element:listProbabilityAscoresBInclTime) {
//            listProbabilityAscoresB.add(element + scoreA);
//        }
//
//        for (Double element:listProbabilityBscoresAInclTime) {
//            listProbabilityBscoresA.add(element + scoreB);
//        }

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

//    public static List<Double> calculateProbabilityAwinsBwinsDrawIncludingTimeAndGoals (double strengthA, double strengthB, double timeToEndRation, int scoreA, int scoreB) {
////
////        Map<String,Double> mapOfResults = calculateMapOfResultProbabilityAB(strengthA, strengthB);
////        Map<String,Double> updatedMapOfResults = new HashMap<>();
////
////        for (Map.Entry<String, Double> element : mapOfResults.entrySet()) {
////            String key = element.getKey() + scoreA;
////            Double value = element.getValue();
////            updatedMapOfResults.put(key, value);
////        }
//
//        List<Double> oldListProbabilityBscoresA = calcProbabilityOfGoals(strengthA);
//        List<Double> oldListProbabilityAscoresB = calcProbabilityOfGoals(strengthB);
//
//        List<Double> listProbabilityBscoresA = new ArrayList<>();
//        List<Double> listProbabilityAscoresB = new ArrayList<>();
//
//        for (Double element:oldListProbabilityAscoresB) {
//            listProbabilityAscoresB
//        }
//
//
//        double aWinsSum = 0;
//        double bWinsSum = 0;
//        double drawSum = 0;
//
//        for (int i=0;i<4;i++) {
//            for(int j=0;j<=4;j++) {
//                double probabilityAiBj = listProbabilityBscoresA.get(i)*listProbabilityAscoresB.get(j);
//
//                if (i>j) {
//                    aWinsSum += probabilityAiBj;
//                } else if (j>i) {
//                    bWinsSum += probabilityAiBj;
//                } else {
//                    drawSum += probabilityAiBj;
//                }
//            }
//        }
//
//        double totalProbabilities = aWinsSum + bWinsSum + drawSum;
//
//        double aWinsProbability = aWinsSum / totalProbabilities;
//        double bWinsProbability = bWinsSum / totalProbabilities;
//        double drawProbability = 1.0 - aWinsProbability - bWinsProbability;
//
//        if (drawProbability<0) {
//            drawProbability = 0;
//        }
//
//        List<Double> listOfProbabilitiesAwinsBwinsDraw = new ArrayList<>();
//        listOfProbabilitiesAwinsBwinsDraw.add(aWinsProbability);
//        listOfProbabilitiesAwinsBwinsDraw.add(bWinsProbability);
//        listOfProbabilitiesAwinsBwinsDraw.add(drawProbability);
//
//        return listOfProbabilitiesAwinsBwinsDraw;
//    }

    //Method helping to calc ration of time left from LocalTime
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
