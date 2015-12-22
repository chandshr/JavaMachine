import all.TextToArr;
import neuralNetwok.BackpropagationCost;
import neuralNetwok.Backwardpropopagation;
import neuralNetwok.FeedForward;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
        Backwardpropopagation backwardpropopagation = new Backwardpropopagation();
        backwardpropopagation.get();
        /*****Backward Propagation End***/

        /******Linear Regression Start******/
//        Linear linearReg = new Linear();
//        linearReg.get();
        /******Linear Regression End******/

        /*******Logistic Regression Start*******/
//        Logistic logisticReg = new Logistic();
//        logisticReg.get();
        /*******Logistic Regression End*******/
    }
}
