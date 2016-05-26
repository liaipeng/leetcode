package com.leetcode.algorithm.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
*/

/*
 * 给定一组候选数字C，以及一个目标数字T
 * 从候选数字C中选择一组数字，使其和为T。同一个数字可以使用无限次。
 * 组合必须以非降序的形式排列
 * 找出所有不同的组合
 * 
 * 显然是要用迭代来解题
 * 
 * 解法基本与permutations代码③和Combinations代码①同一个套路，见代码①
 * 
 * 代码①，5ms，76.83%
 */
public class CombinationSum {
	/*
	 * 代码①
	 */
    private void generate(List<List<Integer>> result, List<Integer> list, int[] candidates, int target, int start) {
    	// 找到一个目标组合
        if (target == 0) {
            result.add(new ArrayList<Integer>(list));
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
                if (candidates[i] > target) break; // 剪枝：因为候选数组已经排好序了，所以如果此时的候选数大于目标数了，后面的也不用考虑了
                list.add(candidates[i]);
             // 选下一个数时，target减去当前选择的数，并且下一个数的start是i。因为candidates已经排好序了
                generate(result, list, candidates, target - candidates[i], i); 
                list.remove(list.size() - 1);
            }
        }    
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates); // 由于要求组合必须是非降序排列，所以这里先对候选数组做一个排序
        generate(result, new ArrayList<Integer> (), candidates, target, 0);
        return result;
    }
}
