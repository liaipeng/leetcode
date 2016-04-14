package com.leetcode.algorithm.medium;

/*
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, 
with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, 
then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
*/

/*
 * 给定一个数组，该数组用0,1,2分别表示红、白、蓝三种颜色，要求对该数组进行排序，使得结果为相同的颜色在一起，并以红、白、蓝的顺序进行排列。
 * 
 * 最直观的思路就是先遍历一遍数组，分别用3个变量记录0,1,2出现的次数（其实只记录2个就可以），然后再遍历数组进行修改即可，见代码①
 * 
 * 代码①，1ms，需遍历2遍数组。
 * 
 * 代码②，0ms，用双指针法，只遍历一遍数组。
 * 
 * Discuss：
 * 代码③，0ms，代码②可读性太差，此代码对代码②进行简化
 * 
 */


public class SortColors {
	/*
	 * 代码①
	 */
    public void sortColors(int[] nums) {
        int redNum = 0, whiteNum = 0, blueNum = 0;
        for (int num : nums) {
            if (num == 0) redNum++;
            else if (num == 1) whiteNum++;
            else blueNum++;
        }
        int i = 0;
        for (; redNum > 0; redNum--) nums[i++] = 0;
        for (; whiteNum > 0; whiteNum--) nums[i++] = 1;
        for (; blueNum > 0; blueNum--) nums[i++] = 2;
    }
    
    /*
     * 代码②
     * 基本的思路就是用cur指针遍历一遍数组，遇到0的时候交换到前面（也就是head的位置），遇到2的时候交换到末尾（也就是tail的位置），遇到1不管。
     */
    public void sortColors_2(int[] nums) {
        int head = 0, cur = 0, tail = nums.length - 1;
        while (cur <= tail) {
        	// 如果cur指向的元素为2，就跟数组末尾的元素交换位置，交换后--，不再去管最后一个元素
            if (nums[cur] == 2) {
                nums[cur] = nums[tail];
                nums[tail] = 2;
                tail--;
            } else {
            	// 如果head指向的元素是0的话，不用再去管这个位置，head向右移
                if (nums[head] == 0) {
                    head++;
                } else {
                	// 否则，若cur指向的元素是0，将head指向的元素与cur进行交换
                    if (nums[cur] == 0) {
                        nums[cur] = nums[head];
                        nums[head] = 0;
                        head++;
                    }
                    // 如果cur指向的是1，不去管他
                }
                cur++; // cur往右遍历
            }
        }
    }
    
    /*
     * 代码③
     * 逻辑简化为3个分支
     * 若cur指向2，则把cur与tail的元素交换
     * 若cur指向0，则与head的元素交换
     * 若cur指向1，不管。
     */
    public void sortColors_3(int[] nums) {
        int head = 0, cur = 0, tail = nums.length - 1;
        while (cur <= tail) {
            if (nums[cur] == 2) {
                nums[cur] = nums[tail];
                nums[tail] = 2;
                tail--;
            } else if (nums[cur] == 0) {
                nums[cur] = nums[head];
                nums[head] = 0;
                head++;
                cur++;
            } else {
                cur++;
            }
        }
    }
}
