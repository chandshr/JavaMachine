/**
 * Created by chandani on 11/12/15.
 */
public class GradientDescent {

    public double[][] theta;
    public double[] J_history;

    /********get GradientDescent*******/
    /****data[][] is featureNormalized X***/
    public double[][] getGradient(double[][] X, double[][] y, double alpha, int iter){
        int row = X.length;
        int col = X[0].length;

        double[][] theta = new double[1][col];
        double[][] newtheta = new double[1][col];

        double[] J_history = new double[iter];

        for(int i=0;i<theta[0].length; i++){
            theta[0][i] = 0;
        }

        for(int k=0; k<iter; k++){
            for(int i=0; i<col; i++){
                double multSum = 0;
                for(int j = 0; j<row; j++){
                    double hyposum = 0;
                    for(int m=0; m<col; m++){
                        hyposum += (theta[0][m]*X[j][m]);
                    }
                    double hypoDiff = hyposum-y[j][0];
                    multSum += hypoDiff*X[j][i];
                }
                newtheta[0][i] = theta[0][i]-(alpha/row)*multSum;
            }
            theta = newtheta;

            Cost costObj = new Cost();
            J_history[k] = costObj.cost(X, y, theta);
        }
        this.theta = theta;
        this.J_history = J_history;
        return theta;
    }
}
