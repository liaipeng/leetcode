package com.leetcode.algorithm.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array 
 such that nums[i] = nums[j] and the difference between i and j is at most k.
 */

/*
 * 217题，Contains Duplicate的升级版
 * 217题给定一个数组，判断数组是否存在相同元素。
 * 这题是给定一个数组和一个数k，判断是否存在两个数相等，且i和j相差不超过k。
 * 
 * 代码①，14ms，217是用HashSet,这题换成用HashMap，记录值的同时记录值的索引。
 *
 * Discuss:
 * 代码②，9ms，用滑动窗口的思想，只用一个HashSet就搞定了
 */

class ContainsDuplicate_II {
	/*
	 * 代码①
	 */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // key: value of nums, value: index of nums
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(nums[i]) && (i - map.get(nums[i]) <= k)) return true;
            // 这里包含两种情况，1.map不包含该key，插入； 2.已经包含，但是两个索引相差超过k，此时把map中的索引改成后面这个
            map.put(nums[i], i);
        }
        return false;
    }
    
    /*
     * 代码②
     * 始终保持着长度为k+1的窗口进行遍历，窗口的头是i，尾是i-k
     */
    public boolean containsNearbyDuplicate_2(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; ++i) {
        	// 把超出窗口的元素删掉
            if (i > k) set.remove(nums[i - k - 1]); // 注意，是remove nums[i - k - 1]，而不是remove(i-k-1)
            if (!set.add(nums[i])) return true; // set.add() 如果添加成功，返回true；如果已经含有该元素，返回false。
        }
        return false;
    }
}
