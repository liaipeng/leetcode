package com.leetcode.algorithm.easy;

import java.util.Arrays;

/*
Description:
Count the number of prime numbers less than a non-negative number, n.
从这里开始还介绍了Sieve of Eratosthenes：埃拉托色尼筛选法，时间复杂度O(nloglogn)

由于一个合数总是可以分解成若干个质数的乘积，那么如果把质数（最初只知道2是质数）的倍数都去掉，那么剩下的就是质数了。
例如要查找100以内的质数，首先2是质数，把2的倍数去掉；
此时3没有被去掉，可认为是质数，所以把3的倍数去掉；
再到5，再到7，7之后呢，因为8，9，10刚才都被去掉了，而100以内的任意合数肯定都有一个因子小于10（100的开方），以此类推，最后剩下的数就是质数
*/

/*
 * 找出n以内素数的个数（不包括n）
 * 
 * 代码①，超时，最直接也是以前做题最常用的方法，复杂度O(n^1.5)
 * 
 * Hint:
 * 代码②，29ms，使用埃拉托色尼筛选法
 * 
 * Discuss:
 * 代码③，30ms，埃拉托色尼筛选法的另一种代码实现，比代码②的更直观好理解一点
 */

public class CountPrimes {
	/*
	 * 代码①
	 */
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 1; i < n; i++) {
           if (isPrime(i)) count++;
        }
        return count;
     }
     private boolean isPrime(int num) {
        if (num <= 1) return false;
        // 使用 i*i<=num代替 i<=sqrt(num)，避免重复调用sqrt()方法
        for (int i = 2; i * i <= num; i++) {
           if (num % i == 0) return false;
        }
        return true;
     }
     
     /*
      * 代码②
      */
     public int countPrimes_2(int n) {
    	 if (n <= 2) return 0;
         boolean[] isPrime = new boolean[n];
         Arrays.fill(isPrime, true); // 初始化数组，虽然0和1不是素数，不过没关系，后面不会碰到
         // 埃拉托色尼筛选法开始
         for (int i = 2; i * i <= n; ++i) {
        	// 如果isPrime[i]已经是false了，说明已经涂过i的乘积的格子了，跳过。比如i=2的乘积已经涂过了，那i=4的乘积都不用涂了
             if (!isPrime[i]) continue; 
             // 埃拉托色尼筛选法，从i*i开始（i*i前面的是重复的），步长i进行涂格子
             // 即p^2 + p, p^2 + 2p, ...。 也就是说，被涂成false的格子，其实都是某个数的乘积，因此肯定不是素数
             for (int j = i * i; j < n; j += i) {
                 isPrime[j] = false; 
             }
         }
         int count = 0;
         // 最终，筛选法结束后没有被涂到的格子仍然保持true（0和1除外），也就是素数
         for (int i = 2; i < n; ++i) {
             if (isPrime[i]) count++;
         }
         return count;
     }
     
     /*
      * 代码③
      * 由于一个合数总是可以分解成若干个质数的乘积，那么如果把质数（最初只知道2是质数）的倍数都去掉，那么剩下的就是质数了。
      */
     public int countPrimes_3(int n) {
    	 if (n <= 2) return 0;
         boolean[] isPrime = new boolean[n]; 
         Arrays.fill(isPrime, true); // 初始化所有数都是素数
         int count = 0;
         for (int i = 2; i < n; ++i) {
             // 如果是素数，把该素数的乘积全部改为false；否则，跳过
             if (isPrime[i])  {
                 count++;
                 // i的2倍到好多倍，也就是n以内i的所有乘积
                 for (int j = 2; i * j < n; j++) {
                     isPrime[i * j] = false;
                 }
             }
         }
         return count;
     }
     
     public static void main(String[] args) {
	}
}
