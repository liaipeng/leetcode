package com.leetcode.algorithm.easy;

/*
Determine whether an integer is a palindrome. Do this without extra space.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", 
you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.
*/

/*
 * 看到题目回文问题，先想起算法作业做过的DP算法。但是算法作业的判断字符串，这里是一个数字，所以还是不一样的。
 * 而且因为不能用额外的space，所以也不能转换成字符串来做
 * 
 * 题目提示了，可以尝试把这个数逆转，但是可能会造成溢出,比如1000000003逆转后3000000001就溢出了
 * 最后没想出来怎么逆转数
 * 
 * 看了Discuss，特别是代码②和代码③，太巧妙了
 * Discuss：
 * 代码①, 12ms, 51%，逆转x，会溢出。
 * 
 * 代码②，11ms，80%，逆转x，不会溢出。
 * 
 * 代码③，11ms，80%，逆转一半x，不会溢出。
 * 
 */
public class PalindromeNumber {
	/*
	 * 代码①
	 * 当输入逆转后溢出时，例如1000000003，会自动转换成负数
	 * 显然会转换成负数的不可能是回文
	 */
    public static boolean isPalindrome(int x) {
        int rev = 0;
        int reminder = x;
        // 逆转x，每进入一次喜欢，rev就乘以10，往高位推一位
        while (reminder > 0) {
            rev = rev * 10 + reminder % 10;
            reminder /= 10;
        }
        return rev == x;
    }
    
    /*
     * 代码②
     * 逆转只逆转到倒数第二位，最后比较的时候分开比较
     * 巧妙的避开了溢出
     */
    public boolean isPalindrome_2(int x) {
        if (x < 0) return false;
        int rev = 0;
        int reminder = x;
        // reminder为个位数的时候就停止循环
        while (reminder >= 10) {
            rev = rev * 10 + reminder % 10;
            reminder /= 10;
        }
        // 最后分别比较最后一位和其他位，避免了溢出
        return rev == x / 10 && reminder == x % 10;
    }
    
    /*
     * 代码③
     * 只逆转x的一半，对比逆转后的一半与原数的另一半是否相等即可
     * 巧妙的避开了溢出
     */
    public boolean isPalindrome_3(int x) {
    	// 此方法必须单独判断x=0 或者 末尾为0的情况
        if (0 == x) return true; 
        if (x < 0 || x % 10 == 0) return false;
        int rev = 0;
        // 循环条件为x>rev，所以当逆转了一半或者一半多一个时循环会停止
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        // 判断逆转后的一半与原数的另一半是否相等，（有可能是奇数位的）
        return x == rev || x == rev/10;
    }
    
}
