package com.dsalgo.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;

/* 
 * Problem statement - https://www.geeksforgeeks.org/count-pairs-difference-equal-k/
 * 
 * */

public class FindNDistinctPairsWithDifferenceK {
	private ArrayList<Integer> findDistinctPairs(int[] arr, int n, int k) {
		ArrayList<Integer> pairs = new ArrayList<Integer>();

		Arrays.sort(arr);

		for (int i = 0; i < n; i++) {
			int[] result = binarySearch(arr, i, n - 1, arr[i] + k, k);
			if (result != null) {
				for (int j = 0; j <= 1; j++) {
					pairs.add(result[j]);
				}
			}
		}
		return pairs;
	}

	private int[] binarySearch(int[] arr, int low, int high, int searchkey, int k) {
		int[] result = new int[2];

		if (low > high) {
			return null;
		}

		int mid = (low + high) / 2;

		if (arr[mid] == searchkey) {
			result[0] = searchkey - k;
			result[1] = arr[mid];
			return result;
		} else if (arr[mid] < searchkey) {
			return binarySearch(arr, mid + 1, high, searchkey, k);
		} else {
			return binarySearch(arr, low, mid - 1, searchkey, k);
		}

	}

	public static void main(String[] args) {
		//int[] arr = { 1, 5, 3, 4, 2 }; int k = 3;
		int []arr={8, 12, 16, 4, 0, 20}; int k=4;
		int n = arr.length;
		FindNDistinctPairsWithDifferenceK soln = new FindNDistinctPairsWithDifferenceK();

		System.out.println("Pairs --> " + soln.findDistinctPairs(arr, n, k));

	}

}
