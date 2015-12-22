package neuralNetwok;

import all.Matrix;
import all.Sigmoid;

/**
 * Created by shrestha on 12/18/2015.
 */
public class BackpropagationCost {

    public double[][] gradtheta1;
    public double[][] gradtheta2;

    public double[][] gettheta1() {
        return gradtheta1;
    }

    public double[][] gettheta2() {
        return gradtheta2;
    }

    public double cost(double[][] theta, int inputlayersize, int hiddenlayersize,
                     int numlabels, double[][] X, double[][] y, double lambda){
        Matrix matrix = new Matrix();
        Sigmoid sigmoid = new Sigmoid();

        int Xrow = X.length;
        int Xcol = X[0].length;

        /***** Theta1 start *****/
        double[][] theta1 = new double[hiddenlayersize][inputlayersize+1];
        int thetaCount = 0;
        for(int i=0; i<hiddenlayersize; i++){
            for(int j=0; j<inputlayersize+1; j++){
                theta1[i][j] = theta[thetaCount][0];
                thetaCount++;
            }
        }
        /***** Theta1 end *****/

        /*** Theta2 start ***/
        double[][] theta2 = new double[numlabels][hiddenlayersize+1];
        for(int i=0; i<numlabels; i++){
            for(int j=0; j<hiddenlayersize+1; j++){
                theta2[i][j] = theta[thetaCount][0];
                thetaCount++;
            }
        }
        /*** Theta2 end ***/

        /****** Part 1 Start *****/
        double[][] a1 = new double[X.length][X[0].length+1];
        for(int i=0; i<X.length; i++){
            a1[i][0] = 1;
            for(int j=0; j<X[0].length; j++){
                a1[i][j+1] = X[i][j];
            }
        }
        double[][] transposOfa1 = matrix.transpose(a1);
        double[][] multTheta1andtransposOfa1 = matrix.multMatrix(theta1, transposOfa1);
        double[][] a2 = new double[multTheta1andtransposOfa1.length+1][multTheta1andtransposOfa1[0].length];

        for(int i=0; i<multTheta1andtransposOfa1.length; i++){
            a2[i+1] = multTheta1andtransposOfa1[i];
        }

        for(int i=1; i<a2.length; i++){
            for(int j=0; j<a2[0].length; j++){
                a2[i][j] = sigmoid.get(a2[i][j]);
            }
        }
        //add rows of ones in a2
        for(int i=0; i<multTheta1andtransposOfa1[0].length; i++){
            a2[0][i] = 1;
        }

        double[][] multTheta2A2 = matrix.multMatrix(theta2, a2);
        double[][] h = sigmoid.getSigmoidArr(multTheta2A2);

        double[][] logOfH = matrix.getLogOfMatrix(h);
        double[][] transposeOfLogOfH = matrix.transpose(logOfH);

        double[][] logOneMinusH = new double[h.length][h[0].length];
        int hRow = h.length;
        int hCol = h[0].length;
        for(int i=0; i<hRow; i++){
            for(int j=0; j<hCol; j++){
                logOneMinusH[i][j] = Math.log(1-h[i][j]);
            }
        }
        double[][] transposeofHminusOne = matrix.transpose(logOneMinusH);

        double[][] Y = new double[Xrow][numlabels]; //Y in octave backpropagation
        int yRow = y.length;
        int yCol = y[0].length;

        int count = 0;
        for(int i=0; i<yCol; i++){
            for(int j=0; j<yRow; j++){
                int yRowWiseVal = (int)y[j][i]-1; //as array starts from 0
                Y[count][yRowWiseVal] = 1;
                count++;
            }
        }

        /***** cost positive and negative start ****/
        double positive;
        double negative;
        double cost;
        double sum = 0;

        for(int i=0; i<Xrow; i++){
            for(int j=0; j<numlabels; j++){
                positive = -Y[i][j] * transposeOfLogOfH[i][j];
                negative = (1-Y[i][j]) * transposeofHminusOne[i][j];
                cost = positive - negative;
                sum += cost;
            }
        }

        double J = sum/yRow;

        /********Regularization Start********/
        double[][] theta1Filtered = new double[theta1.length][theta1[0].length-1];
        double[][] theta2Filtered = new double[theta2.length][theta2[0].length-1];

        double sumSqTheta1 = 0;
        for(int i=0; i<theta1.length; i++){
            for(int j=1; j<theta1[0].length; j++){
                theta1Filtered[i][j-1] = theta1[i][j];
                sumSqTheta1 += (theta1[i][j]*theta1[i][j]);
            }
        }

        double sumSqTheta2 = 0;
        for(int i=0; i<theta2.length; i++){
            for(int j=1; j<theta2[0].length; j++){
                theta2Filtered[i][j-1] = theta2[i][j];
                sumSqTheta2 += (theta2[i][j]*theta2[i][j]);
            }
        }

        double reg = (lambda/(2*yRow))*(sumSqTheta1+sumSqTheta2);
        /********Regularization End********/
        J = J + reg;

        double[][] delta1 = new double[theta1.length][theta1[0].length];
        double[][] delta2 = new double[theta2.length][theta2[0].length];
        double[][] z2 = new double[theta1.length][a1[0].length];
        for(int i=0; i<Xrow; i++){
            /**step1 start**/
            double[] b = X[i];
            double[][] a = matrix.transpose(X[i]);
            a1 = matrix.addOneRowOfOnes(a);

            z2 = matrix.multMatrix(theta1, a1);
            a2 = matrix.addOneRowOfOnes(sigmoid.getSigmoidArr(z2));

            double[][] z3 = matrix.multMatrix(theta2, a2);
            double[][] a3 = sigmoid.getSigmoidArr(z3);
            /**step1 end**/

            /*** step 2 start ***/
            double[][] yt = matrix.transpose(Y[i]);
            double[][] d3 = new double[a3.length][a3[0].length];
            d3 = matrix.elementwiseOp(a3, yt, "-");
            /*** step 2 end ***/

            /*** Step 3 Start ***/
            double[][] multTheta2FiltransAndD3 = matrix.multMatrix(matrix.transpose(theta2Filtered), d3);
            double[][] sigmoidGradientOfZ2 = sigmoid.sigmoidGradient(z2);
            int rowOfsigmoidGradientOfZ2 = sigmoidGradientOfZ2.length;
            int colOfsigmoidGradientOfZ2 = sigmoidGradientOfZ2[0].length;
            double[][] d2 = matrix.elementwiseOp(multTheta2FiltransAndD3, sigmoidGradientOfZ2, "*");
            /*** Step 3 End ***/

            /*** Step 4 start ***/
            double[][] multD3andtransposeofA2 = matrix.multMatrix(d3, matrix.transpose(a2));
            double[][] multD2andtransposeofA1 = matrix.multMatrix(d2, matrix.transpose(a1));
            delta2 = matrix.elementwiseOp(delta2, multD3andtransposeofA2, "+");
            delta1 = matrix.elementwiseOp(delta1, multD2andtransposeofA1, "+");
            /*** Step 4 end ***/
        }

        double x = (double) 1/Xrow;
        double[][] theta1Grad = thetaMultByElement(delta1, x);
        double[][] theta2Grad = thetaMultByElement(delta2, x);

        double[][] temp1 = removeFirstColumn(theta1Grad);
        double[][] temp2 = removeFirstColumn(theta2Grad);

        x = (double) lambda/Xrow;
        theta1Filtered = thetaMultByElement(theta1Filtered, x);
        theta2Filtered = thetaMultByElement(theta2Filtered, x);

        temp1 = matrix.elementwiseOp(temp1, theta1Filtered, "+");
        temp2 = matrix.elementwiseOp(temp2, theta1Filtered, "+");

        theta1Grad = matrix.copy(theta1Grad, temp1, 1);
        theta2Grad = matrix.copy(theta2Grad, temp2, 1);

        this.gradtheta1 = theta1Grad;
        this.gradtheta2 = theta2Grad;

        return J;
        /***** cost positive and negative end ****/
        /****** Part 1 End *****/
    }

    /******divided each element of matrix by second argument*****/
    public double[][] thetaMultByElement(double[][] input, double x){
        int row = input.length;
        int col = input[0].length;
        double[][] result = new double[row][col];

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                result[i][j] = input[i][j]*x;
            }
        }
        return result;
    }

    /******remove first column*****/
    public double[][] removeFirstColumn(double[][] input){
        int row = input.length;
        int col = input[0].length;
        double[][] result = new double[row][col-1];

        for(int i=0; i<row; i++){
            for(int j=1; j<col; j++){
                result[i][j-1] = input[i][j];
            }
        }
        return result;
    }
}
