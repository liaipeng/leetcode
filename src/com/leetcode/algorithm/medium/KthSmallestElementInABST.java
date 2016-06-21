package com.leetcode.algorithm.medium;

import java.util.Stack;

/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).
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
 * 给定一个二叉搜索树，找到其中第k小的元素
 * 
 * 最笨的方法，用中序遍历k次（中序遍历的顺序就是BST从小到大的顺序），见代码①
 * 
 * 代码①，2ms
 * 
 * Disucss：
 * 代码②，1ms，递归中序遍历
 * 
 * 代码③，1ms，二分搜索
 * 
 * 除了以上3种方法外，还可以通过修改BST节点的结构来达到最优的时间复杂度O(height of BST)：
 * 		也就是新建一个带节点数量的BST结构，这样一来，在执行二分搜索时，就不需要去计算左子树的节点数量，可以直接进行二分搜索，
 * 		只需要时间复杂度O(树高)
 * 
 * 具体见代码④
 * 
 * 代码④，2ms
 *
 */

public class KthSmallestElementInABST {
	/*
	 * 代码①
	 */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()){
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            curNode = stack.pop();
            if (k == 1) return curNode.val;
            k--;
            curNode = curNode.right;
        }
        return -1; // 循环内肯定能找到，这边无所谓了
   }
    
    /*
     * 代码②
     */
    private int ans;
    private int count;
    private void inorderTraversal(TreeNode root) {
        if (root.left != null) inorderTraversal(root.left);
        count--;
        if (count == 0) {
            ans = root.val;
            return;
        }
        if (root.right != null) inorderTraversal(root.right);
    }
    public int kthSmallest_2(TreeNode root, int k) {
        count = k;
        inorderTraversal(root);
        return ans;
    }
    
    
    /*
     * 代码③
     * 
     */
    // 返回以root为根的树的节点数量
    private int countNode (TreeNode root) {
        if (root == null) return 0;
        return 1 + countNode(root.left) + countNode(root.right);
    }
    public int kthSmallest_3(TreeNode root, int k) {
        int leftNode = countNode(root.left); // 计算左子树节点数
        // 如果k小于等于左子树节点数，那么第k小的数在左子树中，以root.left为根递归找左子树中第k大的数
        if (k <= leftNode) {
            return kthSmallest_3(root.left, k);
            // 如果k大于左子树个数+1，那么第k小的数在右子树中，以root.right为根递归找右子树中第 k - 1 - leftNode大的数
        } else if (k > leftNode + 1) {
            return kthSmallest_3(root.right, k - 1 - leftNode);
        }
        // 否则，当前结点即为第k大的节点
        return root.val;
    }
    
    /*
     * 代码④
     */
    // 带节点数量的二叉搜索树
    class TreeNodeWithCount {
        int val;
        int count; // 结点数量
        TreeNodeWithCount left;
        TreeNodeWithCount right;
        TreeNodeWithCount(int x) { val = x; count = 1;}
    }
    // 将原BST转换为新的BST树
    private TreeNodeWithCount buildBSTWithCount(TreeNode root) {
        if (root == null) return null;
        TreeNodeWithCount rootWithCount = new TreeNodeWithCount(root.val);
        rootWithCount.left = buildBSTWithCount(root.left);
        rootWithCount.right = buildBSTWithCount(root.right);
        if (rootWithCount.left != null) rootWithCount.count += rootWithCount.left.count;
        if (rootWithCount.right != null) rootWithCount.count += rootWithCount.right.count;
        return rootWithCount;
    }
    // 找第k小节点
    private int kthSmallest(TreeNodeWithCount rootWithCount, int k) {
        if (rootWithCount.left != null) {
            if (k <= rootWithCount.left.count) return kthSmallest(rootWithCount.left, k);
            else if (k > rootWithCount.left.count + 1) return kthSmallest(rootWithCount.right, k - 1 - rootWithCount.left.count);
            return rootWithCount.val;
        } else {
            if (k == 1) return rootWithCount.val; 
            return kthSmallest(rootWithCount.right, k - 1); // 因为题目说了一定是有解的，所以这边肯定有右子树。
        }
    }
    public int kthSmallest_4(TreeNode root, int k) {
        TreeNodeWithCount rootWithCount = buildBSTWithCount(root);
        return kthSmallest(rootWithCount, k);
    }
}
