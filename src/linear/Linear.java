package linear;

import all.TextToArr;
import linear_logistic.FeatureNormalize;
import linear_logistic.RegularizedGradient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by shrestha on 11/16/2015.
 */
public class Linear {
    public void get(){
        TextToArr textToArr = new TextToArr();
        try {
            /******change given txt file to data array start*****/
            File currentDirectory = new File(new File(".").getAbsolutePath());
            String slash = "/";
            String filePath = currentDirectory.getCanonicalPath()+slash+"src"+slash+ "data/linearData.txt";
            String strSeperator = ",";
            ArrayList<ArrayList<Double>> data = textToArr.convert(filePath, strSeperator);
            double[][] dataArr = textToArr.datatoArr(data);
            int row = dataArr.length;
            int col = dataArr[0].length;

            double[][] X = new double[row][col-1];
            double[][] y = new double[row][1];
            /******change given txt file to data array end *****/

            /*******X and y start******/
            for(int i=0; i<row; i++){
                y[i][0] = dataArr[i][col-1];
                for (int j=0; j<col-1; j++){
                    X[i][j] = dataArr[i][j];
                }
            }
            /*******X and y end******/

            /*******feature normalization start*******/
            FeatureNormalize featureNormalize = new FeatureNormalize();
            double[][] featureNormData = new double[row][col];
            featureNormData = featureNormalize.getFeatureNormalize(X);

            X = new double[row][col];

            //X is updated with featureNormalization and 1 is added infront of X
            for(int i=0; i<row; i++){
                X[i][0] = 1;
                for (int j=1; j<col; j++){
                    X[i][j] = featureNormData[i][j-1];
                }
            }
            //this returns a normalized and added 1's infront of X
            /*******feature normalization end*******/

            /**********Gradient Descent start***************/
            LinearGradientDescent gradientDescent = new LinearGradientDescent();
            double alpha = 0.1;
            int iter = 1500;
            double[][] theta = gradientDescent.getGradient(X, y, alpha, iter);
            /**********Gradient Descent end***************/

            /**********Prediction Start ************/
            LinearPredict predictobj = new LinearPredict();
            double[][] input = {{1000025,5,1,1,1,2,1,3,1,1}};
            double[][] mean = featureNormalize.getMean();
            double[][] std = featureNormalize.getStd();
            System.out.println("Linear Regression prediction for input {1000025,5,1,1,1,2,1,3,1,1}: "+predictobj.predict(input, mean, std, theta));
            /**********Prediction End ************/

            /********** Linear Regularized Start**********/
            LinearRegCost linearRegCost = new LinearRegCost();
            double lamda = 1;
            double[][] initial_theta = {{0, 0, 0}};
            double regCost = linearRegCost.regCost(X, y, theta, lamda);
            System.out.println("Linear Regularized cost: "+regCost);

            RegularizedGradient regularizedGradient = new RegularizedGradient();
            double[][] linearGrad = regularizedGradient.getGradient(lamda, alpha, row, theta);
            double regularizedGradientCost = linearRegCost.regCost(X, y, linearGrad, lamda);
            System.out.println("Linear Regualarized cost after implementing regularized gradient"+regularizedGradientCost);
            /********** Linear Regularized End**********/
        } catch (IOException e) {
            e.printStackTrace(); //given input file not found exception
        }
    }
}
