package com.leetcode.algorithm.easy;

/*
You are playing the following Bulls and Cows game with your friend: 
You write down a number and ask your friend to guess what the number is. 
Each time your friend makes a guess, you provide a hint 
that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") 
and how many digits match the secret number but locate in the wrong position (called "cows"). 
Your friend will use successive guesses and hints to eventually derive the secret number.

For example:

Secret number:  "1807"
Friend's guess: "7810"
Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
Write a function to return a hint according to the secret number and friend's guess,
 use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B".

Please note that both secret number and friend's guess may contain duplicate digits, for example:

Secret number:  "1123"
Friend's guess: "0111"
In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
*/

/*
 * 这题目也太长了。。
 * 就是说，给两个字符串secret和guess，如果有位置相同，且值相同的bulls+1，如果值相同，位置不相同，cows+1
 * 用于bulls++的那个值不再计入cows的判断中
 * 
 * 代码①，3ms，90.08%，用两个数组完成，一个用于secret，一个用于guess，遍历字符串，用每一个digit作数组的索引，该digit出现过的次数作为值。
 * 
 * Discuss:
 * 代码②，3ms，用一个数组就可以完成，而且代码清晰易懂
 */

public class BullsAndCows {
	/*
	 * 代码①
	 */
    public String getHint(String secret, String guess) {
        int[] hash1 = new int[10];
        int[] hash2 = new int[10];
        int bulls = 0, cows = 0, len = secret.length();
        for (int i = 0; i < len; ++i) {
            int s = secret.charAt(i) - '0', g = guess.charAt(i) - '0';
            // 若两个digit相等，bulls加一
            if (s == g) ++bulls;
            else {
            	// 否则，s出现次数+1
                hash1[s]++;
                // 若此时g在hash1中对应的位置不为0，说明secret中出现过此digit，cows+1，加完后把出现次数-1
                if (hash1[g] != 0) {
                    cows++;
                    hash1[g]--;
                } else {
                	// 否则，把g在hash2中出现的次数+1，
                    hash2[g]++;
                }
                // 此时，若s在hash2中对应的位置不为0，说明guess中出现过此digit，cows要加1，加完把两边的出现次数都-1
                if (hash2[s] != 0) {
                    cows++;
                    hash2[s]--;
                    hash1[s]--;
                }
            }
        }
        return bulls + "A" + cows + "B";
    }
    
    /*
     * 代码②
     * 还有好多要学习
     */
    public String getHint_2(String secret, String guess) {
        int bulls = 0, cows = 0, len = secret.length();
        // records用于记录digit出现的次数，secret中出现一次则+1，guess中出现一次则-1
        int[] records = new int[10];
        for (int i = 0; i < len; i++) {
            int s = secret.charAt(i) - '0', g = guess.charAt(i) - '0';
            if (s == g) bulls++;
            else {
            	// 假如此时records[s]为负数，说明之前guess中出现过这个digit了，cows++
                if (records[s]++ < 0) cows++;
                // 假如此时records[g]为正数，说明之前secret中出现过这个digit了，cows++
                if (records[g]-- > 0) cows++;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
