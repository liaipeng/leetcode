package com.leetcode.algorithm.easy;

import java.util.HashSet;
import java.util.Set;

/*
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: 
Starting with any positive integer, replace the number by the sum of the squares of its digits, 
and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. 
Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

1² + 9² = 82
8² + 2² = 68
6² + 8² = 100
1² + 0² + 0² = 1
*/

/*
 * 想用递归，找不到递归终止的条件，（因为false的条件是出现无限循环）
 * 看了Discuss后，终止条件可以为出现重复的数字。 自己想问题还是太一根筋。
 */

/*
 * 代码①，7ms，25%，使用set判断是否出现相同的数，以此来判断是否进入死循环
 * 
 * 代码②，4ms，80%，Floyd判圈算法——Cycle detection，也称为龟兔赛跑算法， 6666666666666666。
 * http://blog.csdn.net/thestoryofsnow/article/details/6822576
 * 
 * 
 */

public class HappyNumber {
	/*
	 * 代码① 
	 */
    public static boolean isHappy(int n) {
        if (1 == n) return true;
        if (0 == n) return false;
        Set<Integer> set = new HashSet<Integer>();
        //若set中存在n，说明已经开始死循环，跳出while。（set.add(n)，若已存在n，会返回false）
        //若不存在，添加到set中，开始新一轮循环
        while (set.add(n) && (1 != n)) {
        	 int sum = 0;
             int digit = 0;
            //求n的各位数字平方的和
            while (0 != n) {
                digit = n % 10;
                n /= 10;
                sum += Math.pow(digit, 2);
            }
            n = sum;
        }
        return n == 1;
    }
    
    /*
     * 代码②
     * 使用弗洛伊德判圈算法，快速找出”环“
     */
    public int getSum(int n) {
        int sum = 0;
        int digit = 0;
        while (0 != n) {
            digit = n % 10;
            n /= 10;
            sum += Math.pow(digit, 2);
        }
        return sum;
    }    
    public boolean isHappy_2(int n) {
        if (1 == n) return true;
        if (0 == n) return false;
        int slow = n;
        int fast = n;
        do {
        	//慢指针
            slow = getSum(slow);
            //快指针
            fast = getSum(getSum(fast));
        } while (slow != fast);
        return slow == 1;
    }
    
    public static void main(String[] args) {
    	boolean happy = isHappy(18);
    	System.out.println(happy);
	}
}
