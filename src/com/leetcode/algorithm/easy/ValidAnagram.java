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
 * 代码①，8ms，思路：排序后判断相等
 * 
 * 代码②，，用hash的思想，
 * 			用一个长度为26的数组记录字母的出现次数，s中出现则+1，t中出现则-1，所以如果两个字符串中出现的字母次数相同，最后的结果必然是0。
 * 			因为数组初始化的时候默认都为0，所以遍历完两个字符串后，若数组中有不为0的值，说明必然不是anagram。
 */
		
public class ValidAnagram {
	/*
	 * 代码①
	 */
    public boolean isAnagram(String s, String t) {
        if (s == null && t == null) return true;
    	if ((s == null || t == null) || (s.length() != t.length())) return false;
    	
    	char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
    	Arrays.sort(ss);
    	Arrays.sort(ts);
    	
    	return (String.valueOf(ss).equals(String.valueOf(ts))) ? true : false;
    }
    
    /*
     * 代码②
     */
    public boolean isAnagram_2(String s, String t) {
        if (s == null && t == null) return true;
    	if ((s == null || t == null) || (s.length() != t.length())) return false;
    	
    	int[] alpha = new int[26];
    	
    	char[] ch1 = s.toCharArray();
    	char[] ch2 = t.toCharArray();
    	
    	for (int i = 0; i < s.length(); i++) {
    	    alpha[ch1[i] - 'a']++;
    	    alpha[ch2[i] - 'a']--;
    	}
    	
    	for (int num : alpha) {
    	    if (num != 0) return false;
    	}
    	
    	return true;
    }
}
