package com.leetcode.algorithm.easy;

import java.util.HashMap;
import java.util.Map;

/*
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
*/

/*
 * 判断两个字符串是否是同构的：通过替换字母，s能不能变成t。
 * 本来打算用字符串替换来做，发现是不可行的。
 * 
 * 看了Discuss，主要思想就是遍历两个字符串，两个字符如果之前出现过，必须是之前的那个配对，要不然肯定是错的
 * 
 * Discuss：
 * 代码① ，26ms，使用HashMap
 * 
 * 代码②，15ms，awesome，只需要一个数组就可以实现
 */

public class IsomorphicStrings {
	/*
	 * 代码①
	 * 遍历s和t，每次循环，字符为a和b
	 * 使用map，key存储a值，value存储b值
	 * 当某一次循环，若map的key包含a，而value不包含b，说明目前这对a和b不配对，false
	 * 				若map的key不包含a，而map的value包含b，也说明目前这对a和b不配对，false
	 * 				否则，把a和b这一配对存入map中
	 */
    public boolean isIsomorphic(String s, String t) {
    	if (s == null || s.length() <= 1) return true;
        Map<Character, Character> map = new HashMap<Character, Character>();
        for (int i = 0; i < s.length(); ++i) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (map.containsKey(a)) {
                if (map.get(a) != b) return false;
            } else {
                if (map.containsValue(b)) return false;
                map.put(a, b);
            }
        }
        return true;
    }
    
    /*
     * 代码②
     * 数组长度是512是因为ASCII码长度为256，其实array是两条数组，前一半s的字符使用，后一半t的字符使用
     * 这题是测试用例是ASCII码的，假如有中文之类的，那数组长度要改变，但是思想是一样的
     * 
     * 思路也是hash的思想
     * 第一次出现之前未出现过的字符时，给他们赋予i+1的值
     * 之后如果再碰到相同的字符，如果它们的数组的值不相等，说明不配对
     */
    public boolean isIsomorphic_2(String s, String t) {
        if (s == null || s.length() <= 1) return true;
        int[] array = new int[512];
        for (int i = 0; i < s.length(); ++i) {
            if (array[s.charAt(i)] != array[t.charAt(i) + 256]) return false;
            // 这里用i+1是因为数组初始化的时候每个值都是0，所以加入从i开始会造成错误
            array[s.charAt(i)] = array[t.charAt(i) + 256] = i + 1;
        }
        return true;
    }
}
