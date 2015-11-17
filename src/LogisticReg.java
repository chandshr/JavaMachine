import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by shrestha on 11/16/2015.
 */
public class LogisticReg {
    public void get(){
        TextToArr textToArr = new TextToArr();
        try {
            /******change given txt file to data array start*****/
            File currentDirectory = new File(new File(".").getAbsolutePath());
            String slash = "\\";
            String filePath = currentDirectory.getCanonicalPath()+slash+"src"+slash+"logisticData.txt";
            ArrayList<ArrayList<Double>> data = textToArr.convert(filePath);
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

            /*********** Cost Function Start **********/
            double[][] theta = {{0, 0, 0}};
            /*LogisticCost logisticCost = new LogisticCost();
            logisticCost.get(theta, X, y);
            /*********** Cost Function End **********/

            /***********iterations to find the optimum theta start**********/
            LogisticGradientDescent logisticGradientDescent = new LogisticGradientDescent();
            int iter = 400;
            double alpha = 0.01;
            double[][] gradTheta = logisticGradientDescent.minTheta(theta, X, y, iter, alpha);
            /***********iterations to find the optimum theta end**********/

            /***********Logistic Predictions Start**********/
            LogisticPredict logisticPredict = new LogisticPredict();
            double[][] input = {{1, 45, 85}};
            double prob = logisticPredict.probability(gradTheta, input);
            double[][] pred =  logisticPredict.predict(gradTheta, X);
            double accPercent = logisticPredict.accuracy(pred, y);
            /***********Logistic Predictions End**********/
        } catch (IOException e) {
            e.printStackTrace(); //given input file not found exception
        }
    }
}
