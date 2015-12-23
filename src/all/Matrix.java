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

    public double[][] transpose(double[] input){
        double[][] transpose = new double[input.length][1];
        for(int i=0; i<input.length; i++){
            transpose[i][0] = input[i];
        }
        return transpose;
    }

    public double[][] addOneRowOfOnes(double[][] input){
        int inputRow = input.length;
        int inputCol = input[0].length;
        double[][] result = new double[inputRow+1][inputCol];
        for(int j=0; j<input[0].length; j++){
            result[0][j] = 1;
        }
        for(int i=0; i<inputRow; i++){
            for(int j=0; j<inputCol; j++){
                result[i+1][j] = input[i][j];
            }
        }
        return result;
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

    public double[][] getLogOfMatrix(double[][] input){
        int row = input.length;
        int col = input[0].length;
        double[][] logOfGivenArr = new double[row][col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                logOfGivenArr[i][j] = Math.log(input[i][j]);
            }
        }
        return logOfGivenArr;
    }

//    public double[][] difference(double[][] input1, double[][] input2){
//        double[][] diff = new double[input1.length][input1[0].length];
//        for(int i=0; i<input1.length; i++){
//            for(int j=0; j<input1[0].length; j++){
//                diff[i][j] = input1[i][j] - input2[i][j];
//            }
//        }
//        return diff;
//    }
//
//    public double[][] add(double[][] input1, double[][] input2) {
//        double[][] add = new double[input1.length][input1[0].length];
//        for (int i = 0; i < input1.length; i++) {
//            for (int j = 0; j < input1[0].length; j++) {
//                add[i][j] = input1[i][j] + input2[i][j];
//            }
//        }
//        return add;
//    }

    /****** multiply two matrices element wise *****/
    public double[][] elementwiseOp(double[][] inputA, double[][] inputB, String op){
        int row = inputA.length;
        int col = inputA[0].length;
        double[][] result = new double[row][col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(op=="+")
                    result[i][j] = inputA[i][j] + inputB[i][j];
                else if(op=="-")
                    result[i][j] = inputA[i][j] - inputB[i][j];
                else if(op=="*")
                    result[i][j] = inputA[i][j] * inputB[i][j];
            }
        }
        return result;
    }

    /***copy content Start
     * copies the content in result[][] from input[][]; starts copying content from argument position
     * ***/
    public double[][] copy(double[][] result, double[][] input, int position){
        int row = input.length;
        int col = input[0].length;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                result[i][position+j] = input[i][j];
            }
        }
        return result;
    }
    /***copy content End***/

    /**
     * normalize the given matrix
     * n = norm(v,p) returns the vector norm defined by sum(abs(v)^p)^(1/p), where p is any positive
     * here p is 2
     */

    public double getNorm(double[][] input){
        int row = input.length;
        int col = input[0].length;

        double squareSum = 0;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                squareSum += input[i][j]*input[i][j];
            }
        }
        return Math.sqrt(squareSum);
    }
}
