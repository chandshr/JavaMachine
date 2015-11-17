import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by shresc2 on 11/10/2015.
 */
public class Main {
    public static void main(String[] args){
        /******Linear Regression Start******/
        LinearReg linearReg = new LinearReg();
        linearReg.get();
        /******Linear Regression End******/

        /*******Logistic Regression Start*******/
        LogisticReg logisticReg = new LogisticReg();
        logisticReg.get();
        /*******Logistic Regression End*******/
    }
}
