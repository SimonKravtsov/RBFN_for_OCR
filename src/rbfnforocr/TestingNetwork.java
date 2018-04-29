/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbfnforocr;

import java.text.DecimalFormat;



/**
 *
 * @author kravtz
 */



public class TestingNetwork {
    
    static double testSample[][]=new double[1][65];
    static double sampleToRBF[][]=new double[ExtractingData.Centers.length][64];
    static double wieghtsOutput[][]=new double[1][ExtractingData.Centers.length];
    double RBFfunction=0;
    double distance=0;
    static int count=0;
    static double accuracy=0;
    double sum=0;
    DecimalFormat df = new DecimalFormat("#");
    int prediction0=0;
    int prediction1=0;
    int prediction2=0;
    int prediction3=0;
    int prediction4=0;
    int prediction5=0;
    int prediction6=0;
    int prediction7=0;
    int prediction8=0;
    int prediction9=0;
    double numberIs=0;
    
    int numof0=0; 
    int n=0;
    double predictingNum[]=new double[10];
    double RBFtokmeans[][]=new double[10][64];
    double output[]=new double[10];
    
    public void test(double x[][]){
        count=0;
        numof0=0;
        for(int i=0;i<x.length;i++){
             
            System.arraycopy(x[i], 0, testSample[0], 0, 65);
            
            int rowC=0;
            int columnC=0;
            int columnX=0;
            for(int n=0;n<(ExtractingData.Centers.length)*(64);n++){
                distance=Math.pow(testSample[0][columnX]-ExtractingData.Centers[rowC][columnC],2);
                RBFfunction=Math.pow(Math.E, -distance/2*Math.pow(RBFkernel.sigma, 2));
                
                sampleToRBF[rowC][columnC]=RBFfunction;
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
            //get output with weigthts
            
            for (int clear=0;clear<predictingNum.length;clear++){
                predictingNum[clear]=0;
            }
            
            //comparing sample to each weight and summing the output, if the output is more then 0.5
            predictingN(sampleToRBF, 0);
            predictingN(sampleToRBF, 1);
            predictingN(sampleToRBF, 2);
            predictingN(sampleToRBF, 3);
            predictingN(sampleToRBF, 4);
            predictingN(sampleToRBF, 5);
            predictingN(sampleToRBF, 6);
            predictingN(sampleToRBF, 7);
            predictingN(sampleToRBF, 8);
            predictingN(sampleToRBF, 9);
            
            //predicts number by highest sum
            numberIS();

           

            //checking if prediction is correct, by comparing prediction to the real value of test sample
            if(numberIs==testSample[0][64]){
                count++;
            }

            
            
        }
        //System.out.println(numof0);
        System.out.println("Predicted correctly: "+count);
        accuracy=(double)count/(double)x.length*100;
        //accuracy=(double)count/(double)100*100;
        System.out.println("Accuracy: "+accuracy+" %");
    }
    
     

     
     public void predictingN(double x[][],int N){
        double average=0;
        int columnC=0; 
        for(int n=0;n<x.length;n++){ 
            for (int y=0;y<64;y++){
                    sum+=(x[n][y]*SolvingForWeigths.WieghtsResolts[N][y]);
                }
                
                wieghtsOutput[0][columnC]=sum;
                
                columnC++;
                sum=0;
            }
        
        for(int predictN=0;predictN<x.length;predictN++){
                    if (wieghtsOutput[0][predictN]>0.50 && ExtractingData.Centers[predictN][64]==(double)N){
                        average+=wieghtsOutput[0][predictN];
                        predictingNum[N]++;
                    }
                    
                }
        output[N]=average;
     }
     
    
     
     
     public void numberIS(){
         //getting the index which has the highest sum, the index number is the digit number 
         int largest = 0;
            for ( int i = 1; i < output.length; i++ )
            {
                if ( output[i] > output[largest] ) largest = i;
            }
         numberIs=largest;   
     }
     
     
}
