package com.dsalgo.slidingwindow;

import java.util.Arrays;

/*
 * Problem - https://leetcode.com/problems/max-consecutive-ones-iii/
 * 
 * Given a binary array nums and an integer k, return the maximum
 * number of consecutive 1's in the array if you can flip at most k 0's.
 *
 *
 * Constraints: 
 * 
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 * 0 <= k <= nums.length
 * */
public class MaxConsecutiveOnesIII {
	
	private int longestOnesSlidingWindow(int[] nums, int k) {		
		int left = 0, ans = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                k--;
            }
            while (k < 0 && left <= right) {
                if (nums[left] == 0) {
                    k++;
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        
        return ans;
		
	}
	
	public static void main(String[] args) {
		
		/*
		 * Example 1: Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2 Output: 6
		 * Explanation: [1,1,1,0,0,1*,1,1,1,1,1*]
		 * '*' numbers were flipped from 0 to 1. The longest subarray is underlined. 
		 * 
		 * 
		 * Example 2: Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3 Output: 10
		 * Explanation: [0,0,1,1,1*,1*,1,1,1,1*,1,1,0,0,0,1,1,1,1]
		 * '*' numbers were flipped from 0 to 1. The longest subarray is underlined.
		 * 
		 */
		
		//int nums[] = { 1,1,1,0,0,0,1,1,1,1,0};int k=2;
		//int nums[] = { 0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1 };int k=3;
		//int nums[] = { 0,0,0,1};int k=4;
		int nums[]={1,1,1,0,0,0,1,1,1,1};int k=0;
		//int nums[]={0,0,1,1,1,0,0,1,1,1,1};int k=0;
		//int nums[]={0,0,1,1,1,0,0};int k=0;
		//int nums[]= {1,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1,1,0,1,1};int k=8;
		
		System.out.println("Input numbers  --> "+ Arrays.toString(nums) +" window size "+k);
		MaxConsecutiveOnesIII soln = new MaxConsecutiveOnesIII();
		System.out.println("Max consecutive Ones (SlidingWindow Approach) -> "+soln.longestOnesSlidingWindow(nums, k));

	}

}
