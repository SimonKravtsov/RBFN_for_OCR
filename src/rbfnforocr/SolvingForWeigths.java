/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbfnforocr;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;



/**
 *
 * @author kravtz
 */
public class SolvingForWeigths {
    static double WieghtsResolts[][]=new double [10][64];
    
    
    public void getWiegths(){
                     
        solve(RBFkernel.RBFoutputValue0);
        solve(RBFkernel.RBFoutputValue1);
        solve(RBFkernel.RBFoutputValue2);
        solve(RBFkernel.RBFoutputValue3);
        solve(RBFkernel.RBFoutputValue4);
        solve(RBFkernel.RBFoutputValue5);
        solve(RBFkernel.RBFoutputValue6);
        solve(RBFkernel.RBFoutputValue7);
        solve(RBFkernel.RBFoutputValue8);
        solve(RBFkernel.RBFoutputValue9);
    }
    
    public static void solve(double x[][]){
        //spliting matrix 
        double [][]matrix = new double[x.length][64];
        double [][]constants = new double[x.length][1];
        
        for(int i=0; i<x.length; i++){
                  System.arraycopy(x[i], 0, matrix[i], 0,64);     //x[i].length-1 or x[i].length
        }
                 
        for(int i=0; i<x.length; i++){  
            constants[i][0]=x[i][64];
        }        
        
        // 2d arrays to real matrix conversion 
        RealMatrix m = MatrixUtils.createRealMatrix(matrix);
        RealMatrix c = MatrixUtils.createRealMatrix(constants);
        
        SingularValueDecomposition solution=new SingularValueDecomposition(m);
        
        double weights[][]=solution.getSolver().solve(c).getData();
        //getting weights for all ten digits 
        if(WieghtsResolts[0][0]==0.0){
            
            for (int i=0; i<weights.length;i++){
                WieghtsResolts[0][i]=weights[i][0];
            }     
            
        }else if(WieghtsResolts[1][0]==0.0){
            
            for (int i=0; i<weights.length;i++){
                WieghtsResolts[1][i]=weights[i][0];
            } 
            
        }else if(WieghtsResolts[2][0]==0.0){
            
            for (int i=0; i<weights.length;i++){
                WieghtsResolts[2][i]=weights[i][0];
            } 
            
        }else if(WieghtsResolts[3][0]==0.0){
            
            for (int i=0; i<weights.length;i++){
                WieghtsResolts[3][i]=weights[i][0];
            } 
            
        }else if(WieghtsResolts[4][0]==0.0){
            
            for (int i=0; i<weights.length;i++){
                WieghtsResolts[4][i]=weights[i][0];
            } 
            
        }else if(WieghtsResolts[5][0]==0.0){
            
            for (int i=0; i<weights.length;i++){
                WieghtsResolts[5][i]=weights[i][0];
            } 
            
        }else if(WieghtsResolts[6][0]==0.0){
            
            for (int i=0; i<weights.length;i++){
                WieghtsResolts[6][i]=weights[i][0];
            } 
            
        }else if(WieghtsResolts[7][0]==0.0){
            
            for (int i=0; i<weights.length;i++){
                WieghtsResolts[7][i]=weights[i][0];
            } 
            
        }else if(WieghtsResolts[8][0]==0.0){
            
            for (int i=0; i<weights.length;i++){
                WieghtsResolts[8][i]=weights[i][0];
            } 
            
        }else if(WieghtsResolts[9][0]==0.0){
            
            for (int i=0; i<weights.length;i++){
                WieghtsResolts[9][i]=weights[i][0];
            } 
            
        }
    }
    
    
}
