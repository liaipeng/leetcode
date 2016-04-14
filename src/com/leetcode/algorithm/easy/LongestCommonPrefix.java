package com.leetcode.algorithm.easy;

/*
 Write a function to find the longest common prefix string amongst an array of strings.
 */

/*
 * 找出字符串数组中所有字符串的最长前缀。
 * 
 * 代码①，6ms，使用StringBuilder
 * 
 * Discuss:
 * 代码②，1ms，使用String的indexOf方法
 */

public class LongestCommonPrefix {
	/*
	 * 代码①
	 * 用最直接的思路：遍历strs数组，用prefix记录每次比较后得出的前缀，再与下一个str比较
	 * 比较的过程：对比prefix与str的每一个字符，相同则append到sb里，不同则直接结束比较
	 */
	public static String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) return "";
		StringBuilder prefix = new StringBuilder(strs[0]);
		for (int i = 1; i < strs.length; ++i) {
			String str = strs[i];
			StringBuilder sb = new StringBuilder("");
			for (int j = 0; j < Math.min(prefix.length(), str.length()); ++j) {
				if (prefix.charAt(j) == str.charAt(j)) {
					sb.append(prefix.charAt(j));
				} else {
					break;
				}
			}
			prefix = sb;
		}
		return prefix.toString();
	}
	
	/*
	 * 代码②
	 * 也是遍历strs进行比较，但是与代码①不同是比较的过程：
	 * 利用String的indexOf方法，判断str[i]是否包含当前的prefix并且是开头，若不是，去掉prefix的末尾字符，再判断。
	 * 遍历strs的过程不断修正prefix，直到结束，prefix则为最长前缀
	 * 
	 * ★★注意：str＝str.substring(int beginIndex，int endIndex);中最终得到的值:beginIndex =< str的值 < endIndex
	 */
    public String longestCommonPrefix_2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; ++i) {
            while (strs[i].indexOf(prefix) != 0) 
                prefix = prefix.substring(0, prefix.length() - 1);
        }
        return prefix;
    }

}
