package com.leetcode.algorithm.easy;

/*
Invert a binary tree.

4
/   \
2     7
/ \   / \
1   3 6   9

to

4
/   \
7     2
/ \   / \
9   6 3   1

Trivia:
This problem was inspired by this original tweet by Max Howell:
Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so fuck off.
*/

/*
 * 递归，见方法①
 * 耗时1ms，1.35%
 * 
 * Discuss的代码简洁好多，而且效率更高，见方法②
 * 耗时0ms，26.73%
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

public class InvertBinaryTree {
	/*	
	 * 方法①
	 */
	 public TreeNode invertTree(TreeNode root) {
	        TreeNode tempNode = null;
	        if (root == null) return root;
	        else if (root.left == null && root.right == null) return root;
	        else if (root.left == null) {
	            root.left = root.right;
	            root.right = null;
	        }
	        else if (root.right == null) {
	            root.right = root.left;
	            root.left = null;
	        }
	        else {
	            tempNode = root.left;
	            root.left = root.right;
	            root.right = tempNode;
	        }
	        invertTree(root.left);
	        invertTree(root.right);
	        return root;
	    }
	
	/*	
	 * 方法②
	 */
    public TreeNode invertTree_2(TreeNode root) {
        if (root == null)
            return root;

        TreeNode tmp = root.left;
        root.left = invertTree_2(root.right);
        root.right = invertTree_2(tmp);

        return root;
    }
}
