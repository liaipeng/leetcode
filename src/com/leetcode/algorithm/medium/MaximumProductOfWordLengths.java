package com.leetcode.algorithm.medium;

import java.util.Arrays;
import java.util.Comparator;

/*
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. 
You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.
*/

/*
 * 给定一个字符串数组，求出两个没有任何相同字符的字符串相乘的最大长度。
 * 用暴力破解超时
 * 
 * 正确的解法应该是把一个"word"转换成一个int来表示
 * 也就是说，用int的第1到第26位来表示word中出现的a到z
 * 这样处理过后，判断两个word是否包含相同的字符，只需要把两个int进行与操作，如果结果是0，表示两个word没有任何一个相同的字符。否则结果不会为0
 * 之后只需要遍历int数组，把两个int与不为0时的最大相乘结果找出即可，见代码①
 * 
 * 在此基础上，可以对方法做一些优化：把word从长到短进行排列。这样的好处是可以根据以下条件提前break循环
 * 具体见代码②
 * 
 * Discuss：
 * 代码①，35ms
 * 
 * 代码②，32ms
 * 
 */

public class MaximumProductOfWordLengths {
	/*
	 * 代码①
	 */
    public int maxProduct(String[] words) {
        int[] bits = new int[words.length];
        int maxPro = 0;
        // change word to int
        for (int i = 0; i < words.length; i++) {
            char[] chs = words[i].toCharArray();
            for (int j = 0; j < chs.length; j++) {
                bits[i] |= 1 << (chs[j] - 'a');
            }
        }
        
        for (int i = 0; i < bits.length; i++) {
            for (int j = i + 1; j < bits.length; j++) {
                if ((bits[i] & bits[j]) == 0) {
                    // 换成if (pro > maxPro) maxPro = pro;的形式可以更快，但是没有用max美观
                    maxPro = Math.max(maxPro, words[i].length() * words[j].length());                	
                }
            }
        }
        return maxPro;
    }
    
    /*
     * 代码②
     * 与代码①相比，多了两处剪枝
     */
    public int maxProduct_2(String[] words) {
        int[] bits = new int[words.length];
        int maxPro = 0;
        // 把words进行从长到短的排序
        Arrays.sort(words, new Comparator<String>() {
            public int compare(String a, String b) {
                return b.length() - a.length();  // 注意比较器的使用，从大到小是b-a
                }
            }
        );
        
        for (int i = 0; i < words.length; i++) {
            char[] chs = words[i].toCharArray();
            for (int j = 0; j < chs.length; j++) {
                bits[i] |= 1 << (chs[j] - 'a');
            }
        }
        
        for (int i = 0; i < bits.length; i++) {
        	 // 剪枝1，因为已经从长到短排序，若此时word[i]长度的平方都无法大于maxPro，那后面的肯定不可能出现比maxPro大的了，可以直接拉倒了。
            if (words[i].length() * words[i].length() <= maxPro) break; 
            for (int j = i + 1; j < bits.length; j++) {
                if ((bits[i] & bits[j]) == 0) {
                    maxPro = Math.max(maxPro, words[i].length() * words[j].length());
                    break; // 剪枝2，因为是从长到短的排序，所以往后乘积只可能越来越小，没必要继续循环
                }
            }
        }

        return maxPro;
    }
}
