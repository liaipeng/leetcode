package com.leetcode.algorithm.medium;

/*
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
*/

/*
 * 给定一个排好序的数组（没有重复的值），以及一个值。
 * 若该值在数组中存在，返回其下标；如果不存在，返回应该插入在哪个位置。
 * 
 * 感觉这题不应该出现在Medium啊，在easy里都算简单的了
 * 之后觉得题目应该设一个要求，使用O(logn)时间内，这样就要使用二分查找了
 * 
 * 代码①，1ms，遍历一遍数组
 * 
 * Discuss：
 * 代码②，0ms，二分查找
 */

public class SearchInsertPosition {
	/*
	 * 代码①
	 * 遍历数组，target大于nums[i]，移动到下一个数。
	 * 若target等于当前数，返回其下标i；若小于当前数，说明应该插在这个位置，也是当前下标i。
	 * 若数组遍历到最后都还是小于target，那么就是插入在最后一位，此时i已经等于nums.length了，所以还是返回i
	 */
    public int searchInsert(int[] nums, int target) {
        int i = 0;
        while (i < nums.length && target > nums[i]) i++;
        return i;
    }
    
    /*
     * 代码②
     * 使用二分查找
     * 查找数组中是否存在target，若存在，返回其下标
     * 若不存在，循环结束后low的位置便是要插入的位置
     * 因为循环结束时已经遍历完数组中所有的数，此时low左边的数都会比target小，当前以及右边的数都会比target大
     */
    public int searchInsert_2(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid = 0;
        while (low <= high) {
        	// (high+low)/2 有可能溢出 。 另外，特别注意，要用low+(high-low)/2，不要用high-(high-low)/2！！！
        	// 后者某些时候会计算错误。例如low=2,high=5，(2+5)/2=3，而如果使用后者5-(5-2)/2 = 5 - 1 = 4！！！
        	mid = low + ((high - low) >> 1); 
            if (target == nums[mid]) return mid;
            else if (target < nums[mid]) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }
}
