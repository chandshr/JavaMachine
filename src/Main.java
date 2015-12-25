import logistic.Logistic;
import neuralNetwok.*;

/**
 * Created by shresc2 on 11/10/2015.
 */
public class Main {
    public static void main(String[] args){
        /*********FeedForward Neural Network Start********/
//        FeedForward feedForward = new FeedForward();
//        feedForward.initialize();
//        feedForward.predict();
        /*********FeedForward Neural Network End********/

        /*****Backward Propagation Start***/
//        Backwardpropopagation backwardpropopagation = new Backwardpropopagation();
//        backwardpropopagation.get();
//        double[][] a = {{1, 2}, {3, 4}};
//        double[][] b = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        backwardpropopagation.combineTheta(a, b);
//        DebugInitializeWeights debugInitializeWeights = new DebugInitializeWeights();
//        debugInitializeWeights.createMatrix(5, 3);
//        CheckNNGradients checkNNGradients = new CheckNNGradients();
//        checkNNGradients.checkNeuralNetwork(0);
        /*****Backward Propagation End***/

        /******Linear Regression Start******/
//        Linear linearReg = new Linear();
//        linearReg.get();
        /******Linear Regression End******/

        /*******Logistic Regression Start*******/
        Logistic logisticReg = new Logistic();
        logisticReg.get();
        /*******Logistic Regression End*******/
    }
}
