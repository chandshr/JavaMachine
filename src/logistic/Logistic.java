package logistic;

import all.TextToArr;
import linear_logistic.MapFeature;
import linear_logistic.RegularizedGradient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by shrestha on 11/16/2015.
 * change alpha and number of iterations to get higher number of accuracy
 */
public class Logistic {
    public void get(){
        TextToArr textToArr = new TextToArr();
        try {
            /******change given txt file to data array start*****/
            File currentDirectory = new File(new File(".").getAbsolutePath());
            String slash = "\\";
            String filePath = currentDirectory.getCanonicalPath()+slash+"src"+slash+ "data/logisticData.txt";
            String strSeperator = ",";
            ArrayList<ArrayList<Double>> data = textToArr.convert(filePath, strSeperator);
            double[][] dataArr = textToArr.datatoArr(data);
            int row = dataArr.length;
            int col = dataArr[0].length;

            double[][] X = new double[row][col];
            double[][] y = new double[row][1];
            /******change given txt file to data array end *****/

            /*******X and y start, appended 1 infront of X ******/
            for(int i=0; i<row; i++){
                y[i][0] = dataArr[i][col-1]; //last column of data is y and data array index starts from 0
                X[i][0] = 1;
                for (int j=1; j<col; j++){
                    X[i][j] = dataArr[i][j-1];
                }
            }
            /*******X and y end******/

            /*** Initial Cost Start ***/
            double[][] initalTheta = {{0}, {0}, {0}};
            double alpha = 0.001;
            LogisticCost logisticCost = new LogisticCost();
            double initialCost = logisticCost.getCost(initalTheta, X, y, alpha);
            System.out.println("Logistic Initial Cost: "+initialCost);
            double[][] initialGrad = logisticCost.getGrad();
            /*** Initial Cost End ***/

            /***********iterations to find the optimum theta(gradient Descent) start**********/
            LogisticGradientDescent logisticGradientDescent = new LogisticGradientDescent();
            //int iter = 4000000; //87% accuracy given and alpha 0.01
            //int iter = 40000000; //88% accuracy given and alpha 0.01
            int iter = 400000; //91% accuracy and alpha 0.001
            double[][] gradTheta = logisticGradientDescent.minTheta(initalTheta, X, y, iter, alpha);
//            double[][] gradTheta = {{-10.632767580100028}, {0.0904557645032425}, {0.08425697183190725}};
            double gradCost = logisticCost.getCost(gradTheta, X, y, alpha);
            System.out.println("Logistic Regression Cost with Gradient Descent: "+gradCost);
            /***********iterations to find the optimum theta(gradient Descent) end**********/

            /***********Logistic Predictions Start**********/
            LogisticPredict logisticPredict = new LogisticPredict();
            double[][] input = {{60.18259938620976,86.30855209546826,1}};
            double prob = logisticPredict.probability(gradTheta, input);
            System.out.println("Logistic Probability of getting {0.322,0.5826,1}: "+prob);
            double[][] pred =  logisticPredict.predict(gradTheta, X);
            double accPercent = logisticPredict.accuracy(pred, y);
            System.out.println("Logistic Regression Accuracy Percentage: "+accPercent+"%");
            double[][] input1 = {{1, 60.18259938620976,86.30855209546826}};
            System.out.println("predict "+logisticPredict.predict(gradTheta, input1)[0][0]);
            /***********Logistic Predictions End**********/

            /*********Increment feature Start: This is incomplete, need to work on MapFeature; Mapfeature class is incomplete)******/
//            MapFeature mapFeature = new MapFeature();
//            double[][] A = new double[row][1];
//            double[][] B = new double[row][1];
//            for(int i=0; i<row; i++){
//                A[i][0] = dataArr[i][0];
//                B[i][0] = dataArr[i][1];
//            }
//            double[][] incFeaturesArr = mapFeature.incFeature(3, A, B );
//            //add column of 1's infront of incFeaturesArr
//            int incFeatureRow = A.length;
//            int incFeatureCol = incFeaturesArr[0].length;
//            double[][] incFeaturesWithOne = new double[incFeatureRow][incFeatureCol+1];
//            for(int i=0; i<incFeatureRow; i++){
//                incFeaturesWithOne[i][0] = 1;
//                for(int j=0; j<incFeatureCol; j++){
//                    int a = j+1;
//                    System.out.println("i: "+i+" j: "+j);
//                    double b = incFeaturesArr[i][j];
//                    incFeaturesWithOne[i][j+1] = incFeaturesArr[i][j];
//                }
//            }
//            System.out.println("Check above incremented features, incFeaturesWithOne");
            /*********Increment feature End******/

            /*********Logistic Regularized Start map feature remaining to complete regularized regression********/
//            LogisticRegCost logisticRegCost = new LogisticRegCost();
//            double lambda = 1;
//            RegularizedGradient regularizedGradient = new RegularizedGradient();
//            double[][] logisticGrad = regularizedGradient.getGradient(lambda, alpha, row, initalTheta);
//            double cost = logisticRegCost.regCost(X, y, logisticGrad, lambda, alpha);
//            System.out.println("Logistic Regression Regularized Cost "+cost);
            /********Logistic Regularized End*******/

        } catch (IOException e) {
            e.printStackTrace(); //given input file not found exception
        }
    }
}
