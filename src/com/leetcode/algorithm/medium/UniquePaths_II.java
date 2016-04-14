package com.leetcode.algorithm.medium;

/*
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
*/

/*
 * Unique Paths升级版
 * 比Unique Paths多了路障这一设定：给定一个二维数组作为地图，0表示可以通过，1表示路障
 * 
 * 我的做法是先遍历一遍地图，把地图中的路障改为-1. 
 * 然后用动态规划，与Unique Paths代码①的做法一样，状态转移方程obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
 * 当obstacleGrid[i][j]为-1时，不管这一格。
 * 当obstacleGrid[i-1][j]或obstacleGrid[i][j-1]为-1时，说明该方向不可能到这一格，所以改方向过来的步数是0.
 * 其余和Unique Paths一样
 * 因为要花多余的时间去修改地图的路障，所以效率肯定不是最好的，而且还改动了原地图。具体见代码①
 * 
 * 代码①，2ms,O(1)空间，但是会修改原数组
 * 
 * Discuss：
 * 代码①特地花时间去把路障由1改为-1其实是没必要的，只要对逻辑稍加修改即可
 * 代码②，2ms，代码①优化，O(1)空间，但是会修改原数组
 * 
 * 代码③，1ms，，最佳解法，需要O(obstacleGrid[0].length)的空间，不会改变原数组。
 * 				与Unique Paths中代码②的做法差不多，引入对路障的考虑
 */

public class UniquePaths_II {
	/*
	 * 代码①
	 * 把地图数组obstacleGrid转变为“每一格的值表示到达该格有多少种方式”
	 */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length, cols = obstacleGrid[0].length;
        // 更改路障为-1
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) obstacleGrid[i][j] = -1;
            }
        }
        // 初始化
        for (int i = 0; i < rows; i++) {
            if (obstacleGrid[i][0] == -1) break;
            obstacleGrid[i][0] = 1;
        }
        for (int j = 0; j < cols; j++) {
            if (obstacleGrid[0][j] == -1) break;
            obstacleGrid[0][j] = 1;
        }
        // 状态转移
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (obstacleGrid[i][j] == -1) continue;
                int left = obstacleGrid[i-1][j] == -1 ? 0 : obstacleGrid[i-1][j];
                int up = obstacleGrid[i][j-1] == -1 ? 0 : obstacleGrid[i][j-1];
                obstacleGrid[i][j] = left + up;
            }
        }
        // 如果终点那一个格又路障，那么不可能到达终点，返回0
        return obstacleGrid[rows-1][cols-1] == -1 ? 0 : obstacleGrid[rows-1][cols-1];
    }
    
    /*
     * 代码②
     * 代码①优化版
     * 
     * 把地图数组obstacleGrid转变为“每一格的值表示到达该格有多少种方式”
     */
    public int uniquePathsWithObstacles_2(int[][] obstacleGrid) {
        int rows = obstacleGrid.length, cols = obstacleGrid[0].length;
        
        obstacleGrid[0][0] ^= 1; // 第一格 由 1 -> 0， 由0 -> 1
        
        // 初始化首行首列。如果该格为路障，到达该格的方式有0种，且后面所有格子也都为0
        for (int i = 1; i < rows; i++) {
            obstacleGrid[i][0] = obstacleGrid[i][0] == 1 ? 0 : obstacleGrid[i-1][0];
        }
        for (int j = 1; j < cols; j++) {
            obstacleGrid[0][j] = obstacleGrid[0][j] == 1 ? 0 : obstacleGrid[0][j-1];
        }
        // 状态转移。如果该格为路障，方式为0。否则为左边和上边的方式之和
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                obstacleGrid[i][j] = obstacleGrid[i][j] == 1 ? 0 : obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
            }
        }
        
        return obstacleGrid[rows-1][cols-1];
    }
    
    /*
     * 代码③
     * 
     * 解法与Unique Paths中代码②相似
     * 状态转移方程为：dp[i] = dp[i] + dp[i - 1];
     */
    public int uniquePathsWithObstacles_3(int[][] obstacleGrid) {
        int rows = obstacleGrid.length, cols = obstacleGrid[0].length;
        int[] dp = new int[cols]; // 记录到每一格的走法总数
        dp[0] = 1; // 初始化第一格为1
        // 状态转移
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) dp[j] = 0; // 如果这一格是路障，走法为0
                // 这边必须判断j大于0，否则会数组越界
                // 而且也没必要对j=0的情况做处理。因为如果j=0是路障，那么在上一个if里处理了。如果不是路障，那么dp[0]始终为1
                else if (j > 0){
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return dp[cols - 1];
    }
}
