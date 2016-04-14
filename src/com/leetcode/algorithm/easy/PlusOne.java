package com.leetcode.algorithm.easy;

/*
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
*/

/*
 * 其实就是给一个用数组表示的数，返回加上一的结果
 * 例如[3,5,6,7]表示，3567，加1的结果是3568，所以返回[3,5,6,8]
 * 
 * 代码①, 0ms
 * 
 * Discuss：
 * 代码②，0ms，看完代码②，觉得代码①真傻逼
 */

public class PlusOne {
	/*
	 * 代码①
	 * 最普通最直接的思路
	 */
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        // 是否要进位
        boolean carry = true;
        int i = len - 1;
        // 如果还要进位，而且数组还没遍历完，循环
        while (carry && i >= 0) {
            //如果当前位是9，那么设为0，继续进位
            if (9 == digits[i]) digits[i--] = 0;
            else {
                //如果当前位不是9，说明不用进位了,当前位+1
                digits[i--]++;
                carry = false;
            }
        }
        // 如果数组还没遍历完，或者已经不需要进位，直接返回修改后的digits
        if (i >= 0 || !carry) return digits;
        else {
            // 否则，需要在原数组的基础上补上一个1，开辟新数组，返回新数组
            int[] newDigits = new int[len + 1];
            newDigits[0] = 1;
            // 复制数组内容
            for (i = 0; i < len; ++i) 
                newDigits[i + 1] = digits[i];
            return newDigits;
        }
    }

    /*
     * 代码②
     * 代码①的简化版：1. 首先，完全不需要用carry判断是否需要进位，因为是+1，当前除了是9不可能需要进位
     * 				  2. 当某一位不是9的时候，直接就可以return了，不用继续判断了
     * 				  3. 最最最傻逼的，代码①把原数组copy到新数组中。。。。。。
     *                   因为假如出现需要加一个数组位置的情况，必然是1000...这种情况，直接最高位设为1就可以了
     * 
     */
    public int[] plusOne_2(int[] digits) {
    	// 如果把digitsl.length放在for内，多消耗1ms
        int len = digits.length;
        for (int i = len - 1; i >= 0; --i) {
            if (9 != digits[i]) {
                digits[i]++;
                // 如果出现某一位不是9，直接就可以返回了
                return digits;
            }
            digits[i] = 0;
        }
        int[] newDigits = new int[len + 1];
        // 只需要设最高位为1，其余都为0
        newDigits[0] = 1;
        return newDigits;
    }
    
	public static void main(String[] args) {
	}
}
