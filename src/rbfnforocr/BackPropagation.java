/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbfnforocr;

import java.util.Arrays;

import static rbfnforocr.TestingNetwork.wieghtsOutput;





/**
 *
 * @author kravtz
 */
public class BackPropagation extends Thread {
    TestingNetwork forwardPass=new TestingNetwork();
    double trainSample[][]=new double[1][65];
    double l_rate=0.0001;//learning rate, can be changed to optimize the improvemnt of the weights  
    double error=0;
    double distance=0;
    double sum=0;
    double firstw=0;
     TestingNetwork examin=new TestingNetwork();
    int i=0;
    int iterate = 0;
    int iterateCount=0;
    int samples=100;
    double topAccuracy=0;
    
    //new wieghts
    double newWieghts[][]=new double [10][64];
    //This is called from the main
    
    
    public void updateWeights(){
       iterate = 0; 
       l_rate=0.0001;
        //System.out.println(Arrays.toString(SolvingForWeigths.WieghtsResolts[0]));
       while(iterate < 10000){ //num of itterations 
        for(i=0;i<ExtractingData.TrainON.length;i++)
         {//ExtractingData.TrainON.length, trains on the rest of the training data excluding the centers. 
           
                 
            
            System.arraycopy(ExtractingData.TrainON[i], 0, trainSample[0], 0, 65);
            
            int rowC=0;
            int columnC=0;
            int columnX=0;
            for(int n=0;n<(ExtractingData.Centers.length)*(64);n++){
                distance=Math.pow(trainSample[0][columnX]-ExtractingData.Centers[rowC][columnC],2);
                forwardPass.RBFfunction=Math.pow(Math.E, -distance/2*Math.pow(RBFkernel.sigma, 2));
                
                TestingNetwork.sampleToRBF[rowC][columnC]=forwardPass.RBFfunction;
                columnC++;
                columnX++;

                if(columnC==64){
                    rowC++;
                    columnC=0;
                    columnX=0;
                }

                if (rowC==ExtractingData.Centers.length){
                    
                    rowC=0;
                }
            }
            
            //trains each weight according to its digit number
            if(trainSample[0][64]==0.0){
                updatingWeights(TestingNetwork.sampleToRBF,0);
            }
            else if(trainSample[0][64]==1.0){
               updatingWeights(TestingNetwork.sampleToRBF,1);
                 
            }
            else if(trainSample[0][64]==2.0){
                updatingWeights(TestingNetwork.sampleToRBF,2);
               
            }else if(trainSample[0][64]==3.0){
                updatingWeights(TestingNetwork.sampleToRBF,3);
                
            }else if(trainSample[0][64]==4.0){
                updatingWeights(TestingNetwork.sampleToRBF,4);
                
            }else if(trainSample[0][64]==5.0){
                updatingWeights(TestingNetwork.sampleToRBF,5);
                
            }else if(trainSample[0][64]==6.0){
                updatingWeights(TestingNetwork.sampleToRBF,6);
                
            }else if(trainSample[0][64]==7.0){
                updatingWeights(TestingNetwork.sampleToRBF,7);
                
            }else if(trainSample[0][64]==8.0){
                updatingWeights(TestingNetwork.sampleToRBF,8);
               
            }else if(trainSample[0][64]==9.0){
                updatingWeights(TestingNetwork.sampleToRBF,9);
                
            } 
            
            }   
        //was used to save new weights to csv file after backP training 
//            examin.test(ExtractingData.TestData);
//            if(TestingNetwork.accuracy>topAccuracy){
//                topAccuracy=TestingNetwork.accuracy;
//                for(int i=0; i<SolvingForWeigths.WieghtsResolts.length; i++)
//                    System.arraycopy(SolvingForWeigths.WieghtsResolts[i], 0, newWieghts[i], 0, SolvingForWeigths.WieghtsResolts[i].length);
//            }
           l_rate = l_rate * Math.pow((1-0.3),2);//exponential decrease in learning rate
          
          //l_rate = Math.pow(l_rate, 2);
          
          
//          Main.display(newWieghts);
          iterate++;
          
       }
        
       
//        Main.display(newWieghts);

    }
    
   

     
    public void updatingWeights(double x[][],int wIndex){
        int columnC=0; 
        int wOutC=0;
        int wResultC=0;
        int RBFC=0;
        int centerRow=0;
        for(int i=0;i<ExtractingData.Centers.length*64;i++){
            
            for(int n=0;n<x.length;n++){ 
                for (int y=0;y<SolvingForWeigths.WieghtsResolts[wIndex].length;y++){
                        sum+=(x[n][y]*SolvingForWeigths.WieghtsResolts[wIndex][y]);
                    }

                    TestingNetwork.wieghtsOutput[0][columnC]=sum;
                    columnC++;
                    sum=0;
                }
                columnC=0;
                
                

                if (wieghtsOutput[0][wOutC]!=1.0 && ExtractingData.Centers[centerRow][64]==(double)wIndex){
                    error=Math.abs(1-wieghtsOutput[0][wOutC]);


                    //w=w+learningRate*error*input
                    if(wieghtsOutput[0][wOutC]<1.0){
                        SolvingForWeigths.WieghtsResolts[wIndex][wResultC]=SolvingForWeigths.WieghtsResolts[wIndex][wResultC]+l_rate*error*x[centerRow][RBFC];
                    }else if(wieghtsOutput[0][wOutC]>1.0){
                        SolvingForWeigths.WieghtsResolts[wIndex][wResultC]=SolvingForWeigths.WieghtsResolts[wIndex][wResultC]-l_rate*error*x[centerRow][RBFC];
                    }
                    
                    
                    //wOutC++;
                    wResultC++;
                    RBFC++;
                    
                    if(wResultC==64 && RBFC==64){
                        wResultC=0;
                        centerRow++;
                        RBFC=0;
                        wOutC++;
                    }
                    if(wOutC==wieghtsOutput[0].length){
                        wOutC=0;
                    }
                    
                }else if(ExtractingData.Centers[centerRow][64]!=(double)wIndex){
                    error=Math.abs(0-wieghtsOutput[0][wOutC]);

                    if(wieghtsOutput[0][wOutC]<0.0){
                        SolvingForWeigths.WieghtsResolts[wIndex][wResultC]=SolvingForWeigths.WieghtsResolts[wIndex][wResultC]+l_rate*error*x[centerRow][RBFC];
                    }else if(wieghtsOutput[0][wOutC]>0.0){
                        SolvingForWeigths.WieghtsResolts[wIndex][wResultC]=SolvingForWeigths.WieghtsResolts[wIndex][wResultC]-l_rate*error*x[centerRow][RBFC];
                    }

                    wResultC++;
                    RBFC++;
                    
                    if(wResultC==64 && RBFC==64){
                        wResultC=0;
                        centerRow++;
                        RBFC=0;
                        wOutC++;
                    }
                    if(wOutC==wieghtsOutput[0].length){
                        wOutC=0;
                    }

                }
                    
            }
        
        
     }
    
    
    

    
    public static void display(double x[][]){ //change to double 
        for (int row=0;row<x.length;row++) {
            for (int column = 0; column < x[row].length; column++) {
                System.out.print(x[row][column] + "\t");
            }
            System.out.println();
        }
    }
}

