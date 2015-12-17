package logistic;

import logistic.LogisticCost;

/**
 * Created by shrestha on 11/16/2015.
 */
public class LogisticGradientDescent {

    public double[][] minTheta(double[][] theta, double[][] X, double[][] y, int iter, double alpha){
        double[] J_history = new double[iter];
        for(int k=0; k<iter; k++){
            LogisticCost logisticCost = new LogisticCost();
            double cost = logisticCost.getCost(theta, X, y, alpha);
            J_history[k] = cost;
            theta = logisticCost.getGrad();
        }
        return theta;
    }
}
