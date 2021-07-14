package com.dsalgo.prefixsum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 
 * Problem - https://leetcode.com/problems/subarray-sum-equals-k/
 * ------------------
 * Solution approach : 
 * ------------------
 *  sum[i+1,j] = PS[j] - PS[i]
 *  k= PS[j] - PS[i]
 *  PS[i] = PS[j] -k
 *  
 * 
 * */
public class SubarraySumEqualsK {
	private int findSubarrayCountForGivenSum(int[] input,int n, int k) {
		Map<Integer, Integer> psumFrequencyMap = new HashMap<Integer, Integer>();
		
		// Prefix sum
		int psum = 0;
		
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			psum += input[i];
			
			/*
			 * Checking exact match of current sum with given sum
			 */
			if (psum == k) {
				count++;
			}
			
			/*
			 * sum(i+1,j) i.e. goal = psum[j] - psum[i] 
			 * goal= psum[j] - psum[i] 
			 * psum[i] = psum[j] - goal
			 * 
			 */
			if(psumFrequencyMap.containsKey(psum -k)) {
				count++;
			}
			
			if(!psumFrequencyMap.containsKey(psum)) {
				psumFrequencyMap.put(psum, 1);
			}else {
				psumFrequencyMap.put(psum, psumFrequencyMap.get(psum)+1);
			}
			
		}
		
		return count;

	}
	
	public static void main(String[] args) {
		//int arr[] = { 1,1,1 }; int k=2;
		//int arr[] = { 1,2,3 }; int k=3;
		int arr[] = { 3,4,7,2,-3,1,4,2 }; int k=7;
		
		System.out.println("Input sequence  --> "+ Arrays.toString(arr));
		SubarraySumEqualsK soln = new SubarraySumEqualsK();
		System.out.println("Number of sub-arrays "+soln.findSubarrayCountForGivenSum(arr, arr.length, k));
		
	}

}
