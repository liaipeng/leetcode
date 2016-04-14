package com.leetcode.algorithm.easy;

/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/

/*
 * 计算两个以字符串表示的二进制数的和，也以字符串表示
 * 这是第一次自己写完代码，去看Discuss发现不用再改进，泪流满面
 * 
 * 代码①，5ms，把字符 - 48，转换成int进行计算
 * 
 * 代码②，4ms，跟代码①思路一样，只是使用StringBuilder，提高效率
 */

public class AddBinary {
	/*
	 * 代码①
	 */
    public String addBinary(String a, String b) {
        int carry = 0, i = a.length()-1, j = b.length()-1, x , y;
        String sum = "";
        while (i >=0 || j >=0 || carry != 0) {
            x = i < 0 ? 0 : a.charAt(i--) - 48;
            y = j < 0 ? 0 : b.charAt(j--) - 48;
            sum = (x ^ y ^ carry) + sum;
            carry = (x + y + carry) / 2; 
        }
        return sum;
    }
    
    /*
     * 代码②
     * 虽然最后还得reverse一下浪费时间，但是还是会比代码①速度快
     */
    public String addBinary_2(String a, String b) {
        int carry = 0, i = a.length()-1, j = b.length()-1, x, y;
        StringBuilder sum = new StringBuilder("");
        while (i >=0 || j >=0 || carry != 0) {
            x = i < 0 ? 0 : a.charAt(i--) - 48;
            y = j < 0 ? 0 : b.charAt(j--) - 48;
            sum.append(x ^ y ^ carry);
            carry = (x + y + carry) / 2;
        }
        return sum.reverse().toString();
    }
    
    

}
