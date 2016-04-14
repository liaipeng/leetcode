package com.leetcode.algorithm.medium;

import java.util.ArrayList;
import java.util.List;

/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

/*
 * 给定n和k，求出C(n, k)的所有组合
 * 
 * 这题的做法与Permutations的代码③几乎一样，用回溯，见代码①
 * 
 * 代码①，3ms
 */

public class Combinations {
	/*
	 * 代码①
	 */
    private void generate(List<List<Integer>> result, List<Integer> list, int[] nums, int k, int start) {
    	// 找到可行组合
        if (list.size() == k) {
            result.add(new ArrayList<Integer>(list));
            return;
        } else {
        	// 从左到右，依次选择一个元素排在当前位
            for (int i = start; i < nums.length; i++) {
                list.add(nums[i]); // 选择
                generate(result, list, nums, k, i + 1); // 选下一个元素，因为组合是没有顺序的，所以下一轮的start=i+1，也就是只看右边的元素即可
                list.remove(list.size() - 1); // 去掉当前元素，选择i+1那个元素排在当前位置
            }
        }
    }
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i + 1;
        generate(result, new ArrayList<Integer>(), nums, k, 0);
        return result;
    }
    
}
