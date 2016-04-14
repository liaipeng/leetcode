package com.leetcode.algorithm.medium;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
*/

/*
 * Single Number系列的第三题，SingleNumber的升级版的升级版
 * 从一堆重复出现2次的元素中找出两个只出现一次的元素
 * 线性时间，以及常数空间。
 * 
 * 代码①，11ms，用HashSet，用了O(n)的空间，违反题目要求了，下下策
 * 
 * 正确的解法应该是用位运算
 * Discuss：
 * 代码②，2ms，用位运算，O(n)时间，O(1)空间，666666
 */

public class SingleNumber_III {
	/*
	 * 代码①
	 */
    public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int[] singles = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) set.remove(nums[i]);
        }
        Iterator<Integer> it = set.iterator();
        singles[0] = it.next();
        singles[1] = it.next();
        return singles;
    }
    
    /*
     * 代码②
     * 把所有数进行异或，出现两次的都为0了，剩下的是两个SingleNumber的异或结果
     * 因为两个SingleNumber是不相等的，所以必然有至少一位二进制位异或结果为1
     * 也就是该二进制位Single1为1，而Single2为0
     * 那么，把所有数以该位是否为1分为两组，相同的数必然被分到同一组，而SingleNumber会被分到两个组
     * 此时，只需分别把两组的数全部异或起来，即能找出两个SingleNumber
     */
    public int[] singleNumber_2(int[] nums) {
        int diff = 0;
        int[] singles = new int[2];
        for (int num : nums) {
            diff ^= num;
        }
        // 找出两个SingleNumber异或结果最低位的1，因为-diff 等于 ~(diff - 1)，（Java用补码表示负数）
        // 若觉得难理解，可以用 diff = Integer.highestOneBit(diff);代替（找出最高位的1，不管哪一位都一样）
        diff &= -diff;
        // 进行分组异或
        for (int num : nums) {
            if ((num & diff) == 0) {  // 若该位为0
                singles[0] ^= num;
            } else {
                singles[1] ^= num; 
            }
        }
        return singles;
    }
}
