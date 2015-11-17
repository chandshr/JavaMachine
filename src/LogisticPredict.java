/**
 * Created by shrestha on 11/17/2015.
 */
public class LogisticPredict {
    public double probability(double[][] theta, double[][] input){
        int row = input.length;
        int col = input[0].length;
        Sigmoid sigmoid = new Sigmoid();
        double multsum = 0;
        for(int i=0; i<col; i++){
            multsum += (input[0][i]*theta[0][i]);
        }
        double prob = sigmoid.get(multsum);
        System.out.println("Probability is: "+prob);
        return prob;
    }

    public double[][] predict(double[][] theta, double[][] X){
        int row = X.length;
        int col = X[0].length;
        double[][] hypo = new double[row][1];
        double[][] pred = new double[row][1];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                hypo[i][0] += X[i][j]*theta[0][j];
            }
            Sigmoid sigmoid = new Sigmoid();
            double sig = sigmoid.get(hypo[i][0]);
            if(sig>=0.5){
                pred[i][0] = 1;
            }else {
                pred[i][0] = 0;
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
