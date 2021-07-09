package com.dsalgo.prefixsum;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 
 * Problem statement - https://leetcode.com/problems/continuous-subarray-sum/
 */


/*
 * Find if there exists a subarray of length at least 2 such that the sum of that subarray is divisible by k
 *
 * sum of a range [a, b] : prefix[b] - prefix[a-1]
 * sum % k : (prefix[b] - prefix[a-1]) % k;
 * m and n are any integers (m-n) is divisible by k
 * m-n = k*q, m = k*q1 + r1 , n = k*q2 + r2
 * m-n = k*(q1-q2) + (r1-r2) = k*q
 * or r1-r2 = 0 or r1 = r2
 * m-n is divisible by k if m%k = n%k
 *
 * sum of range [a, b] is divisible by k iff prefix[b]%k = prefix[a-1]%k
 *
 * */

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
