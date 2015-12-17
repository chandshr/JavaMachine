package logistic;

import all.Matrix;
import all.Sigmoid;

/**
 * Created by shrestha on 11/17/2015.
 */
public class LogisticPredict {
    public double probability(double[][] theta, double[][] input){
        Sigmoid sigmoid = new Sigmoid();
        Matrix matrix = new Matrix();

        double[][] inputThetaMult = matrix.multMatrix(input, theta); //it returns a single element
        double prob = sigmoid.get(inputThetaMult[0][0]);
        return prob;
    }

    public double[][] predict(double[][] theta, double[][] X){
        Matrix matrix = new Matrix();
        double[][] multXTheta = matrix.multMatrix(X, theta);
        int row = multXTheta.length;
        int col = multXTheta[0].length;
        double[][] pred = new double[row][1];
        Sigmoid sigmoid = new Sigmoid();
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                double sig = sigmoid.get(multXTheta[i][j]);
                if(sig>=0.5){
                    pred[i][0] = 1;
                }else {
                    pred[i][0] = 0;
                }
            }
        }
        return pred;
    }

    public double accuracy(double[][] pred, double[][] y){
        int row = pred.length;
        double sum = 0;
        for(int i=0; i<row; i++){
            if(pred[i][0]==y[i][0]){
                sum++;
            }
        }
        double mean = sum/row;
        double accPercent = mean*100;
        return accPercent;
    }
}
