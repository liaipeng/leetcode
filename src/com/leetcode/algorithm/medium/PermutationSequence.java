package com.leetcode.algorithm.medium;

import java.util.LinkedList;

/*
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
*/

/*
 * 给定数字n，和k
 * 返回n位数字[1,2,3,…,n]按照字典序全排列的第k个序列
 * 
 * 如果通过调用k次Next Permutation的函数来求的话会超时
 * 
 * Discuss：
 * 
 * 假设有n个元素，第K个permutation是
		a1, a2, a3, .....   ..., an
		那么a1是哪一个数字呢？
		
		那么这里，我们把a1去掉，那么剩下的permutation为
		a2, a3, .... .... an, 共计n-1个元素。 n-1个元素共有(n-1)!组排列，那么这里就可以知道
		
		设变量K1 = K
		a1 = K1 / (n-1)!// 第一位的选择下标
		
		然后，剩下的数字为a2, a3, .....   ..., an
		同理，去掉a2后，剩下的n-2个元素共有(n-2)!组排列
		
		此时的K2 = K1 % (n-1)!
		因为在第一轮的选择中我们已经淘汰掉了(n-1)!*a1种排序，所以要从K1中去掉(n-1)!*a1，
		也就是剩余从原来的选出第k个排序变成选出第K1 % (n-1)!个排序
		然后第二位的选择下标就为：
		a2 = K2 / (n-2)!
		
		以此类推
		。。。。。
		
		K(n-1) = K(n-2) /2!
		a(n-1) = K(n-1) / 1!
		
		an = K(n-1)
		
		具体见代码①
 * 
 * 代码①，3ms
 */

public class PermutationSequence {
	/*
	 * 代码①
	 */
    public String getPermutation(int n, int k) {
        LinkedList<Integer> nums = new LinkedList<>();
        for (int i = 1; i <=n; i++) nums.add(i); // [1,2,3,...,n]
        int[] factorial = new int[n]; // 存储1! ~ (n-1)!
        factorial[0] = 1;
        for (int i = 1; i < n; i++) factorial[i] = i * factorial[i-1];
        k--; // 因为[1,2,3,...,n]的下标是从0开始的，所以让k--省的麻烦
        StringBuilder res = new StringBuilder("");
        for (int i = n; i > 0; i--) {
            res.append(nums.remove(k/factorial[i-1])); // 选中该下标的数的同时从list中remove掉该数
            k %= factorial[i-1]; // 求剩余k
        }
        return res.toString();
    }
}
