package com.leetcode.algorithm.easy;

/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/

/*
 * 动态规划  http://www.cnblogs.com/steven_oyj/archive/2010/05/22/1741374.html，描述的不是很清楚，具体参考算法课程课件
 * 
 * 复习完DP算法，结果把问题想得过于复杂了，一直纠结于ways[i,j]，从第i层到第j层有几种走法，陷入死胡同了
 * 
 * 最后还是去看了Discuss的代码，其实思路很简单，对于第n阶楼梯，可以从第[n-2]阶楼梯跨两步到达，也可以从第[n-1]阶楼底跨一步到达两种情况。
 * 
 * 用递归应该也是可以解的，但是效率太低，直接超时了，如代码①
 * 
 * 代码②，使用动态规划解题
 * 
 * 代码③，对代码②的空间消耗进行优化
 * 
 * 把结果排列出来，可以发现其实爬楼梯就是裴波那契数列，所以碰到类似的题目如果没有头绪的话，可以尝试列出前几个结果找一下规律
	斐波那契数列：0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ...
	注：此时a1=0，a2=1，an=a(n-1)+a(n-2)（n>=2,n∈N*）
	即：裴波那契数列的第n项的值是第n阶楼梯的爬法的种类数
 */

public class ClimbingStairs {
	/*
	 * 代码①
	 * 若为1级台阶，只有一种走法；若2级台阶，有两种走法，可以是1+1和2+0.
	 * 所以，要求n级台阶的走法，应该求n-2级台阶走法 加上 n-1级台阶走法，因为这两种走法肯定是不一样的
	 * 递归下去
	 * 然后TLE - -
	 */
    public static int climbStairs_1(int n) {
    	if (n < 1) return 0;
        if (1 == n) return 1;
        if (2 == n) return 2;
        return climbStairs_1(n-2) + climbStairs_1(n-1);
    }
    
	/*
	 * 代码②
	 * 动态规划
	 * 使用ways数组记录每一级阶梯的走法，也就是动态规划中的子问题，如ways[0]记录阶梯数n=1时的走法
	 * 
	 */
    public int climbStairs_2(int n) {
        if (n < 0) return 0;
        if (1 == n) return 1;
        if (2 == n) return 2;
        int[] ways = new int[n];
        //这步是动态规划中的初始化
        ways[0] = 1;
        ways[1] = 2;
        for (int i = 2; i < n; ++i) {
            ways[i] = ways[i - 1] + ways[i - 2];
        }
        return ways[n - 1];
    }
    
	/*
	 * 代码③
	 * 实际上，代码②中，每一级阶梯的走法每次只是参考上一级阶梯和上两级阶梯的走法，之前的走法可以删除，没必要记录每次的走法
	 * 从而可以节省空间。
	 * 由于这题是一维的，只要2个int变量记录就行
	 * 除此之外，可以使用滚动数组来优化空间消耗 ，见注释部分
	 */
    public int climbStairs_3(int n) {
        if (n < 0) return 0;
        if (1 == n) return 1;
        if (2 == n) return 2;
        //使用变量代替数组
        int twoStepBefore = 1;
        int oneStepBefore = 2;
        int ways = 0;
        for (int i = 2; i < n; ++i) {
        	ways = twoStepBefore + oneStepBefore;
        	twoStepBefore = oneStepBefore;
        	oneStepBefore = ways;
        }
        /*        
        int[] ways = new int[3];
        ways[0] = 1;
        ways[1] = 2;
        for (int i = 2; i < n; ++i) {
            ways[i % 3] = ways[(i - 2) % 3] + ways[(i - 1) % 3];
        }
        return ways[(n - 1) % 3];
        */
        return ways;
    }
    
    public static void main(String[] args) {
		
	}
}
