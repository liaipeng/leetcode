package com.leetcode.algorithm.easy;

/*
You are a product manager and currently leading a team to develop a new product. 
Unfortunately, the latest version of your product fails the quality check. 
Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. 
Implement a function to find the first bad version. You should minimize the number of calls to the API.
*/

/*
 * 给定一个isBadVersion(version)方法和一个整数n代表n个version，isBadVersion方法会返回该version是不是bad
 * 设计一个方法firstBadVersion，找出n个version中第一个bad version
 * 要求尽可能少调用isBadVersion方法
 * 
 * 尝试了一下，从n开始一直n--调用isBadVersion，会超时
 * 所以采用二分查找
 * 
 * 代码①，18ms，二分查找，O(logN)
 */

/* The isBadVersion API is defined in the parent class VersionControl.
boolean isBadVersion(int version); */

/*
 * 代码①
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int low = 1, high = n;
        while (low != high) {
            int mid = low + ((high - low)>>1);  ★★这里不能用 (low + high) >> 1代替，因为low+high有可能溢出 ★★
            if (isBadVersion(mid)) high = mid; // 如果mid是bad version，继续判断左半边
            else low = mid + 1; // 否则，判断右边
        }
        return low; // 当low=high循环结束，此时low和high就是第一个bad version
    }
}
*/
