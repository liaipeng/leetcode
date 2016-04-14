package com.leetcode.algorithm.easy;

/*
Given an integer, write a function to determine if it is a power of two.
*/

/*
 * 想不出来。
 * Discuss：如果是power of 2，那么它的二进制一定是只有1位是1，其余都是0，666666666666666啊
 * 
 * 看了Discuss的思想后，写了代码①，简直跟作弊一样，2ms
 * 
 * 代码② ，如果不使用java的自带函数，那就用 n & (n - 1) 的思想 
 * n & (n - 1)的妙用：http://6460646.blog.163.com/blog/static/27779875201132893614412/
 * 2ms
 * 
 * 代码③ ，一般思路， 用递归, 2ms
 */
public class PowerOfTwo {
	/*
	 * 代码①
	 */
    public boolean isPowerOfTwo(int n) {
        if (n < 1) return false;
        return Integer.bitCount(n) == 1;
    }
    
    /*
     * 代码②
     * 因为只有1位为1，那么n & (n - 1)一定为0
     */
    public boolean isPowerOfTwo_2(int n) {
        if (n < 1) return false;
        return (n & (n - 1)) == 0;
    }
    
    /*
     * 代码③
     * 最后一句，如果不用else，直接return，结果是3ms，不是2ms
     */
    public boolean isPowerOfTwo_3(int n) {
        if (n < 1) return false;
        if (1 == n) return true;
        if (n % 2 != 0) return false;
        else return isPowerOfTwo_3(n >> 1);
    }
    
    
}
