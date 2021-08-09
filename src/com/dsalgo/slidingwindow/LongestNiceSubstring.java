package com.dsalgo.slidingwindow;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;

/*
 * Problem - https://leetcode.com/problems/longest-nice-substring/
 * 
 * A string s is nice if, for every letter of the alphabet that s contains, it appears 
 * both in uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear,
 * and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.
 * 
 * Given a string s, return the longest substring of s that is nice. 
 * If there are multiple, return the substring of the earliest occurrence. 
 * If there are none, return an empty string.
 *
 * */
public class LongestNiceSubstring {

	private String longestNiceSubstringDivideConquer(String input) {
		if (input.length() < 2)
			return "";

		Set<Character> set = new HashSet<Character>();
		for (int i = 0; i < input.length(); i++) {
			set.add(input.charAt(i));
		}

		for (int i = 1; i < input.length(); i++) {
			char c = input.charAt(i);
			if ((Character.isLowerCase(c) && set.contains(Character.toUpperCase(c)))
					|| (Character.isUpperCase(c) && set.contains(Character.toLowerCase(c)))) {
				continue;
			}

			String previousLongerSubstring = longestNiceSubstringDivideConquer(input.substring(0, i));
			String nextLongerSubstring = longestNiceSubstringDivideConquer(input.substring(i + 1));

			return previousLongerSubstring.length() >= nextLongerSubstring.length() ? previousLongerSubstring
					: nextLongerSubstring;

		}

		return input;
	}

	private String longestNiceAdjoiningcharSubstringSlidingWindow(String input) {

		int j = 0;
		int windowSize = 2;
		int n = input.length();

		if (n < 2) {
			return "";
		}

		LinkedHashMap<String, Integer> niceSubstringMap = new LinkedHashMap<>();
		int previousNiceSubstringLastIndex = -1;
		String niceLongestSubstring = "";

		for (int i = 0; i < n && j < n; i++) {
			j = i + windowSize;
			String substring = input.substring(i, j);
			if (!isNice(substring)) {
				continue;
			} else {
				niceSubstringMap.put(substring,
						previousNiceSubstringLastIndex > 0 ? j - previousNiceSubstringLastIndex : 0);
				previousNiceSubstringLastIndex = j;
			}
		}

		// System.out.println(" niceSubstringMap "+niceSubstringMap.toString());

		if (niceSubstringMap.isEmpty()) {
			return "";
		} else {
			for (String eachNiceSubString : niceSubstringMap.keySet()) {
				if (niceSubstringMap.get(eachNiceSubString) == 2 || niceSubstringMap.get(eachNiceSubString) == 0) {
					niceLongestSubstring += eachNiceSubString;
				} else if (niceSubstringMap.get(eachNiceSubString) == 1) {
					niceLongestSubstring += eachNiceSubString.charAt(1);
				}
			}
		}

		return niceLongestSubstring;
	}
	

	private boolean isNice(String substring) {
		char firstChar = substring.charAt(0);
		char lastChar = substring.charAt(1);

		if ((Character.toLowerCase(firstChar) == Character.toLowerCase(lastChar))
				&& ((Character.isLowerCase(firstChar) && Character.isUpperCase(lastChar))
						|| (Character.isUpperCase(firstChar) && Character.isLowerCase(lastChar)))) {
			return true;
		}

		return false;
	}

	public static void main(String[] args) {

		/*
		 * Example 1: s = "YazaAay" Output: "aAa" Example 2: s = "Bb" Output: "Bb"
		 * Example 3: s = "c" Output: "" Example 4: s = "dDzeE" Output: "dD" Example 5:
		 * s= "BebjJE"
		 */

		String input = "YazaAayA";
		// String input="LaAYAabB";
		// String input="dDzeE";
		// String input = "azABaabba";
		// String input = "c";
		// String input = "Bb";
		// String input = "BebjJE"; //"BebjJE"

		System.out.println("Input string  --> " + input);
		LongestNiceSubstring soln = new LongestNiceSubstring();
		System.out.println("longestNiceAdjoiningcharSubstringSlidingWindow (SlidingWindow Approach) -> "
				+ soln.longestNiceAdjoiningcharSubstringSlidingWindow(input));
		System.out.println("LongestNiceSubstring (Divide and Conquer Approach) -> "
				+ soln.longestNiceSubstringDivideConquer(input));

	}

}
