package com.dsalgo.slidingwindow;

import java.util.Arrays;

/*
 * Problem - https://leetcode.com/problems/maximum-average-subarray-i/
 * 
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and 
 * return this value. Any answer with a calculation error less than 10^-5 will be accepted.
 *
 * */
public class MaximumAverageSubarrayI {
	
	private double findMaxAverageBruteForce(int[] input,int n,int windowSize) {		
		double maxAvg=0;
		double currentWindowSum=0;
		
		if(windowSize >=n) {
			return findMaxAverageWindowMorethanInputArray(input,n,windowSize);
		}
		
		for(int i=0; i<n-windowSize+1; i++){
			currentWindowSum=0;
			for(int j=i; j<=i+windowSize-1 && j<n; j++){
				currentWindowSum +=input[j];
			}
	        maxAvg=Math.max(maxAvg, currentWindowSum / windowSize);

	    }
		
		return maxAvg;
		
	}
	
	private double findMaxAverageSlidingWindow(int[] input,int n,int windowSize) {		
		double maxAvg=0;
		double currentWindowSum=0;
		
		if(windowSize >=n) {
			return findMaxAverageWindowMorethanInputArray(input,n,windowSize);
		}
		
		// calculating sum of first elements of window size 
		for(int i=0; i<windowSize; i++) currentWindowSum +=input[i];
		
		maxAvg=currentWindowSum / windowSize;
		
		for(int i=windowSize; i<n; i++){
			currentWindowSum = (currentWindowSum + input[i]) - input[i-windowSize];
	        maxAvg=Math.max(maxAvg, currentWindowSum / windowSize);
	    }
		
		return maxAvg;
		
	}
	
	private double findMaxAveragePrefixSum(int[] input,int n,int windowSize) {		
		double maxAvg=0;
		double []prefixSum = new double[n];	
		
		if(windowSize >=n) {
			return findMaxAverageWindowMorethanInputArray(input,n,windowSize);
		}
		
		prefixSum[0]=input[0];
		for(int i=1; i<n; i++) prefixSum[i]=prefixSum[i-1]+input[i];
				
		maxAvg=prefixSum[windowSize-1]/windowSize;
		
		for(int i=windowSize; i<n; i++){
	        maxAvg=Math.max(maxAvg, (prefixSum[i] - prefixSum[i-windowSize]) / windowSize); 
	    }
		
		return maxAvg;
		
	}
	
	private double findMaxAverageWindowMorethanInputArray(int[] input,int n,int windowSize) {
		double currentWindowSum=0;
		
		for(int i=0; i<n; i++) currentWindowSum +=input[i];
		
		return currentWindowSum / windowSize;
	}
	public static void main(String[] args) {
		
		/*
		 * Example 1: nums = [1,12,-5,-6,50,3], k = 4 Output: 12.75000 
		 * Example 2: nums = [5], k = 1 Output: 5.00000 
		 * 
		 */
		
		int arr[] = { 1,12,-5,-6,50,3 };int windowSize=4;
		//int arr[] = { 5};int windowSize=1;
		
		System.out.println("Input numbers  --> "+ Arrays.toString(arr) +" window size "+windowSize);
		MaximumAverageSubarrayI soln = new MaximumAverageSubarrayI();
		System.out.println("Max average (BruteForce Approach) -> "+soln.findMaxAverageBruteForce(arr, arr.length,windowSize));
		System.out.println("Max average (SlidingWindow Approach) -> "+soln.findMaxAverageSlidingWindow(arr, arr.length,windowSize));
		System.out.println("Max average (PrefixSum Approach) -> "+soln.findMaxAveragePrefixSum(arr, arr.length,windowSize));

	}

}
