package com.leetcode.algorithm.easy;

/*
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

Hint:
Could you do it in-place with O(1) extra space?
*/

/*
 *  给定一个数组，和一个步长k，将数组右移k位
 *  要求只能使用O(1)的空间
 *  所以不能先把要右移的数存起来，把其他数右移完再把数填回去。
 *  
 *  然后就不会做了
 *  
 *  Discuss：
 *  代码①，1ms
 *  
 */

public class RotateArray {
	/*
	 * 代码①
	 * 三部分：1.逆转整个数组		2.逆转0到k-1部分(逆转完即为从右边移位到左边的部分)		3.逆转剩下的部分(逆转完即为从左右移位到右边的部分)
	 */
    public void rotate(int[] nums, int k) {
        k %= nums.length; // 看了答案我才想到k有可能大于nums.length的，防不胜防啊
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    // 逆转数组的start到end部分
    private void reverse(int[] nums, int start, int end) {
        int temp;
        while (start < end) {
            temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            start++;
            end--;
        }
    }
}
