package com.leetcode.algorithm.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

此处有一张图：手机按键

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
*/

/*
 * 这题跟Combinations系列 的其他题目其实也没多大区别
 * 也是用回溯，代码的整体框架几乎都一样
 * 
 * 代码①，1ms
 *
 * Discuss：
 * 
 * 代码②，1ms，解法有个性，使用队列来解，太牛逼了
 * 如果代码①是DFS，那么代码②其实就是BFS
 * 
 */


public class LetterCombinationsOfAPhoneNumber {
	/*
	 * 代码①
	 */
    private void combinate(List<String> result, char[][] mask, char[] digits, int index, String res) {
        if (digits.length == res.length()) {
            result.add(res);
        } else {
            char[] letters = mask[digits[index] - '0'];
            for (int i = 0; i < letters.length; i++) {
                res += letters[i];
                combinate(result, mask, digits, index + 1, res); // 从下一个子数组(index + 1)中挑选一个数
                res = res.substring(0, index);
            }
        }
    }
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if (digits.length() == 0) return result;
        // 前面故意放两个空的子数组，是为了满足数组下标与对应子数组的关系。例如 2 => {'a', 'b', 'c'}, 3 => {'d', 'e', 'f'}
		char[][] mask = {
		        {}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, 
		        {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'},
				{'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
		}; 
		combinate(result, mask, digits.toCharArray(), 0, "");
		return result;
    }
    
    
    /*
     * 代码②
     * 使用队列求解，BFS
     */
    public List<String> letterCombinations_2(String digits) {
        LinkedList<String> result = new LinkedList<String>(); // 表面上它是一个LinkedList，实际上它是一个Queue
        if (digits.length() == 0) return result;
        char[][] mask = {
		        {}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, 
		        {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'},
				{'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
		};
		result.offer(""); // 初始化
		for (int i = 0 ; i < digits.length(); i++) {
			// 一层一层进行BFS
		    while (result.peek().length() == i) {
		        String combi = result.poll();
		        for (char ch : mask[digits.charAt(i) - '0']) {
		            result.offer(combi + ch);
		        }
		    }
		}
		return result;
    }
    
}

