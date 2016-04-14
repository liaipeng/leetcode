package com.leetcode.algorithm.easy;

import java.util.HashMap;
import java.util.Map;

/*
Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: Reverse Integer
*/

/*
 * 试了半天，只能通过一半的test，n为负数（其实不能说是负数，因为n是无符号数，就是说最高位为1的数）的时候全错，不知道怎么取到符号位
 * 
 * Discuss:
 * 代码①，2ms，就差一点，思路其实跟这个代码一样，就差一点，前功尽弃
 * 
 * 代码②，6ms，题目的Follow up：假如这个方法要被调用多次，有什么办法可以优化？
 * 			         代码②将一个int切分位4个byte进行逆转，同时用一个cache存储逆转过的byte，
 * 			         然后每次调用方法的时候如果cache中存在该值，直接返回它的逆序，不用再计算
 * 
 */


public class ReverseBits {
	/*
	 * 代码①
	 */
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int rev = 0;
        for (int i = 0; i < 32; ++i) {
            // ★★★就差这一步：要先移位后操作，先操作后移位会出错。先移位其实相当于i=31的那次不要移位
        	// 这样做是因为，总共对n取了32位的值，但是rev移位其实只要移位31次
            rev = (rev << 1) | (n & 1);
            // 无符号右移，会把符号位也往右移，高位补0，也就是把n当做无符号数来右移
            n >>>= 1;
        }
        return rev;
    }
    
    /*
     * 代码②
     */
    // 存储逆转过的byte
    private Map<Byte, Integer> cache = new HashMap<Byte, Integer>();
    // you need treat n as an unsigned value
    public int reverseBits_2(int n) {
        byte[] bytes = new byte[4];
        // 将int切分为4个byte
        for (int i = 0; i < 4; ++i) {
           bytes[i] = (byte)(n & 0x00FF);
           n >>>= 8;
        }
        int rev = 0;
        // 逐一转换
        for (int i = 0; i < 4; ++i) {
            rev = (rev << 8) | reverseBytes(bytes[i]);
        }
        return rev;
    }
    // 逆转每个byte
    private int reverseBytes(byte n) {
        Integer value = cache.get(n); 
        if (null != value) return value;
        value = 0;
        for (int i = 0; i < 8; ++i) {
            value = (value << 1) | (n & 1);
            n >>>= 1;
        }
        return value;
    }
    
    
}
