package com.leetcode.algorithm.medium;

import java.util.ArrayList;
import java.util.List;

/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Ensure that numbers within the set are sorted in ascending order.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
*/

/*
 * CombinationSum升级升级版
 * 这次是只能用[1,2,...,9]这几个候选数（经过测试发现，每个数最多只能用一次），从中选择k个数，使其和等于n
 * 
 * 这一题最大的不同在于指定了选择的数的个数
 * 
 * 不过。。不就是CombinationSum的解法中加一个判断是否使用了k个数不就可以了吗？。。
 * 见代码①
 * 
 * 代码①，1ms
 * 
 * Discuss：
 * 
 * 代码②，1ms，代码①居然还用数组，这种行为真是太傻逼了
 */

public class CombinationSum_III {
	/*
	 * 代码①
	 */
    private void generate(List<List<Integer>> result, List<Integer> list, int[] nums, int target, int start, int k) {
    	// 如果k个数已经使用完了 ，判断是否得到可行解。其他就跟CombinationSum一样了(还有数不能重复使用这一点)
        if (k == 0) {
            if (target != 0) return;
            result.add(new ArrayList<Integer>(list));
            return;
        } else {
            for (int i = start; i < nums.length; i++) {
                if (nums[i] > target) break;
                list.add(nums[i]);
                generate(result, list, nums, target - nums[i], i + 1, k - 1); 
                list.remove(list.size() - 1);
            }
        }    
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int[] nums = new int[9];
        for (int i = 0; i < 9; i++) nums[i] = i + 1;
        generate(result, new ArrayList<Integer> (), nums, n, 0, k);
        return result;
    }
    
    /*
     * 代码②
     * 因为候选数是1到9，所以完全没有必要用数组存。直接用int递增就好了
     */
    private void generate_2(List<List<Integer>> result, List<Integer> list, int target, int start, int k) {
        if (k == 0) {
            if (target == 0) {
                result.add(new ArrayList<Integer>(list));
                return;
            }
        } else {
            for (int num = start; num < 10; num++) {
                if (num > target) break;
                list.add(num);
                generate_2(result, list, target - num, num + 1, k - 1); 
                list.remove(list.size() - 1);
            }
        }    
    }
    public List<List<Integer>> combinationSum3_2(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        generate_2(result, new ArrayList<Integer> (), n, 1, k);
        return result;
    }
}
