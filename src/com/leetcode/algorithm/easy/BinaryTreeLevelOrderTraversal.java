package com.leetcode.algorithm.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
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
 *  代码①，1ms，93%！，递归，虽然代码难看了点，但是毕竟是自己没看答案做出来了，泪流满面
 *  题目的tag是BFS，我这是用DFS做的，哈
 *  
 *  Discuss：
 *  代码②，BFS，用队列实现
 */
public class BinaryTreeLevelOrderTraversal {
	/*
	 * 代码①
	 */
    public List<List<Integer>> order(List<List<Integer>> list, TreeNode root, int level) {
        if (null == root) return list;
        //如果level大于等于list的size，说明还没遍历到过这一level，新建一个子表
        if (level >= list.size()) list.add(new ArrayList<Integer>());
        //获取该level的子表，添加当前结点
        list.get(level).add(root.val);
        //深度优先遍历
        order(list, root.left, level + 1);
        order(list, root.right, level + 1);
        return list;
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        return order(list, root, 0);
    }
	
	/*
	 * 代码②
	 * 广度优先遍历
	 * 一层一层往下遍历，每层建立一个subList
	 */
    public List<List<Integer>> levelOrder_2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (null == root) return list;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int size = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            List<Integer> subList = new ArrayList<Integer>();
            //把当前这一层的所有结点存入一个子表，把这一层的所有子节点放进队列，为下一层循环做准备
            while (size-- > 0) {
                 TreeNode current = queue.poll();
                 subList.add(current.val);
                 if (null != current.left) queue.offer(current.left);
                 if (null != current.right) queue.offer(current.right);                     
            }
            list.add(subList);
        }
        return list;        
    }
}
