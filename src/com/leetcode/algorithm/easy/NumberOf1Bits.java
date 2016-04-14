package com.leetcode.algorithm.easy;

/*
Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
*/

/*
 * 这题考察位运算。Java位运算知识：http://www.cnblogs.com/zhengtao/articles/1916751.html
 * 题目说要把n当作一个unsigned value，无符号数。所以给的测试用例可能会超过int的范围，java是没有unsigned int的。
 * 
 * 对位运算的掌握很差，看了Discuss代码才知道怎么做。代码①，hammingWeight耗时2ms，13.71%
 */

public class NumberOf1Bits {
    // you need to treat n as an unsigned value
	//方法①
	//PS: 好像直接用return Integer.bitCount(n);就搞定了，ORZ
    public static int hammingWeight(int n) {
    	int count = 0;
    	//不能用 n>0 来判断是否遍历完成，因为比如当输入为2147483648时，Java会把它当成-2147483648
    	//这时候n>0就不起作用了
    	while (n != 0) {
    		//最后一位与1，00000000000000000000000000000001，若结果为1，说明该位是1
    		if ((n & 1) == 1) count++;
    		//Java的“无符号”右移位运算符（>>>），它使用了“零扩展”：无论正负，都在高位插入0。这一运算符是C或C++没有的。
    		//因为n是无符号数，所以用>>>移位，在高位补0，若使用>>，当数为负数时，会在高位补1
    		n >>>= 1;
    	}
    	return count;
    }
    
	/*
	 *  方法②
	    方法①对于0x1ffffff的判定需要循环32次才能计算出结果，超时。 
	    故转换思路，举例： 
	    n = 0x110100 n-1 = 0x110011 n&(n - 1) = 0x110000 
	    n = 0x110000 n-1 = 0x101111 n&(n - 1) = 0x100000 
	    n = 0x100000 n-1 = 0x011111 n&(n - 1) = 0x0 
	    看到这里已经得到了一种新的解法，n中本来有3个1，按照此种思路只需要循环3此即可求出最终结果，比第一种暴力枚举的解法要少很多次。
	  */
    public static int hammingWeight_2(int n) {
    	int count = 0;
    	while (n != 0) {
    		// 每执行一次，去掉n的一个1，同时count+1;直到n为0
    		n = n & (n - 1);
    		count++;
    	}
    	return count;
    }
    
    public static void main(String[] args) {
    	int n = 21474836;
    	System.out.println(hammingWeight(n));
    	
	}
}
