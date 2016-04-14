package com.leetcode.algorithm.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
*/

/*
 * Permutation升级版
 * 这一题给定的数组将会出现重复的数字
 * 求出全排列
 * 
 * 可以在Permutation的代码基础上加上一个判断：if (!result.contains(newList)) result.add(newList);
 * 但是这样效率非常非常低
 * 
 * 或者是用Set代替List，如代码①。虽然效率比上一个方法要高，但是总的来说还是很低
 * 
 * 代码①，19ms，23%
 * 
 * Discuss：
 * 
 * 代码②，4ms，82%，递归
 * 
 * 代码②的方法是，先把数组进行排序。然后依次从排好序的数组中选择元素进行排列，并记录选择过的元素
 * 
 * 对于重复元素的处理方法：当有重复的时候(如2个1)，用第2个1的时候要保证第1个1已经用过（这样就保证了不会出现重复的排序）
 * 否则跳过：if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
 * 
 */
public class Permutations_II {
	/*
	 * 代码①
	 */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> result = new HashSet<List<Integer>>(); // 用Set代替List
        result.add(new ArrayList<Integer>());
        
        for (int i = 0; i < nums.length; i++) {
            Set<List<Integer>> tempResult = new HashSet<List<Integer>>();
            for (List<Integer> al : result) {
                int size = al.size();
                for (int j = 0; j <= size; j++) {
                    List<Integer> tempAl = new ArrayList<Integer>(al);
                    tempAl.add(j, nums[i]);
                    tempResult.add(tempAl);
                }
            }
            result = tempResult;
        }
        return new ArrayList<List<Integer>>(result); // 最后再把Set转换回List
    }
    
    /*
     * 代码②
     */
    private void permute(List<List<Integer>> result, List<Integer> list, int[] nums, boolean[] visited) {
    	// 找到一个排序
        if (list.size() == nums.length) {
            result.add(new ArrayList<Integer>(list)); // 这边必须new一个List！！！否则对list进行修改的时候这边会同步
            return;
        }
        // 依次选择数组里的元素
        for (int i = 0; i < nums.length; i++) {
        	// 如果已经选择过了，跳过
            if (!visited[i]) {
                if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue; // 如果是跟上一个元素相同，必须保证上一个元素被选择过
                visited[i] = true; // 选择该元素
                list.add(nums[i]);
                permute(result, list, nums, visited); // 进行下一个元素的选择
                list.remove(list.size() - 1); // 不选这个元素。（不同的排序就出现了）
                visited[i] = false;               
            }
            
        }
    }
    public List<List<Integer>> permuteUnique_2(int[] nums) {
        Arrays.sort(nums); // 排序，方便相同元素是否选择过
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        boolean[] visited = new boolean[nums.length]; // 记录选择过的元素
        permute(result, list, nums, visited);
        return result;
    }
}
