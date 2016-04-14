package com.leetcode.algorithm.medium;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
*/

/*
 * 二叉树前序遍历
 * 用迭代实现
 * 
 * 代码①，1ms，练练手，递归
 * 
 * 代码②，2ms，迭代，比中序遍历简单多了
 * 
 */

public class BinaryTreePreorderTraversal {
	/*
	 * 代码①
	 */
    private	List<Integer> path = new LinkedList<Integer>();
    
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return path;
        
        path.add(root.val);
        if (root.left != null) preorderTraversal(root.left);
        if (root.right != null) preorderTraversal(root.right);
        
        return path;
    }
    
    /*
     * 代码②
     */
    public List<Integer> preorderTraversal_2(TreeNode root) {
        List<Integer> path = new LinkedList<Integer>();
        if (root == null) return path;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode curNode;
        
        while (!stack.isEmpty()) {
            curNode = stack.pop();
            path.add(curNode.val);
            if (curNode.right != null) stack.push(curNode.right);
            if (curNode.left != null) stack.push(curNode.left);
        }
        
        return path;
    }
}
