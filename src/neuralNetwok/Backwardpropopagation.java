package neuralNetwok;

import all.TextToArr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by shrestha on 12/18/2015.
 */
public class Backwardpropopagation {
    public void get(){
        NNCostFunction NNCostFunction = new NNCostFunction();
        TextToArr textToArr = new TextToArr();
        try {
            File currentDirectory = new File(new File(".").getAbsolutePath());
            String slash = "/";
            String xPath = currentDirectory.getCanonicalPath()+slash+"src"+slash+ "data/feedforwardX.txt";
//            String xPath = currentDirectory.getCanonicalPath()+slash+"src"+slash+ "data/test.txt";
            String yPath = currentDirectory.getCanonicalPath()+slash+"src"+slash+ "data/feedforwardY.txt";
            String theta1Path = currentDirectory.getCanonicalPath()+slash+"src"+slash+ "data/feedforwardTheta1.txt";
            String theta2Path = currentDirectory.getCanonicalPath()+slash+"src"+slash+ "data/feedforwardTheta2.txt";
            String strSeperator = "\\s+";
            ArrayList<ArrayList<Double>> xdata = textToArr.convert(xPath, strSeperator);
            ArrayList<ArrayList<Double>> ydata = textToArr.convert(yPath, strSeperator);
            ArrayList<ArrayList<Double>> theta1data = textToArr.convert(theta1Path, strSeperator);
            ArrayList<ArrayList<Double>> theta2data = textToArr.convert(theta2Path, strSeperator);
            double[][] X = textToArr.datatoArr(xdata);
            double[][] y = textToArr.datatoArr(ydata);
            double[][] theta1 = textToArr.datatoArr(theta1data);
            double[][] theta2 = textToArr.datatoArr(theta2data);
//            double[][] X = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {4, 4, 4}, {5, 5, 5}};
//            double[][] y = {{1}, {2}, {3}, {2}, {2}};
//            double[][] theta1 = {{2, 2, 2, 2}, {3, 3, 3, 3}, {4, 4, 4, 4}};
//            double[][] theta2 = {{3, 3, 3, 3}, {5, 5, 5, 5}};
            double[][] theta = combineTheta(theta1, theta2);
            int inputLayerSize = X[0].length;
            int hiddenLayerSize = 25;
            int numLabels = 10;
            double lambda = 0;
            NNCostFunction.cost(theta, inputLayerSize, hiddenLayerSize, numLabels, X, y, lambda);
        }catch (IOException e){
            e.printStackTrace(); //given input file not found exception
        }
    }

    public double[][] combineTheta(double[][] theta1, double[][] theta2){
        double[][] theta = new double[theta1.length*(theta1[0].length)+theta2.length*(theta2[0].length)][1];
        int count = 0;
        for(int i=0; i<theta1[0].length; i++){
            for(int j=0; j<theta1.length; j++){
                theta[count][0] = theta1[j][i];
                count++;
            }
        }
        for(int i=0; i<theta2[0].length; i++){
            for(int j=0; j<theta2.length; j++){
                theta[count][0] = theta2[j][i];
                count++;
            }
        }
        return theta;
    }
}
