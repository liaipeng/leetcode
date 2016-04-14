package com.leetcode.algorithm.easy;

/*
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/

/*
 * 一开始题目都看不懂什么意思。意思是：第一个序列是 1， 第二个序列由第一个生成， 叫 一个1 ，写为 11，问 第n个是什么 
 * 
 * 代码①，22ms，原来我以为这代码好挫，没想到看了Discuss之后发现大家都是这思路
 * 
 * Discuss:
 * 代码②，6ms，跟代码①一模一样，只不过把String替换成StringBuffer
 */

public class CountAndSay {
	/*
	 * 代码①
	 * 很直接的思路
	 * 每一次迭代中，遍历string，看连着出现相同的数有几个，相邻的数不同时就加起来
	 */
    public String countAndSay(int n) {
        String str = "1";
        while (n-- > 1) {
            String tempStr = "";
            int count = 1;
            char c = str.charAt(0);
            for (int i = 1; i < str.length(); ++i) {
                if (c == str.charAt(i)) {
                    count++;
                } else {
                    tempStr = tempStr + count + c;
                    c = str.charAt(i);
                    count = 1;
                }
            }            
            tempStr = tempStr + count + c;
            str = tempStr;
        }
        return str;
    }
    
    /*
     * 代码②
     * 如果有大量字符串拼接的操作，StringBuffer的效率要比String快非常多
     * 因为append不需要每次都复制一次原来字符串的值。
     * 
     * String既属于引用类型也属于基础数据类型，而且内容是静态的，在通常情况效率高些   
  	 *	但如果涉及到连接字符串的时候，对于String每次连接都要重新分配内存，因此StringBuffer好些   
  	 *	总之，对于静态处理，如：储存字符串，用String   
  	 *	动态改变字符串内容则使用StringBuffer
  	 *
  	 *	另外，StringBuilder的速度要比StringBuffer更快。
  	 *	但是StringBuilder是线程非安全的，StringBuffer是线程安全的
  	 *
  	 *	在这一题中没有线程安全的问题，所以其实应该使用StringBuilder，速度更快
  	 *	这份代码的测试执行时间是6ms，若改成StringBuilder，可以缩短到4ms
     */
    public String countAndSay_2(int n) {
        StringBuffer str = new StringBuffer("1");
        while (n-- > 1) {
            StringBuffer tempStr = new StringBuffer("");
            int count = 1;
            char c = str.charAt(0);
            for (int i = 1; i < str.length(); ++i) {
                if (c == str.charAt(i)) {
                    count++;
                } else {
                    tempStr.append(count).append(c);
                    c = str.charAt(i);
                    count = 1;
                }
            }
            tempStr.append(count).append(c);
            str = tempStr;
        }
        return str.toString();
    }
}
