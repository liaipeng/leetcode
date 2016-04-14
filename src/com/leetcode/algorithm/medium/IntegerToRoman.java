package com.leetcode.algorithm.medium;

import java.util.HashMap;
import java.util.Map;

/*
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
*/

/*
 * easy等级有一题相反的题目Roman to Integer
 * 罗马数字：
 * Ⅰ－1、Ⅱ－2、Ⅲ－3、Ⅳ－4、Ⅴ－5、Ⅵ－6、Ⅶ－7、Ⅷ－8、Ⅸ－9、X - 10、L-50、C-100、D-500、M-1000
 * 
 * 代码①，27ms，暴力- -。。。。搞笑来的
 * 
 * Discuss：
 * 代码②，10ms，本来我以为代码①是来搞笑的，看了代码②才发现，高手是怎么把搞笑变成屌爆的，牛逼
 * 
 * 代码③，8ms，太棒了！
 */

public class IntegerToRoman {
	/*
	 * 代码①
	 */
    public String intToRoman(int num) {
        int rest = num, tempNum = 0, times = 1;
        String ans = "";
        Map<Integer, String> dict = new HashMap<Integer, String>();
        dict.put(0, "");
        dict.put(1, "I"); dict.put(2, "II"); dict.put(3, "III"); dict.put(4, "IV"); dict.put(5, "V");
        dict.put(6, "VI"); dict.put(7, "VII"); dict.put(8, "VIII"); dict.put(9, "IX"); 
        dict.put(10, "X"); dict.put(20, "XX"); dict.put(30, "XXX"); dict.put(40, "XL"); dict.put(50, "L");
        dict.put(60, "LX"); dict.put(70, "LXX"); dict.put(80, "LXXX"); dict.put(90, "XC"); 
        dict.put(100, "C"); dict.put(200, "CC"); dict.put(300, "CCC"); dict.put(400, "CD"); dict.put(500, "D");
        dict.put(600, "DC"); dict.put(700, "DCC"); dict.put(800, "DCCC"); dict.put(900, "CM"); 
        dict.put(1000, "M"); dict.put(2000, "MM"); dict.put(3000, "MMM");
        
        while (rest > 0) {
            tempNum = rest % 10;
            rest /= 10;
            tempNum *= times;
            times *= 10;
            ans = dict.get(tempNum) + ans;
        }
        
        return ans;
    }
    
    /*
     * 代码②
     */
    public static String intToRoman_2(int num) {
        String[] M = {"", "M", "MM", "MMM"};
        String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        
        return M[num / 1000] + C[(num%1000) / 100] + X[(num%100) / 10] + I[(num % 10)];
    }
    
    /*
     * 代码③
     */
    public static String intToRoman_3(int num) {
        int[] weights = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] tokens = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder ans = new StringBuilder("");
        
        for (int i = 0; i < weights.length; i++) {
            while (num >= weights[i]) {
                ans.append(tokens[i]);  
                num -= weights[i];
            }
        }
        
        return ans.toString();
    }
}
