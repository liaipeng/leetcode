package com.leetcode.algorithm.hard;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/*
 * Best Time to Buy and Sell Stock系列第三题
 * 这次是只允许做两次交易（两次买进卖出）
 * 
 * 动态规划
 * 
 * Discuss：
 * 代码①，2ms，从左到右与从右到左，进行两次遍历
 * 
 * 代码②，2ms，思路有点像SingleNumber_II的代码②，有点难理解，看了好久
 * 
 */
public class BestTimeToBuyAndSellStock_III {
	/*
	 * 代码①
	 * 动态规划，第一步扫描，先计算出子序列[0,…,i]中的最大利润，用一个数组保存下来，那么时间是O(n)。
	 *			第二步是逆向扫描，计算子序列[i,…,n-1]上的最大利润，这一步同时就能结合上一步的结果计算最终的最大利润了，这一步也是O(n)。
	 *			第一步和第二步扫描都是可以用于解Best Time to Buy and Sell Stock系列第一题的
	 *
	 *			第二次要用逆序的原因是因为把原来数组分成(0,i)(i+1,n)，我们要把这两个数组作为单独的数组来处理
	 *			如果第二部分再接着顺序扫，是依赖前i个元素的
	 */
    public int maxProfit(int[] prices) {
        int totalProfit = 0; // 总收益
        int leftMin = Integer.MAX_VALUE, leftMaxPro = 0; // 记录扫描过程的最低价格leftMin
        int[] leftProfits = new int[prices.length]; // 存储第一次扫描每一个i的收益
        // 第一次扫描
        for (int i = 0; i < prices.length; i++) {
        	// 老样子，这边如果用Math.max Math.min的话，效率低好多; 用 leftMin>prices[i]?prices[i]:leftMin;也会更低，但比Math.max好一点
            if (leftMin > prices[i]) leftMin = prices[i];
            else if (prices[i] - leftMin > leftMaxPro) leftMaxPro = prices[i] - leftMin;
            leftProfits[i] = leftMaxPro;
        }
        // 第二次逆序扫描
        int rightMax = Integer.MIN_VALUE, rightMaxPro = 0; // 因为是逆序扫描的，所以更新的最高出手价格rightMax
        for (int i = prices.length - 1; i >= 0; i--) {
            if (rightMax < prices[i]) rightMax = prices[i];
            else if (rightMax - prices[i] > rightMaxPro) rightMaxPro = rightMax - prices[i];
            int curProfit = i > 0 ? rightMaxPro + leftProfits[i - 1] : rightMaxPro; // 特别判断1<0
            if (curProfit > totalProfit) totalProfit = curProfit; // 扫描过程中更新最高总收益
        }
            
        return  totalProfit;       
    }
    
    
    /*
     * 代码②
     * 
     */
    public int maxProfit_2(int[] prices) {
    	// 以下4个变量分别记录在执行完”第一次买进“、”第一次卖出“、”第二次买进“以及”第二次卖出“4种操作后的收益，初始收益为0
    	// 所以买进是-curPrice，卖出是+curPrice
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;
        for (int curPrice : prices) {
            if (firstBuy < -curPrice) firstBuy = -curPrice; // 第一次买入后的最大收益
            if (firstSell < firstBuy + curPrice) firstSell = firstBuy + curPrice; // 第一次买入后卖出，得到的最大的收益
            if (secondBuy < firstSell - curPrice) secondBuy = firstSell - curPrice;// 第一卖出后第二次买入，得到的最大收益
            if (secondSell < secondBuy + curPrice) secondSell = secondBuy + curPrice;// 第二次买入后卖出， 得到的最大收益
        }
        return secondSell;
    }
}
