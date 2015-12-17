package all;

/**
 * Created by shrestha on 11/16/2015.
 */
public class Sigmoid {

    public double get(double z){
        double g = 1/(1+Math.exp(-z));
        return g;
    }
}
