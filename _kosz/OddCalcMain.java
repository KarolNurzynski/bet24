package pl.coderslab.app;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.BetOffer;
import pl.coderslab.entity.Event;
import pl.coderslab.serviceImpl.OddsServiceImpl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Statistical equations found at: https://stats.stackexchange.com/questions/168411/probability-of-a-team-scoring-a-goal
// Implemented and adjusted by Karol Nurzynski
// TO DO: Change from double to BigDecimal
public class OddCalcMain {


//    public static double strengthA = 0.6;
//
//    public static double strengthB = 0.2;
//
//    public static List<Double> calcProbabilityOfGoals(double strengthA) {
//
//        double lambdaAB = -Math.log(1.0-strengthA);
//
//        double probabilityAB;
//
//        List<Double> listProbabilityAB = new ArrayList<>();
//
//        double sumAB = 0;
//
//        for (int i=0;i<4;i++) {
//            probabilityAB = ((Math.exp(-lambdaAB))*(Math.pow(lambdaAB,i)))/ArithmeticUtils.factorial(i);
//            listProbabilityAB.add(probabilityAB);
//            sumAB += probabilityAB;
//        }
//
//        listProbabilityAB.add(1-sumAB);
//
//        return listProbabilityAB;
//    }
//
//    public static Map<String,Double> calculateMapOfResultProbabilityAB (double strengthA, double strengthB) {
//
//        List<Double> listProbabilityAscoresB = calcProbabilityOfGoals(strengthA);
//        List<Double> listProbabilityBscoresA = calcProbabilityOfGoals(strengthB);
//
//        Map<String,Double> mapOfResultProbability = new HashMap<>();
//
//        for (int i=0;i<4;i++) {
//            for(int j=0;j<=4;j++) {
//                String resultCode = String.valueOf(i) + String.valueOf(j);
//                double probabilityAiBj = listProbabilityAscoresB.get(i)*listProbabilityBscoresA.get(j);
//                mapOfResultProbability.put(resultCode, probabilityAiBj);
//            }
//        }
//
//        return mapOfResultProbability;
//    }
//
//    public static List<Double> calculateProbabilityAwinsBwinsDraw (double strengthA, double strengthB) {
//
//        List<Double> listProbabilityBscoresA = calcProbabilityOfGoals(strengthA);
//        List<Double> listProbabilityAscoresB = calcProbabilityOfGoals(strengthB);
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


    public static void main(String[] args) {

//        List<Double> list = OddsCalculator.calculateProbabilityAwinsBwinsDrawInclTimeAndCurrentResult (0.1, 0.5, 1.0, 1, 1);
//
//
//        System.out.println(list.get(0));
//        System.out.println(list.get(1));
//        System.out.println(list.get(2));


//        double strengthA = 50.0;
//
//        double strengthB = 70.0;
//
//        System.out.println(calcProbabilityOfGoals(0.6));
//
////        List<Double> probabilities = OddsCalculator.calculateProbabilityAwinsBwinsDraw(strengthA, strengthB);
////
////
////        System.out.println(probabilities.get(0));
//
//        Map<String,Double> mapOfResultProbability = calculateMapOfResultProbabilityAB(0.6, 0.2);
//
//        System.out.println(mapOfResultProbability);
////
////        Double r00 = mapOfResultProbability.get(00);
////        Double r20 = mapOfResultProbability.get(20);
////        Double r11 = mapOfResultProbability.get(11);
////
////        System.out.println(r00);
////        System.out.println(r20);
////        System.out.println(r11);
////
////        List<Double> results = calculateProbabilityAwinsBwinsDraw(strengthA, strengthB);
////
////        System.out.println(results.get(0));
////        System.out.println(results.get(1));
////        System.out.println(results.get(2));
////
////        System.out.println(results.get(0)+results.get(1)+results.get(2));

    }

}
