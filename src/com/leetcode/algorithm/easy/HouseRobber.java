package com.leetcode.algorithm.easy;

/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that adjacent houses have security system connected 
and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.
*/
/*
 * 很明显要用DP算法
 * 代码①，0ms
 * 
 * 代码②，0ms
 */
public class HouseRobber {
	/*
	 * 代码①
	 * sum数组记录盗窃的过程，也就是动态规划中的：每个阶段的状态
	 * 盗取第n家房子时候，如果选择盗窃这一家，那么要放弃n-1这一家。所以要选择这两种情况中的更大者。
	 * sum[n] = Math.max(sum[n-2] + nums[n], sum[n-1])，也就是动态规划中的：状态转移的递推关系
	 * 
	 * 另外，可以用两个变量分别记录过程，而不用开辟一个数组耗费O(N)的space，具体见代码②
	 * 但是实际上代码①在理解上要好理解多了，没有特殊要求的情况下，还是用代码①方便
	 */
    public int rob(int[] nums) {
        int len = nums.length;
        if (len < 2) return len == 0 ? 0 : nums[0];
        int[] sum = new int[len];
        //动态规划中的：问题初始化
        sum[0] = nums[0];
        sum[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < len; ++i) {
            sum[i] = Math.max(sum[i-2] + nums[i], sum[i-1]);
        }
        return sum[len - 1];
    }
    
    /*
     * 代码②
     * 代码①改良版
     */
    public int rob_2(int[] nums) {
        int len = nums.length;
        if (len < 2) return len == 0 ? 0 : nums[0];
        //rob决定抢当前房子得到的总钱数
        int rob = 0;
        //noRob表示不抢当前房子得到的钱
        int noRob = 0;
        for(int i = 0; i < len; ++i) {
            //临时记录rob的值
            int tempRob = rob;
            //要抢当前房子，那么就只能不抢前一个房子
            rob = noRob + nums[i];
            //假如不抢当前房子，那么可以选择不抢前一个房子和不抢前前一个房子的两种情况中大者
            noRob = Math.max(tempRob, noRob);
        }
        //到了最后一个房子面前，返回抢当前房子和不抢当前房子两种情况中大者
        return Math.max(rob, noRob);
    }
    
    
}
