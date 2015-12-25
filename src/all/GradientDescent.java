package all;

import logistic.LogisticCost;

/**
 * Created by shrestha on 12/23/2015.
 */
public class GradientDescent {

    private double[] J_history;
    private double[][] grad;

    public double[][] getGradientDescent(double[][] theta, double[][] X, double[][] y, int iter, double alpha){
        this.J_history = new double[iter];
        this.grad = theta;

        Matrix matrix = new Matrix();
        Sigmoid sigmoid = new Sigmoid();

        LogisticCost logisticCost = new LogisticCost();

        double[][] diff = new double[y.length][1];
        int row = X.length;

        for(int k=0; k<iter; k++){
            double cost = logisticCost.getCost(theta, X, y, alpha);
            this.J_history[k] = cost;
            double[][] XthetaMult = matrix.multMatrix(X, theta);
            int XthetaMultRow = XthetaMult.length;
            double[][] h = new double[XthetaMultRow][1];

            for(int i=0; i<XthetaMultRow; i++){
                h[i][0] = sigmoid.get(XthetaMult[i][0]);
            }
            for(int i=0; i<y.length; i++){
                diff[i][0] = h[i][0]-y[i][0];
            }

            double[][] transposeOfX = matrix.transpose(X);
            double[][] multOfXtransDiff = matrix.multMatrix(transposeOfX, diff);
            grad = new double[multOfXtransDiff.length][1];
            for(int i=0; i<multOfXtransDiff.length; i++){
                grad[i][0] = theta[i][0]-(alpha/row*multOfXtransDiff[i][0]);
            }
            theta = this.grad;
        }
        return grad;
    }

    public double[] getJhistory(){
        return this.J_history;
    }
}
