package com.leetcode.algorithm.easy;

import java.util.LinkedList;
import java.util.Queue;

/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
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
/*
 * 给一颗二叉树，找到它的最短深度，同学们这是道送分题啊！
 * 代码①，1ms，用队列实现BFS。我觉得这题BFS应该更快啊，不知道为什么是一样快的
 * 
 * 代码②，1ms，递归实现DFS
 * 
 * Discuss:
 * 代码③，1ms，递归实现DFS，代码②画蛇添足
 */
public class MinimumDepthOfBinaryTree {
	/*
	 * 代码①
	 */
    public int minDepth(TreeNode root) {
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if (null != root) queue.offer(root);
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            // 遍历该层所有节点
            while (size-- != 0) {
                TreeNode current = queue.poll();
                // 如果出现叶子节点，直接返回当前深度
                if (null == current.left && null == current.right) return depth;
                if (null != current.left) queue.offer(current.left);
                if (null != current.right) queue.offer(current.right);
            }
        }
        return depth;
    }
	
	/*
	 * 代码②
	 */
	int minDepth = Integer.MAX_VALUE;    
    public int minDepthHelper(TreeNode root, int depth) {
        if (null == root.left && null == root.right && minDepth > depth) minDepth = depth;
        if (null != root.left) minDepthHelper(root.left, depth + 1);
        if (null != root.right) minDepthHelper(root.right, depth + 1);
        return minDepth;
    }
    public int minDepth_2(TreeNode root) {
        if (null == root) return 0;
        return minDepthHelper(root, 1);
    }
	
	/*
	 * 代码③
	 */
    public int minDepth_3(TreeNode root) {
        if (null == root) return 0;
        // 如果左子树为空，返回右子树的最小高度  + 1
        if (null == root.left) return minDepth_3(root.right) + 1;
        if (null == root.right) return minDepth_3(root.left) + 1;
        // 如果都不为空，返回左子树和右子树中最小高度更小的那个 +１
        return Math.min(minDepth_3(root.left), minDepth_3(root.right)) + 1;
    }
	
}
