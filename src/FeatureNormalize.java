/**
 * Created by shresc2 on 11/12/2015.
 */
public class FeatureNormalize {

    public double[][] getMean() {
        return mean;
    }

    public void setMean(double[][] mean) {
        this.mean = mean;
    }

    public double[][] getStd() {
        return std;
    }

    public void setStd(double[][] std) {
        this.std = std;
    }

    private double[][] mean;
    private double[][] std;
    private double[][] range;
    private double[][] featureNormalize;

    public void xNormVar(double[][] data){
        /*******this function is dealing with calculating with X_norm variables, X has only col-1 columns******/
        int row = data.length;
        int col = data[0].length;
        double[][] mean = new double[1][col-1];
        double[][] max = new double[1][col-1];
        double[][] min = new double[1][col-1];
        double[][] range = new double[1][col-1];
        double[][] std = new double[1][col-1];
        double diff;

        /*initialization*/
        for(int j=0; j<col-1; j++){
             mean[0][j] = data[0][j];
             max[0][j] = data[0][j];
             min[0][j] = data[0][j];
            //standard deviation
            diff = data[0][j] - mean[0][j];
        }

        for(int i=1; i<row; i++){
            for(int j=0; j<col-1; j++){
                mean[0][j] = mean[0][j]+data[i][j];
                double ma = max[0][j];
                double mi = min[0][j];
                double da = data[i][j];
                max[0][j]  = max[0][j] > data[i][j] ? max[0][j] : data[i][j];
                min[0][j]  = min[0][j] < data[i][j] ? min[0][j] : data[i][j];
                //std
                diff = data[i][j] - mean[0][j];
            }
        }

        for(int j=0; j<col-1; j++){
            mean[0][j] = mean[0][j]/row;
            range[0][j] = max[0][j]-min[0][j];
            std[0][j] = (double)Math.sqrt(std[0][j]/(row));
        }


        this.mean = mean;
        this.range = range;
        this.std(data);
    }



    public void std(double[][] data){
        int row = data.length;
        int col = data[0].length;
        double[][] std = new double[1][col-1];
        double[][] mean = this.mean;
        double diff;
        /*initialization*/
        for(int j=0; j<col-1; j++){
            diff = data[0][j] - mean[0][j];
            std[0][j] = (double)Math.pow(diff, 2);
        }
        for(int i=1; i<row; i++){
            for(int j=0; j<col-1; j++){
                diff = data[i][j] - mean[0][j];
                std[0][j] += (double)Math.pow(diff, 2);
            }
        }
        for(int j=0; j<col-1; j++){
            std[0][j] = (double)Math.sqrt(std[0][j]/(row-1));
        }
        this.std = std;
    }

    public double[][] getFeatureNormalize(double[][] data){
        xNormVar(data);
        int row = data.length;
        int col = data[0].length;
        double[][] normalizedX = new double[row][col-1];
        for(int i=0; i<row; i++){
            for(int j=0; j<col-1; j++){
                normalizedX[i][j] = (data[i][j]-this.mean[0][j])/std[0][j];
            }
        }
        return normalizedX;
    }
}
