package com.dsalgo.array;

import java.util.Arrays;

/*
https://leetcode.com/problems/koko-eating-bananas/
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
Return the minimum integer k such that she can eat all the bananas within h hours.
 Example 1:
Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:
Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:
Input: piles = [30,11,23,4,20], h = 6
Output: 23
 ,
Constraints:
1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109
 */
public class KokoEatingBanana {
	/*
	 * Approach: 1. Assume koko can eat atleast 1 banana per hour and atMax as
	 * maximum number of bananas in all the piles so, left = 1, right = maximum of
	 * given array, mid = (l+r)/2 2. we do binary search here with mid point as
	 * minimum number of bananas that koko can eat in an hour and calculate the
	 * speed for the mid point. 3.if koko takes less than or equal time than h means
	 * we need to try in the lower bound for optimization --> right = mid-1 if koko
	 * takes more time, then we will try with upper bound --> mid+1.
	 */

	private int minEatingSpeed(int[] piles, int h) {
		int left = 1, right = Arrays.stream(piles).max().getAsInt();
		while (left <= right) {
			int mid = left + (right - left) / 2;
			boolean canKokoEatInTime = canEatInTime(piles, mid, h);
			if (canKokoEatInTime) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	/*
	 * we calculate how many hours koko is taking to eat bananas for given speed (
	 * k) if koko takes less number of hours than h return true else return false
	 */
	private boolean canEatInTime(int[] piles, int k, int h) {
		int hoursTakenToEat = 0;
		for (int i = 0; i < piles.length; i++) {
			if (piles[i] < k) {
				hoursTakenToEat = hoursTakenToEat + 1;
			} else {
				hoursTakenToEat = hoursTakenToEat + (int) (Math.ceil((double) piles[i] / k));
			}
		}
		return hoursTakenToEat <= h ? true : false;
	}

	public static void main(String[] args) {
		KokoEatingBanana koko = new KokoEatingBanana();
		int[] a = { 30, 11, 23, 4, 20 };
		System.out.println(koko.minEatingSpeed(a, 6));
	}
}
