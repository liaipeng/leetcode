package com.leetcode.algorithm.medium;

import java.util.Arrays;

/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*/

/*
 * 给定一个数组，包含不同的n个数，找出从0到n缺失的那个数，如果都在就返回最大的数max+1
 * 要求在O(n)时间和O(1)空间内完成
 * 
 * 代码①，14ms，O(nlogn)，超过O(n)了。。。
 * 
 * 代码②，2ms，用位运算，O(n)时间和O(1)空间，误打误撞居然做出来了
 * 
 * Discuss:
 * 代码③，2ms，代码②加强版，666666
 * 
 * 代码④，1ms，利用等差数列求和，666666666666666
 * 
 * 代码⑤，1ms，把代码④转变为减法的思路，思路简单粗暴有效，66666666666666666
 */
public class MissingNumber {
	/*
	 * 代码①
	 * 先对数组进行排序，然后遍历一遍
	 * 因为排序的复杂度是O(nlogn)，所以时间复杂度超过O(n)了。。
	 */
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) return nums[i - 1] + 1;
        }
        return nums[0] != 0 ? 0 : nums[nums.length - 1] + 1;
    }
    
    /*
     * 代码②
     * 用位运算。
     * 计算数组中所有数的异或。=a
     * 然后计算0到max(数组中最大的数)所有数的异或，=b
     * 再将a和b进行异或，得出的结果就是缺少的那个数
     * 
     * 其实原理很简单，因为a^b^b = a。所以剩下的那个数肯定是缺少的那个数
     */
    public int missingNumber_2(int[] nums) {
        int a = 0, b = 0, max = 0;
        for (int num : nums) {
            a ^= num;
            if (num > max) max = num;
        }
        
        for (int i = 0; i <= max; i++) {
            b ^= i;
        }
        // 假如没有缺少数，那么max + 1 会等于 nums.length
        return max + 1 == nums.length ? max + 1 : a ^ b;
    }
    
    /*
     * 代码③
     * 在代码②的基础简化代码
     * 
     * 直接在一次循环中进行异或运算
     * 		1. 假如缺少某个数，例如0,1,2,3,5。 那么在循环中，i为0,1,2,3,4. 最后return时，i等于5
     * 		2. 假如没有缺少，例如0,1,2,3,4,5，那么在循环中,i为0,1,2,3,4,5。 最后return时，i等于6，即为max + 1;
     */
    public int missingNumber_3(int[] nums) {
        int xor = 0, i = 0;
        for (; i < nums.length; i++) {
            xor ^= i ^ nums[i];
        }
        return xor ^ i;
    }
    
    /*
     * 代码④ 
     * 利用等差数列求和公式 ： 1到n的和为 (n + 1) * n / 2
     * 将nums数组中所有数加起来，得到sum
     * 		1. 假如缺少某个数，例如0,1,2,3,5。此时n = nums.length = 5; (n+1)*n/2为1到5所有数的和，那么-sum即为缺少的那个数
     * 		2. 假如没有缺少，例如0,1,2,3,4,5，此时n = nums.length = 6; (n+1)*n/2为1到6所有数的和，那么-sum即为max + 1;
     *	
     *	之所以会这样，是因为数组是从0开始的，假如数组没有缺少数的话，nums.length是会超过nums最大的那个数的
     */
    public int missingNumber_4(int[] nums) {
        int sum = 0, n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        return (n * (n + 1) - 2 * sum) / 2; // 本来是 (n * (n + 1)) / 2 - sum的，但是这样当n比较大的时候会溢出
    }
    
    
    /*
     * 代码⑤
     * 直接在一次循环中进行+ -运算即可。
     * 		1. 假如缺少某个数，例如0,1,2,3,5。 那么在循环中，i为0,1,2,3,4。 那么循环结束时，ans = 1， 此时i等于5， ans = 5 - 1 = 4
     * 		2. 假如没有缺少，例如0,1,2,3,4,5，那么在循环中,i为0,1,2,3,4,5。 那么循环结束时，ans = 0，此时i等于6，即为max + 1;
     */
    public int missingNumber_5(int[] nums) {
        int diff = 0, i = 0;
        for (; i < nums.length; i++) {
            diff += nums[i] - i;
        }
        return i - diff;
    }
    
    
}
