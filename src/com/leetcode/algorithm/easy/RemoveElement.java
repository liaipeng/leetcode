package com.leetcode.algorithm.easy;


/*
Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

给定一个数组和一个值，就地删除数组中的所有等于该值的实例，返回数组的新长度。（注意，是就地删除，不能用辅助数组）

元素的顺序可以改变，新长度之后的数据是什么都没关系
*/

/*
 * 代码①，5ms，使用双指针法
 * 
 * Discuss：
 * 代码②，0ms，双指针，一个从前往后，一个从后往前，这样不用遍历整个数组。
 * 能这样做的前提是题目说了元素的顺序是可以改变的。
 */

public class RemoveElement {
	/*
	 * 代码①
	 * next指针遍历数组，把值不等于val的数据往前提，（提到current指针指向的位置，每提完一次，current向后移动一次）
	 * 最终current停留的地方就是新数组的长度
	 */
    public int removeElement(int[] nums, int val) {
        int current = 0;
        int next = 0;
        for (; next < nums.length; ++next) {
            if (nums[next] != val)
                nums[current++] = nums[next];
        }
        return current;
    }
    
    public int removeElement_2(int[] nums, int val) {
        int len = nums.length;
        //i不超过len，len从后往前移动
        for (int i = 0; i < len; ++i)
            //如果当前元素等于val，把数组后端len指向的元素提到当前位置
            //这里用while循环，是因为有可能len指向的那个元素也是等于val的
            //同时，要注意i不能超过len
            while (nums[i] == val && i < len) {
                nums[i] = nums[--len];
            }
        return len;    
    }
    
    
}
