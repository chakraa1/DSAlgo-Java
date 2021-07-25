package com.dsalgo.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/*
 * Problem - https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/
 * A string is good if there are no repeated characters.
 * Given a string s​​​​, return the number of good substrings of length three in s​​​​​​.
 * Note that if there are multiple occurrences of the same substring, every occurrence should be counted.
 * 
 * A substring is a contiguous sequence of characters in a string.
 * 
 * */
public class SubstringsofSizeThreewithDistinctCharacters {

	private int countGoodSubstringsSlidingWindow(String input, int n, int windowSize) {
		int j = 0;
		int goodStringCount = 0;

		for (int i = 0; i < n && j < n; i++) {
			j = i + windowSize;
			String substring = input.substring(i, j);

			Set<Character> set = new HashSet<Character>();
			boolean isGoodString = true;
			for (char c : substring.toCharArray()) {
				if (set.contains(c)) {
					isGoodString = false;
					break;
				} else {
					set.add(c);
				}
			}

			if (isGoodString)
				goodStringCount++;

			set = null;
		}
		return goodStringCount;
	}

	public static void main(String[] args) {

		/*
		 * Example 1: Input: s = "xyzzaz", k = 3 Output: 1(xyz) Explanation - There are
		 * 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz". The only good
		 * substring of length 3 is "xyz".
		 * 
		 * 
		 * Example 2: Input: s = "aababcabc", k = 3 Output: 4 Explanation - There are 7
		 * substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
		 * The good substrings are "abc", "bca", "cab", and "abc".
		 */

		//String input = "xyzzaz";int windowSize = 3;
		String input="aababcabc"; int windowSize=3;

		System.out.println("Input sequence  --> " + input + " window size " + windowSize);
		SubstringsofSizeThreewithDistinctCharacters soln = new SubstringsofSizeThreewithDistinctCharacters();
		System.out.println("countGoodSubstringsSlidingWindow -> "
				+ soln.countGoodSubstringsSlidingWindow(input, input.length(), windowSize));

	}

}