package com.leetcode.algorithm.medium;

/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/

/*
 * 最大子串和问题
 * 要求用O(n)时间复杂度的分治法完成
 * 
 * 首先想到的就是kadane算法，但是kadane算法一般是用于最大和>0的情况，这一题有可能最大和是负数（也就是序列为全负），所以要做一些修改
 * 也就是将初始max设为MIN_VALUE，同时调整一下判断逻辑即可，具体见代码①
 * 
 * 代码①，1ms，kadane算法，O(n)时间复杂度
 * 
 * 
 * 
 */

public class MaximumSubarray {
	/*
	 * 代码①
	 * 与一般的kadane算法相比，要处理序列为全负的情况，只需调整两个if语句的顺序，初始化max为MIN_VALUE
	 */
    public int maxSubArray(int[] nums) {
        int curMax = 0, max = Integer.MIN_VALUE;
        for (int num : nums) {
            curMax += num;
            if (curMax > max) max = curMax; // 显然，假如序列全负，最大子串和的子串即为：绝对值最小的那个数
            if (curMax < 0) curMax = 0; // 若前n个数组成的子串和为负数，舍弃
        }
        return max;
    }
}
