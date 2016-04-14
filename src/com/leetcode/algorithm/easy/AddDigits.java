package com.leetcode.algorithm.easy;


/* 	
 Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 For example
 	Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
*/

/*
Follow up:
	Could you do it without any loop/recursion in O(1) runtime?
*/

/*
Hint:

A naive implementation of the above process is trivial. Could you come up with other methods?
What are all the possible results?
How do they occur, periodically or randomly?
You may find this Wikipedia article useful.
*/



/* 先用循环的方法(见addDigits_old)完成题目，然后输入1到30，得到输出。
 * 列出所有输出查找规律(见方法②addDigits_old注释)
 * 得到规律  sum = (num - 1) % 9 + 1
 * 
 */

public class AddDigits {
	//方法①
	public static int addDigits(int num) {
		return (num - 1) % 9 + 1;
    }
	
	
	/* 方法②
	 * 输入		输出
	 *  1		1
		2		2
		3		3
		4		4
		5		5
		6		6
		7		7
		8		8
		9		9
		10		1
		11		2
		12		3
		13		4
		14		5
		15		6
		16		7
		17		8
		18		9
		19		1
		20		2
		21		3
		22		4
		23		5
		24		6
		25		7
		26		8
		27		9
		28		1
		29		2
		30		3
	 */
	public int addDigits_old(int num) {
		while (num>9) {
			num = getEachSum(num);
		}
		return num;
    }
	
	public int getEachSum(int num) {
		int eachSum = 0;
		while (num>0) {
			eachSum = eachSum + num % 10;
			num = num / 10;
		}
		return eachSum;
	}
	
	public static void main(String[] args) {
		for (int num=1; num<=30; num++)  {
			System.out.println(num + "\t\t" + addDigits(num));
		}
	}
}
