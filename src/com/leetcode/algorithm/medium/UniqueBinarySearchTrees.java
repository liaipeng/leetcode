package com.leetcode.algorithm.medium;

/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

/*
 * 给定一个整数n，求可以构造出几种结构不同的二叉搜索树
 * 
 * Discuss：
 * 
 * 动态规划问题
 * 设
 * G(n)：长度为n时BST的数量
 * F(i, n), 1<= i<= n：以i为根，序列1到n BST的数量
 * 显然
 * G(n) = F(1, n) + F(2, n) + F(3, n) + ... + F(n, n); ①
 * 且，G(0) = 1, G(1) = 1;
 * 而F(i, n)的值会等于左子树和右子树的笛卡尔积。
 * 例如，F(3, 7)，也就是构建以3为根，序列1到7的BST时，我们需要从[1, 2]和[4, 5, 6, 7]构造BST子树，然后组合在一起，也就是笛卡尔积。
 * 于是，F(i, n) = G(i-1) * G(n-i)   1 <= i <= n; ②
 * 将①和②合并，得到状态转移方程：G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0);
 * 最后，从小到大计算G(n)即可（因为大的依赖前面小的）
 * 过程可看最下方的注释
 * 
 * 代码①，0ms
 * 
 */

public class UniqueBinarySearchTrees {
	/*
	 * 代码①
	 * G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0);
	 */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                G[i] += G[j] * G[i - j - 1];
            }
        }
        return G[n];
    }
}

/*    
Hope it will help you to understand :

    n = 0;     null   

    count[0] = 1


    n = 1;      1       

    count[1] = 1 


    n = 2;    1__                    __2     
                  \                 /                 
                 count[1]       count[1]    

    count[2] = 1 + 1 = 2



    n = 3;    1__                     __2__                    __3
                  \                 /       \                 /     
              count[2]        count[1]    count[1]      count[2]

    count[3] = 2 + 1 + 2  = 5



    n = 4;    1__                   __2__                      ___3___                  
                  \              /        \                   /       \         
              count[3]       count[1]    count[2]         count[2]   count[1]

                 __4                
               /
           count[3]   

    count[4] = 5 + 2 + 2 + 5 = 14     


And  so on...
*/
