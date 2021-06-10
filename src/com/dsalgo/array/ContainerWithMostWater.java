package com.dsalgo.array;

/*
https://leetcode.com/problems/container-with-most-water/
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
Notice that you may not slant the container.
Example 1:
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
Example 2:
Input: height = [1,1]
Output: 1
Example 3:
Input: height = [4,3,2,1,4]
Output: 16
Example 4:
Input: height = [1,2,1]
Output: 2
Constraints:
n == height.length
2 <= n <= 105
0 <= height[i] <= 104
 */
public class ContainerWithMostWater {
	/***
	 * Brute force Solution ---> O(Nsquare) Shorter cell * distance between 2 lines
	 ***/
	private int maxArea(int[] height) {
		int currentArea, maxArea = 0;
		for (int i = 0; i < height.length; i++) {
			for (int j = i + 1; j < height.length; j++) {
				currentArea = (height[i] < height[j] ? height[i] : height[j]) * (j - i);
				if (currentArea > maxArea) {
					maxArea = currentArea;
				}
			}
		}
		return maxArea;
	}

	/*
	 * Optimized Solution In Single Pass: 1. take 2 pointers as i = 0, j= length -1;
	 * calculate the area --> shorter line height * difference between ith and jth
	 * vertical line if height of ith index is less than j then move right else
	 * decrement j ( move left) reason: we want max area, so if height of ith index
	 * is less than jth line, then there is no point in calculating area with
	 * smaller number.
	 */
	private int maxAreaOfWater(int[] height) {
		int currentArea, maxArea = 0;
		int i = 0, j = height.length - 1;
		while (i < j) { // why not i<=j?? if i and j are equal they will point to same vertical line, so
						// that is wrong
			currentArea = (height[i] < height[j] ? height[i] : height[j]) * (j - i);
			if (currentArea > maxArea) {
				maxArea = currentArea;
			}
			if (height[i] < height[j]) { // if height[i] is less, go forward and see if we have any bigger number
				i++;
			} else {
				j--; // height[j] is less, so move left and check if we can get maxArea.
			}
		}
		return maxArea;
	}

	public static void main(String[] args) {
		ContainerWithMostWater water = new ContainerWithMostWater();
		int[] a = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		System.out.println(water.maxArea(a));
	}
}
