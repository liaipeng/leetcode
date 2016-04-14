package com.leetcode.algorithm.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, 
such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.

Notes:
You may assume pattern contains only lowercase letters, 
and str contains lowercase letters separated by a single space.
*/

/*
 * 205题 Isomorphic Strings的升级版
 * 思路一模一样，考查的是hash思想
 * 
 * 代码①，3ms，使用HashMap
 * 
 * Discuss:
 * 代码②，3ms，使用HashMap和Objects.equals()方法 
 */

public class WordPattern {
	/*
	 * 代码①
	 * 跟205题代码①几乎一模一样，不同的值是map的key是字符，而value是字符串而已
	 */
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null && str == null) return true;
        if (pattern == null || str == null) return false;
        Map<Character, String> map = new HashMap<Character, String>();
        String[] words = str.split(" ");
        if (pattern.length() != words.length) return false;
        for (int i = 0; i < words.length; ++i) {
            char p = pattern.charAt(i);
            if (map.containsKey(p)) {
                if (!words[i].equals(map.get(p))) return false;
            } else {
                if (map.containsValue(words[i])) return false;
                map.put(p, words[i]);
            }
        }
        return true;
    }
    
    /*
     * 代码②
     * 跟205题代码②的思想一样
     * 只不过205题用的是数组，这题用map
     * map的put方法会返回value，判断返回value否相等
     * ★★注意：map的put方法，返回的是“前妻”！ 即返回与key关联的旧值，如果key没有任何映射关系，则返回null
     *  见：http://bbs.itheima.com/thread-27232-1-1.html
     *  
     *  所以这边value返回的值是上一次put进去的值，这样其实就跟205题代码②中取数组的值是一样的了
     *  不用先判断containsKey，然后get出来比较。
     */
    public boolean wordPattern_2(String pattern, String str) {
        if (pattern == null && str == null) return true;
        if (pattern == null || str == null) return false;
        Map<Object, Integer> map = new HashMap<Object, Integer>();
        String[] words = str.split(" ");
        if (pattern.length() != words.length) return false;
        for (Integer i = 0; i < words.length; ++i) {
        	// 我不知道为什么不直接用  if (map.put(pattern.charAt(i), i) !=  map.put(words[i], i)) return false;
            if (!Objects.equals(map.put(pattern.charAt(i), i), map.put(words[i], i))) return false;
        }
        return true;
    }
}
