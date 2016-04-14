package com.leetcode.algorithm.easy;

import java.util.Stack;


/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
*/

/*
 * 代码①，递归, 1ms
 * 
 * 代码②，迭代, 5ms
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


public class SymmetricTree {
	/*
	 * 代码①
	 */
    public boolean isSym(TreeNode left, TreeNode right) {
        if ((null == left) && (null == right)) return true;
        if ((null == left) || (null == right)) return false;
        return (left.val == right.val) && isSym(left.left, right.right) && isSym(left.right, right.left);
    }
    public boolean isSymmetric(TreeNode root) {
        return (null == root) || isSym(root.left, root.right);
    }
	
	
	/*
	 * 代码②
	 */
    public boolean isSymmetric_2(TreeNode root) {
        if (null == root) return true; 
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode left = root.left;
        TreeNode right = root.right;
        stack.push(left);
        stack.push(right);
        
        while (!stack.empty()) {
            //如果栈的size不是偶数，肯定不对称
            if (stack.size() % 2 != 0) return false;
            right = stack.pop();
            left = stack.pop();
            // 如果left和right都是null，跳过；如果只有一个为null，不对称
            if (null == left && null == right) continue;
            if (null == left || null == right) return false;
            if (left.val != right.val) return false;
            
            //★★注意：stack是可以push null进去的，也是占size的，而且可以pop()出来，如果是空栈，pop()是会报错的
            //所以这边可以直接push，不用判断left和right的子树是否为空
            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        return true;
    }
}
