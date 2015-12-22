package logistic;

import all.Matrix;
import all.Sigmoid;

/**
 * Created by shrestha on 11/16/2015.
 */
public class LogisticCost {

    public double[][] getGrad() {
        return grad;
    }

    public void setGrad(double[][] grad) {
        this.grad = grad;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    private double[][] grad;
    private double cost;

    public double getCost(double[][] theta, double[][] X, double[][] y, double alpha){
        int row = X.length;
        int col = X[0].length;

        this.grad = theta;

        Matrix matrix = new Matrix();
        Sigmoid sigmoid = new Sigmoid();

        double[][] XthetaMult = matrix.multMatrix(X, theta);
        int XthetaMultRow = XthetaMult.length;
        int XthetaMultCol = XthetaMult[0].length;
//        double[][] h = new double[XthetaMultRow][XthetaMultCol];
        double[][] h = new double[XthetaMultRow][1];

        for(int i=0; i<XthetaMultRow; i++){
//            for(int j=0; j<XthetaMultCol; j++){
//                h[i][j] = sigmoid.get(XthetaMult[i][j]);
//            }
            h[i][0] = sigmoid.get(XthetaMult[i][0]);
        }

        /**********cost start************/
        double costPos = 0;
        double costNeg = 0;
        int theta_col = theta[0].length;
        for(int i=0; i<row; i++){
            costPos += (-y[i][0]*Math.log(h[i][0]));
            costNeg += (1-y[i][0])*Math.log(1-h[i][0]);
        }

        /********transpose multiplication start*********/
        double[][] diff = new double[y.length][1];
        for(int i=0; i<y.length; i++){
            diff[i][0] = h[i][0]-y[i][0];
        }

        double[][] transposeOfX = matrix.transpose(X);
        double[][] multOfXtransDiff = matrix.multMatrix(transposeOfX, diff);
        this.grad = new double[multOfXtransDiff.length][1];
        for(int i=0; i<multOfXtransDiff.length; i++){
            this.grad[i][0] = theta[i][0]-(alpha/row*multOfXtransDiff[i][0]);
//            this.grad[i][0] = (1/row)*multOfXtransDiff[i][0];
        }
        /********transpose multiplication end*********/

        double J = (costPos-costNeg)/row;
        this.cost = J;
        return J;
    }
}
