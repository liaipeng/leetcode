package com.leetcode.algorithm.easy;

/*
Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

Note that 1 is typically treated as an ugly number.
*/

/* 
 *	代码①，循环次数太多，效率低下， 62ms，0.5% ORZ
 *
 * 	代码②， 2ms
 * 
 * 	代码③， 2ms，代码②简化版
 */

public class UglyNumber {
	/*
	 * 代码①
	 */
    public static boolean isUgly(int num) {
        if (1 == num) return true;
        if (0 == num) return false;
        while (num != 1) {
            if (num % 2 == 0) num = num / 2;
            else if (num % 3 == 0) num = num / 3;
            else if (num % 5 == 0) num = num / 5;
            else break;
        }
        return num == 1;
    }
    
	/*
	 * 代码②
	 * 如果num能被2整除，就一直除以2；3和5同理。
	 * 最后剩下的num如果为1，说明num的因数只有2、3和5，是ugly number
	 * 如果不为1，那么可能有其他素因数，或者其他非素因数，不管哪种情况，都不是ugly number
	 */
    public static boolean isUgly_2(int num) {
        if (1 == num) return true;
        //题目说是正数，但是测试数据里居然有0，如果不判断的话，num为0时就TLE了
        if (0 == num) return false;
        while (num % 2 == 0) num >>= 1;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;
        return num == 1;
    }
    
    /*
     * 代码③
     * 代码②简化版，i = 2, 3, 4, 5
     * 多了一个4是没关系的，因为能被4整除肯定能被2整除的（或者说，前面已经被2整除过了，余数到4这边肯定是不能整除了，直接跳过）
     * 我觉得如果倒过来除的话会不会更快，因为/4比/2要快
     */
    public boolean isUgly_3(int num) {
        for (int i=2; i<6 && num>0; i++)
            while (num % i == 0)
                num /= i;
        return num == 1;
    }
    
    
    public static void main(String[] args) {
		System.out.println(isUgly(412412444));
	}
}
