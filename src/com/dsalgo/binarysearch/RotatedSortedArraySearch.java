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
	private int findPivot(int arr[], int low, int high){
        // base cases
        if (high < low)
            return -1;
        if (high == low)
            return low;
 
        /* low + (high - low)/2; */
        int mid = (low + high) / 2;
        // Corner cases (if mid is the pivot) start 
        if (mid < high && arr[mid] > arr[mid + 1])
            return mid;
        if (mid > low && arr[mid] < arr[mid - 1])
            return (mid - 1);
        // Corner cases (if mid is the pivot) End  
        if (arr[low] >= arr[mid])
            return findPivot(arr, low, mid - 1);
        return findPivot(arr, mid + 1, high);
    }
	private int rotatedSortedArrayBinarySearch(int[] inputArray, int lowIndex, int highIndex, int searchedKey) {
		//int searchedIndex = -1;
        
		int pivot = findPivot(inputArray, 0, highIndex);
		 
        // If we didn't find a pivot, then
        // array is not rotated at all
        if (pivot == -1)
            return binarySearch(inputArray, 0, highIndex, searchedKey);
 
        // If we found a pivot, then first
        // compare with pivot and then
        // search in two subarrays around pivot
        if (inputArray[pivot] == searchedKey)
            return pivot;
        if (inputArray[0] <= searchedKey)
            return binarySearch(inputArray, 0, pivot - 1, searchedKey);
        return binarySearch(inputArray, pivot + 1, highIndex, searchedKey);
        

	}

	public static void main(String[] args) {
		// https://www.interviewbit.com/problems/rotated-sorted-array-search/
		RotatedSortedArraySearch util = new RotatedSortedArraySearch();
//		int[] A = { 4, 5, 6, 7, 0, 1, 2, 3 };
//		int B = 5;
		int A[] = { 4, 5, 6, 7, 8, 9, 1, 2, 3 };
		int B = 2;
		int low=0, high=A.length -1;
		System.out.println("Input Array --> " + Arrays.toString(A) +" searched key --> "+B);
		
		int index = util.rotatedSortedArrayBinarySearch(A, low,high, B);
		System.out.println("Output --> " + index);
	}

}
