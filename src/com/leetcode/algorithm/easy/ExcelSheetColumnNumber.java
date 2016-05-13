package com.leetcode.algorithm.easy;

/*
 Related to question Excel Sheet Column Title

 Given a column title as appear in an Excel sheet, return its corresponding column number.

 For example:

 A -> 1
 B -> 2
 C -> 3
 ...
 Z -> 26
 AA -> 27
 AB -> 28 
 */
/*
 * 与168：Excel Sheet Column Title相反
 * 输入字母，返回对应数字
 * 思路：
 * 		把s转换为char数组，遍历数组，依次转换为相应数字，相加
 * 		注意遍历的顺序是从后往前
 */

public class ExcelSheetColumnNumber {
	public static int titleToNumber(String s) {
		char[] chs = s.toCharArray();
		int number = 0, tempNum = 0, j = 0;

		for (int i = chs.length - 1; i >= 0; i--) {
			tempNum = (chs[i] + 1 - 'A') * (int) Math.pow(26, j++);
			number += tempNum;
		}

		return number;
	}

	public static void main(String[] args) {
		int number = titleToNumber("AA");
		System.out.println(number);
	}

}
