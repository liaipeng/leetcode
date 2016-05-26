package com.leetcode.algorithm.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
*/

/*
 * Combination Sum升级版
 * 这一题中每一个组合中每一个候选数只能用一次，而且候选数可能出现重复的数，所以按照CombinationSum中的解法有可能出现重复的组合
 * 
 * 代码①在CombinationSum的基础上稍作修改，效率不高，具体见代码①
 * 
 * 代码①，16ms，28%
 * 
 * 其实这题最大的不同是候选数中可能出现重复的数，其实这跟Permutation_II中的代码②是一样一样的。
 * 也就是说，当发现某一个数与上一个数相等时，必须上一个数已经使用过此数才能使用，否则就会造成重复。
 * 所以需要一个数组来记录数的使用过程，以此来对重复的数进行过滤，具体见代码②
 * 
 * 代码②，4ms，90%
 * 
 * 
 * Discuss：
 * 与代码②类似，思想是过滤重复元素，但是此方法只需在代码①中加一行代码即可，不需要用visited数组来浪费额外空间
 * 代码②，4ms，90%
 * 
 */

public class CombinationSum_II {
	/*
	 * 代码①
	 * 两处修改
	 */
    private void generate(List<List<Integer>> result, List<Integer> list, int[] candidates, int target, int start) {
    	// 找到一个目标组合
        if (target == 0) {
            if (!result.contains(list)) result.add(new ArrayList<Integer>(list)); // 修改1：判断是否是重复的组合
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
                if (candidates[i] > target) break; 
                list.add(candidates[i]);
                generate(result, list, candidates, target - candidates[i], i+1);  // 修改2：由于同一个数字只能用一次，所以start变成i+1
                list.remove(list.size() - 1);
            }
        }    
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        generate(result, new ArrayList<Integer> (), candidates, target, 0);
        return result;
    }
    
    /*
     * 代码②
     */
    private void generate_2(List<List<Integer>> result, List<Integer> list, int[] candidates, int target, int start, boolean[] visited) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(list));// 无需判断重复了
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
            	// 当某个数与上一个数相等时，判断上一个数是否使用过，若没使用过，过滤当前的数。
                if (i > 0 && candidates[i] == candidates[i-1] && !visited[i-1]) continue;
                if (candidates[i] > target) break;
                visited[i] = true;
                list.add(candidates[i]);
                generate_2(result, list, candidates, target - candidates[i], i+1, visited); 
                list.remove(list.size() - 1);     
                visited[i] = false;
            }
        }
    }
    public List<List<Integer>> combinationSum2_2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        boolean[] visited = new boolean[candidates.length]; // 用visited数组来记录数的选择情况
        generate_2(result, new ArrayList<Integer> (), candidates, target, 0, visited);
        return result;
    }
    
    
    /*
     * 代码②
     */
    private void generate_3(List<List<Integer>> result, List<Integer> list, int[] candidates, int target, int start) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(list));
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
            	// i > start的情况其实就是上一个数没有被选择的情况。因为如果有选择的话，此时的i肯定是等于start的
                if (i > start && candidates[i] == candidates[i-1]) continue; 
                if (candidates[i] > target) break;
                list.add(candidates[i]);
                generate_3(result, list, candidates, target - candidates[i], i+1); 
                list.remove(list.size() - 1);     
            }
        }
    }
    public List<List<Integer>> combinationSum2_3(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        generate_3(result, new ArrayList<Integer> (), candidates, target, 0);
        return result;
    }
}
