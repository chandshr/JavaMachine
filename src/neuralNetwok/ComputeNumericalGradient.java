package neuralNetwok;

import all.Matrix;

/**
 * Created by shrestha on 12/22/2015.
 */
public class ComputeNumericalGradient {

    public void compute(double[][] theta, double[][] theta1, double[][] theta2, int inputlayersize, int hiddenlayersize,
                        int numlabels, double[][] X, double[][] y, double lambda){
        double[][] numGrad = new double[theta1.length+theta2.length][1];
        double[][] perturb = new double[theta.length][theta[0].length];
        double e = 1e-4;
        int row = theta.length;
        int col = theta[0].length;
        Matrix matrix = new Matrix();
        BackpropagationCost backpropagationCost = new BackpropagationCost();

        for(int i=0; i<row; i++){
            perturb[i][0] = e;
            double[][] diffTheta = matrix.elementwiseOp(theta, perturb, "-");
            double[][] positiveTheta = matrix.elementwiseOp(theta, perturb, "+");

            double loss1 = backpropagationCost.cost(diffTheta, inputlayersize, hiddenlayersize,
            numlabels, X, y, lambda);
            double loss2 = backpropagationCost.cost(positiveTheta, inputlayersize, hiddenlayersize,
                    numlabels, X, y, lambda);
            numGrad[i][0] = (loss2-loss1)/(2*e);
            perturb[i][0] = 0;
        }
    }
}
