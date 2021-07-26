package com.dsalgo.slidingwindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

/*
 * Problem - https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/
 * 
 * Given an array of integers arr and two integers k and threshold.
 * Return the number of sub-arrays of size k and average greater than or equal to threshold.
 * 
 * */
public class NumberSubSizeKandAverageGreateThreshold {
	
	 	
	private int numOfSubarrays(int[] input, int n, int windowSize, int threshold) {
		int [] psum=new int [n];
		psum[0]=input[0];
		for(int i=1;i<n;i++) {
			psum[i] =psum[i-1] +input[i];
		}
		
		double avg=psum[windowSize -1]/windowSize;
		int ans = avg >= threshold ? 1 : 0;
		
		for(int i=windowSize; i<n;i++) {
			avg=(psum[i]-psum[i-windowSize])/windowSize;
			if(avg >= threshold) ans++;
		}
		
		return ans;
	}
	
	public static void main(String[] args) { 
		
		/*
		 * Example 1: Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4, Output: 3
		 * 
		 * Explanation: 
		 * Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively. 
		 * All other sub-arrays of size 3 have averages less than 4 (the threshold).
		 * 
		 * Example 2: Input: arr = [1,1,1,1,1], k = 1, threshold = 0; Output: 5
		 * 
		 * 
		 * Example 3: Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5. Output: 6
		 * Explanation: 
		 * The first 6 sub-arrays of size 3 have averages greater than 5. Note that averages are not integers.
		 */
		//int arr[]= {2,2,2,2,5,5,5,8}; int k=3; int threshold = 4;
		//int arr[]= {1,1,1,1,1}; int k=1; int threshold = 0;
		int arr[]= {11,13,17,23,29,31,7,5,2,3}; int k=3; int threshold = 5;

		System.out.println("Input sequence  --> " + Arrays.toString(arr) + " window size " + k);
		NumberSubSizeKandAverageGreateThreshold soln = new NumberSubSizeKandAverageGreateThreshold();
		System.out.println("number of sub-arrays of size k and average greater than or equal to threshold -> "
				+ soln.numOfSubarrays(arr, arr.length, k,threshold));
	}

}
