package com.leetcode.algorithm.medium;

/*
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

/*
 * 给定一个int型数组，里面每个元素都出现2次，有一个只出现一次，找到这个元素。
 * 要求用O(n)时间，且不用额外空间完成
 * 
 * 没有想出什么好解法，这是我第一题Medium的题目，第一题就受挫。。
 * 
 * Discuss：
 * 代码①，1ms，用XOR运算，6666666666666
 * 
 */
public class SingleNumber {
	/*
	 * 代码①
	 * 把所有元素进行异或，剩下那个就是只出现一次的元素。因为((2^2)^(1^1)^(4^4)^(5)) => (0^0^0^5) => 5.
	 */
    public int singleNumber(int[] nums) {
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            num ^= nums[i];
        }
        return num;
    }
}
