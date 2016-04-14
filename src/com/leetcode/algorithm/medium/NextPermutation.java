package com.leetcode.algorithm.medium;

import java.util.Arrays;

/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order
(ie, sorted in ascending order).

 The replacement must be in-place, do not allocate extra memory.

 Here are some examples. 
 Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1
 */

/*
 * 给一个数组，返回其按照字典序全排列的顺序的下一个排序
 * 字典序：
 * 		字典序法中，对于数字1、2、3......n的排列，不同排列的先后关系是从左到右逐个比较对应的数字的先后来决定的。例如对于5个数字的排列 12354和12345，排列12345在前，排列12354在后。按照这样的规定，5个数字的所有的排列中最前面的是12345，最后面的是 54321。
		字典序算法如下：
		设P是1～n的一个全排列:p=p1p2......pn=p1p2......pj-1pjpj+1......pk-1pkpk+1......pn
		1）从排列的右端开始，找出第一个比右边数字小的数字的序号j（j从左端开始计算），即   j=max{i|pi<pi+1}
		2）在pj的右边的数字中，找出所有比pj大的数中最小的数字pk
		3）对换pj，pk 
		4）再将pj+1......pk-1pkpk+1pn倒转得到排列p'=p1p2.....pj-1pjpn.....pk+1pkpk-1.....pj+1，这就是排列p的下一个排列。
		例如839647521是数字1～9的一个排列。从它生成下一个排列的步骤如下： 
		自右至左找出排列中第一个比右边数字小的数字4          839647521
		在该数字后的数字中找出比4大的数中最小的一个5        839647521
		将5与4交换                                                                     839657421
		将7421倒转                                                                     839651247
		所以839647521的下一个排列是839651247。
	
	按照以上方法，得到代码①
 *	
 *	代码①，2ms
 *
 *Discuss：
 *	代码②，2ms，对代码①进行小优化
 */

public class NextPermutation {
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp; 
    }
    private void reverse(int i, int j, int[] nums) {
        while (i < j) {
            swap(i++, j--, nums);
        }
    }
    /*
     * 代码①
     */
    public void nextPermutation(int[] nums) {
        if (nums.length == 1) return;
        int i, j, len = nums.length;
        int min = Integer.MAX_VALUE, minJ = -1; // 记录第(2)步寻找比nums[i]大中最小的数过程中的最小值以及其下标
        // 第(1)步
        for (i = len - 2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) break;
        }
        // 如果第(1)步没找到符合要求的数，按题目要求返回升序
        if (i == -1) {
            Arrays.sort(nums);
            return;
        }
        // 第(2)步
        for (j = i+1; j < len; j++) {
        	// 注意，这边需要用<=，否则类似[2,3,1,3,3]得到的结果是[2,3,3,3,1]，实际上应该是[2,3,3,1,3]
            if ((nums[j] > nums[i]) && (nums[j] <= min)) { 
            	min = nums[j];
                minJ = j;
            }
        }
        swap(i, minJ, nums); // 第(3)步
        reverse(i + 1, len - 1, nums); // 第(4)步
    }

    /*
     * 代码②
     * 对代码①进行一些优化
     */
    public void nextPermutation_2(int[] nums) {
        if (nums.length == 1) return;
        int i, j, len = nums.length, minJ = 0;
        for (i = len - 2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) break;
        }
        if (i == -1) {
        	// 优化1：因为没有下一个排序的情况时，nums是逆序的，所以只要用reverse函数，不需要用Arrays.sort() => O(nlogn)
            reverse(0, len-1, nums); 
            return;
        }
        for (j = i+1; j < len; j++) {
            if ((nums[j] > nums[i])) { 
                minJ = j; // 优化2：不需要记录最小值min，因为按照字典序排序，越后面的肯定越小
            }
        }
        swap(i, minJ, nums); 
        reverse(i + 1, len - 1, nums);
    }
}
