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

    public double get(double[][] theta, double[][] X, double[][] y, double alpha){
        int row = X.length;
        int col = X[0].length;

        double cost = 0;

        double[][] h = new double[row][1];
        for(int i=0; i<row; i++){
            double hyposum = 0;
            for(int j=0; j<col; j++){
                double a = X[i][j];
                double b = theta[0][j];
                hyposum += X[i][j]*theta[0][j];
            }
            Sigmoid sigmoid = new Sigmoid();
            h[i][0] = sigmoid.get(hyposum);
        }

        /**********cost start************/
        double costPos = 0;
        double costNeg = 0;
        int theta_col = theta[0].length;
        /**********cost end************/
        for(int i=0; i<row; i++){
            costPos += (-y[i][0]*Math.log(h[i][0]));
            costNeg += (1-y[i][0])*Math.log(1-h[i][0]);
        }

        /********transpose multiplication start*********/
        for(int i=0; i<theta_col; i++){
            double multsum = 0;
            for(int j=0; j<row; j++){
               multsum += X[j][i]*(h[j][0]-y[j][0]);
            }
//            grad[0][i] = multsum/row;
            theta[0][i] = theta[0][i] - (alpha/row)*multsum;
        }
        /********transpose multiplication end*********/

        double J = (costPos-costNeg)/row;
        this.cost = J;
        this.grad = theta;
        return J;
    }
}
