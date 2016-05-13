package com.leetcode.algorithm.easy;

/*
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: 
“The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants 
(where we allow a node to be a descendant of itself).”

        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. 
Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
*/

/*
 * 基础知识不够，对二叉搜索树的概念不清楚：
 * 		重要性质: 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值
 * 		搜索,插入,删除的复杂度等于树高，O(log(n)).
 * 
 * 		在二叉排序树b中查找x的过程为：
			若b是空树，则搜索失败，否则：
			若x等于b的根结点的数据域之值，则查找成功；否则：
			若x小于b的根结点的数据域之值，则搜索左子树；否则：
			查找右子树。	
 * 		向一个二叉排序树b中插入一个结点s的算法，过程为：
			若b是空树，则将s所指结点作为根结点插入，否则：
			若s->data等于b的根结点的数据域之值，则返回，否则：
			若s->data小于b的根结点的数据域之值，则把s所指结点插入到左子树中，否则：
			把s所指结点插入到右子树中。
		在二叉排序树删去一个结点，分三种情况讨论：
			若*p结点为叶子结点，即PL(左子树)和PR(右子树)均为空树。由于删去叶子结点不破坏整棵树的结构，则只需修改其双亲结点的指针即可。
			若*p结点只有左子树PL或右子树PR，此时只要令PL或PR直接成为其双亲结点*f的左子树或右子树即可，作此修改也不破坏二叉排序树的特性。
			若*p结点的左子树和右子树均不空。在删去*p之后，为保持其它元素之间的相对位置不变，可按中序遍历保持有序进行调整，可以有两种做法：
				其一是令*p的左子树为*f的左子树，*s为*f左子树的最右下的结点，而*p的右子树为*s的右子树；
				其二是令*p的直接前驱（或直接后继）替代*p，然后再从二叉排序树中删去它的直接前驱（或直接后继）。
 * 
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
 *  方法① 10ms, 50.19%
 *  
 *  Discuss里有人用不需要比较的方法，哈，如方法② 11ms,14.59%
 */
public class LowestCommonAncestorOfABinarySearchTree {
	
    // 方法①
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	//如果root的值比p和q的值都大，那公共祖先一定在左子树
        if (root.val > Math.max(p.val, q.val)) return lowestCommonAncestor(root.left, p, q);
        //如果root的值比p和q的值都小，那一定在右子树
        if (root.val < Math.min(p.val, q.val)) return lowestCommonAncestor(root.right, p, q);
        //其实这一分支包括了：1.root的值介于p和q之间，那么最小公共祖先一定是root. 2.root的值等于p的值或q的值，那么最小公共祖先一定是root
        return root;
    }
    
	
	
    // 方法②
    public TreeNode lowestCommonAncestor_2(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root==q) return root;

        TreeNode left = lowestCommonAncestor_2(root.left,p,q);
        TreeNode right = lowestCommonAncestor_2(root.right,p,q);

        if(left==null) return right;
        if(right==null) return left;

        return root;
    }
    
}
