package com.leetcode.algorithm.easy;

/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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
 * 题目很显然是要用DFS对树进行遍历，判断是否存在一条路径的总和 == sum
 * 
 * 代码①，1ms，第一梯队。用递归，不得不说，用递归不仅易于理解，代码也简洁很多
 * 
 * 代码②，7ms, 不知道第几梯队去了。用栈迭代实现DFS
 * 
 * Discuss：
 * 代码③，1ms， 代码①的优化版
 * 
 */

public class PathSum {
	
	/*
	 *  代码①
	 *  思路就是用递归的方式DFS，当遍历到叶子的时候判断经过的数总和是否为sum，若是返回true，否则回到上一个结点继续遍历
	 */
	/*	
    public boolean hasPathSum(TreeNode root, int sum) {
        if (null == root) return false;
        // 遍历到叶子结点，判断层层相减剩下的sum是否与当前结点的val相等，若是返回true
        if (null == root.left && null == root.right) return sum == root.val ? true : false;
        // 遍历到左子树，同时sum减去当前结点的val，若从左子树中找到了路径，返回true
        // 注意: 这里不用判断左右子树是否为空，因为方法开头的部分判断了 root是否为null
        if (hasPathSum(root.left, sum - root.val)) return true;
        // 否则，判断右子树是否为空，遍历到右子树
        if (hasPathSum(root.right, sum - root.val)) return true;
        // 若遍历完整棵树都找不到，返回false
        return false;
    }
	*/
	
	/*
	 * 代码②
	 * 跟代码①的思想是一样的，只不过是用迭代不是用递归
	 */
	/*	
    public boolean hasPathSum(TreeNode root, int sum) {
        if (null == root) return false;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        // 记录遍历过程每一层相减剩下的数
        Stack<Integer> remainders = new Stack<Integer>();
        stack.push(root);
        remainders.push(sum);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            int remainder = remainders.pop() - current.val;
            if (null == current.left && null == current.right) {
                if (0 == remainder) return true;
            } 
            if (null != current.right) {
                stack.push(current.right);   
                remainders.push(remainder);
            }
            if (null != current.left) {
                stack.push(current.left);        
                remainders.push(remainder);
            }
        }
        return false;
    }
	*/
	
	
	/*
	 * 代码③
	 * 代码①的优化版
	 */
	/*   
    public boolean hasPathSum(TreeNode root, int sum) {
        if (null == root) return false;
        // 如果遍历到叶节点，判断是否是可行的路径，如果是返回true
        if (null == root.left && null == root.right) return sum == root.val;
        // 遍历左子树，如果左子树没找到路径，遍历右子树
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
	*/
}
