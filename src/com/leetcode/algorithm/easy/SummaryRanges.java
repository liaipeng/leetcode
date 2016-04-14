package com.leetcode.algorithm.easy;

import java.util.LinkedList;
import java.util.List;

/*
Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
*/

/*
 * 给定一个int型数组，返回该数组的范围集合。
 * 这题还是比较简单的，不知道为什么通过率才20%
 * 
 * 代码①，1ms
 * 
 * Discuss:
 * 代码②，1ms，代码①简化版
 */

public class SummaryRanges {
	/*
	 * 代码①
	 */
    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new LinkedList<String>(); 
        if (nums.length == 0) return ranges;
        int start = nums[0], end = nums[0]; // start为范围起始，end为范围的末尾，初始为nums[0]
        for (int i = 0; i < nums.length - 1; ++i) {
        	// 如果下一个数不是当前数+1，说明范围断了，把当前start和end存入ranges中，start重定位为下一个数
            if (nums[i+1] != nums[i] + 1) {
                if (start != end) ranges.add(start + "->" + end);
                else ranges.add(start + "");
                start = nums[i + 1];
            }
                end = nums[i + 1]; // 每遍历一个值，end往后移动一格
        }
        // 因为循环条件是i < nums.length - 1，所以for循环结束时，最后一个范围还没有处理
        if (start != end) ranges.add(start + "->" + end);
        else ranges.add(start + "");
        
        return ranges;
    }
    
    /*
     * 代码②
     * 思路与代码①一样
     * 比起代码①，直接用nums[i]作为end，也不需要在循环体外再做一个处理
     */
    public List<String> summaryRanges_2(int[] nums) {
        List<String> ranges = new LinkedList<String>();
        if (nums.length == 0) return ranges;
        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            // 如果下一个数与当前数相差1，i往后移动，直到下一个数与当前数不差1，则从start到nums[i]即为1个范围
            // 或者i已经等于nums.length了结束循环，也就是指向最后一个数，那么start到nums[i]为一个范围
            while ((i < nums.length - 1) && (nums[i + 1] == nums[i] + 1)) {
                i++;
            }
            if (start == nums[i]) ranges.add(start + "");
            else ranges.add(start + "->" + nums[i]);
        }
        return ranges;
    }
}
