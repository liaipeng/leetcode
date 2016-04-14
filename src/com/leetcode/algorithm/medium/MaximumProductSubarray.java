package com.leetcode.algorithm.medium;

/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/

/*
 *  Maximum Subarray的升级版
 *  Maximum Subarray求出最大子串和，而这题是要求出最大子串乘积
 *  
 *  与加法相比，乘法不同就是负负得正，正负得负，以及所有数与0相乘都为0
 *  所以，求最大子串和是在遍历过程中不断抛弃前面的负数和。而最大子串乘积，则是当前面的乘积为0时将前面的结果抛弃
 *  也就是有一个选择起点的逻辑，如果之前的最大和最小值同当前元素相乘之后，没有当前元素大（或小）那么当前元素就可作为新的起点。
 *  例如，前一个元素为0的情况，{1,0,9,2}，到9的时候9应该作为一个最大值，也就是新的起点，
 *  {1,0,-9,-2}也是同样道理，-9比当前最小值还小，所以更新为当前最小值。
 *  
 *  另外就是要分开考虑整数和负数，因为当nums[i]为负数时，原来的最大值会变成最小值，反之，最小值会变最大值
 *  也就是说子数组乘积最大值的可能性为：累乘的最大值碰到了一个正数；或者，累乘的最小值（负数），碰到了一个负数。
 *  所以，我们在处理乘法的时候，除了需要维护一个局部最大值，同时还要维护一个局部最小值
 *  
 *  这种方法只需要遍历一次数组即可，算法时间复杂度为O(n)。
 *  具体见代码①
 *  
 *  动态规划
 *  
 *  Discuss：
 *  代码①，4ms
 */

public class MaximumProductSubarray {
	/*
	 * 代码①
	 */
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        int sofarMax = nums[0], sofarMin = nums[0], max = nums[0]; // 分别记录目前为止的最大乘积、最小乘积，以及全局最大乘积
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= 0) { // nums[i]为整数，求出此时的sofarMax以及sofarMin
                sofarMax = Math.max(sofarMax * nums[i], nums[i]); //若sofarMax同当前元素相乘之后，没有当前元素大那么当前元素就作为新的起点。
                sofarMin = Math.min(sofarMin * nums[i], nums[i]); //若sofarMin同当前元素相乘之后，没有当前元素小那么当前元素就作为新的起点。
            } else { // 反之，如果nums[i]为负数，那么sofarMax和sofarMin要反过来计算
                int temp = sofarMax;
                sofarMax = Math.max(sofarMin * nums[i], nums[i]); 
                sofarMin = Math.min(temp * nums[i], nums[i]);
            }
            max = Math.max(sofarMax, max); // 判断遍历到目前为止的sofarMax是否比max大，是的话更新。不断更新全局最大乘积
        }
        return max; 
    }
}
