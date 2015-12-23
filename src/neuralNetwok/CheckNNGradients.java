package neuralNetwok;

import all.Matrix;

/**
 * Created by shrestha on 12/22/2015.
 */
public class CheckNNGradients {

    public void checkNeuralNetwork(double lamda){
        int input_layer_size = 3;
        int hidden_layer_size = 5;
        int num_labels = 3;
        int m = 5;

        DebugInitializeWeights debugInitializeWeights = new DebugInitializeWeights();

        //generate random test data
        double[][] theta1 = debugInitializeWeights.createMatrix(hidden_layer_size, input_layer_size);
        double[][] theta2 = debugInitializeWeights.createMatrix(num_labels, hidden_layer_size);
        double[][] X = debugInitializeWeights.createMatrix(m, input_layer_size-1);
        double[][] y = {{2}, {3}, {1}, {2}, {3}};

        Backwardpropopagation backwardpropopagation = new Backwardpropopagation();
        NNCostFunction NNCostFunction = new NNCostFunction();
        double[][] theta = backwardpropopagation.combineTheta(theta1, theta2);

        double cost = NNCostFunction.cost(theta, input_layer_size, hidden_layer_size, num_labels, X, y, lamda);
        double[][] grad = NNCostFunction.gettheta();

        ComputeNumericalGradient computeNumericalGradient = new ComputeNumericalGradient();
        double[][] numgrad = computeNumericalGradient.compute(theta, input_layer_size, hidden_layer_size, num_labels, X, y, lamda);

        Matrix matrix = new Matrix();
        double[][] sumofNumgradAndGrad = matrix.elementwiseOp(numgrad, grad, "+");
        double[][] diffofNumgradAndGrad = matrix.elementwiseOp(numgrad, grad, "-");

        double diff = (double) matrix.getNorm(diffofNumgradAndGrad)/matrix.getNorm(sumofNumgradAndGrad);
        double a = matrix.getNorm(diffofNumgradAndGrad);
        double b = matrix.getNorm(sumofNumgradAndGrad);
        System.out.println("Test above diff value");
    }
}
