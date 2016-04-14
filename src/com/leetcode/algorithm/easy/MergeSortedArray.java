package com.leetcode.algorithm.easy;

/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
The number of elements initialized in nums1 and nums2 are m and n respectively.
*/

/*
 * 代码①，0ms
 */

public class MergeSortedArray {
	/*
	 * 代码①
	 * 数组nums1后面有多出来的位置，对比nums1和nums2的值，把大的往后面多的位置扔
	 */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int current1 = m - 1;
        int current2 = n - 1;
        int i = n + m - 1;
        while (current1 >= 0 && current2 >= 0) {
        	nums1[i--] = (nums1[current1] >= nums2[current2] ? nums1[current1--] : nums2[current2--]);        	
        }
        while (current2 >= 0) {
        	nums1[i--] = nums2[current2--];
        }
    }
}
