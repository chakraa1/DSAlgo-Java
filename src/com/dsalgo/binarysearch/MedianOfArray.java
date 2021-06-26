package com.dsalgo.binarysearch;

import java.util.Arrays;

public class MedianOfArray {
	private float findMedianSortedArrays(int[] a, int[] b) {
		int[] merged_array = findMergedSortedArrays(a, b);
		System.out.println("Merged sorted array  --> " + Arrays.toString(merged_array));

		int middle_term_index = merged_array.length / 2;
		float median = (merged_array[middle_term_index] + merged_array[middle_term_index - 1]) / 2;

		return median;

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
		int[] A = { 1, 4, 5 };
		int[] B = { 2, 3 };
		System.out.println("Array 1 " + Arrays.toString(A));
		System.out.println("Array 2 " + Arrays.toString(B));
		float medianofarrays = util.findMedianSortedArrays(A, B);
		System.out.println("Median --> " + medianofarrays);

	}

}
