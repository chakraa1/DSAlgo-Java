package com.dsalgo.array;

import java.util.Arrays;

/*
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
34. Find First and Last Position of Element in Sorted Array
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.
Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:
Input: nums = [], target = 0
Output: [-1,-1]
Constraints:
0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
 */
public class FirstAndLastPositionInSorted {
	/*
	 * Binary Search Approach 1. Check if the target is there in left part of the
	 * array/right part 2. Once element is found, do binary search on left side
	 * again to find first occurrence and binary search on right side to find last
	 * occurrence
	 */
	private int[] searchRange(int[] nums, int target) {
		int[] result = { -1, -1 };
		int left = 0, right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				result[0] = binarySearchLeftSide(left, mid - 1, nums, target);
				result[1] = binarySearchRightSide(mid, right, nums, target);
				break;
			} else if (nums[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return result;
	}

	// Binary search on Left Part of the array
	private int binarySearchLeftSide(int left, int right, int[] nums, int target) {
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target || nums[mid] > target) {
				right = mid - 1;
			}
			if (nums[mid] < target) {
				left = mid + 1;
			}
		}
		return left;// we are returning left to give first occurrence.
	}

	// Binary search on Right Part of the array
	private int binarySearchRightSide(int left, int right, int[] nums, int target) {
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target || nums[mid] < target) {
				left = mid + 1;
			}
			if (nums[mid] > target) {
				right = mid - 1;
			}
		}
		return right;// we are returning right to give last occurrence.
	}

	/*
	 * 2 pointer approach iterating one pointer from start and other from end if
	 * element found respectively, just fill the array
	 */
	private int[] search(int[] nums, int target) {
		int[] result = { -1, -1 };
		int end = nums.length - 1;
		boolean firstIndexFound = false, secondIndexFound = false;
		if (nums.length == 1) {
			if (nums[0] == target) {
				result[0] = 0;
				result[1] = 0;
				return result;
			}
		}
		if (nums.length == 0)
			return result;
		for (int i = 0, j = end; i < end || j > 0; i++, j--) {
			if (!firstIndexFound && nums[i] == target) {
				firstIndexFound = true;
				result[0] = i;
			}
			if (!secondIndexFound && nums[j] == target) {
				secondIndexFound = true;
				result[1] = j;
			}
			if (firstIndexFound && secondIndexFound)
				break;
			if (firstIndexFound) {
				i = end;
			}
			if (secondIndexFound) {
				j = -1;
			}
		}
		if (firstIndexFound && !secondIndexFound) {
			result[1] = result[0];
		}
		if (!firstIndexFound && secondIndexFound) {
			result[0] = result[1];
		}
		return result;
	}

	public static void main(String[] args) {
		FirstAndLastPositionInSorted sorted = new FirstAndLastPositionInSorted();
		int[] a = { 5, 7, 7, 8, 9, 10 };
		// System.out.println (Arrays.toString(sorted.search(a,8)));
		System.out.println(Arrays.toString(sorted.searchRange(a, 8)));
	}
}