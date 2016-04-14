package com.leetcode.algorithm.medium;

/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid 
(marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note: m and n will be at most 100.
*/

/*
 * 给一个m * n的地图，左上角为起点，右下角为终点，每次只能往右或往左移动一格，求有多少种不同的走法。
 * 
 * Discuss：
 * 
 * 动态规划问题
 * 
 * 用二维数组map[i][j]代表到达该位置时有多少种不同的走法
 * 那么初始化首行和首列为1，因为不能往左或往上走，所以到达首行和首列的某一格的走法肯定只有1种
 * 
 * 状态转移方程：map[i][j] = map[i-1][j] + map[i][j-1];
 * 也就是说，除了首行首列外，到达其他格子的走法有从左边走来和从上边走来两种。
 * 
 * 最终map[i-1][j-1]即为到达终点时不同的走法，具体见代码①
 * 
 * 代码①，1ms
 * 
 * 代码①可以进行优化：
 * 1. 时间优化：剪枝：
 * 		当 m = 1 或者 n = 1时，只可能有一种走法，直接返回1
 * 2. 空间优化：
 * 		实际上计算map[i][j] = map[i-1][j] + map[i][j-1];时没必要使用二维数组，因为map[i-1][j]就是map[i][j]上一行对应的那一格
 * 		所以，按照从左到右，从上到下的计算方法
 * 		只要 for i: 0 -> m-1, for j: 0 -> j-1，map[j] += map[j-1]即可。（可以手动画一个格子演算一遍即可发现）
 * 
 * 具体见代码②
 * 
 * 代码②，1ms
 * 
 * 
 * 第二种方法，不使用动态规划，用数学知识解题，太牛逼了	
 * 
 * 首先，不论是哪一种走法，从起点到终点要走的步数肯定是  n + m - 2 （m-1步向下，n-1步向右）
 * 然后，每一种走法其实就是向下或向右走的序列，其中每一步不同，则是一种不同的走法
 * 那么，我们要做的就是，从(n + m - 2)的总步数中，选出(n - 1)步来向右走，那么剩下的(m - 1)向左走的步数也就确定了
 * 
 * 因此，总的步数其实也就是 从(n + m - 2)选出(n - 1)的组合
 * 假设 N = n + m - 2, K = n - 1。 那么步数就是 C(N, K) = K! / (N! * (N-K)!);
 * 约分后： C(N, K) = (N - K + 1) * (N - K + 2) * ... * N ) / K!
 * 
 * 具体见代码③
 * 
 * 代码③，0ms
 * 
 */
public class UniquePaths {
	/*
	 * 代码①
	 */
    public int uniquePaths(int m, int n) {
        int[][] map = new int[m][n];
        int i, j;
        // 初始化
        for (j = 0; j < n; j++) map[0][j] = 1;
        for (i = 0; i < m; i++) map[i][0] = 1;
        
        // 状态转移
        for (i = 1; i < m; i++) {
            for (j = 1; j < n; j++) {
                map[i][j] = map[i-1][j] + map[i][j-1];
            }
        }    
        return map[i-1][j-1];
    }
    
    /*
     * 代码②
     * 
     * 代码①优化版
     */
    public int uniquePaths_2(int m, int n) {
        if (m == 1 || n == 1) return 1;
        int[] map = new int[n];
        int i, j;
        for (j = 0; j < n; j++) map[j] = 1;
        
        for (i = 1; i < m; i++) {
            for (j = 1; j < n; j++) {
            	//每次计算前，map[j]保存的值是上一行那一格的数据，所以map[j] = map[j] + map[j-1]相当于map[i][j] = map[i-1][j] + map[i][j-1];
                map[j] += map[j-1];
            }
        }
        return map[j-1];
    }
    
    /*
     * 代码③
     * 数学知识：
     * 组合Cnk = n!/(k!(n-k)!)
	 * 排列Ank = n!/(n-k)!
     */
    public int uniquePaths_3(int m, int n) {
        int N = m + n - 2, K = n - 1;
        double res = 1; // 必须使用double，不能用float，会溢出
        for (int i = 1; i <= K; i++) {
            res = res * (N - K + i) / i;  // 不能写成 res *= (N - K + i) / i; 因为/的计算顺序不一样，会影响结果
        }
        return (int)Math.round(res);
    }
}
