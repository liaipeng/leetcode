package com.leetcode.algorithm.easy;

/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.
*/

/*
 * 求字符串最后一个单词的长度，这题用Java的方法做太简单了
 * 代码①，0ms，用Java字符串的trim()和lastIndexOf()方法，但是题目本意应该是要用更底层的方法
 * 
 * 代码②，0ms，不用现成方法，用底层方法也很简单
 * 
 * 
 */

public class LengthOfLastWord {
	/*
	 * 代码①
	 * 用trim()、lastIndexOf()方法: s原来的长度减去从后往前数第一个空格的位置（因为是从0开始数，所以要再减个1）
	 * 用split()方法切开后判断最后一个片段的长度也是可以的
	 */
    public int lengthOfLastWord(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
    }
    
    /*
     * 代码②
     * 用底层方法
     */
    public int lengthOfLastWord_2(String s) {
        int count = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
        	// 有类似"a "这样的test，真是坑爹
            if (s.charAt(i) == ' ' && count == 0) continue;
            if (s.charAt(i) == ' ' && count != 0) return count;
            count++;
        }
        return count;
    }
	
    
}
