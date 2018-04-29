/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbfnforocr;

import java.io.IOException;
import java.text.DecimalFormat;



/**
 *
 * @author kravtz
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException  {
        //will give average of two-fold test
        double averageAccuracy=0;
    
        //Extractingdata class
        ExtractingData data =new ExtractingData();

        
        
        //trainning dataset 
        data.TrainingDataSet("cw2DataSet1.csv"); //before was kmeans with 91.1%
        //testing dataset
        data.TestDataSet("cw2DataSet2.csv");

        
        

       //RBFKernel class
       RBFkernel kernel=new RBFkernel();
       kernel.RBFCalculations();
       
       //Solving for weigths using least squared regression by getting svd
       SolvingForWeigths weigths=new SolvingForWeigths();
       weigths.getWiegths();
       
       
       
       //Testing network on testing set 
       TestingNetwork examin=new TestingNetwork();
       // examin.test(ExtractingData.TestData);

       
       //Back-Propagation
       BackPropagation improve=new BackPropagation();
       
       //improve.updateWeights();
      
       //This is resolts of wieghts after training network on Back propagation. 
       data.trainedW("trainedW.csv");
       
       //after back prop
       System.out.println("After Back-Propagation:");
       examin.test(ExtractingData.TestData);
       averageAccuracy=TestingNetwork.accuracy;
       
       //Swapping datasets 
        System.out.println("____Swapping dataSets ");
       //trainning dataset 
       data.TrainingDataSet("cw2DataSet2.csv");
       //testing dataset
       data.TestDataSet("cw2DataSet1.csv");
        
       kernel.RBFCalculations();
       weigths.getWiegths();
       
       //examin.test(ExtractingData.TestData);

       //improve.updateWeights();
        
       data.trainedW("trainedW2.csv");
        //after back prop
       System.out.println("After Back-Propagation:");
       examin.test(ExtractingData.TestData);
       averageAccuracy+=TestingNetwork.accuracy;
       
       averageAccuracy=averageAccuracy/2;
       DecimalFormat df = new DecimalFormat("#.#");
       System.out.println("_____________________________________________");
       System.out.println("Average Accuracy of two-fold testing: "+df.format(averageAccuracy)+"%");
       
               
    }
    
    
    //was used to display 2d arrays, mainly for troubleshooting 
    public static void display(double x[][]){ //change to double 
        for (int row=0;row<x.length;row++) {
            for (int column = 0; column < x[row].length; column++) {
                System.out.print(x[row][column] + "\t");
            }
            System.out.println();
        }
    }
    
   
    
}

