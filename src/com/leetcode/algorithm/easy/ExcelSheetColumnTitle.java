package com.leetcode.algorithm.easy;

/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
 */

/*
 * 卧槽，什么鬼，题目都看不懂
 * 
 * 看了别人的博客：
 * 		Excel序是这样的：A~Z, AA~ZZ, AAA~ZZZ, ……

		本质上就是将一个10进制数转换为一个26进制的数

		注意：由于下标从1开始而不是从0开始，因此要减一操作。
		
		思路：(n - 1) % 26 ，得到的余数 + 'A'，得到该位转换后的值
			  若(n - 1) / 26 不为0，说明要进位，把值作为新的一轮输入
			  循环
 */

public class ExcelSheetColumnTitle {
    public static String convertToTitle(int n) {
        StringBuilder title = new StringBuilder("");
    	while(n > 0) {
    		title.append((char) ((n-1) % 26 + 'A'));
    		n = (n - 1) / 26;    		
    	}
    	return title.reverse().toString();
    }
    
    public static void main(String[] args) {
    	String str = convertToTitle(1);
    	System.out.println(str);
	}
}











