package com.leetcode.algorithm.easy;

/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.

*/

/*
 * 给定一个整数数组，求i到j(i<=j)范围内数的和，数组不会改变， 且sumRange方法会被多次调用
 * 如果使用普通的遍历数组的方式来计算和肯定会超时
 * 
 * 尝试用二维数组来算出每一个i到j的和，超时了~
 * 又是把问题复杂化了。。
 * 其实只需要用一个一维数组，把0-1,0-2,...,0-5的记录下来，调用sumRange时，扣掉某一部分即可
 * 例如，获取3-5的和，只需要0-5的和减去0-2的和即可
 * 
 * Discuss：
 * 代码①，3ms，用一维数组计算0到1,...,0到nums.length的和
 * 
 * 实际上题目提到了sumRange()方法会被多次调用，所以我觉得其实这边应该用一个二维数组做一个cache
 * 把计算得到的和存入cache中，调用sumRange()方法的时候查看cache[i][j]是否已经计算过，若有则直接返回，不用再计算
 * cache 可以用long，用一个超过int范围的值来表示没有计算过
 * 
 */
public class RangeSumQuery_Immutable {
	/*
	 * 代码①
	 */
	private int[] sums;
	
    public RangeSumQuery_Immutable(int[] nums) {
        sums = new int[nums.length];
        if (nums.length > 0) sums[0] = nums[0];
        for (int i = 1; i < sums.length; i++) sums[i] = sums[i-1] + nums[i];
    }

    public int sumRange(int i, int j) {
        return i == 0 ? sums[j] : sums[j] - sums[i - 1];
    }
    
 //原题中类名和构造方法是叫NumArray  
 // Your NumArray object will be instantiated and called as such:
 // NumArray numArray = new NumArray(nums);
 // numArray.sumRange(0, 1);
 // numArray.sumRange(1, 2)
}
