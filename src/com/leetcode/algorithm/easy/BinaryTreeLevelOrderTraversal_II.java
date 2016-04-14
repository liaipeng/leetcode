package com.leetcode.algorithm.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
*/

/*
 * 102题，Binary Tree Level Order Traversal的姐妹篇
 * 这题要求跟102题相反，自底向上存入list。
 * 试了一下，其实直接用102题的代码，在最后把list反转一下：Collections.reverse(list);就完成题目了- -。
 * 2ms，86.14%，效率还相当高。不过这跟作弊差不多吧 - -。
 * 
 * 但是实际上跟102题不一样的 地方，也就是插入子表的时候，每次都插在表头就行了，list.add(0, subList);
 * 具体见代码，都是基于102题的两个代码进行的修改
 * 
 * 代码①, 2ms, 86.14%, DFS
 * 代码②, 4ms, 20%, BFS
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


public class BinaryTreeLevelOrderTraversal_II {
	/*
	 * 代码①
	 * 与102题代码①不同的地方就2处，见代码注释
	 */
    public List<List<Integer>> order(List<List<Integer>> list, TreeNode root, int level) {
        if (null == root) return list;
        //插入子表的时候，每次都插在list表头
        if (level >= list.size()) list.add(0, new ArrayList<Integer>());
        //因为子表的插入顺序不一样了，所以取的时候也不一样，要从后往前数，因为list是从0开始到size-1的，所以要-1
        list.get(list.size() - level - 1).add(root.val);
        order(list, root.left, level + 1);
        order(list, root.right, level + 1);
        return list;
    }
    
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        return order(list, root, 0);
    }
	
	/*
	 * 代码②
	 * 因为BFS只要插入子表，不需要取出子表
	 * 所以，与102题的代码②不同的地方就1处，见代码注释
	 */	
    public List<List<Integer>> levelOrderBottom_2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (null == root) return list;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int size = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            List<Integer> subList = new ArrayList<Integer>();
            while (size-- > 0) {
                 TreeNode current = queue.poll();
                 subList.add(current.val);
                 if (null != current.left) queue.offer(current.left);
                 if (null != current.right) queue.offer(current.right);                     
            }
            // 插入子表的时候，每次都插在list表头
            list.add(0, subList);
        }
        return list;  
    }
}
