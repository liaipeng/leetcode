package com.leetcode.algorithm.medium;

import java.util.LinkedList;
import java.util.List;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
*/

/*
 * 给一个整数n，返回n对括号能组成的所有组合
 * 
 * 没有头绪
 * 
 * Discuss：
 * 代码①，2ms，使用DFS回溯
 * 
 */

public class GenerateParentheses {
	/*
	 * 代码①
	 * 假设在位置k我们还剩余open个左括号和close个右括号，如果open>0，则我们可以直接打印左括号，而不违背规则。
	 * 能否打印右括号，我们还必须验证open和close的值是否满足规则，如果open>=close，则我们不能打印右括号，因为打印会违背合法排列的规则
	 * 否则可以打印右括号。
	 * 如果open和close均为零，则说明我们已经完成一个合法排列，可以将其打印出来。通过深搜，我们可以很快地解决问题
	 */
    private void backTrack(List<String> list, String prefix, int open, int close) {
    	// 可以使用的左右括号都用完了，说明已经得到了一个合法排列
        if (open == 0 && close == 0) {
            list.add(prefix);
            return;
        }
        // 递归，将深度遍历出所有可能性
        if (open > 0) backTrack(list, prefix + "(", open - 1, close); // 如果左括号还没用完，排列中加一个左括号，左括号数量-1
        if (open < close) backTrack(list, prefix + ")", open, close - 1); // 如果剩下的右括号比左括号多，排列中加一个右括号，右括号数量-1
    }
    public List<String> generateParenthesis(int n) {
        LinkedList<String> list = new LinkedList<String>();
        backTrack(list, "", n, n);
        return list;
    }
    
    
    
}
