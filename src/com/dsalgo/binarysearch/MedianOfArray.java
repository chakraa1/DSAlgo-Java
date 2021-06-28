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
		int smaller_array_length = input1.length;
		int bigger_array_length = input2.length;

		int low = 0;
		int high = smaller_array_length;  
		while (low <= high) {
			// if we can find how many elements are coming from array 1 then we can easily find how many of them are from 2nd array
			int cut1 = (low + high) / 2;
			int cut2 = (smaller_array_length + bigger_array_length + 1) / 2 - cut1;

			// if cut1 is 0 it means nothing is there on left side.Set l1 to -INF for
			// if r1 is length of input then there is nothing on right side.Set r1 to +INF
			
			int l1 = (cut1 == 0) ? Integer.MIN_VALUE : input1[cut1 - 1];
			int r1 = (cut1 == smaller_array_length) ? Integer.MAX_VALUE : input1[cut1];

			int l2 = (cut2 == 0) ? Integer.MIN_VALUE : input2[cut2 - 1];
			int r2 = (cut2 == bigger_array_length) ? Integer.MAX_VALUE : input2[cut2];

			if (l1 <= r2 && l2 <= r1) {
				// We have partitioned array at correct place
				// Now get max of left elements and min of right elements to get the median in
				// case of even length combined array size
				// or get max of left for odd length combined array size.
				if ((smaller_array_length + bigger_array_length) % 2 == 0) {
					return ((double) Math.max(l1, l2) + Math.min(r1, r2)) / 2;
				} else {
					return (double) Math.max(l1, l2);
				}
			} else if (l1 > r2) { // we are too far on right side for partitionX. Go on left side.
				high = cut1 - 1;
			} else { // we are too far on left side for partitionX. Go on right side.
				low = cut1 + 1;
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
