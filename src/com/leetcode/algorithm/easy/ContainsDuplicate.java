package com.leetcode.algorithm.easy;

import java.util.HashSet;
import java.util.Set;

/*
 * Given an array of integers, find if the array contains any duplicates. 
 * Your function should return true if any value appears at least twice in the array, 
 * and it should return false if every element is distinct.
 */

/*
 * 最直接的想法就是遍历进行比较，复杂度O(n²),然后就超时了。。。。
 * 
 * 看了别人的博客后，才想起Java有HashSet。。。
 * 因为HashSet只能存储不重复的对象，所以只要遍历一遍数组，set中不存在的就加入到set中
 * 如果存在，return true
 * 
 * 复杂的为O(n)，耗时17ms
 * 
 * 另外，如果不能使用set的话，可以用快排排序，然后遍历一次，看看相邻的元素是否有重复的。O(nlogn)的复杂度。
 */

public class ContainsDuplicate {
	public static boolean containsDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if(!set.contains(nums[i])) {
				set.add(nums[i]);
			}
			else
				return true;
		}
		return false;
	}
}
