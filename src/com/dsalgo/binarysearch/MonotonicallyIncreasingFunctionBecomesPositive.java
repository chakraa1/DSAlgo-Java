package com.dsalgo.binarysearch;

public class MonotonicallyIncreasingFunctionBecomesPositive {
	public static int function(int x) {
		return (x * x - 10 * x - 20);
	}

	private int findFirstPositive() {
		int x = 1;

		if (function(0) > 0) { // x is a non-negative integer
			return 0;
		}

		while (function(x) <= 0) {
			x = x * 2;
		}

		return binarySearch(x / 2, x);
	}

	private int binarySearch(int low, int high) {
		int result = -1;

		while (low <= high) {
			int mid = low + (high - low)/2;
			/*
			 * why condition if(function(mid) == 0) only not valid , If f(mid) is greater
			 * than 0 and one of the following two conditions is true: a) mid is equal to
			 * low b) f(mid-1) is negative
			 * 
			 */
			
			if (function(mid) > 0 && (mid == low || function(mid - 1) <= 0)) {
				return mid;
			} else if (function(mid) <= 0) {
				return binarySearch(mid + 1, high);
			} else {
				return binarySearch(low, mid - 1);
			}
			
		}
		
		return result;
	}

	public static void main(String[] args) {
		MonotonicallyIncreasingFunctionBecomesPositive solution = new MonotonicallyIncreasingFunctionBecomesPositive();
		int firstpositive = solution.findFirstPositive();
		System.out.println("Find first positive ---> " + firstpositive);

	}

}
