package com.leetcode.algorithm.easy;

import java.util.Arrays;

/*
 Given an array of size n, find the majority element. The majority element is the element that appears more than  n/2 （取下界） times.

 You may assume that the array is non-empty and the majority element always exist in the array.
 */

/*
 * 先把数组排序，然后遍历一遍数组，数字发生变化时判断一次count值是否大于n/2了
 * 方法①耗时4ms 32.81%  sort()需要O(nlogn)的时间
 * 看了Discuss的代码，觉得自己太傻逼了。。
 * 
 *    Arrays.sort(nums);
      int len = nums.length;
      return nums[len/2];

 *  
 * 
 * 方法②，Discuss中的方法，耗时2ms, 61.49%， 不用排序，直接遍历
 * 思路是用一个变量统计，遇到相同的元素+1，遇到不同的元素-1，最终剩下的那个数就是所求的数。
 * 其实就是Moore voting algorithm--每找出两个不同的element，就成对删除即count--，最终剩下的一定就是所求的。时间复杂度：O(n)
 * 这种算法的前提是必须存在主元素，否则，需要再花O(n)时间遍历一遍数组，看看major出现的次数是否大于一半。
 * 
 * 具体代码见 majorityElement_Discuss
 * 
 * 
 */

public class MajorityElement {
	//方法①
	public static int majorityElement(int[] nums) {
		Arrays.sort(nums);
		int majority = nums[0];
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (majority == nums[i])
				count++;
			else if (count > nums.length / 2)
				return majority;
			else {
				majority = nums[i];
				count = 1;
			}
		}
		return majority;
		
		
	}

	//方法②
	public static int majorityElement_Discuss(int[] nums) {
		//初始值，major为第一个数，计数为1
		int major = nums[0], count = 1;
		for (int i = 1; i < nums.length; i++) {
			//如果计数为0，说明前面的已经都抵消了。重新选择major，count为1
			if (count == 0) {
				count++;
				major = nums[i];
			//如果与当前major相同，计数+1
			} else if (major == nums[i]) {
				count++;
			//否则，计数-1
			} else
				count--;

		}
		return major;
	}

	public static void main(String[] args) {
		int[] arrays = new int[] { -1, 3, 5, 6, 7, 3, 3, 3 };
		// int [] arrays = new int[]{-1,1,1,1,2,1};
		System.out.println(majorityElement(arrays));
	}
}
