package neuralNetwok;

import all.Matrix;
import all.Sigmoid;
import all.TextToArr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by shrestha on 12/13/2015.
 */
public class FeedForward {

    private double[][] X;
    private double[][] y;
    private double[][] theta1;
    private double[][] theta2;

    public void initialize(){
        TextToArr textToArr = new TextToArr();
        try {
            File currentDirectory = new File(new File(".").getAbsolutePath());
            String slash = "/";
            String xPath = currentDirectory.getCanonicalPath()+slash+"src"+slash+ "data/x.txt";
            String yPath = currentDirectory.getCanonicalPath()+slash+"src"+slash+ "data/y.txt";
            String theta1Path = currentDirectory.getCanonicalPath()+slash+"src"+slash+ "data/theta1.txt";
            String theta2Path = currentDirectory.getCanonicalPath()+slash+"src"+slash+ "data/theta2.txt";
            String strSeperator = "\\s+";
            ArrayList<ArrayList<Double>> xdata = textToArr.convert(xPath, strSeperator);
            ArrayList<ArrayList<Double>> ydata = textToArr.convert(yPath, strSeperator);
            ArrayList<ArrayList<Double>> theta1data = textToArr.convert(theta1Path, strSeperator);
            ArrayList<ArrayList<Double>> theta2data = textToArr.convert(theta2Path, strSeperator);
            this.X = textToArr.datatoArr(xdata);
            this.y = textToArr.datatoArr(ydata);
            this.theta1 = textToArr.datatoArr(theta1data);
            this.theta2 = textToArr.datatoArr(theta2data);
        } catch (IOException e) {
            e.printStackTrace(); //given input file not found exception
        }
    }

    /*****theta argument counts equal to number of hidden layers *****/
    public void predict(){
        int theta1Row = theta1.length;
        int theta1Col = theta1[0].length;

        int[] result = new int[theta1.length];

        /***
         * a1 input at first layer
         * a2 input at second layer
         * a3 output of the network

         * theta1 X a1
         * theta2 X a2

         * z2 output of first layer which is second layer(hidden layer); added a row of ones in z2 and changed it to a2
         * z3 output of second layer which is third layer(output layer); changed it to a3
         * ***/

        double[][] a1 = new double[X.length][X[0].length+1]; //input units
        /*******add column of ones in a1******/
        for(int i=0; i<X.length; i++){
            a1[i][0] = 1;
            for(int j=0; j<X[0].length; j++){
                a1[i][j+1] = X[i][j];
            }
        }

        Matrix matrix = new Matrix();

        double[][] a1Trans = matrix.transpose(a1);

        double[][] z2 = matrix.multMatrix(theta1, a1Trans);

        Sigmoid sigmoid = new Sigmoid();
        for(int i=0; i<theta1Row; i++){
            z2[i][0] = 1;
            for(int j=0; j<z2[0].length; j++){
               z2[i][j] = sigmoid.get(z2[i][j]);
            }
        }

        int z2Row = z2.length;
        int z2Col = z2[0].length;
        double[][] a2 = new double[z2Row+1][z2Col];
        /****add rows of ones in a2****/
        for(int i=0; i<z2Col; i++){
            a2[0][i] = 1;
        }
        for(int i=0; i<z2Row; i++){
            for(int j=0; j<z2Col; j++){
                a2[i+1][j] = z2[i][j];
            }
        }

        double[][] z3 = matrix.multMatrix(theta2, a2);

        int theta2Row = theta2.length;
        int theta2Col = theta2[0].length;

        int z3Col = z3[0].length;
        double[][] a3 = new double[theta2Row][z3Col];
        for(int i=0; i<theta2Row; i++){
            for(int j=0; j<z3Col; j++){
                a3[i][j] = sigmoid.get(z3[i][j]);
            }
        }

        double[][] transpose = matrix.transpose(a3);

        int transposeRow = transpose.length;
        int transposeCol = transpose[0].length;
        double[] max = new double[transposeRow];
        double maxIndex = 0;
        int sumForMean = 0;

        for(int i=0; i<transposeRow; i++){
            max[i] = transpose[i][0];
            for(int j=1; j<transposeCol; j++){
                if(transpose[i][j]>max[i]){
                    max[i] = transpose[i][j];
                    maxIndex = j+1;
                }
            }
            if(maxIndex==y[i][0]){
                sumForMean++;
            }
        }
        double accuracy = (sumForMean*100/transposeRow);
        System.out.println("Feedforward Neural Network accuracy: "+accuracy+"%");
    }
}
