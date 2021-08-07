package com.dsalgo.slidingwindow;

/*
 * Problem # 1358. - https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
 * 
 * Given a string s consisting only of characters a, b and c.
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 * 
 * */
public class NumberSubstringsContainingAllThreeCharacters {
	
	 	
	private int numberOfSubstrings(String s) {
		int ans=0;
		int startIndex=0;
		for(int i=3;i<=s.length();i++) {
			String substring=s.substring(startIndex,i);
			if(substring.contains("a") && substring.contains("b") && substring.contains("c")) {
				ans +=1; 
			}
			
			System.out.println("substring --> "+substring+" ans "+ans);
		}
		
		return ans;
	}
	
	private int numberOfSubstringsOptimized(String s) {
        int n = s.length();
        int ptr1 = 0, ptr2 = 0;
        int []f = new int[3];
        f[0] = f[1] = f[2] = 0;
        int ans = 0;
        f[(int)(s.charAt(0)-'a')]++;
        /*
            aabcabcd
        
        */
        while(ptr2<n){
            if(f[0] > 0 && f[1] > 0 && f[2] > 0){
                //Move ptr1
                ans+= (n-ptr2);
                f[(int)(s.charAt(ptr1) - 'a')]--;
                ptr1++;
            } else{
                //Move ptr2
                ptr2++;
                if(ptr2<n)
                    f[(int)(s.charAt(ptr2) - 'a')]++;
            }
            
            
        }
        /*
            [i, j] [i, i1)
            i1 = i
        */
        return ans;
    }
	
	public static void main(String[] args) { 
		
		/*
		 * Example 1: Input: s = "abcabc" Output: 10 
		 * Explanation: The substrings containing at least one occurrence of the characters 
		 * a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 
		 * 
		 * Example 2: Input: s = "aaacb" Output: 3 
		 * Explanation: The substrings containing at least one occurrence of the characters
		 * a, b and c are "aaacb", "aacb" and "acb". 
		 * 
		 * Example 3: Input: s = "abc" Output: 1
		 */
		
		String s="abcabc";

		System.out.println("Input string  --> " + s);
		NumberSubstringsContainingAllThreeCharacters soln = new NumberSubstringsContainingAllThreeCharacters();
		//System.out.println("numberOfSubstrings -> "
		//		+ soln.numberOfSubstrings(s));
		
		System.out.println("numberOfSubstringsOptimized -> "
				+ soln.numberOfSubstringsOptimized(s));
	}

}
