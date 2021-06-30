package com.dsalgo.binarysearch;

import java.util.Arrays;

/**
 * Find missing number in consecutive numbers.
 */
public class MissingNumberInConsecutiveNumbers {

	public Integer findMissing(int arr[]) {

		int startingElement = arr[0];
		int low = 0;
		int high = arr.length - 1;
		int middle = (low + high) / 2;
		while (low <= high -1) {
			middle = (low + high) / 2;
			if (predicate(arr,middle)) {
				return startingElement+ middle;
			} else if ((middle + startingElement) == arr[middle]) { //forward sequence 
				low = middle + 1;
			} else { //reverse sequence
				high = middle - 1;
			}
		}
		return null;
	}
	
	private boolean predicate(int arr[],int mid) {
		
		if(mid -1 >=0  && ((arr[mid] - arr[mid -1]) != (arr[mid +1] - arr[mid]))) {
			return true;
		}
		
		return false;
		
	}

	public static void main(String args[]) {
		int arr[] = { -5, -4, -3, -1, 0, 1, 2, 3 };
		System.out.println("Input sequence  --> "+ Arrays.toString(arr));
		MissingNumberInConsecutiveNumbers mn = new MissingNumberInConsecutiveNumbers();
		System.out.println("Missing number in consecutive numbers --> "+ mn.findMissing(arr));
	}
}
