package com.leetcode.algorithm.easy;

/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as 
a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
*/

/*
 * Discuss。。
 * 代码①，2ms
 * 思路：自顶向下，先计算根节点的左右子树的树高，若相差大于1则不平衡，否则递归判断左子树是否平衡、右子树是否平衡。
 * O(N*logN)，因为树高logN，每层调用depth O（N）
 * 
 * 代码②，
 * 思路：DFS，计算树高的同时，判断树是否平衡，只要O(N)
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

public class BalancedBinaryTree {
	/*
	 * 代码①
	 */
    public int depth(TreeNode root) {
        if (null == root) return 0;
        //计算树高
        return Math.max(depth(root.left), depth(root.right)) + 1;        
    }
    public boolean isBalanced(TreeNode root) {
        if (null == root) return true;
        //若左右子树树高相差1，则该节点是平衡的，但是不保证所有子树的左右子树是平衡的，所以还要递归判断左右子树是否平衡。
        return (Math.abs(depth(root.left) - depth(root.right)) <= 1) && isBalanced(root.left) && isBalanced(root.right);
    }
	
    
	/*
	 * 代码②
	 * 
	 */
    public int DFSDepth(TreeNode root) {
        if (null == root) return 0;
        //计算左子树的树高
        int left = DFSDepth(root.left);
        //如果左子树树高为-1，说明左子树是不平衡的，直接返回-1
        if (-1 == left) return -1;
        //同右子树
        int right = DFSDepth(root.right);
        if (-1 == right) return -1;
        //如果左右子树树高相差大于1，返回-1，表示不平衡
        if (Math.abs(left - right) > 1) return -1;
        //否则，返回当前根的树高
        return Math.max(left, right) + 1;
    }     
    public boolean isBalanced_2(TreeNode root) {
        //如果root为null，那么DFSDepth结果为0，显然不等于-1，返回true
        return DFSDepth(root) != -1;
    } 
}
