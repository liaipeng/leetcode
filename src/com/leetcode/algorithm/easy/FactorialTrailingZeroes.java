package com.leetcode.algorithm.easy;

/*
Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*/

/*
 * 求n！末尾的0的个数，要求时间复杂度不超过O(logN)
 * 
 * 仔细观察，会发现末尾出现0的情况，只可能是2*5，2是随便都有的，但是5只有每过5个数才会出现
 * 所以，只要算1~n区间出现5的个数即可。注意，类似25可以拆分成5*5，两个5
 * 
 * 代码①， 2ms，第二梯队
 * 
 * 代码②，用递归，代码只需要1行
 */

public class FactorialTrailingZeroes {
	/*
	 * 代码①
	 * n不断除以5，直到等于0为止
	 * 将过程中的商相加即为结果
	 * 
	 * 值得一提的是，把while循环的条件改为 n >= 5，可以减少一次循环，让时间从2ms到1ms
	 */
    public int trailingZeroes(int n) {
        int count = 0;
        while(n / 5 != 0) {
            n /= 5;
            count += n;
        }
        return count;
    }
    
    /*
     * 代码②
     * 递归，计算n/5，然后把商放进去递归，直到n<5为止
     */
    public int trailingZeroes_2(int n) {
        return n < 5 ? 0 : n / 5 + trailingZeroes_2(n / 5);
    }
    
    
}
