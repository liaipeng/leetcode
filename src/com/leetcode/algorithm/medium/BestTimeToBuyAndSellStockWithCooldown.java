package com.leetcode.algorithm.medium;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]
*/

/*
 * Best Time to Buy and Sell Stock系列第五题。。
 * 题目基本上跟Best Time to Buy and Sell Stock II 一样，都是可以随便交易几次
 * 但是这题有一个冷却时间，卖掉的股票，第二天不能再买，必须要第三天才能买
 * 所以，原本Best Time to Buy and Sell Stock II是可以只要低价就买，只要有赚就卖。最终一定是最大收益
 * 而这题就不能这样了，因为选择哪天卖股票，第二天就不能操作，最终就会导致结果的不同
 * 
 * 很明显的动态规划问题，前一次交易的选择会影响到下一次交易
 * 这时候最重要的就是找到状态的表示方法，以及状态的转移方程
 * 
 * 明知如此，还是没有想出来，好不爽
 * 
 * Discuss:
 * 
 * 代码①，2ms，非常典型的DP算法的解题过程
 * 
 * 代码②，2ms，也是DP，比较独特的思路，但是思考的思路是状态机，比代码①还更清晰的思路
 * 
 */

public class BestTimeToBuyAndSellStockWithCooldown {
	/*
	 * 代码①
	 * 动态规划问题最关键的两个部分：状态  + 状态转移方程
			状态：
			buy[i] 表示直到下标i时，一系列的交易以buy为结尾的最大收益
			sell[i] 表示直到下标i时，一系列的交易以sell为结尾的最大收益
			
			初始状态：
			buy[0] = -prices[0]; // 买入股票prices[0]
			sell[0] = 0; // 卖掉prices[0]
			
			状态转移
			buy[i]有可能是在i-2的时候卖了股票，i-1冷却一天，然后在i的时候买入。（因为冷却，所以不可能在i-1的时候卖股票）
				     也有可能在当前什么都不做，仍然使用上一轮也就是buy[i-1]的值。取决于哪种情况产生更大的效益
			同样的，
			sell[i]有可能是在i-1时买进了股票，在i的时候卖出
			                   也有可能是按兵不动，仍然使用上一轮也就是sell[i-1]的值。取决于哪种情况产生更大的效益
			所以
			状态转移方程：
			buy[i] = max(buy[i-1], sell[i-2] - prices[i]);
			sell[i] = max(sell[i-1], buy[i-1] + prices[i]);
			
			最终，sell[n-1]就是最大的效益，因为buy[n-1]显然不可能是最大效益（在最后一天买进，没机会卖出）
			
			进一步的，由于状态转移时，i只依赖于i-2和i-1的状态，所以可以优化空间，使空间复杂度为O(1)：
			
			用 prevPrevSell 表示 sell[i-2]
			prevBuy 表示 buy[i-1], prevSell 表示 sell[i-1]
			buy 表示 buy[i], sell 表示 sell[i]
			
			具体见代码:
	 */
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int prevPrevSell = 0, prevBuy = -prices[0], prevSell = 0, buy = 0, sell = 0;
        
        for (int i = 1; i < prices.length; i++) {
            buy = Math.max(prevBuy, prevPrevSell - prices[i]);
            sell = Math.max(prevSell, prevBuy + prices[i]);
            prevBuy = buy;
            prevPrevSell = prevSell;
            prevSell = sell;
        }
        
        return sell;
    }
	
	
	/*
	 * 代码②
	 * 用状态机的思想：

		根据题意，有3个状态，分别设为s0, s1, s2

		s0 是初始状态，此时能做的只有买股票（跳到状态s1）和休息（等待更好的价格，维持在状态s0）
		s1，此时可以选择卖掉股票（跳到状态s2）和休息（等待更好的卖出价格，维持在状态s1）
		s2，此时只能选择休息一天，然后回到状态s1
		
		可以画成状态转移图
		
		所以最大收益可以写成：
		
		s0[i] = max(s0[i-1], s2[i-1]);
		s1[i] = max(s1[i-1], s0[i-1] - prices[i]);
		s2[i] = s1[i-1] + prices[i];
		
		初始化：
		
		s0[i] = 0; // 初始状态，什么都没有
		s1[i] = -prices[0];  // 花钱买进股票
		s2[i] = Integer.MIN_VALUE; // 初始化为最小，所以一开始s1会选择买进股票，而不是休息
		
		最终的最大收益即为 max(s0[n-1], s2[n-1]);
		因为s1显然不可能是最大收益（花钱）
		
		然后，因为每一次迭代的时候，只依赖上一轮的状态，所以可以进一步进行优化，使用滚动数组，或者变量代替
	 * 
	 */
    public int maxProfit_2(int[] prices) {
        if (prices.length < 2) return 0;
        int s0 = 0, s1 = -prices[0], s2 = Integer.MIN_VALUE; // 初始化
        int newS0 = 0, newS1 = 0; // 临时变量
        
        for (int i = 1; i < prices.length; i++) {
            newS0 = Math.max(s0, s2);
            newS1 = Math.max(s0 - prices[i], s1);
            s2 = s1 + prices[i];
            s0 = newS0;
            s1 = newS1;
        }
        return Math.max(s0, s2);
    }
}
