package neuralNetwok;

/**
 * Created by shrestha on 12/12/2015.
 */
public class randInitializeWeights {

    public double[][] getRandomWeights(int inUnit, int outUnit){
        double[][] theta = new double[outUnit][inUnit+1];
        double epsilon = 0.12;
        for(int i=0; i<outUnit; i++){
            for(int j=0; j<inUnit; j++){
                theta[i][j] = Math.random()*2*epsilon - epsilon;
            }
        }
        return theta;
    }
}
