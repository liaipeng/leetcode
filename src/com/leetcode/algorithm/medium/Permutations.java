package com.leetcode.algorithm.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].

给定一个值不重复的数组，返回所有的排列情况

我的第一想法是迭代：			   - [3,2,1]
					 - [2,1] -|- [2,3,1]
					|		   - [2,1,3]
				[1]-|			
					|		   - [3,1,2]
					 - [1,2] -|- [1,3,2]
							   - [1,2,3]
* 每一次迭代，就是在上一轮的list中，从左到右插入新的元素组成新的list。见代码①，
* 
* 代码①，4ms，比较费空间
* 
* Discuss：
* 代码②，4ms，跟代码①是一样的思路，但是LinkedList的pollFirst()方法用的很巧妙，之前从来没接触过，学习一下
* 
* 代码③，3ms，递归
*/							

public class Permutations {
	/*
	 * 代码①
	 */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> tempResult = new ArrayList<List<Integer>>();
         // 遍历上一轮的所有list
            for (List<Integer> al : result) {
                int size = al.size();
                // 在上一轮的list中，从左到右插入新的元素，每插入一个就是一个新的排列
                for (int j = 0; j <= size; j++) {
                    List<Integer> tempAl = new ArrayList<Integer>(al);
                    tempAl.add(j, nums[i]);
                    tempResult.add(tempAl);
                }
            }
            result = tempResult;
        }
        return result;
    }
    
    /*
     * 代码②
     */
    public List<List<Integer>> permute_2(int[] nums) {
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>(); // 必须用LinkedList
        result.add(new ArrayList<Integer>());
        
        for (int num : nums) {
            int size = result.size();
            for (; size > 0; size--) {
                List<Integer> list = result.pollFirst(); // 摘掉第一个节点
                for (int i = list.size(); i >= 0; i--) {
                    List<Integer> newList = new ArrayList<Integer>(list);
                    newList.add(i, num);
                    result.add(newList);
                }
            }
        }
        return result;
    }
    
    /*
     * 代码③
     */
    private void collect(int[] nums, int index, List<Integer> list, List<List<Integer>> result) {
    	// 当list插入完所以元素后，一个新的排列完成
        if (list.size() == nums.length) {
            result.add(list);
            return;
        }
        for (int i = list.size(); i >= 0; i--) {
            List<Integer> newList = new ArrayList<Integer>(list);
            newList.add(i, nums[index]); // 依次按每个位置插入
            collect(nums, index + 1, newList, result); // 递归
        }
    }
    public List<List<Integer>> permute_3(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        collect(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }
}
