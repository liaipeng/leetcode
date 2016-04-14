package com.leetcode.algorithm.medium;

/*
Given an array of n integers where n > 1, nums, 
return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? 
(Note: The output array does not count as extra space for the purpose of space complexity analysis.)
*/

/*
 * 给定一个数组，返回这样的数组：output[i]是nums里除了nums[i]以外所有元素的乘积
 * 要求在O(n)时间和O(1)额外空间的限制下完成。
 * 而且不能用除法。擦，我第一个想到的就是用除法。
 * 
 * ORZ。。。
 * 
 * Discuss：
 * 代码①，2ms，两次遍历，第一次从左到右，第二次从右到左
 * 
 */

public class ProductOfArrayExceptSelf {
	/*
	 * 代码①
	 * 去掉自己，其他所有数的乘积。可以想成是i左边的所有数乘积 * i右边所有数的乘积
	 * 而且计算乘积的时候是一个迭代的过程，每次用前面的乘积 * 当前的值即可
	 * 
	 * 所以只需2次遍历
	 * 第一次从左边开始，存储i左边所有数的乘积
	 * 第二次从右边开始，计算右边所有数的乘积，与第一步左边数的乘积相乘，即为结果
	 */
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        int assist = 1;
        output[0] = 1;
        // left
        for (int i = 1; i < output.length; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }
        // right
        for (int i = nums.length - 2; i >= 0; i--) {
            assist = assist * nums[i + 1];
            output[i] = output[i] * assist;
        }
        return output;
    }
}
