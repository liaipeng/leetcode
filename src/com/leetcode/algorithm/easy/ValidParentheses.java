package com.leetcode.algorithm.easy;

import java.util.Stack;

/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/

/*
 * 括号匹配，很经典的问题。思路就是利用栈的后进先出。
 * 
 * 代码①，1ms，用栈
 * 
 * Discuss:
 * 代码②，8ms，虽然效率很低，但是挺有意思的解法，用字符串替换
 * 
 * 代码③，1ms，也是用栈，大体思路跟代码①一样，在判断匹配上有个小技巧
 */

public class ValidParentheses {
	/*
	 * 代码①
	 * 遇到左括号就压到栈里，遇到右括号就出栈判断是否配对，最后判断栈是否为空
	 */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                int difference = c - stack.pop();
                // {和}以及[和]的ASCII码相差2，(和)相差1
                if (difference != 1 && difference != 2) return false;
            }
        }
        return stack.isEmpty() ? true : false;
    }
    
    /*
     * 代码②
     * 每次循环将(),[],和{}三个字符串替换为空，直到字符串不再变化
     * 最后判断剩余字符串长度是否为0
     */
    public boolean isValid_2(String s) {
        int length = s.length();
        do {
            length = s.length();
            s = s.replace("()", "").replace("[]", "").replace("{}", "");
        } while (length != s.length());
        return s.length() == 0;
    }
    
    /*
     * 代码③
     * 遇到左括号时，把它匹配的右括号压到栈里，这样的好处是遇到右括号的时候可以直接判断它们是否相等就可以确定是否匹配
     */
    public boolean isValid_3(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c  == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}
