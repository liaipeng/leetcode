package com.leetcode.algorithm.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;


/*
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?
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
 * 二叉树的中序遍历
 * 要求用递归和迭代两种方式实现
 * 做Medium题目以来最简单的一题了
 * 
 * 代码①，1ms，递归
 * 
 * 代码②，3ms，迭代
 * 
 * Discuss:
 * 代码③，2ms，迭代，无论是效率还是可读性都比代码②强多了
 * 
 */

public class BinaryTreeInorderTraversal {
	/*
	 * 代码①
	 */
    private List<Integer> path = new LinkedList<Integer>();
    
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return path;
        
        if (root.left != null) inorderTraversal(root.left);
        path.add(root.val);
        if (root.right != null) inorderTraversal(root.right);
        
        return path;
    }

	/*
	 * 代码②
	 */
    public List<Integer> inorderTraversal_2(TreeNode root) {
        List<Integer> path = new LinkedList<Integer>();
        if (root == null) return path;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Set<TreeNode> isTraversed = new HashSet<TreeNode>(); // 存储曾经判断过的TreeNode
        stack.push(root);
        TreeNode curNode;
        while (!stack.isEmpty()) {
            curNode = stack.pop();
            if (!isTraversed.add(curNode)) { // 如果该TreeNode曾经判断过，直接加入list
                path.add(curNode.val);
                continue;
            }
            // 先让右子树入栈，然后自身入栈，最后左子树入栈
            if (curNode.right != null) stack.push(curNode.right);
            if (curNode.left == null) { // 如果没有左子树，加入list
                path.add(curNode.val);
            } else {
                stack.push(curNode); 
                stack.push(curNode.left);
            }
        }
        return path;
    }
    
    /*
     * 代码③
     * 我的理解是模拟手动中序遍历的过程
     * 我们手动中序遍历的时候
     * 		1. 首先是先一直找根节点的左子树的左子树，直到左子树为空，将此节点加入遍历路径中
     * 		2. 若该节点有右子树，把右子树当作根节点执行第1步
     * 		3. 若没有右子树，返回该节点的父节点，将此父节点加入遍历路径中，然后根据其有无右子树，进行第2或第3步。
     * 
     * 此代码基本上就是模拟这种过程
     */
    public List<Integer> inorderTraversal_3(TreeNode root) {
        List<Integer> path = new LinkedList<Integer>();
        if (root == null) return path;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curNode = root;
        // 注意这里的循环条件，只有curNode为空以及栈为空同时成立时才算遍历结束
        while (curNode != null || !stack.isEmpty()) {
        	// 手动模拟第1步中的迭代寻找左子树的过程，同时把迭代过程中的结点都入栈
        	// 或者是 第 2、3 步的分支点（如果非空就是第2步；如果是空，就是第3步，不进入while循环，直接pop，也就是返回该结点的父节点） 
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            curNode = stack.pop();
            path.add(curNode.val); // 手动模拟中的第1步中的“直到左子树为空，将此节点加入遍历路径中”
            curNode = curNode.right; 
        }
        return path;
    }
    
}
