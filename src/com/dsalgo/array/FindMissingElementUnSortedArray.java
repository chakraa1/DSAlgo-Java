package com.dsalgo.array;

import java.util.Arrays;

public class FindMissingElementUnSortedArray {

	private int findMissingElement(int[] arr, int n, int search_element) {
		int count = 0;

		for (int i = 1; i < n; i++) {
			if (arr[i] == search_element) {
				search_element++;
				count++;
				return findMissingElement(arr, n, search_element);
			}
		}

		if (count == 0) {
			return search_element;
		} else {
			return -1;
		}

	}

	private int findMissingElementUsingAPSum(int[] arr, int n) {
		int current_sum = 0;
		
		int natural_sum = n * (n + 1) / 2;

		for (int i = 0; i < n-1; i++) {
			current_sum += arr[i];
		}
		
		int missing_element = natural_sum - current_sum;

		return missing_element;
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 4, 6, 3, 7, 8 };
		int n = a.length;
		System.out.println(Arrays.toString(a));
		FindMissingElementUnSortedArray sol = new FindMissingElementUnSortedArray();
		System.out.println("findMissingElement -->  " + sol.findMissingElement(a, n, a[1]));

		System.out.println("findMissingElementUsingAPSum " + sol.findMissingElementUsingAPSum(a, n));
	}

}
