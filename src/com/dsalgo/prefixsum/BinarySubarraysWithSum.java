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
		Map<Integer, Integer> psumFrequencyMap2 = new HashMap<Integer, Integer>();
		
		// Prefix sum
		int psum = 0;
		
		int count = 0;
		
		int exact_match=0;
		
		for (int i = 0; i < n; i++) {
			psum += input[i];
			
			if (psum == goal) {
				count++;
				exact_match++;
			}
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
			psumFrequencyMap2.put(psum-goal, psumFrequencyMap.getOrDefault(psum-goal, 0) + 1);
			
			System.out.println("psumFrequencyMap -->  "+psumFrequencyMap +" (psum -goal) ---> "+(psum -goal));
		}
		
		System.out.println(" exact match  --> "+exact_match);
		System.out.println("psumFrequencyMap2 -->  "+psumFrequencyMap2 );
		
		return count;

	}
	
	public static void main(String[] args) {
		//int arr[] = { 1,0,1,0,1 }; int goal=2;
		int arr[] = { 0, 1, 0, 1, 0, 1 }; int goal=2;
		
		System.out.println("Input sequence  --> "+ Arrays.toString(arr));
		BinarySubarraysWithSum soln = new BinarySubarraysWithSum();
		System.out.println("Solution approach "+soln.findSubarrayCountForGivenSum(arr, arr.length, goal));
		
	}

}
