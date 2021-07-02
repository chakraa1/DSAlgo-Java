package com.dsalgo.binarysearch;

import java.util.Arrays;

/* 
 * Possibility  #1 - MMMMMxxxxx
 * Possibility  #2 - xxxxxMMMMM
 * Possibility  #3 - xMMMMMMMMx
 * Solution approach  - 
 *      1. Need to find the range in which mid element exists - lower_bound & upper bound
 *      2. Better approach that covers elements not even in un-sorted order
 *      (i.e. Moore's Voting algorithm A. Find eligible ME B. Validate ME
*/

public class CheckMajorityElementInSortedArray {

	// Complexity O(1)
	static boolean isMajorityElementOrderOfOne(int arr[], int n, int key) {
		if (arr[n / 2] == key)
			return true;
		else
			return false;
	}
	
	static int findEligibleMajorityElement(int arr[],int n) {
		int majority_elelment_index=0, count=1;
	
		for (int i=1;i< n; i++) {
			if(arr[majority_elelment_index] == arr[i]) {
				count++;
			}else {
				count--;
			}
			if(count == 0) {
				majority_elelment_index=i;
				count=1;
			}
		}
		
		return arr[majority_elelment_index];
	}
	
	static boolean validateEligibleIsMajorityElement(int arr[],int n, int eligibleME) {
		int actual_frequency_of_major_element=0;
		for (int i=0;i< n; i++) {
			if(eligibleME == arr[i]) {
				actual_frequency_of_major_element++;
			}
		}
		
		if (actual_frequency_of_major_element >= n/2+1) {
			return true;
		}else {
			return false;
		}
	}
	
	// Complexity O(n) - Moore's Algorithm (It works even on unsorted arrays
	static boolean isMajorityElementOrderOfN(int arr[], int n, int key) {
		//Step 1 -  Finding eligible ME
		int eligibleME=findEligibleMajorityElement(arr,n);
		//Step 2 -  Validating eligible ME
		return validateEligibleIsMajorityElement(arr,n,eligibleME);
	}

	// Complexity O(log n) - Binary search Algorithm
	static boolean isMajorityElementBS(int arr[], int n, int searchkey) {
		int lower_bound_indx=findLowerBound(arr, n,searchkey);
		int upper_bound_indx=findUpperBound(arr,n/2,n,searchkey);
		int range=upper_bound_indx-lower_bound_indx;
		if (range >= n/2)
			return true;
		else
			return false;
	}
	
	static int findUpperBound(int arr[], int lower, int n, int key) {
		
		int low=lower;
		int high=n-1;
		int upper_bound=-1;
		
		while(low <= high) {
			int mid=(low+high)/2;
			if(arr[mid] == key) {
				upper_bound=mid;
				low=mid+1;
			}else if(arr[mid] < key){
				low=mid+1;
			}else {
				high=mid-1;
			}
			
		}
		
		return upper_bound;
	}
	
   static int findLowerBound(int arr[], int n, int key) {
		
		int low=0;
		int high=n;
		int lower_bound=-1;
		
		while(low <= high) {
			int mid=(low+high)/2;
			if(arr[mid] == key) {
				lower_bound=mid;
				high=mid-1;
			}else if(arr[mid] > key){
				high=mid-1;
			}else {
				low=mid+1;
			}			
		}
		
		return lower_bound;
	}

	// Driver Code
	public static void main(String[] args) {
		int arr[]= { 1, 2, 3, 3, 3, 3, 10 }; // PASS - both algorithm (i.e. O(1) & O(logn) works
		// int arr[] = { 2, 3, 4, 3, 3 }; // PASS - exception case for order 1 function] because it's not sorted VALID
		// int arr[] = { 2, 2, 2, 3, 4}; // PASS- both algorithm (i.e. O(1) & O(logn) works
		// int arr[] = { 2, 3, 7, 3, 4 }; //PASS - Moore's Algorithm works as well as number of majority elements (3's are present only "2" times) and it's not greater than n/2+1 (i.e. 3) .
		// int arr[] = { 1, 2, 3};  // Again - n/2+1 condition is not satisfied

		int n = arr.length;
		int x = 3;
		
		if (isMajorityElementOrderOfOne(arr, n, x))
			System.out.println("[isMajorityElementOrderOfOne] MajorityElement --> " + x);
		else
			System.out.println("[isMajorityElementOrderOfOne] " + x + " is not the majority element");

		if (isMajorityElementOrderOfN(arr, n, x))
			System.out.println("[isMajorityElementOrderOfN] MajorityElement --> " + x);
		else
			System.out.println("[isMajorityElementOrderOfN] " + x + " is not the majority element");

		if (isMajorityElementBS(arr, n, x))
			System.out.println("[isMajorityElementBS] MajorityElement --> " + x);
		else
			System.out.println("[isMajorityElementBS] " + x + " is not the majority element");
		
		
		
	}
}
