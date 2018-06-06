package pl.coderslab.app;

import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.ArrayList;
import java.util.List;

public class OddCalcMain {

    public static double strengthA = 0.6;

    public static double strengthB = 0.2;

//    public static double lambdaAB;
//
//
//    public static double probabilityAB;



    public static List<Double> calcProbabilityOfGoals(double strengthA) {

        double lambdaAB = -Math.log(1.0-strengthA);

        double probabilityAB = ((Math.exp(-lambdaAB))*(Math.pow(lambdaAB,1.0)))/ArithmeticUtils.factorial(1);

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

    public static void main(String[] args) {

        List<Double> listProbabilityAB = calcProbabilityOfGoals(strengthA);
        List<Double> listProbabilityBA = calcProbabilityOfGoals(strengthB);



    }

}
