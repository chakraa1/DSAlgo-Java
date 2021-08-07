package com.dsalgo.prefixsum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.dsalgo.binarysearch.MissingNumberInConsecutiveNumbers;

/* 
 * Problem - https://leetcode.com/problems/binary-subarrays-with-sum/
 * Solution - Need to find all possible sub arrays that sums to goal 
 * 
 * Input- [1,0,1,0,1] goal =2 
 * Output - 4
 * Possible combinations :
 * 
 * [1,0,1]    Range index [0-2]
 * [1,0,1,0]  Range index [0-3]
 * [0,1,0,1]  Range index [1-4]
 * [1,0,1]    Range index [2-4]
 * 
 * ------------------
 * Solution approach : 
 * ------------------
 * PS     [1,1,2,2,3]
 * 
 * [1,0,1,0,1]
 *      ^   ^
 *      i   j
 *  sum[i+1,j] = PS[j] - PS[i]
 *  k= PS[j] - PS[i]
 *  PS[i] = PS[j] -k
 *  
 *      0  1  2  3  4  5
 * A = [0, 1, 0, 1, 0, 1], goal = 2
 * 
 * (0,0) --> 0 ==> 1 | sum(0,0) - goal = -2
 * (0,1) --> 1 ==> 1 | sum(0,1) - goal = -1
 * (0,2) --> 1 ==> 2 | sum(0,2) - goal = -1
 * (0,3) --> 2 ==> 1 | sum(0,3) - goal = 0
 * (0,4) --> 2 ==> 2 | sum(0,4) - goal = 0
 * (0,5) --> 3 ==> 1 | sum(0,5) - goal = 1
 * 
 * 
 * (1,1) --> 1 ==> 1 | sum(1,1) - goal = -1
 * (1,2) --> 1 ==> 2 | sum(1,2) - goal = -1
 * (1,3) --> 2 ==> 1 | sum(1,3) - goal = 0
 * (1,4) --> 2 ==> 2 | sum(1,4) - goal = 0
 * (1,5) --> 3 ==> 1 | sum(1,5) - goal = 1
 * 
 * 
 * (2,2) --> 0 ==> 1 | sum(2,2) - goal = -2
 * (2,3) --> 1 ==> 1 | sum(2,3) - goal = -1
 * (2,4) --> 1 ==> 2 | sum(2,4) - goal = -1
 * (2,5) --> 2 ==> 1 | sum(2,5) - goal = 0
 * 
 * (3,3) --> 1 ==> 1 | sum(3,3) - goal = -1
 * (3,4) --> 1 ==> 2 | sum(3,4) - goal = -1
 * (3,5) --> 2 ==> 1 | sum(3,5) - goal = 0
 * 
 * (4,4) --> 0 ==> 1 | sum(4,4) - goal = -2
 * (4,5) --> 1 ==> 1 | sum(4,5) - goal = -1
 * 
 * (5,5) --> 1 ==> 1 | sum(5,5) - goal = -1
 * 
 * ======================================================
 * Valid solutions
 * ======================================================
 * (0,3) --> 2 ==> 1 | sum(0,3) - goal = 0
 * (0,4) --> 2 ==> 2 | sum(0,4) - goal = 0
 * (1,3) --> 2 ==> 1 | sum(1,3) - goal = 0
 * (1,4) --> 2 ==> 2 | sum(1,4) - goal = 0
 * (2,5) --> 2 ==> 1 | sum(2,5) - goal = 0
 * (3,5) --> 2 ==> 1 | sum(3,5) - goal = 0
 * 
 * 
 * */
public class BinarySubarraysWithSum {
	private int findSubarrayCountForGivenSum(int[] input,int n, int goal) {
		Map<Integer, Integer> psumFrequencyMap = new HashMap<Integer, Integer>();
		
		// Prefix sum
		int psum = 0;
		
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			psum += input[i];
			
			if (psum == goal) {
				count++;
			}
			
			/*
			 * Problem reduces to finding # of pairs (i, j) (i < j) such that
			 * sum(i+1,j) i.e. goal = psum[j] - psum[i] 
			 * goal= psum[j] - psum[i] 
			 * psum[i] = psum[j] - goal
			 * 
			 */
			if (psumFrequencyMap.containsKey(psum - goal)) {
				count += psumFrequencyMap.get(psum - goal); 
			}
			
			// If psum not found earlier , please put it into the map
			psumFrequencyMap.put(psum, psumFrequencyMap.getOrDefault(psum, 0) + 1);
						
			System.out.println(" i -> "+i + " | psum -> "+psum +" | (psum - goal) -> "+(psum - goal) + " | psumFrequencyMap -> "+psumFrequencyMap  +" | psumFrequencyMap.get(psum - goal) "+psumFrequencyMap.get(psum - goal) +" | count -> "+count);

		}
		
		
		return count;

	}
	
	
	private int findSubarrayCountForGivenSumWithoutHashMap(int[] input, int n, int goal) {
		int[] frequency = new int[n];
		int[] psum = new int[n];
		
		System.out.println("input array "+Arrays.toString(input));

		for (int i = 0; i < n; i++) {
			frequency[i] = 0;
		}
		
		psum[0]=input[0];
		for (int i = 1; i < n; i++) {
			psum[i] = psum[i-1]+input[i];
		}
		
		System.out.println("psum array "+Arrays.toString(psum));
		
		int ans = 0;

		for (int i = 0; i < n; i++) {
			if((psum[i] - goal) >=0) {
				ans += frequency[psum[i] - goal];
				frequency[psum[i] - goal]++;
			}
		}
		
		System.out.println("frequency array "+Arrays.toString(frequency));

		return ans;

	}
	
	private int findSubarrayCountOptimized(int[] input, int n, int goal) {
		int[] frequency = new int[n+1];
		int[] psum = new int[n];
		
		System.out.println("input array "+Arrays.toString(input));

		for (int i = 0; i < n; i++) {
			frequency[i] = 0;
		}
		
		psum[0]=input[0];
		for (int i = 1; i < n; i++) {
			psum[i] = psum[i-1]+input[i];
		}
		
		System.out.println("psum array "+Arrays.toString(psum));
		
		int ans = 0;

		for (int i = 0; i < n; i++) {
			if(psum[i] == goal) ans++;
			
			if((psum[i] - goal) >=0) {
				ans += frequency[psum[i] - goal];
			}
			
            frequency[psum[i]]++;
		}
		
		System.out.println("frequency array "+Arrays.toString(frequency));

		return ans;

	}
	 
	public static void main(String[] args) {
		//int arr[] = { 1,0,1,0,1 }; int goal=2;
		int arr[] = { 0, 1, 0, 1, 0, 1 }; int goal=2;
		
		System.out.println("Input sequence  --> "+ Arrays.toString(arr));
		BinarySubarraysWithSum soln = new BinarySubarraysWithSum();
		System.out.println("Number of sub-arrays "+soln.findSubarrayCountForGivenSum(arr, arr.length, goal));
		System.out.println("Number of sub-arrays (without hashmap) "+soln.findSubarrayCountForGivenSumWithoutHashMap(arr, arr.length, goal));
		System.out.println("Number of sub-arrays (optimized) "+soln.findSubarrayCountOptimized(arr, arr.length, goal));

		
	}

}
