package com.leetcode.algorithm.medium;

/*
There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, 
you toggle every third bulb (turning on if it's off or turning off if it's on). For the nth round, you only toggle the last bulb. 
Find how many bulbs are on after n rounds.

Example:

Given n = 3. 

At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 

So you should return 1, because there is only one bulb is on.
*/

/*
 * 假设有n个灯泡，初始状态都灭的
 * 第一轮每一个灯拨一次开关
 * 第二轮每两个灯拨一次开关
 * 第三轮每三个灯拨一次开关
 * ……
 * 第n轮每n个灯拨一次开关
 * 
 * 问最后还有几个灯是亮的
 * 
 * 分析类型的题目，或者叫脑筋急转弯
 * 
 * 显然，灯如果被拨动奇数次是亮的，偶数次则是灭的。
 * 然后每个灯泡，除了第一轮一定会被碰一次之外，剩下的就是自己的因数
 * 所以有两种情况（1除外）：
 * 		1. 不是平方数的，例如6，被触摸的次数为，1*6， 6*1， 2*3， 3*2，一共4次，必然是偶数次触摸（因为必然会对称），所以也肯定是灭的
 * 		2. 是平方数的，例如16，被触摸的次数为，1*16, 16*1, 2*8, 8*2, 4*4，一共是5次
 * 		        也就是说，对于平方数的情况，因数相乘的情况是对称的，唯独多出来的一个是平方的那个，也就是多出来的这一个让灯泡变成亮的
 * 
 * 于是，题目也就转变成了求n以为平方数的个数了
 * 
 * Discuss：
 * 代码①，1ms
 * 
 */

public class BulbSwitcher {
	/*
	 * 代码①
	 */
    public int bulbSwitch(int n) {  
        return (int)Math.sqrt(n);
    }  
}
