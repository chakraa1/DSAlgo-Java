package com.dsalgo.prefixsum;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 
 * Problem statement - https://leetcode.com/problems/continuous-subarray-sum/
 * 523. Continuous Subarray Sum
 */
 
/*
 * Find if there exists a subarray of length at least 2 such that the sum of
 * that subarray is divisible by k
 *
 * sum of a range [a, b] : prefix[b] - prefix[a-1] sum % k : (prefix[b] -
 * prefix[a-1]) % k; m and n are any integers (m-n) is divisible by k m-n = k*q,
 * m = k*q1 + r1 , n = k*q2 + r2 m-n = k*(q1-q2) + (r1-r2) = k*q or r1-r2 = 0 or
 * r1 = r2 m-n is divisible by k if m%k = n%k
 *
 * sum of range [a, b] is divisible by k iff prefix[b]%k = prefix[a-1]%k Hence
 * we've to find the index where remainders are same
 */

public class ContinuousSubarraySum {

	/*
	 * private int[] getRemainderOfPrefixSum(int[] input, int n, int k) {
	 * 
	 * int[] prefix_sum = new int[n]; prefix_sum[0] = input[0]; for (int i = 1; i <
	 * n; i++) { prefix_sum[i] = prefix_sum[i - 1] + input[i]; }
	 * 
	 * System.out.println("prefix sum array --> " + Arrays.toString(prefix_sum));
	 * 
	 * int[] remainder = new int[n]; for (int i = 0; i < n; i++) { if (k > 0)
	 * remainder[i] = prefix_sum[i] % k; }
	 * 
	 * System.out.println("remainder array --> " + Arrays.toString(remainder));
	 * return remainder;
	 * 
	 * }
	 */
	/*
	 * private boolean doesSubarrayExists(int[] input, int n, int k) { int[]
	 * remainders = getRemainderOfPrefixSum(input, n, k); HashMap<Integer, Integer>
	 * remainderToIndexMap = new HashMap<>();
	 * 
	 * for (int i = 0; i < n; i++) {
	 * 
	 * Checking whether same remainder found earlier or not If found that means ,
	 * they are equal Hence a sub array exists
	 * 
	 * if (remainderToIndexMap.containsKey(remainders[i])) { return true; } else {
	 * remainderToIndexMap.put(remainders[i], i); } }
	 * 
	 * remainderToIndexMap = null;
	 * 
	 * return false;
	 * 
	 * }
	 */
	private boolean doesSubarrayExistsWithoutHashmap(int[] input, int n, int k) {
		int[] prefix_sum = new int[n];
		int[] frequency = new int[k]; // frequency array should be of size k
		System.out.println("frequency --> " + Arrays.toString(frequency));

		prefix_sum[0] = input[0];
		for (int i = 1; i < n; i++) {
			prefix_sum[i] = prefix_sum[i - 1] + input[i];
		}

		System.out.println("prefix sum array --> " + Arrays.toString(prefix_sum));

		for (int i = 1; i < n; i++) {
			int remainder = prefix_sum[i] % k;
			if (frequency[remainder] > 0) { // i> 0 because sub array has to at-least 2
				return true;
			} else {
				/**
				 * sum of sub-array range [i,j] will be divisible by k only if (prefix[j]-
				 * prefix[i-1]) %k =0 In other words prefix[i-1]%k = prefix[j] %k Hence we're
				 * storing the remainder flag at i-1
				 */
				frequency[prefix_sum[i - 1] % k] += 1;
			}

			System.out.println("frequency --> " + Arrays.toString(frequency));
		}

		System.out.println("frequency --> " + Arrays.toString(frequency));

		return false;

	}

	/*
	 * private int [] findRangeOfSubarrayIfExists(int[] input, int n, int k) { int
	 * [] remainders=getRemainderOfPrefixSum(input,n, k); HashMap<Integer, Integer>
	 * remainderToIndexMap= new HashMap<>();
	 * 
	 * for (int i = 1; i < n; i++) {
	 * 
	 * Checking whether same remainder found earlier or not If found that means ,
	 * they are equal Hence a sub-array exists
	 * 
	 * if(remainderToIndexMap.containsKey(remainders[i])) { int[] matched_subarray=
	 * new int[2]; matched_subarray[1]=input[i+1];
	 * matched_subarray[0]=input[remainderToIndexMap.get(remainders[i])]; return
	 * matched_subarray; }else { remainderToIndexMap.put(remainders[i], i); } }
	 * 
	 * remainderToIndexMap=null;
	 * 
	 * return null;
	 * 
	 * }
	 */

	public static void main(String[] args) {
		int[] arr = { 23, 2, 6, 4, 7 };
		int k = 6;
		// int [] arr= {23,2,4,6,7}; int k=6;
		// int[] arr = { 23, 2, 6, 4, 7 };int k = 13;
		System.out.println("Input array --> " + Arrays.toString(arr));
		ContinuousSubarraySum sol = new ContinuousSubarraySum();

		//System.out.println("doesSubarrayExists --> " + sol.doesSubarrayExists(arr, arr.length, k));
//		System.out.println("Matched sub array start and end element --> "
//				+ Arrays.toString(sol.findRangeOfSubarrayIfExists(arr, arr.length, k)));

		System.out.println(
				"doesSubarrayExistsWithoutHashmap --> " + sol.doesSubarrayExistsWithoutHashmap(arr, arr.length, k));
	}

}
