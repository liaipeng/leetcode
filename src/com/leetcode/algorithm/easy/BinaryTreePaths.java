package com.leetcode.algorithm.easy;

import java.util.LinkedList;
import java.util.List;

/*
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
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
 * 找出一颗二叉树的所有从根到叶子的路径
 * 找路径很明显是要使用DFS，至于路径字符串的构造则有不同的技巧。
 * 
 * 代码①，3ms，使用递归 + StringBuilder， 代码比较冗余
 * 
 * Discuss: 
 * 代码②，2ms，使用递归和字符串，相比之下代码①的路径构造画蛇添足，因为不需要对根节点做特别处理
 * 
 * 代码③，4ms，代码①和代码②都使用一个辅助函数，而代码③不使用辅助函数，非常有个性的解法，不过这解法也就是作为观赏用~
 */


public class BinaryTreePaths {
	/*
	 * 代码①
	 * 遍历到新的结点，用append()加入到当前path中，碰到叶子结点时把path加入到list中
	 * 返回上一个结点时，delete最后一个"->X"
	 * 
	 * 另外，这边使用StringBuilder其实是画蛇添足，对效率的提升没有帮助
	 * 因为在append()方法里面还是要用到字符串的拼接，所以不如直接使用字符串
	 * 
	 * list应该命名为paths，可读性更好
	 */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new LinkedList<String>();
        if (root == null) return list;
        StringBuilder path = new StringBuilder();
        path.append("" + root.val);
        if (root.left == null && root.right == null) list.add(path.toString());
        if (root.left != null) getPath(root.left, path, list);
        if (root.right != null) getPath(root.right, path, list);
        
        return list;
    }
    private StringBuilder getPath(TreeNode root, StringBuilder path, List<String> list) {
        path.append("->" + root.val);
        if (root.left == null && root.right == null) {
            list.add(path.toString());
            path.delete(path.lastIndexOf(">")-1, path.length());
            return path;
        }
        if (root.left != null) getPath(root.left, path, list);
        if (root.right != null) getPath(root.right, path, list);
        
        path.delete(path.lastIndexOf(">")-1, path.length());
        return path;
    }
	
	/*
	 * 代码②
	 * 比代码①简洁非常多
	 * 在path的传递方面，没必要return path，所以也不需要在返回上一节点的时候删掉path的最后一节"->x"，代码①多消耗的1ms也是因为这个
	 */
    public List<String> binaryTreePaths_2(TreeNode root) {
        List<String> paths = new LinkedList<String>();
        if (root != null) searchBT(root, "", paths);
        return paths;
    }
    private void searchBT(TreeNode root, String path, List<String> paths) {
        if (root.left == null && root.right == null) paths.add(path + root.val);
        if (root.left != null) searchBT(root.left, path + root.val + "->", paths);
        if (root.right != null) searchBT(root.right, path + root.val + "->", paths);
    }

	/*
	 * 代码③
	 */
	public List<String> binaryTreePaths_3(TreeNode root) {
        List<String> paths = new LinkedList<>();
        if(root == null) return paths;
        if(root.left == null && root.right == null){
            paths.add(root.val+"");
            return paths;
        }
         for (String path : binaryTreePaths_3(root.left)) {
             paths.add(root.val + "->" + path);
         }
         for (String path : binaryTreePaths_3(root.right)) {
             paths.add(root.val + "->" + path);
         }
         return paths;
    }
}
