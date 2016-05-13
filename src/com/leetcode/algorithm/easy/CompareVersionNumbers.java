package com.leetcode.algorithm.easy;

/*
Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
*/

/*
 * 判断用字符串表示的两个版本的大小。"1.0"和"1"是一样的，还有更坑爹的，有"1.0.00000.0.000000.0"这样的，神经病
 * 
 * 代码①，3ms，用split以"."为分割点进行切割，然后遍历判断。为了处理那该死的0.0.000，代码难看的一逼
 * 
 * Discuss：
 * 代码②，4ms，也是用split，代码①的简化版
 * 
 * 代码③，1ms，速度最快，不使用split，遍历字符
 */

public class CompareVersionNumbers {
	/*
	 * 代码①
	 * 很大的代码量浪费在其中一个version结束了，判断另一个version剩下部分是否全部为0上
	 */
    public int compareVersion(String version1, String version2) {
        String[] ver1 = version1.split("\\.");
        String[] ver2 = version2.split("\\."); 
        int i = 0;
        for (; i < ver1.length && i < ver2.length; i++) {
            Integer v1 = Integer.valueOf(ver1[i]);
            Integer v2 = Integer.valueOf(ver2[i]);
            if (v1 > v2) return 1;
            if (v1 < v2) return -1;
        }
        if (i == ver1.length) {
            while (i < ver2.length) {
                if ("0".equals(ver2[i]) || ver2[i].contains("00")) i++;
                else return i == ver2.length ? 0 : -1;
            }
        }
        else if (i == ver2.length) {
            while (i < ver1.length) {
                if ("0".equals(ver1[i]) || ver1[i].contains("00")) i++;
                else return 1 == ver1.length ? 0 : 1;
            }
        }
        return 0;
    }
    
    /*
     * 代码②
     * 看了本代码，被代码①蠢哭。
     */
    public int compareVersion_2(String version1, String version2) {
        String[] ver1 = version1.split("\\.");
        String[] ver2 = version2.split("\\."); 
        int len = Math.max(ver1.length, ver2.length);
        for (int i = 0; i < len; i++) {
        	// 这一部分巧妙的减少了大量的代码量，避免了代码①for循环之后冗长的处理：其中一个version遍历结束了，之后都用0进行比较。
            Integer v1 = i < ver1.length ? Integer.parseInt(ver1[i]) : 0;
            Integer v2 = i < ver2.length ? Integer.parseInt(ver2[i]) : 0;
            if (v1 > v2) return 1;
            if (v1 < v2) return -1;
        }
        return 0;
    } 
    
    /*
     * 代码③
     */
    public int compareVersion_3(String version1, String version2) {
        char[] ver1 = version1.toCharArray();
        char[] ver2 = version2.toCharArray();
        int temp1, temp2;
        for (int i = 0, j = 0; i < ver1.length || j < ver2.length; i++, j++) {
        	// temp的作用除了在while循环体内计算之外，另一个作用是当一个version遍历结束，以0来进行与另一个version剩余部分的对比
            temp1 = 0; 
            temp2 = 0;
            // 遇到'.'前，把字符转成int
            while (i < ver1.length && ver1[i] != '.') {
                temp1 = temp1 * 10 + ver1[i++] - '0';
            }
            while (j < ver2.length && ver2[j] != '.') {
                temp2 = temp2 * 10 + ver2[j++] - '0';
            }
            if (temp1 > temp2) return 1;
            if (temp1 < temp2) return -1;
        }
        return 0;
    } 
    
    
}
