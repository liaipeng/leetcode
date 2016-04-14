package com.leetcode.algorithm.easy;

import java.util.Arrays;

/*
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
*/

/*
 * anagram ：颠倒字母顺序构成的字
 * 
 * 思路：排序后判断相等
 * 
 * 还可以使用计数法，将两个字符串的字符分别相加，将得到的和进行对比，若相等true。
 */
		
public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
    	if(s == null && t == null) return true;
    	else if(s == null || t == null) return false;
    	else if (s.length() != t.length()) 
    		return false;
    	else {
    		char[] ss = s.toCharArray();
        	char[] ts = t.toCharArray();
        	Arrays.sort(ss);
        	Arrays.sort(ts);
//        	for (int i = 0; i < ss.length; i++) {
//        		if (ss[i] != ts[i]) 
//        			return false;
//        	}
        	if (String.valueOf(ss).equals(String.valueOf(ts)))
        		return true;
        	else
        		return false;
    	}   	
    }
    
    public static void main(String[] args) {
    	String s = "abc";
    	String t = "bac";
    	
    	System.out.println(isAnagram(s, t));
	}
}
