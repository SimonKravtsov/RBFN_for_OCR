# Using RBF Networks for OCR

## A radial basis function networks (RBFN) were used as a machine learning algorithm to recognize hand written digits Implemented in Java, on Neatbeans Platform 

## Algorithm 

This algorithm was chosen after a research on Support Vector Machines(SVM) and transforming data into higher dimensions, using different kernel functions. A particular kernel was researched, Gaussian/RBF kernel since it could transform data into infinite number of dimensions and the idea was to transform Hand Written data set into 64 dimensions, as each sample had 64 pixels and then find the decision boundary. In the process of research I came across RBF networks with uses the Gaussian kernel to perform transformation on data and and get  a boundary/ hyperplanes, as in normal SVM, however it then uses this hyperplanes to calculate and set the initial values for weights in the network. 
This already is a better approach then randomly setting weights as in basic neural network as it would already give you a good network accuracy. The final process is to train this network using standard training procedure as in typical neural network, back-propagation.

## Explanation of the flow of the program and brief data usage 

A program has 6 classes, and one main class that calls needed methods and controls the flow order. 
The program begins by loading the two data set files “cw2DataSet1.csv” and “cw2DataSet2.csv” to **ExtractingData.java** class where the first stage of data processing is being done. 

The first file that is being imported, will be used as a training set. This file is first copied into 2 dimensional array. After I decided to select 300 random centers (the number of centers have been tested with later, I decide to select 300 since it gave relatively good result without make calculations too computationally exhaustive in the later stages ), since all the samples in the file was already randomized the centers are taken by selecting the first 300 samples of training TrainingSet Array and copying them into Centers Array.  By then using k-means I clustered all data sets into total of 10 samples by taking the averages, the kmeans array, is later used to compared to each center by RBF kernel. Originally random 10 samples were chosen to be compared to the centers, after the k-means were added, which improved the accuracy of the results by approx 10%. The final data usage was to take the rest of the training samples from TraningSet Array excluding centers  and copy them into TrainOn Array, this array will be used to train network with back-propagation after getting the weight values. 
The second file that is imported is copied to the TestData array and being used as testing data, the final weights of the network will be used to test the accuracy of the network to predict the number of a sample.

After data usage, program from the main executes **RBFkernel.java** class where each row of k-means array is transformed into being transformed into higher dimensional space, each index is being compared to same index of the centers by RBF kernel and outputs a value between 1 and >0(excluding 0). 

Below in Fig.1 is shown RBF kernel.

<img src="https://user-images.githubusercontent.com/22767231/39408176-f3800f24-4bd2-11e8-9a92-23c8f42bbca8.png" align="middle" width=300 />


Initially after some research the value of sigma σ was set to 1.5, different values were experimented with, the best results were achieved by using small value of sigma 0.2, and decreasing any further was leading to over-fitting, and decrease in accuracy.    

Since we have 300 centers and with 64 columns and we are comparing it to 1 row of 64 columns, we will get the end result from of 64 by 300 matrix for each number from 0-9. The output of the row can be considered as a liner equations with the sum of dot products from weights and inputs, giving output from 0 to 1. At this stage we don’t have the weights, however we can set the outputs of the equation. The sample number is of the same value (0-9) with the center sample will have the output of 1 and different number will have output of 0. Fig.2 illustrates process of Gaussian kernel for each kmeans sample (0-9). 

<img src="https://user-images.githubusercontent.com/22767231/39408199-296d3666-4bd3-11e8-8cb1-521a61fa745e.png" align="middle" width=550 />

<img src="https://user-images.githubusercontent.com/22767231/39408208-47d359b4-4bd3-11e8-87e4-5f0594e6d2eb.png" align="middle" width=550 />

In Fig.3 is visualization of how the output should be look at, the f1,f2,f3… are the output of the function w is are the weights that will be found in the next stage, and the output is set to 1 or 0 depending on the value of the digit that centers were compared to.  

After transforming all the kmeans samples with **RBFkernel class** we have 10 300x64 arrays, which we have to convert to 10 weights one for each digit. My original idea was to have a 64x64 matrix, and by having same number of samples and unknowns you can find the exact values of weights, by simple calculating the inverse of the matrix and multiplying it by the output matrix. However it turned out that the matrix had no inverse, therefore an alternative method  had to be used by finding single value decomposition (SVD) which was done by **SingleValueDecomposition.java** class and then using least-squared regression, which would not give the exact value of weights, but still would be a close approximation. For this method the more number sample will give you the closest results of weights and as was mentioned before 300 centers were selected to get 300 samples. 
In class **SolvingForWeights.java** I get the 10 outputs (300x64) of RBFkernel and one by one finding 64 unknown weights for 10 digits. In this class I first convert 2d array from RBFkernel to 2 matrices one with the inputs and the second with outputs. 
All results of weights were saved into WieghtsResults 10x64 array each row for specific digit weights. 

After finding the weights the accuracy of the network was tested on the TestData array. The accuracy of prediction is tested using TestingNetwork.java class in this class the samples of TestData array is taken one by one and converted to 300x64 array using RBFkernel with the same centers, after the obtained weights are multiplied by the input of each row and the sum product of them results in output. Therefore we get 300 outputs, for one weight set row from WieghtsResults array. Now I take the sum of the outputs which are greater then 0.5 and are equal to the same digit of the weights that are used. Fig.4 show example of how algorithm predicts if the number is 0.

<img src="https://user-images.githubusercontent.com/22767231/39408239-7f0a3dc6-4bd3-11e8-8cd8-95f13c0dfd88.png" align="middle" width=550 />

In the example above even thou the sample in center 7 gave us higher output value when we combine the sum centers 0 we get higher output value. We then test the same sample with different weights 1-9 and get the sum of these centers. After the highest output sum is chosen to be the predicted value. The predicted value is compared to the actual value if they match. Finally all the matched samples are divided by the total test sample to get the percentage for the accuracy for the network. 
Since we do a two fold testing the datasets are swapped and the same procedure is repeated and the average of two results is taken as the accuracy of the network.  
After getting the weights from least-square regression the network already had accuracy of **82.5%**.

The next step was to train the network on the rest of the samples with back-propagation, which is a simple process of showing to the network a bunch of samples one by one and calculating how far off the networks prediction, then re-adjusting weights according to the error difference and the learning rate of the network. **BackPropagation.java** class is used to perform back-propagation on the obtained weights.

The formula that was used to calculate the weights

```
w = w +/- learning_rate *error*input
```
The error was calculate by taking the absolute difference between the expected and the actual output of the system. The learning rate determines the scale of how much the weights are going to change, if error is small then we should use small value for the learning rate and further decrease as the error decreases, either linearly or exponentially. To train the weights of RNB network, I chose a small initial value of l_rate of 0.001 since, network already had relatively small error, and after each iteration I decreased l_rate exponentially by using formula.

```
l_rate = l_rate * (1-x)²
```
Where x is the percentage by how much l_rate should decrease. Since we adding a square, we will get exponential decay.  

Back-propagation is a slow process, taking a lot of processing power. I did not have an enough time to run back-propagation throw all train data with enough iterations, since it took a very long time. Therefore I had to manually split train data to train the network over portion of samples with around 10 iterations, and train it over couple of days. The weights which gave me the best results i saved to csv file, so I can continue training those later. Unfortunately I did not manage to train weights on the whole training data, with ideal number of iterations, which would be at least around a 1000. 

## Discussions of results 

So far the network after back-propagation has an accuracy of **90.9%** . That is 8.4% increase after obtaining original weights using least-square regression. The accuracy of the network, however can still be increase as not even half of the training samples were used and not as much as 1000 iterations were made. There are also other ways that would be interesting to see the observe. There are a lot of variables that can be changed, in the RBF network to effect the result, such as the value of number of centers (which can also be selected by clustering instead of random selection), the sigma value can also be changed which would effect the networks accuracy. The original value of learning rate and the amount on which it should decrease each iteration will also effect the final result. There are a lot of variables that effect the networks final output, and without and exhaustive testing of how each variable effects the output in relationship with one another, it is hard to say which are the ideal settings for the current network. 

## Procedure of running a project

After clonning the repository, project folder can be opened with Netbeans. 
```
git clone https://github.com/SimonKravtsov/RBFN_for_OCR.git
```
When the program is run from the main the the accuracy with the trained weights after back-propagation will be displayed, and same results after switching datasets.

<img src="https://user-images.githubusercontent.com/22767231/39408298-75e0806a-4bd4-11e8-89c4-1710f17cc7e1.png" align="middle" width=400 />

On line 60 loads the already trained weights which gives close to 91% accuracy, the improve.updateWeights() method that starts back-propagation was commented.

<img src="https://user-images.githubusercontent.com/22767231/39408310-95ed4618-4bd4-11e8-8787-67013a4243bf.png" align="middle" width=550 />

The Back-propagation class can be tested, by commenting lines 60 and 81 and enabling lines 57 and 79. To see the effect of back-propagation, the best is to test it over small sample since it would take extremely long time to run thou all sample with multiple iterations. 
The iteration number can be changed on line 44 in and sample size on line 45 in **BackPropagation.java** class. 
Other variables such as number of centers can be changed in **ExtractingData.java** line 26, 
sigma value in **RBFkernel.java** line 17 and learning rate in **BackPropagation.java** should be changed on lines 23 and 42. 


