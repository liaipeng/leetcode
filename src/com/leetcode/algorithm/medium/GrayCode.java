package com.leetcode.algorithm.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. 
A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
*/

/*
 * 给定一个非负整数n，生成n bits的格雷码序列。
 * 
 * 格雷码：
 * 		二进制码->格雷码（编码）：从最右边一位起，依次将每一位与左边一位异或（XOR），作为对应格雷码该位的值，最左边一位不变（相当于左边是0）；
		格雷码->二进制码（解码）：从左边第二位起，将每位与左边一位解码后的值异或，作为该位解码后的值（最左边一位依然不变）。
		
 *	最笨的办法就是把1到2的n次方-1的数，一个个按照位运算转换成格雷码，见代码①
 *	
 *代码①，2ms
 *
 *Discuss：
 *
 *代码②，1ms，代码①太傻了。。格雷码的编码是依次将每一位与左边一位异或（XOR），所以直接 (num >> 1) ^ num 就可以了啊
 *		根本不需要代码①那样移来移去。
 *		如：
		　　Binary Code ：1011 要转换成Gray Code
		　　1011 = 1（照写第一位）, 1(第一位与第二位异或 1^0 = 1), 1(第二位异或第三位， 0^1=1), 0 (1^1 =0) = 1110
		　　其实就等于 (1011 >> 1) ^ 1011 = 1110
 *
 *代码③，1ms，用迭代的方式求序列。
 *		例如：
 *			当n=3时，可以利用n=2时的序列求出。
 *			当n=2时，格雷码为00,01,11,10。 	n=3时，格雷码为 (000,001,011,010 ) (110,111,101,100)
 *			可以看出	，除了最高位，左边的部分跟右边的部分其实是对称的。所以只要在n=2的序列基础上，对称构造高位为1的序列即可
 *			具体见代码③
 *
 */

public class GrayCode {
	/*
	 * 代码①
	 */
    public List<Integer> grayCode(int n) {
        List<Integer> list = new LinkedList<Integer>();
        int max = 1<<n, num;
        
        for (int i = 0; i < max; i++) {
            num = i;
            // 模拟格雷码的编码过程
    		for (int j = 0; j < n; j++) {
                num ^= (num>>1) & (1<<j); // 从右到左，把当前位异或左边一位的结果作为该位的新结果
    		}
    		list.add(num);
        }
        return list;
    }
    
    /*
     * 代码②
     */
    public List<Integer> grayCode_2(int n) {
        List<Integer> result = new LinkedList<Integer>();
        int max = 1 << n;
        for (int num = 0; num < max; num++) result.add((num>>1) ^ num); // 依次将0到max-1的数转成格雷码 (num>>1) ^ num
        return result;
    }
    
    /*
     * 代码③
     */
    public List<Integer> grayCode_3(int n) {
        List<Integer> result = new ArrayList<Integer>(); // 因为要取list里的值，所以这里用ArrayList
        result.add(0); // 初始的时候肯定是为0
        // 迭代过程
        for (int i = 0; i < n; i++) {
        	// 对称构造
            for (int index = result.size()-1; index >=0; index--) {
                result.add(result.get(index) | 1<<i); // 最高位置位1
            }
        }
        return result;
    }
}
