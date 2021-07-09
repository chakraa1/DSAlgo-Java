package com.dsalgo.prefixsum;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 
 * Problem statement - https://leetcode.com/problems/continuous-subarray-sum/
 */
public class ContinuousSubarraySum {

	private int[] getRemainderOfPrefixSum(int[] input, int n, int k) {

		int[] remainder = new int[n];
		remainder[0] = input[0];
		remainder[0] %= k;
		for (int i = 1; i < n; i++) {
			remainder[i] = remainder[i - 1] + input[i];
			remainder[i] %=k;
		}

		System.out.println("remainder array --> " + Arrays.toString(remainder));
		return remainder;

	}
	
	private boolean doesSubarrayExists(int[] input, int n, int k) {
		int [] remainders=getRemainderOfPrefixSum(input,n, k);
		HashMap<Integer, Integer> remainderToIndexMap= new HashMap<>();
		
		for (int i = 1; i < n; i++) {
			if(remainderToIndexMap.get(remainders[i]) != null && remainderToIndexMap.get(remainders[i]) > 0) {
				return true;
			}else {
				remainderToIndexMap.put(remainders[i], i);
			}
		}
		
		remainderToIndexMap=null; 
		
		return false;

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr= {23,2,4,6,7}; int k=6;
		//int[] arr = { 23, 2, 6, 4, 7 };int k = 13;

		ContinuousSubarraySum sol = new ContinuousSubarraySum();
		System.out.println(" result --> " + sol.doesSubarrayExists(arr, arr.length, k));

	}

}
