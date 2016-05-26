package com.leetcode.algorithm.medium;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
design an algorithm to find the maximum profit.
*/

/*
 * 给一组数字，代表每天股票的价格，假设只能做一次交易(买+卖)，求出能够得到的最大收益
 * 
 * 动态规划问题
 * 
 * Medium等级的题目对我来说还是比较难的，这已经是连续第4题没想出来怎么做了
 * 
 * Discuss：
 * 代码①，1ms，简单粗暴，没有想到有点不应该了
 * 
 * 代码②，1ms，把问题转换成求最大子串和的问题，然后运用Kadane算法进行求解 http://blog.csdn.net/joylnwang/article/details/6859677
 */


public class BestTimeToBuyAndSellStock {
	/*
	 * 代码①
	 * 动态规划法。从前向后遍历数组，记录当前出现过的最低价格，作为买入价格，并计算以当天价格出售的收益，
	 * 作为可能的最大收益，整个遍历过程中，出现过的最大收益就是所求。
	 */
    public int maxProfit(int[] prices) {
        int maxProfit = 0, minPrice = Integer.MAX_VALUE;
        // 在遍历prices的过程中，不停更换最优的最高收益maxProfit和最低价格minPrice
        for (int curPrice : prices) {
        	// 比使用Math.max 和 Math.min 要快2ms
            if (minPrice > curPrice) minPrice = curPrice; // 若找到更低的价格，将minPrice替换为该买入价格
            //每天都尝试卖股票，若当天卖的钱比maxProfit高，替换maxProfit
            else if (curPrice - minPrice > maxProfit) maxProfit = curPrice - minPrice;
        }
        return maxProfit;
    }
    
    /*
     * 代码②
     * 将原问题进行转换，例如把给定的数组从{1, 7, 4, 11}考虑成{0, 6, -3, 7}
     * 也就是把 股票每天的价格 转换成 股票每天相较昨天的涨跌
     * 原问题也就变成，已知股票每天的涨跌，求在哪一天卖出能有最大效益
     * 问题也就变成了求{0, 6, -3, 7}的最大子串和
     */
    public int maxProfit_2(int[] prices) {
        int maxSofar = 0, maxCur = 0; // maxSofar是到目前为止的最高收益，macCur是当前的最高收益
        for (int i = 1; i < prices.length; i++) {
            maxCur += prices[i] - prices[i - 1]; // prices[i] - prices[i - 1] ==> 每天的股价的涨跌
            if (maxCur < 0) maxCur = 0; // 如果当前最高收益小于0，直接抛弃掉前面的子串，重新开始计算
            else if (maxCur > maxSofar) maxSofar = maxCur; // 如果当前最高收益大于目前为止的最高收益，更改目前最高收益
        }
        return maxSofar;
    }
}
