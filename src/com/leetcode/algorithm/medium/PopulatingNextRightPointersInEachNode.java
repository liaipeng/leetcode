package com.leetcode.algorithm.medium;


/*
Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
     1
   /  \
  2    3
 / \  / \
4  5  6  7
After calling your function, the tree should look like:
     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL
*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

/*
 * 给定一颗完美二叉树（满二叉树），让所有next结点指向其右边的结点，如果没有，则指向NULL
 * 要求只能用常数空间
 * 
 * Discuss：
 * 代码①，1ms，迭代
 * 
 * 代码②，1ms，递归，貌似递归使用到的堆栈空间也是应该计算进去的，所以使用递归应该是超过了常数空间
 * 
 * 代码③，1ms，也是递归，超过常数空间
 */
public class PopulatingNextRightPointersInEachNode {
	/*
	 * 代码①
	 */
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode preNode = root;
        TreeLinkNode curNode = null;
        
        while (preNode.left != null) { // 因为是满二叉树，所以只要判断是否有左子树即可知道是否还有下一层
            curNode = preNode;
            while (curNode != null) { // 从左到右遍历这一层，把子树连起来
                curNode.left.next = curNode.right;
                if (curNode.next != null) curNode.right.next = curNode.next.left;
                curNode = curNode.next;
            }
            preNode = preNode.left; // 到下一层
        }
    }
    
    
    /*
     * 代码②
     * 会有重复连接
     */
    private void connectHelper(TreeLinkNode left, TreeLinkNode right) {
        left.next = right; // 连接左右结点
        if (left.left != null) { // 如果还有下一层
            connectHelper(left.left, left.right); // 连接左子树的左右结点
            connectHelper(left.right, right.left);// 连接左子树的右节点和右子树的左结点
            connectHelper(right.left, right.right);// 连接右子树的左右结点
        }
    }
    public void connect_2(TreeLinkNode root) {
        if (root == null || root.left == null) return;
        connectHelper(root.left, root.right);
    }
    
    /*
     * 代码③
     * 相比代码②，更简洁，而且不会有重复连接
     */
    public void connect_3(TreeLinkNode root) {
        if (root == null || root.left == null) return;
        root.left.next = root.right;
        if (root.next != null) root.right.next = root.next.left;
        connect_3(root.left);
        connect_3(root.right);
    }
}
