package com.dsalgo.array;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/find-k-closest-elements/
Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
An integer a is closer to x than an integer b if:
|a - x| < |b - x|, or
|a - x| == |b - x| and a < b
Example 1:
Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:
Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]
Constraints:
1 <= k <= arr.length
1 <= arr.length <= 104
arr is sorted in ascending order.
-104 <= arr[i], x <= 104
*/
public class KClosestElements {

	/*
	 * Approach: 1)we have to binary search with left = 0, right = length-k, mid =
	 * (left+right)/2 2) while doing this, moving of array should be on the below
	 * formula if( x-arr[mid] > arr[mid+k]-x) then left side is more farther than
	 * right side, so we move the left to mid+1, otherwise move the right to mid.
	 * idea is to find left bound, once we find the left bound, The K numbers would
	 * be left+k-1 ( why -1?? because left is already included).
	 */
	private List<Integer> findClosestElements(int[] arr, int k, int x) {
		int left = 0, right = arr.length - k, mid = left + (right - left) / 2;
		List<Integer> closestElements = new ArrayList<>();
		while (left < right) {
			if (x - arr[mid] > arr[mid + k] - x) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		for (int i = left; i < (left + k); i++) {
			closestElements.add(arr[i]);
		}

		return closestElements;
	}

	public static void main(String[] args) {
		KClosestElements kClosestElements = new KClosestElements();
		int[] a = { 1, 2, 3, 4, 5 };
		System.out.println(kClosestElements.findClosestElements(a, 4, 3));
	}
}