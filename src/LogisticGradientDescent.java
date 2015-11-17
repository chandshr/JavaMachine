/**
 * Created by shrestha on 11/16/2015.
 */
public class LogisticGradientDescent {

    public double[][] minTheta(double[][] theta, double[][] X, double[][] y, int iter, double alpha){
        int col = theta[0].length;
        int row = X.length;
        double[][] optimumTheta = new double[1][col];
        double minCost = Double.POSITIVE_INFINITY;
        double[] J_history = new double[iter];
        for(int k=0; k<iter; k++){
            LogisticCost logisticCost = new LogisticCost();
            double cost = logisticCost.get(theta, X, y, alpha);
            J_history[k] = cost;
            theta = logisticCost.getGrad();
        }
        return theta;
    }
}
