package com.leetcode.algorithm.medium;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. 
You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). 
However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/*
 * 
 * Best Time to Buy and Sell Stock 系列第二题
 * BestTimeToBuyAndSellStock只能买一次卖一次，这题可以不限买卖次数，
 * 但是不能同时进行两项操作，要买一只新股，前提是把当前手上的股票先卖掉
 * 
 * 贪心算法问题
 * 
 * 只要作出在当前看来是最好的选择，最终全局结果就会是最好的结果
 * 
 * 代码①，2ms，O(n)时间复杂度
 * 
 * Discuss：
 * 
 * 代码②，2ms，代码①简化版
 * 
 * 代码③，2ms，简单粗暴的令人发指。。。但是Discuss中有争议
 * 
 */

public class BestTimeToBuyAndSellStock_II {
	/*
	 * 代码①
	 * 把给定的股价看成折线图，在波谷的时候买进，波峰的时候卖出
	 */
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        // totalProfit = 总收益， curProfit = 当前波谷到波峰的最大收益， buy表示当前买入价格(也就是波谷的值)
        int totalProfit = 0, curProfit = 0, buy = prices[0];
        for (int i = 0; i < prices.length - 1; i++) {
        	// 下坡过程：
        	// ① 刚刚开始下坡，即找到了波峰prices[i]，把波谷到波峰的收益加入总收益中，并清空当前收益
        	// ② 一直在下坡，也就是找波谷的过程，此时buy一直往前走，直到找到了波谷
            if (prices[i] > prices[i + 1]) {
                totalProfit += curProfit;
                buy = prices[i + 1];
                curProfit = 0;
                // 上坡过程：
                // ① 刚刚开始上坡，也就是找到了波谷prices[i]
                // ② 计算波峰与波谷的差值
            } else {
                curProfit = prices[i + 1] - buy; // 找到波峰
            }
        }
        // 最后一次波谷到波峰（也有可能没有波峰，curProfit为0）还没加入到总收益中
        return totalProfit + curProfit;
    }
    
    /*
     * 代码②
     * 思路跟代码①一样，找波谷波峰。但是比代码①要简洁易于理解很多。这种嵌套while进行遍历的写法应该学习。
     */
    public int maxProfit_2(int[] prices) {
        int totalProfit = 0, buy = 0, i = 0;
        while (i < prices.length) {
            while (i < prices.length - 1 && prices[i + 1] < prices[i]) i++; // 下坡，找波谷
            buy = prices[i++]; // 找到波谷，买入
            while (i < prices.length - 1 && prices[i + 1] > prices[i]) i++; // 上坡，找波峰
            if (i < prices.length) totalProfit += prices[i++] - buy; // 波峰-波谷，判断i < prices.length是因为有可能根本没有波峰
        }
        return totalProfit;
    }
    
    /*
     * 代码③
     * 不管波谷波峰，反正只要第二天比今天价格高，我就今天买，明天卖，赚一点是一点。
     * 但是有争议：
     * 反方认为，似乎违背了题目的要求 "you may not engage in multiple transactions at the same time"，在同一天进行买进和卖出了
     * 例如，[1, 2, 3]，此代码的计算过程是totalProfit = (2 - 1) + (3 - 2) = 2;
     * 
     * 正方认为，①题目有歧义，at the same time只是说买前要先卖出去而已，并没有说不能再同一天进行买进和卖出（我觉得这个是强词夺理）
     * 			 ②(2 - 1) + (3 - 2) = (3 - 1) + (2 - 2)，也就是说，在第二天卖了股票，然后又买入股票，相当于第二天什么也没有做。
     * 			       虽然totalProfit的计算过程是(2 - 1) + (3 - 2)，但是可以理解为(3 - 1)，所以结果是与(3 - 1)一样的。（这个我还是比较认同的）
     * 
     * 总之，使用代码②万无一失
     */
    public int maxProfit_3(int[] prices) {
        int totalProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) totalProfit += prices[i + 1] - prices[i];
        }
        return totalProfit;
    }
}
