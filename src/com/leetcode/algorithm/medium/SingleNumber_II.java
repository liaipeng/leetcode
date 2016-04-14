package com.leetcode.algorithm.medium;

/*
Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

/*
 * SingleNumber的升级版，SingleNumber是从所有出现2次的元素中找到只出现一次的元素，可以直接用异或搞定。
 * 这题是从所有出现3次的元素中找到只出现一次的元素。
 * ORZ。。。。
 * 
 * Discuss：
 * 
 * 代码①，3ms，用位运算，O(32*n)的时间复杂度，O(1)的空间
 * 
 * 代码②，2ms，看着都费脑。。。O(n)时间复杂度，O(1)空间，理解了思路后觉得6到不行
 * 
 */
public class SingleNumber_II {
	/*
	 * 代码①
	 * 这是一个通用的解法，把%3换成%k就是从出现k次的元素中找出出现1次的元素。
	 */
    public int singleNumber(int[] nums) {
        int single = 0, tempNum = 0;
        // 一个int有32位
        for (int i = 0; i < 32; i++) {
            tempNum = 0;
            // 将nums数组中所有元素按位累加
            for (int j = 0; j < nums.length; j++) {
                tempNum += (nums[j] >> i) & 1;
            }
            // 将位的累加值 % 3，除了单独的那个元素，其他的%3后变成0。 32位循环完毕后，最终single的值就是单独的那个数
            single |= (tempNum % 3) << i;
        }
        return single;
    }
    
    /*
     * 代码②
     * 其实整个思想就是在模拟二进制加法，twos和ones 从 00 -> 01 -> 10 -> 11 -> 00。 ones和twos代表%3的余数
     */
    public int singleNumber_2(int[] nums) {
        int ones = 0, twos = 0, three = 0; // 利用三个变量分别保存各个二进制位上 1出现一次、两次、三次的分布情况
        for (int i = 0; i < nums.length; i++) {
            twos |= ones & nums[i]; // 计算出现两次的二进制位，先计算twos，因为先计算ones值就变了。
            ones ^= nums[i];  // 计算出现一次的二进制位
            three = ones & twos; // 计算出现三次的二进制位(ones和twos中都存在的，合起来就是3次了)
            ones &= ~three; // three取反再与ones和twos进行与操作，把出现了三次的位置清零。也就是从11->00
            twos &= ~three;
        }
        return ones; // 最终剩下的出现一次的二进制位就是单独的那个元素
    }
}
