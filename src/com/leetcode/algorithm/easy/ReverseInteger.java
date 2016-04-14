package com.leetcode.algorithm.easy;

/*
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
*/

/*
 * 逆转一个数字，逆转容易，关键如何处理溢出问题
 * 写出了逆转的代码，但是不会处理溢出，看答案 T_T
 * 
 * Discuss：
 * 代码①，2ms，很巧妙
 * 代码②，2ms，虽然也能AC，但是思想不如代码①
 */

public class ReverseInteger {
	/*
	 * 代码①
	 * 每次转换的时候再反转换回去一次，对比转换前后是否相等，如果溢出了转换前后肯定是不相等的
	 */
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int tempRev = rev * 10 + x % 10;
            if ((tempRev - x % 10) / 10 != rev) return 0;
            rev = tempRev;
            x /= 10;
        }
        return rev;
    }
	
	/*
	 * 代码②
	 * 用long存储rev，这样rev就不会溢出，然后判断是否超过int的表示范围了
	 */
    public int reverse_2(int x) {
        long rev = 0;
        while (x != 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) return 0;
        }
        return (int)rev;
    }
}
