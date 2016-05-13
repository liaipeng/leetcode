package com.leetcode.algorithm.easy;

/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/

/*
 * 判断字符串是否回文，只考虑字母和数字，且不考虑字母的大小写
 * 也就是说要过滤掉除了字母和数字之外的字符
 * 有两种方法，第一个是用正则表达式替换字符串，如代码①；第二个是在遍历的过程中用Character.isLetterOrDigit()方法判断，如代码②
 * 
 * 代码①，42ms，使用正则表达式替换原字符串，然后从两头向中间比较字符是否相等
 * 
 * 代码②，10ms，也是从两头向中间比较字符是否相等，在遍历的过程中用Character.isLetterOrDigit()判断，不是数字字母则跳过
 * 
 * 代码③，37ms，使用正则表达式替换原字符串，然后使用StringBuilder逆转字符串，与原串对比
 */

public class ValidPalindrome {
	/*
	 * 代码①
	 */
    public boolean isPalindrome(String s) {
		s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
		int i = 0, j = s.length() - 1;
		while (i < j) {
		    if (s.charAt(i++) != s.charAt(j--)) return false;
		}
		return true;
    }	
    
    /*
     * 代码②
     * ★ 若字符串比较长，应选择toCharArray()代替charAt，效率更高。
     * 例如本代码消耗时间是10ms，若是使用charAt，消耗时间是12ms
     */
    public boolean isPalindrome_2(String s) {
		s = s.toLowerCase();
		char[] ch = s.toCharArray();
		int i = 0, j = ch.length - 1;
		while (i < j) {
		    while (i < j && !Character.isLetterOrDigit(ch[i])) { i++; }
		    while (i < j && !Character.isLetterOrDigit(ch[j])) { j--; }
		    if (ch[i] != ch[j]) return false;
		    i++;
		    j--;
		}
		return true;
    }
    
    /*
     * 代码③
     * 代码①的正则替换也应该跟本代码一样，先小写再替换，就不用A-Z了
     */
    public boolean isPalindrome_3(String s) {
        s=s.toLowerCase().replaceAll("[^a-z0-9]", "");
        return new StringBuilder(s).reverse().toString().equals(s);
    }
}
