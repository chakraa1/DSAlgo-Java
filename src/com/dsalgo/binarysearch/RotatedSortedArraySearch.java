package com.dsalgo.binarysearch;

import java.util.Arrays;

public class RotatedSortedArraySearch {

	private int binarySearch(int[] inputArray, int lowIndex, int highIndex, int searchedKey) {
		int searchedIndex = -1;

		while (lowIndex <= highIndex) {
			int midIndex = (lowIndex + highIndex) / 2;
			if (inputArray[midIndex] == searchedKey) {
				return midIndex;
			}
			if (inputArray[midIndex] < searchedKey) {
				lowIndex = midIndex + 1;
			} else {
				highIndex = midIndex - 1;
			}
		}

		return searchedIndex;

	}
	
	private int rotatedSortedArrayBinarySearch(int[] inputArray, int lowIndex, int highIndex, int searchedKey) {
		int searchedIndex = -1;

		searchedIndex=binarySearch(inputArray,lowIndex,highIndex,searchedKey);

		return searchedIndex;

	}

	public static void main(String[] args) {
		// https://www.interviewbit.com/problems/rotated-sorted-array-search/
		RotatedSortedArraySearch util = new RotatedSortedArraySearch();
//		int[] A = { 4, 5, 6, 7, 0, 1, 2, 3 };
//		int B = 5;
		int A[] = { 4, 5, 6, 7, 8, 9, 1, 2, 3 };
		int B = 6;
		int low=0, high=A.length -1;
		System.out.println("Input Array --> " + Arrays.toString(A) +" searched key --> "+B);
		
		int index = util.rotatedSortedArrayBinarySearch(A, low,high, B);
		System.out.println("Output --> " + index);
	}

}
