package com.dsalgo.array;

/*
https://leetcode.com/problems/minimum-size-subarray-sum/
Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.

Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0

Constraints:

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 105
 */
import java.util.HashMap;

public class MinimumSizeSubArraySum {
    /*
    Approach: Using Prefix Sum and Hashmap
    1) Compute prefix sum by including the ith element
    2) Maintain a hash map with key as prefix Sum and value as index
    3) If difference between prefixSum and target element exists in map, means there are numbers exists
       whose sum is k
    4) get the size of the above array indices and mark it as candidate, if in next iterations, come across lesser size
    then that would be minimum size.
    */
    private int minimumSize(int[] a,int target){
        HashMap<Integer,Integer> map = new HashMap<>();
        int prefixSum = 0, minSize = 0,previousMinSize=0;
        for(int i = 0; i<a.length;i++){
            prefixSum = prefixSum + a[i];
            map.put(prefixSum,i);
            if(map.get(prefixSum-target)!=null){ //
                // the numbers between the following indexes sum will be K
              int fromIndex = map.get(prefixSum-target) + 1;
              int toIndex = i;
              minSize = (toIndex-fromIndex)+1;// calculating sub array size whose sum is K
              if(minSize <= previousMinSize){
                  previousMinSize = minSize;
              }
            }
        }
        return minSize;
    }

    /*
    * Approach: maintain 2 pointer i and j
    * loop through array with pointer j and once sum is more than or equal target element, mark the size of the sub array as minimum size
    * if sum is more than target, minus the first element from sub array and perform above step
    *
    * */
	private int minimum(int[] a, int target) {
		int minSize = 0, previousMinSize = 0, i = -1, j = 0, sum = 0;
		for (j = 0; j < a.length; j++) {
			sum = sum + a[j];
			while (sum >= target) {
				if (sum == target) { // record the sub array size whose sum is K
					minSize = j - i + 1;
					if (minSize <= previousMinSize) {
						previousMinSize = minSize;
					}
				}
				i++;
				sum = sum - a[i];
			}
		}
		return minSize;
	}

	public static void main(String[] args) {
		MinimumSizeSubArraySum minSize = new MinimumSizeSubArraySum();
		int[] a = { 2, 3, 1, 2, 4, 3 };
		// System.out.println (minSize.minimumSize(a,7));
		System.out.println(minSize.minimum(a, 7));
	}
}
