package com.leetcode.algorithm.easy;

/*
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, 
please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 

You are responsible to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. 
If you still see your function signature accepts a const char * argument, 
please click the reload button  to reset your code definition.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. 
Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, 
which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, 
or if no such sequence exists because either str is empty or it contains only whitespace characters, 
no conversion is performed.

If no valid conversion could be performed, a zero value is returned. 
If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/

/*
 * 把字符串转换成整型，模仿C语言的atoi函数，没用过这个函数，看Requirements for atoi，要求还真多
 * 字符串转成整型本身不难，但是各种边界测试搞了半天
 * 
 * 代码①，4ms，代码冗长，可读性差
 * 
 * Discuss：
 * 代码②，4ms，比代码①代码量减少很多
 * 
 */
public class StringtoInteger_atoi {
	/*
	 * 代码①
	 */
    public int myAtoi(String str) {
        int len = str.length();
        int num = 0, sign = 0; // sign是num的符号，初始为0
        boolean ready = false; // ready表示已经过滤掉开头的干扰字符，进入转换环节了
        char[] digits = str.toCharArray();
        for (int i = 0; i < len; i++) {
            if (digits[i] == '+' || digits[i] == '-') {
                if (sign != 0) return 0; // 如果遇到了+-号，然而此时sign已经不是初始值了，说明是类似 "+-343"这样的非法情况
                sign = digits[i] == '+' ? 1 : -1;  // 第一次遇到正负号，给sign赋值
                ready = true; // 开始进入转换环节
            } else {
                if (Character.isDigit(digits[i])) { // 如果遇到的是数字
                    ready = true; // 进入转换环节，因为有正数有可能没有+号
                    // 判断值是否溢出，根据题目要求，溢出了返回2147483647或-2147483648
                    if (Integer.MAX_VALUE / 10 < num || Integer.MAX_VALUE / 10 == num && Integer.MAX_VALUE % 10 < (digits[i]-'0')) {
                        return sign == -1 ? -2147483648 : 2147483647;
                    }
                    num = num * 10 + digits[i] - '0'; // 转换
                } else { // 如果遇到的不是数字
                    if (ready) break; // 如果此时已经进入转换环节了，比如"+1231 abc342"，直接过pass掉后面的部分,返回1231
                 // 如果还没进入转换环节，如果是空格，比如"+   123"，则跳过，返回123。如果不是空格，比如"+sdf12312"，非法
                    else if (digits[i] != ' ') return 0;
                }  
            }
        }
        return sign == -1 ? -num : num; // 最后，根据符号返回正负数，不能直接return num * sign; 因为sign有可能为0
    }
    
    
    /*
     * 代码②
     * 与代码②相比，代码①很多冗余的判断
     * 总共分为3个步骤
     */
    public int myAtoi_2(String str) {
        int len = str.length();
        if (len == 0) return 0;
        int num = 0, sign = 1, i = 0;
        char[] digits = str.toCharArray();
        // 1. 过滤开头的空格
        for (; i < len; i++) {
            if (digits[i] != ' ') break;
        }
        // 2. 处理符号
        if (digits[i] == '+' || digits[i] == '-') sign = (digits[i++] == '-') ? -1 : 1;
        // 3. 开始转换
        while (i < len) {
        	// 如果不是数字，直接break，不用跟代码①一样那么多冗余判断，然后return 0;什么的。
        	// 因为如果是开头非法字符，num默认是0，直接返回num就是返回0。如果是数字之后出现非法字符，直接返回目前的num即可
            if (!Character.isDigit(digits[i])) break; 
            if (num > Integer.MAX_VALUE / 10 || num == Integer.MAX_VALUE / 10 && digits[i] - '0' > Integer.MAX_VALUE % 10)
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            num = num * 10 + digits[i++] - '0';
        }
        return num * sign;
    }
    
}
