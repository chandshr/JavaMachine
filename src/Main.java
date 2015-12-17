import all.Matrix;
import all.Sigmoid;
import linear.Linear;
import logistic.Logistic;
import neuralNetwok.FeedForward;

import java.util.Arrays;

/**
 * Created by shresc2 on 11/10/2015.
 */
public class Main {
    public static void main(String[] args){
        /*********Neural Network Start********/
        FeedForward feedForward = new FeedForward();
        feedForward.initialize();
        feedForward.predict();
        /*********Neural Network End********/

        /******Linear Regression Start******/
        Linear linearReg = new Linear();
        linearReg.get();
        /******Linear Regression End******/

        /*******Logistic Regression Start*******/
        Logistic logisticReg = new Logistic();
        logisticReg.get();
        /*******Logistic Regression End*******/
    }
}
