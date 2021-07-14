package com.dsalgo.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Problem - https://leetcode.com/problems/contains-duplicate-ii/
 * 
 * Given an integer array nums and an integer k, return true if there are two distinct indices
 * i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 * 
 * */
public class ContainsDuplicateII {
	
	private boolean containsNearbyDuplicate(int[] input,int n,int windowSize) {
		Map<Integer, Integer> uniqueElementMap = new HashMap<Integer, Integer>();
		
		for(int i=0; i<n; i++){
	        if(uniqueElementMap.containsKey(input[i])){
	            int prevFoundIndex = uniqueElementMap.get(input[i]);
	            if(i-prevFoundIndex <= windowSize)
	                return true;
	        }
	 
	        uniqueElementMap.put(input[i], i);
	    }
		
		return false;
		
	}

	public static void main(String[] args) {
		
		/*
		 * Example 1: Input: nums = [1,2,3,1], k = 3 Output: true 
		 * Example 2: Input: nums = [1,0,1,1], k = 1 Output: true 
		 * Example 3: Input: nums = [1,2,3,1,2,3], k = 2 Output: false
		 */
		
		//int arr[] = { 1,2,3,1 };int windowSize=3;
		int arr[] = { 1,0,1,1 };int windowSize=1;
		//int arr[] = { 1,2,3,1,2,3 };int windowSize=2;
		
		System.out.println("Input sequence  --> "+ Arrays.toString(arr) +" window size "+windowSize);
		ContainsDuplicateII soln = new ContainsDuplicateII();
		System.out.println("Duplicate found -> "+soln.containsNearbyDuplicate(arr, arr.length,windowSize));


	}

}
