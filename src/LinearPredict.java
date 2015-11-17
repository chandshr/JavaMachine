/**
 * Created by shrestha on 11/16/2015.
 */
public class LinearPredict {

    public double predict(double[][] input, double[][] mean, double[][] std, double[][] theta){
        int col = input[0].length;
        double predict = theta[0][0]; //here it should be initialized with theta[0][0] because mean, std are of size equal to input but we need to have X(0)*theta(0)
        double inputMeanDiff;
        double divByStd;
        for(int i=0; i<col; i++){
            inputMeanDiff = input[0][i]-mean[0][i];
            divByStd = inputMeanDiff/std[0][i];
            predict += divByStd*theta[0][i+1];
        }
        return predict;
    }
}
