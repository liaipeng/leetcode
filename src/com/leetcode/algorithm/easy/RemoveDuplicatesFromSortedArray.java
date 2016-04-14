package com.leetcode.algorithm.easy;

/*
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
It doesn't matter what you leave beyond the new length.
*/

/*
 * 我是先做的27题，Remove Element才做的这题。26题是给一个数组和一个值，删除数组中所有等于这个值的元素，返回新数组长度
 * 多出来的空位置可以放任何数字，然后也是要求就地操作，不能用辅助数组
 * 
 * 而这题是给定一个已经排好序的数组，就地删除数组中重复的元素，也就是每个元素只能出现一次,返回新数组长度
 * 
 */

/*
 * 拿到题目先暴力破解把分拿到 0.0 代码①， O(n²)，3% 
 * 
 * Discuss
 * 代码②，2ms，双指针法，思路和283题 Move Zeroes类似
 */

public class RemoveDuplicatesFromSortedArray {
	/*
	 * 代码①
	 * 暴力破解
	 */
    public int removeDuplicates(int[] nums) {
        int count = 0;
        int tail = nums.length - 1;
        //从屁股往头遍历
        for (; tail > 0; --tail)
            //如果相等，把后面的往前提一个位置
            if (nums[tail-1] == nums[tail]) {
                count++;
                for (int i = tail - 1; i < nums.length - 1; ++i) 
                    nums[i] = nums[i + 1];
            }
        return nums.length - count;
    }
    
    /*
     * 代码②
     * 双指针，current遍历旧数组，tail经过的地方变成新数组
     * 如果current指向的数等于tail指向的数，current无视该数，遍历到下一个数
     * 如果不相等，把current指向的数填到tail指向的数。tail向后移动一位（相当于新数组扩充一位）
     */
    public int removeDuplicates_2(int[] nums) {
        if (nums.length < 2) return nums.length;
        int tail = 0;
        for (int current = 1; current < nums.length; ++current) {
            if (nums[current] != nums[tail])
                nums[++tail] = nums[current];
        }
        //tail是新数组的下标，而数组是从0计数的，所以返回长度的时候要+1
        return ++tail;
    }
}
