package com.leetcode.algorithm.easy;

/*
 Given a binary tree, find its maximum depth.

 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
/*
 * 看到树就先怕了。。没想出来 
 * 看了别人的答案恍然大悟，用递归
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
public class MaximumDepthOfBinaryTree {
	public int maxDepth(TreeNode root) {
      //若根为空，深度为0
		if (root == null)
			return 0;
		//否则，返回左子树和右子树中深度高的那枝，加上根的高度，所以+1
		return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}
}
