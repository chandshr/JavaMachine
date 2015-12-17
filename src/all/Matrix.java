package all;

/**
 * Created by shrestha on 12/14/2015.
 */
public class Matrix {

    public double[][] transpose(double[][] input){
        int row = input.length;
        int col = input[0].length;
        double[][] trans = new double[col][row];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                trans[j][i] = input[i][j];
            }
        }
        return trans;
    }

    public double[][] multMatrix(double[][] firstMatrix, double[][] secMatrix){
        int firstRow = firstMatrix.length;
        int firstCol = firstMatrix[0].length;
        int secRow = secMatrix.length;
        int secCol = secMatrix[0].length;

        if(firstCol != secRow){
            System.out.println("no. of column of first matrix = "+firstCol+" not equal to no. of row of second matrix = "+secRow);
            return null;
        }
        double[][] result = new double[firstRow][secCol];
        for(int k=0; k<secCol; k++){
            for(int i=0; i<firstRow; i++){
                double sum = 0;
                for(int j=0; j<firstCol; j++){
                    sum += firstMatrix[i][j]*secMatrix[j][k];
                }
                result[i][k] = sum;
            }
        }
        return result;
    }
}
