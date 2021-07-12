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
 * 
 * */
public class BinarySubarraysWithSum {
	private int findFrequencySubarrayWithSum(int[] input,int n, int goal) {
		Map<Integer, Integer> psumFrequencyMap = new HashMap<Integer, Integer>();
		
		// Prefix sum
		int psum = 0;
		
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			psum += input[i];
			
			if (psum == goal)
				count++;
			
			/*
			 * Problem reduces to finding # of pairs (i, j) (i < j) such that
			 * sum(i+1,j) i.e. goal = psum[j] - psum[i] 
			 * goal= psum[j] - psum[i] 
			 * psum[i] = psum[j] - goal
			 * 
			 */
			if (psumFrequencyMap.containsKey(psum - goal)) {
				count += psumFrequencyMap.get(psum - goal); // Not able to understand this line ,why not only count++
			}
			
			// If psum not found earlier , please put it into the map
			psumFrequencyMap.put(psum, psumFrequencyMap.getOrDefault(psum, 0) + 1);
			
			System.out.println("psumFrequencyMap -->  "+psumFrequencyMap);
		}
		
		return count;

	}
	
	public static void main(String[] args) {
		int arr[] = { 1,0,1,0,1 }; int goal=2;
		System.out.println("Input sequence  --> "+ Arrays.toString(arr));
		BinarySubarraysWithSum soln = new BinarySubarraysWithSum();
		System.out.println("Solution approach "+soln.findFrequencySubarrayWithSum(arr, arr.length, goal));
		
	}

}
