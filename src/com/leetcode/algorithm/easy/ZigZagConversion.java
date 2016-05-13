package com.leetcode.algorithm.easy;

/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
(you may want to display this pattern in a fixed font for better legibility)
*****************
P   A   H   N
A P L S I I G
Y   I   R
*****************
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

/*
 * 给定一个字符串，指定行数，把它转换成之字形的写法，返回转换后的字符串。
 * 题目给的是行数为3行的走法，如果是4行，就变成这样：
 * *****************
 * P    I    N
 * A  L S  I G   所以转换后是 PINALSIGYAHRPI
 * Y A  H R
 * P    I
 * *****************
 * 这种题目一看就是要找规律，奈何没找出来 T T
 * 想每行用一个字符串存储，最后把字符串进行拼接，在选择某行字符串的时候使用%的结果作为字符串数组的下标进行选择，折腾了半天以失败告终。
 * 代码①就是用的每行的字符串拼接的方法。
 * 
 * Discuss：
 * 代码①，12ms，用大小为numRows的字符串数组存储每一行的字符，然后把字符串拼接起来
 * 
 * 
 * 代码②，7ms，另一种解法是找规律（见链接），规律真的挺难找的，还是推荐用代码①解
 * http://blog.csdn.net/zhouworld16/article/details/14121477
 * http://blog.csdn.net/ljiabin/article/details/40477429
 */

public class ZigZagConversion {
	/*
	 * 代码①
	 */
    public String convert(String s, int numRows) {
        char[] ch = s.toCharArray();
        StringBuilder[] ans = new StringBuilder[numRows];
        for (int i = 0; i < ans.length; ++i) ans[i] = new StringBuilder("");
        for (int i = 0; i < ch.length;) {
        	// 按照题目********中的之字形字符方阵，垂直向下走
            for (int row = 0; row < numRows && i < ch.length; ++row) ans[row].append(ch[i++]);
            // 沿对角线往上走，要过滤上下边缘，所以row是从 numRows - 2 到 1.
            for (int row = numRows - 2; row >= 1 && i < ch.length; --row) ans[row].append(ch[i++]);
        }
        for (int i = 1; i < ans.length; ++i) ans[0].append(ans[i]);
        return ans[0].toString();
    }
    
    /*
     * 代码②
     * 规律：
     * 第0行和最后一行中，前一个下标的值和后一个下标的值相差 2 * nRows - 2 
     * 中间行中，前一个下标到下一个下标相差的值是它们所处的行i下面的所有行的点的个数，即2 * (nRows - 1 - i)
     * 
     * 中间行的规律真的挺难找的
     */
    public String convert_2(String s, int nRows) {
        int len = s.length();
        if (len == 0 || nRows < 2) return s;
        char[] ch = s.toCharArray();
        StringBuilder ans = new StringBuilder("");
        int lag1 = 2 * nRows - 2; // 首行和末行的循环周期
        for (int i = 0; i < nRows; i++) {
            for (int j = i; j < len; j += lag1) {
                ans.append(ch[j]);
                //非首行和末行时还要加一个
                if (i > 0 && i < nRows-1) {
                	int lag2 = 2 * (nRows - 1 - i); // 中间行的循环周期
                    int t = j + lag2;
                    if (t < len) {
                        ans.append(ch[t]);
                    }
                }
            }
        }
        return ans.toString();
    }
}
