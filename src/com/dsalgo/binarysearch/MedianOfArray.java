package com.dsalgo.binarysearch;

import java.util.Arrays;

public class MedianOfArray {
	// Time Complexity O(n)
	private double findMedianSortedArrays(int[] a, int[] b) {
		int[] merged_array = findMergedSortedArrays(a, b);
		System.out.println("Merged sorted array  --> " + Arrays.toString(merged_array));
		int total_number_of_elements_in_merged_array = merged_array.length;

		// check for even case
		if (total_number_of_elements_in_merged_array % 2 != 0)
			return (double) merged_array[total_number_of_elements_in_merged_array / 2];

		return (double) (merged_array[(total_number_of_elements_in_merged_array - 1) / 2]
				+ merged_array[total_number_of_elements_in_merged_array / 2]) / 2.0;

	}

	// Time Complexity O(log n)
	private double findMedianUsingBinarySearch(int[] input1, int[] input2) {

		// if input1 length is greater than switch them so that input1 is smaller than
		// input2.
		if (input1.length > input2.length) {
			return findMedianSortedArrays(input2, input1);
		}
		int x = input1.length;
		int y = input2.length;

		int low = 0;
		int high = x;
		while (low <= high) {
			int partitionX = (low + high) / 2;
			int partitionY = (x + y + 1) / 2 - partitionX;

			// if partitionX is 0 it means nothing is there on left side. Use -INF for
			// maxLeftX
			// if partitionX is length of input then there is nothing on right side. Use
			// +INF for minRightX
			int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : input1[partitionX - 1];
			int minRightX = (partitionX == x) ? Integer.MAX_VALUE : input1[partitionX];

			int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : input2[partitionY - 1];
			int minRightY = (partitionY == y) ? Integer.MAX_VALUE : input2[partitionY];

			if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
				// We have partitioned array at correct place
				// Now get max of left elements and min of right elements to get the median in
				// case of even length combined array size
				// or get max of left for odd length combined array size.
				if ((x + y) % 2 == 0) {
					return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
				} else {
					return (double) Math.max(maxLeftX, maxLeftY);
				}
			} else if (maxLeftX > minRightY) { // we are too far on right side for partitionX. Go on left side.
				high = partitionX - 1;
			} else { // we are too far on left side for partitionX. Go on right side.
				low = partitionX + 1;
			}
		}

		// Only we we can come here is if input arrays were not sorted. Throw in that
		// scenario.
		throw new IllegalArgumentException();

	}

	private int[] findMergedSortedArrays(int[] a, int[] b) {
		int i = 0, j = 0, k = 0;
		int s1 = a.length, s2 = b.length, s3 = s1 + s2;
		int[] merged = new int[s3];

		while (i < s1 && j < s2) {
			if (a[i] < b[j]) {
				merged[k++] = a[i++];
			} else {
				merged[k++] = b[j++];
			}
		}

		// Copying remaining element of bigger array(i.e. arr1)
		while (i < s1) {
			merged[k++] = a[i++];
		}

		// Copying remaining element of bigger array(i.e. arr2)
		while (j < s2) {
			merged[k++] = b[j++];
		}

		return merged;

	}

	public static void main(String[] args) {
		// https://www.interviewbit.com/problems/median-of-array/
		MedianOfArray util = new MedianOfArray();
		int[] A = { 1, 4, 5, 7 };
		int[] B = { 2, 3 };
		System.out.println("Array 1 " + Arrays.toString(A));
		System.out.println("Array 2 " + Arrays.toString(B));
		double medianofarrays = util.findMedianSortedArrays(A, B);
		System.out.println("Median using brute force  --> " + medianofarrays);

		double median_using_binary_search = util.findMedianUsingBinarySearch(A, B);

		System.out.println("Median using binary search --> " + median_using_binary_search);
	}

}
