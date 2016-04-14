package com.leetcode.algorithm.easy;

/*
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/

/*
 * 其实就是模拟indexOf的实现
 * 尝试一个个字符进行对比，60多个test有10个无法通过
 * 
 * Discuss:
 * 
 * 代码①，1ms，使用substring和equals方法，从hayback中取出所有needle的长度的片段与needle进行对比
 * 
 * 代码②，4ms，代码①的底层版本，效率更低
 * 
 * 
 */


public class ImplementstrStr {
	/*
	 * 代码①
	 * 虽然效率很高，但是觉得在对待这种类型的问题，使用substring是不是不够底层？
	 */
    public int strStr(String haystack, String needle) {
        int len1 = haystack.length(), len2 = needle.length();
        if (len1 < len2) return -1;
        if (len2 == 0) return 0;
        int threshold = len1 - len2;
        for (int i = 0; i <= threshold; i++) {
            if (needle.equals(haystack.substring(i, i + len2))) return i;
        }
        return -1;
    }
    
    /*
     * 代码②
     */
    public int strStr_2(String haystack, String needle) {
        int len1 = haystack.length(), len2 = needle.length();
        if (len1 < len2) return -1;
        if (len2 == 0) return 0;
        int threshold = len1 - len2;
        for (int i = 0; i <= threshold; ++i) {
            for (int j = 0; j < len2; ++j) {
                if (haystack.charAt(i + j) != needle.charAt(j)) break;
                if (j == len2 - 1) return i;
            }
        }
        return -1;
    }
}
