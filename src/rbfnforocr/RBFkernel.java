/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbfnforocr;

import java.util.Arrays;
import static rbfnforocr.ExtractingData.kmeans;

/**
 *
 * @author kravtz
 */
public class RBFkernel {
    double RBFfunction=0;
    static double sigma= 0.2; //then change will effect the accuracy of the results  
    double distance=0;
    static double RBFoutputValue0[][]=new double[ExtractingData.Centers.length][65];
    static double RBFoutputValue1[][]=new double[ExtractingData.Centers.length][65];
    static double RBFoutputValue2[][]=new double[ExtractingData.Centers.length][65];
    static double RBFoutputValue3[][]=new double[ExtractingData.Centers.length][65];
    static double RBFoutputValue4[][]=new double[ExtractingData.Centers.length][65];
    static double RBFoutputValue5[][]=new double[ExtractingData.Centers.length][65];
    static double RBFoutputValue6[][]=new double[ExtractingData.Centers.length][65];
    static double RBFoutputValue7[][]=new double[ExtractingData.Centers.length][65];
    static double RBFoutputValue8[][]=new double[ExtractingData.Centers.length][65];
    static double RBFoutputValue9[][]=new double[ExtractingData.Centers.length][65];

    
    
    
    
    public void RBFCalculations(){
        //this array kmeans will be compared to the centers
        
        //Applying RBF kernel 
            int rowC=0;
            int columnC=0;
            int rowX=0;
            int columnX=0;
        for(int i=0;i<(ExtractingData.Centers.length)*(ExtractingData.Centers.length)*kmeans.length;i++){
            
            distance=Math.pow(kmeans[rowX][columnX]-ExtractingData.Centers[rowC][columnC],2);
            RBFfunction=Math.pow(Math.E, -distance/2*Math.pow(sigma, 2));
            
            switch (rowX) {
                case 0:
                    RBFoutputValue0[rowC][columnC]=RBFfunction;
                    if(kmeans[rowX][64]==ExtractingData.Centers[rowC][64]){
                        RBFoutputValue0[rowC][64]=1;
                    }else{
                         RBFoutputValue0[rowC][64]=0;
                    } 
                    break;
                case 1:
                    RBFoutputValue1[rowC][columnC]=RBFfunction;
                    if(kmeans[rowX][64]==ExtractingData.Centers[rowC][64]){
                        RBFoutputValue1[rowC][64]=1;
                    }else{
                         RBFoutputValue1[rowC][64]=0;
                    } 
                    break;
                case 2:
                    RBFoutputValue2[rowC][columnC]=RBFfunction;
                    if(kmeans[rowX][64]==ExtractingData.Centers[rowC][64]){
                        RBFoutputValue2[rowC][64]=1;
                    }else{
                         RBFoutputValue2[rowC][64]=0;
                    } 
                    break;
                case 3:
                    RBFoutputValue3[rowC][columnC]=RBFfunction;
                    if(kmeans[rowX][64]==ExtractingData.Centers[rowC][64]){
                        RBFoutputValue3[rowC][64]=1;
                    }else{
                         RBFoutputValue3[rowC][64]=0;
                    } 
                    break;
                case 4:
                    RBFoutputValue4[rowC][columnC]=RBFfunction;
                    if(kmeans[rowX][64]==ExtractingData.Centers[rowC][64]){
                        RBFoutputValue4[rowC][64]=1;
                    }else{
                         RBFoutputValue4[rowC][64]=0;
                    } 
                    break;
                case 5:
                    RBFoutputValue5[rowC][columnC]=RBFfunction;
                    if(kmeans[rowX][64]==ExtractingData.Centers[rowC][64]){
                        RBFoutputValue5[rowC][64]=1;
                    }else{
                         RBFoutputValue5[rowC][64]=0;
                    } 
                    break;
                case 6:  
                    RBFoutputValue6[rowC][columnC]=RBFfunction;
                    if(kmeans[rowX][64]==ExtractingData.Centers[rowC][64]){
                        RBFoutputValue6[rowC][64]=1;
                    }else{
                         RBFoutputValue6[rowC][64]=0;
                    } 
                    break;
                case 7:
                    RBFoutputValue7[rowC][columnC]=RBFfunction;
                    if(kmeans[rowX][64]==ExtractingData.Centers[rowC][64]){
                        RBFoutputValue7[rowC][64]=1;
                    }else{
                         RBFoutputValue7[rowC][64]=0;
                    } 
                    break;
                case 8:
                    RBFoutputValue8[rowC][columnC]=RBFfunction;
                    if(kmeans[rowX][64]==ExtractingData.Centers[rowC][64]){
                        RBFoutputValue8[rowC][64]=1;
                    }else{
                         RBFoutputValue8[rowC][64]=0;
                    } 
                    break;
                case 9:
                    RBFoutputValue9[rowC][columnC]=RBFfunction;
                    if(kmeans[rowX][64]==ExtractingData.Centers[rowC][64]){
                        RBFoutputValue9[rowC][64]=1;
                    }else{
                         RBFoutputValue9[rowC][64]=0;
                    } 
                    break;
                default:
                    break;
            }
            columnC++;
            columnX++;
            
            if(columnC==64){
                rowC++;
                columnC=0;
                columnX=0;
            }
            
            if (rowC==ExtractingData.Centers.length){
                rowX++;
                rowC=0;
            }
            
            if(rowX==10){
                rowX=0;
            }
           
            
        }
    }
    
}
