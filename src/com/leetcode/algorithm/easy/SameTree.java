package com.leetcode.algorithm.easy;

/*
Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
*/

/*
 * 做了MaximumDepthOfBinaryTree.java之后，看到这题首先想起用递归，代码如方法①
 * 耗时 1ms, 2.6%
 * 
 * 看了下Discuss，发现自己画蛇添足了，别人写的很精练，如方法②
 * 而且只要耗时0ms
 * 
 * 
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class SameTree {
	/*	
	 * 方法①
	 */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p == null && q != null) || (p != null && q == null)) return false;
        else if (p == null && q == null) return true;
        else if (p.val != q.val) return false;
        else if (isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) return true;
        else return false;
    }
	
	/* 
	 * 方法②
	 */
	public boolean isSameTree_2(TreeNode p, TreeNode q) {
	    if(p==null && q==null) return true;
	    if(p==null || q==null) return false;
	    return (p.val==q.val) && isSameTree_2(p.left,q.left) && isSameTree_2(p.right,q.right);
	}
}
