/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbfnforocr;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author kravtz
 */
public class ExtractingData {
//This class imports data from csv files to 2d arrays on which we do our calculations on     

    static double Centers[][]= new double[300][65];
    static double [][] TrainingSet;
    static double [][] TestData;
    static double [][] TrainON;
    static double kmeans[][]=new double[10][65];
    
    static double TrainedWieghts[][]=new double [10][64];
    //save into array already trained weights after back propagation was done. 
    public void trainedW(String fileName) throws IOException{
        
        //Centers = new double[countRows(fileName)][65];
        
        int Rowc = 0;
        
        
        
        try {
            
            Scanner read =new Scanner(new BufferedReader(new FileReader(fileName)));
            
            while (read.hasNextLine()){
                //read line in from file
                String InputLine = read.nextLine();
                //split the InputLine into an array at the commas
                String[] inLine=InputLine.split(",");
                
                //copy the content of the inArray to the CentersArray
                
                for (int i=0; i<inLine.length; i++){
                    
                    SolvingForWeigths.WieghtsResolts[Rowc][i] = Double.parseDouble(inLine[i]);
                    
                }
                //Increment the row in the array
                Rowc++; 
            }

        } catch (FileNotFoundException ex) {
           System.out.println("FILE NOT FOUND \n"+ex);
        }
        
    }
    //full dataset on which from which centers and averages of samples would be taken /performed data usage 
    public void TrainingDataSet(String fileName) throws IOException{
        
        TrainingSet = new double[countRows(fileName)][65];
        
        int Rowc = 0;
        
        
        
        try {
            
            Scanner read =new Scanner(new BufferedReader(new FileReader(fileName)));
            
            while (read.hasNextLine()){
                //read line in from file
                String InputLine = read.nextLine();
                //split the InputLine into an array at the commas
                String[] inLine=InputLine.split(",");
                
                //copy the content of the inArray to the TrainingSet
                
                for (int i=0; i<inLine.length; i++){
                    
                    TrainingSet[Rowc][i] = Double.parseDouble(inLine[i]);
                    
                }
                //Increment the row in the array
                Rowc++; 
            }
            CentersAndTrainingSet();
            kmeans();

        } catch (FileNotFoundException ex) {
           System.out.println("FILE NOT FOUND \n"+ex);
        }
        
    }
    
    //getting the average of all samples for each number 1-9, which will later be compared to centers  
    public void kmeans(){
        int total=0;
        for(int x=0;x<kmeans.length;x++){
            for(int i=0;i<ExtractingData.TrainingSet.length;i++){
            if(ExtractingData.TrainingSet[i][64]==(double)x){
                total++;
                for(int n=0;n<ExtractingData.TrainingSet[i].length-1;n++){ 
                        kmeans[x][n]+=ExtractingData.TrainingSet[i][n];
                        kmeans[x][64]=(double)x;
                    }
                    
                 }
            }

            for(int j=0;j<kmeans[x].length-1;j++){
                kmeans[x][j]=kmeans[x][j]/total;

             }
            total=0;
        }
    }
    
    //we make 300 centers for RBF and the rest of samples are used to train the network 
    public void CentersAndTrainingSet(){
        for(int i=0; i<Centers.length;i++){
            for(int c=0;c<TrainingSet[i].length;c++){
                Centers[i][c]=TrainingSet[i][c];
            }
        }
        
        TrainON = new double[TrainingSet.length-Centers.length][65];
        
        for(int i=Centers.length; i<TrainingSet.length-Centers.length+Centers.length;i++){
            for(int c=0;c<TrainingSet[i].length;c++){
                TrainON[i-Centers.length][c]=TrainingSet[i][c];
            }
        }
        
    }
    
    //testing on file
    public void TestDataSet(String fileName) throws IOException{
        
        TestData = new double[countRows(fileName)][65];
        
        int Rowc = 0;
        
        
        
        try {
            
            Scanner read =new Scanner(new BufferedReader(new FileReader(fileName)));
            
            while (read.hasNextLine()){
                //read line in from file
                String InputLine = read.nextLine();
                //split the InputLine into an array at the commas
                String[] inLine=InputLine.split(",");
                
                //copy the content of the inArray to the CentersArray
                
                for (int i=0; i<inLine.length; i++){
                    
                    TestData[Rowc][i] = Double.parseDouble(inLine[i]);
                    
                }
                //Increment the row in the array
                Rowc++; 
            }

        } catch (FileNotFoundException ex) {
           System.out.println("FILE NOT FOUND \n"+ex);
        }
        
    }
    
    //training on the rest of data with back prorpagation 
    public void TrainOn(String fileName) throws IOException{
        
        TrainON = new double[countRows(fileName)][65];
        
        int Rowc = 0;
        
        
        
        try {
            
            Scanner read =new Scanner(new BufferedReader(new FileReader(fileName)));
            
            while (read.hasNextLine()){
                //read line in from file
                String InputLine = read.nextLine();
                //split the InputLine into an array at the commas
                String[] inLine=InputLine.split(",");
                
                //copy the content of the inArray to the CentersArray
                
                for (int i=0; i<inLine.length; i++){
                    
                    TrainON[Rowc][i] = Double.parseDouble(inLine[i]);
                    
                }
                //Increment the row in the array
                Rowc++; 
            }

        } catch (FileNotFoundException ex) {
           System.out.println("FILE NOT FOUND \n"+ex);
        }
        
    }
    
    //counting rows in a file 
    public int countRows(String fileName) throws IOException {
    try (InputStream is = new BufferedInputStream(new FileInputStream(fileName))) {
    byte[] c = new byte[1024];
    int count = 0;
    int readChars = 0;
    boolean empty = true;
    while ((readChars = is.read(c)) != -1) {
        empty = false;
        for (int i = 0; i < readChars; ++i) {
            if (c[i] == '\n') {
                ++count;
            }
        }
    }
    return (count == 0 && !empty) ? 1 : count;
    }
    
    }
    
   
}
