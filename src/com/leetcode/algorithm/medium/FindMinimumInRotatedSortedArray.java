package com.leetcode.algorithm.medium;

/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

/*
 * 有一个已经排好序的数组，以某个点为轴进行旋转后，求出数组的最小值
 * 
 * 
 * 代码①，1ms，首先，暴力破解当然是可以的，O(n)复杂度
 * 
 * 
 * 但是作为Medium的题目，肯定不是要考察这种解法的。
 * 因为本身是已经排好序的数组，所以不管以哪个点为轴旋转，旋转后的两段也还是有序的，所以应该想到用二分法
 * 最小值有两种情况：
 * 		1. 假如数组没有旋转，那么最小值为nums[0];
 * 		2. 否则，最小值应该是nums[i] < nums[i - 1]; 也就是比前一位还小的数，例如题目中的0
 * 
 * 所以，使用二分法：
 * 		1. 首先，比较第一个数与最后一个数，如果前者小于后者，说明没有旋转，返回前者即可
 * 		2. 否则，找中间数，如果该数比比第一个数大，那么最小值会在该数的右侧
 * 		3. 否则，最小值在该数的左侧
 * 具体见代码②
 * 
 * Discuss：
 * 
 * 代码②，1ms，二分法
 * 			虽然代码②可以通过，但实际上代码②只能处理数组是升序的情况
 * 			（但是实际上只是因为题目没有说清楚，经过测试，题目应该就是只要求升序的数组的）
 * 			    若要处理降序的情况（例如[2,1,6,5,4,3]），需在代码②的基础上做出改进，让其对降序的情况也能处理
 * 
 */

public class FindMinimumInRotatedSortedArray {
	/*
	 * 代码①
	 */
    public int findMin(int[] nums) {
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (min > nums[i]) min = nums[i];
        }
        return min;
    }
    
    /*
     * 代码②
     * 用二分法
     */
    public int findMin_2(int[] nums) {
        if (nums.length == 1) return nums[0];
        int low = 0, high = nums.length - 1;
        if (nums[low] < nums[high]) return nums[low];
        while (low < high) {
            int mid = low + ((high - low)>>1); // 防止溢出
            if (nums[mid] > nums[high]) low = mid + 1; // > 或者 >=都可以，没差别
            // 这边一定要是mid，不能是mid-1
            // 因为，比如[4,5,6,0,1,2,3]，mid指向的0就是最小数，但是此时因为nums[mid] <= nums[high]，
            // 所以下一轮搜索会到0的左侧，此时如果high=mid-1，之后的搜索直接就把0给排除在外了
            // 而low则不存在这样的问题，用mid + 1
            // 因为碰到中间值刚好为最小值时，根据判断逻辑，必然是到左侧继续搜索
            else high = mid;
        }
        return nums[low];
    }
}
