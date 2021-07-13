package com.dsalgo.prefixsum;

import java.util.Arrays;

/**
 * Problem statement -
 * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
 * 
 * Given an integer array nums and an integer k, find three non-overlapping
 * subarrays of length k with maximum sum and return them. Return the result as
 * a list of indices representing the starting position of each interval
 * (0-indexed). If there are multiple answers, return the lexicographically
 * smallest one.
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,1,2,6,7,5,1], k = 2 Output: [0,3,5] Explanation: Subarrays
 * [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5]. We could
 * have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically
 * larger. Example 2:
 * 
 * Input: nums = [1,2,1,2,1,2,1,2,1], k = 2 Output: [0,2,4]
 * 
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 2 * 10^4 1 <= nums[i] < 2^16 ****** (what is significance
 * of this constraint) ******* 1 <= k <= floor(nums.length / 3) ****** (what is
 * significance of this constraint) *******
 */

/*
 * Solution approach - (sub-array problems , try to fix an index i) Step 1 -
 * Let's fix the size of middle sub-array it will range from i to i+k-1
 * 
 * Input - [1,2,1,2,6,7,5,1] , sub-array size k=2
 * 
 * i=1 L= [1,2] , M=[1,2] R=[6,7,5,1] --> [6,7] , [7,5] , [5,1] : Max sum - 3(L) + 3(M) + 13(R) =19 
 * i=2 L= [1,2,1] --> [1,2] , [2,1] , M=[2,6] R=[7,5,1] --> [7,5] , [5,2] : Max sum - 3(L) + 8(M) + 12(R) =23 
 * i=3 L= [1,2,1,2] --> [1,2] , [2,1] [1,2] , M=[6,7] R=[5,1] : Max sum - 3(L) + 13(M) + 6(R) =22
 * 
 * Answer - Max(19,23,22) = 23
 * 
 */

/*
 * Complexity - nlog(n)
 * 
 */

public class MaximumSumNonOverlappingSubarrays {
	private int findMax(int[] nums, int k) {
		int n = nums.length;

		int[] prefix_sum = new int[n];
		int[] max_l = new int[n];
		int[] max_r = new int[n];

		System.out.println("Input array -->" + Arrays.toString(nums));

		// Generate prefix sum
		prefix_sum[0] = nums[0];
		for (int i = 1; i < n; i++) {
			prefix_sum[i] = prefix_sum[i - 1] + nums[i];
		}

		System.out.println("prefix sum array -->" + Arrays.toString(prefix_sum));

		/*
		 * Forming max_l array 
		 * Sum in range [i,i+k-1] can be interpreted as prefix_sum[i] - prefix_sum[i - k]
		 */
		max_l[k - 1] = prefix_sum[k - 1];
		for (int i = k; i < n; i++) {
			max_l[i] = Math.max(max_l[i - 1], prefix_sum[i] - prefix_sum[i - k]);
		}

		System.out.println("max_l -->" + Arrays.toString(max_l));

		/* Forming max_r array
		 * 
		 * Right array will range from N-K to N-1
		 * Hence N-K is the "last index" that we can choose for the last sub-array
		 * 
		 */
		
		/*1,2,3,4,5,6,17, 8,9,10,11
		S(1...17) + 8+ S1  = T
		8+S1(8,9,10,11)
		*/
		
		max_r[n - k] = prefix_sum[n - 1];
		// why are we checking this ?
		if ((n - k - 1) >= 0)
			max_r[n - k] -= prefix_sum[n - k - 1];

		System.out.println("max_r -->" + Arrays.toString(max_r));

		/*
		 * why reverse loop - max_r is calculated in reverse way than max_l.
		 *
		 */
		// why prefix of prefix_sum[i + k - 1] - prefix_sum[i - 1] 
		for (int i = n - k - 1; i >= 0; i--) {
			max_r[i] = Math.max(max_r[i], prefix_sum[i + k - 1] - (i > 0 ? prefix_sum[i - 1] : 0));
		}

		int ans = 0;
		/*
		 * If we fix the middle element in range [i,i+k-1] then max of 3 non overlapping
		 * sub array would be as below max(max_l[i - 1] + sum (i, i+k-1) +max_r[i + k]
		 */
		for (int i = k; (i + k - 1) < n - k; i++) {
			ans = Math.max(ans, (max_l[i - 1] + max_r[i + k] + prefix_sum[i + k - 1] - prefix_sum[i - 1])); 
		}

		return ans;
	}

	public static void main(String[] args) {
		int arr[] = { 1, 2, 1, 2, 6, 7, 5, 1 };
		// int arr[] = {1,2,1,2,1,2,1,2,1};
		int k = 2;
		MaximumSumNonOverlappingSubarrays sol = new MaximumSumNonOverlappingSubarrays();
		System.out.println("Max sum --> " + sol.findMax(arr, k));

	}

}
