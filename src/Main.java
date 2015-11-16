import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by shresc2 on 11/10/2015.
 */
public class Main {
    public static void main(String[] args){
        TextToArr textToArr = new TextToArr();
        try {
            File currentDirectory = new File(new File(".").getAbsolutePath());
            String slash = "/";
            String filePath = currentDirectory.getCanonicalPath()+slash+"src"+slash+"data.txt";
            ArrayList<ArrayList<Double>> data = textToArr.convert(filePath);
            double[][] dataArr = textToArr.datatoArr(data);
            int row = dataArr.length;
            int col = dataArr[0].length;

            /*******feature normalization start*******/
            double[][] featureNormData = new double[row][col];
            FeatureNormalize featureNormalize = new FeatureNormalize();
            featureNormData = featureNormalize.getFeatureNormalize(dataArr);

            double[][] X = new double[row][col];
            double[][] y = new double[row][1];

            for(int i=0; i<row; i++){
                X[i][0] = 1;
                y[i][0] = dataArr[i][col-1];
                for (int j=1; j<col; j++){
                    X[i][j] = featureNormData[i][j-1];
                }
            }
            /*******feature normalization end*******/

            /**********Gradient Descent start***************/
            GradientDescent gradientDescent = new GradientDescent();
            double alpha = 0.1;
            int iter = 1500;
            double[][] theta = gradientDescent.getGradient(X, y, alpha, iter);
            /**********Gradient Descent end***************/

            /**********Prediction Start ************/
            Predict predictobj = new Predict();
            double[][] input = {{1323477,1,2,1,3,2,1,2,1,1}};
            double[][] mean = featureNormalize.getMean();
            double[][] std = featureNormalize.getStd();
            System.out.println("Predicted: "+predictobj.predict(input, mean, std, theta));
            /**********Prediction End ************/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
