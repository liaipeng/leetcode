package com.leetcode.algorithm.easy;


/*
 Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 */
/*
 Note:
 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.
 */

/*
 * 不能复制，只能就地操作
 * 遍历数组，遇到0就把0移到末尾
 * 显然不可能是最优解。。。
 * 复杂度为O(n²)
 * 见方法①
 */

/*
 * 方法② MoveZeroes_Discuss 为O(n)复杂度的代码
 * 
 * 思路：
 * 		实际上就是将所有的非0数向前尽可能的压缩，最后把没压缩的那部分全置0就行了。
 * 		比如103040，先压缩成134，剩余的3为全置为0。过程中需要一个指针记录压缩到的位置。
 * 
 *  	但是不能这样实现（遍历数组，若遇到0，把后一位往前提一个位置补位。）：
 * 			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == 0) {
					nums[i] = nums[i+1];
					...
				}
			}
		如果是这样实现，必须一次性把后面的全部提到前面，这样就又回到O(n²)复杂度了。	
 * 
 * 		所以，应该使用双指针压缩法，过程中使用一个指针记录压缩到的位置，具体见代码
 * 
 */

/*
 * Discuss里的答案：
 * public void moveZeroes(int[] nums) {
        int z = -1;
        for (int i=0; i< nums.length; i++) {
            int temp = nums[i];
            if (temp != 0) {
                nums[i]=nums[++z];
                nums[z]=temp;
            }
        }
    }
 *
 */
public class MoveZeroes {
	public static void moveZeroes(int[] nums) {
		int length = nums.length;
		int j;
		for (int i=0; i<length; i++) {
			if (nums[i] == 0) {
				for (j=i; j<length-1; j++) {
					nums[j] = nums[j+1];
				}
				nums[j] = 0;
				i--;
				length--;
			}
		}
	}
	
	public static void moveZeroes_Discuss(int[] nums) {
		int z = 0;
		//压缩：把非0的数往前压缩
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[z++] = nums[i];
			}
		}
		//置0
		while (z < nums.length) {
			nums[z++] = 0;
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 0, 1, 0, 3, 12, 35, 0, 234};

		moveZeroes(nums);
		
		for (int num : nums) {
			System.out.print(num+",");
		}
	}
}
