package all;

import java.io.*;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by shresc2 on 11/10/2015.
 */
public class TextToArr {
    public  ArrayList<ArrayList<Double>> convert(String filePath, String seperator) throws IOException {
        BufferedReader inputStream = null;
        ArrayList<ArrayList<Double>> output = null;
        try {
            File currentDirectory = new File(new File(".").getAbsolutePath());

            inputStream = new BufferedReader(new FileReader(filePath));
            String strLine;
            Scanner scanner = new Scanner(inputStream);
            output = new ArrayList<ArrayList<Double>>();
            while (scanner.hasNextLine()) {
                ArrayList<Double> wordArrL = new ArrayList<Double>();
                strLine = scanner.nextLine();
                String[] words = strLine.split(seperator);
                ArrayList<Double> dataArrL = new ArrayList<>();
                for(int i=0; i<words.length; i++){
                    String dataStr = words[i];
                    wordArrL.add(Double.parseDouble(dataStr));
                }
                output.add(wordArrL);
            }
        }finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return output;
    }

    public double[][] datatoArr(ArrayList<ArrayList<Double>> data){
        int row = data.size();
        int col = data.get(0).size();
        double[][] result = new double[row][col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                result[i][j] = data.get(i).get(j);
            }
        }
        return result;
    }
}
