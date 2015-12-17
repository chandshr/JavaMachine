package linear;

/**
 * Created by chandani on 11/13/15.
 */
public class LinearCost {
    public double cost(double[][]X, double[][]y, double[][]theta){
        int row = X.length;
        int col = X[0].length;

        double cost = 0;
        double[][] hyposumdiff = new double[row][1];
        double sum = 0;
        for(int i=0; i<row; i++){
            double hyposum = 0;
            for(int j=0; j<col; j++){
                hyposum += theta[0][j]*X[i][j];
            }
            hyposumdiff[i][0] = hyposum-y[i][0];
            double pow = Math.pow(hyposumdiff[i][0], 2);
            sum += Math.pow(hyposumdiff[i][0], 2);
        }
        cost = sum/(2*row);
        return cost;
    }
}
