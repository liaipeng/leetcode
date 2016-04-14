package com.leetcode.algorithm.hard;

import java.util.Arrays;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/
/*
 * Best Time to Buy and Sell Stock系列第四题
 * 这系列还真多，这题是给一个k，代表最多可以执行k次交易
 * 
 * 动态规划问题
 * 
 * 代码①，3ms，结合BestTimeToBuyAndSellStock_III和BestTimeToBuyAndSellStock_II
 * 
 * Discuss里也都是这样做的，只不过他们用的是二维数组，我用2个数组，我觉得用2个数组更好理解
 */

public class BestTimeToBuyAndSellStock_IV {
	/*
	 * 代码①
	 * 第一个反应就是根据BestTimeToBuyAndSellStock_III代码②进行修改，但是定义2个长度为k+1的数组会超出内存限制
	 * 尝试使用滚动数组来解决，一直出错
	 * 
	 * 所以把代码分为两个部分，如果k > prices.length / 2，执行第一部分，否则，执行第二个部分
	 */
    public int maxProfit(int k, int[] prices) {
        if (prices.length < 2 || k < 1) return 0;
        // 加入k比数组的一半还大，问题退化成Best Time to Buy and Sell Stock II：可以随意任意执行几次交易
        if (k > prices.length / 2) {
        	// 以下是BestTimeToBuyAndSellStock_II代码③
            int totalProfit = 0;
            for (int i = 0; i < prices.length - 1; i++) {
                if (prices[i + 1] > prices[i]) totalProfit += prices[i + 1] - prices[i];
            }
            return totalProfit;
        }
        
        // 其实这边应该是可以使用滚动数组来节省空间的，但是测试一直出错，所以最后妥协了
        int[] buy = new int[k+1];
        int[] sell = new int[k+1];
        Arrays.fill(buy, 1, k+1, Integer.MIN_VALUE); // buy[0] = 0, buy[1 ~ end] = Integer.MIN_VALUE
        // 以下其实是BestTimeToBuyAndSellStock_III代码②的变型
        for (int curPrice : prices) {
            for (int i = 1; i <= k; i++) {
                if (buy[i] < sell[i-1] - curPrice) buy[i] = sell[i-1] - curPrice;
                if (sell[i] < buy[i] + curPrice) sell[i] = buy[i] + curPrice;
            }
        }
        return sell[k];
    }
}
