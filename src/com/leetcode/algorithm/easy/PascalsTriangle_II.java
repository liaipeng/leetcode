package com.leetcode.algorithm.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/

/*
 * 118题，Pascal's Triangle的升级版
 * 118题是要用list构造指定的杨辉三角。这一题是用list返回杨辉三角的某一层
 * 而且要求值用O(k)的空间（也就是就一个list，不能用其他辅助数据结构）
 * 既然如此，思路就是在原来的list的基础上迭代构造下一层的list
 */

/*
 * 代码①，3ms， 25%，第三梯队的代码。各种数组越界问题，做了好长时间
 * 
 * Discuss:
 * 代码②， 3ms，25%，看了Discuss里的代码②，我怀疑自己为什么会写出代码①那么画蛇添足的代码。。。。
 * 
 * 代码③， 2ms， 75%，思想其实跟代码②一样，只不过是用数组实现，最后再把数组转换成list
 * 
 * 第一梯队，最快的代码是1ms，用的是杨辉三角的递推公式。
 */

public class PascalsTriangle_II {
     /*
      * 代码①
      * 用一个list不断迭代
      */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        if (rowIndex < 0) return list;
        // 初始化，如果rowIndex等于0，不会进入for循环，直接返回[1]
        list.add(1);
        int j;
        // 不断迭代改变list,一直到指定的层
        for (int i = 1; i <= rowIndex; ++i) {
        	// 改变原list的左半边
            for (j = i / 2; j > 0; --j) list.set(j, list.get(j) + list.get(j - 1));
            // 对称改变原list又半边
            for (j = (i + 1) / 2; j < i; ++j) list.set(j, list.get(i - j));                
            // 补上最后一个1
            list.add(1);
        }
        return list;
    }
    
    /*
     * 代码②
     * 其实跟代码①的思想是一样的，迭代修改list
     */
    public List<Integer> getRow_2(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        if (rowIndex < 0) return list;
        for (int i = 0; i <= rowIndex; ++i) {
        	// 每次循环，都在链表头加上一个1
            list.add(0, 1);
            // 除了表头和表尾，改变list的值
            for (int j = 1; j < list.size() - 1; ++j) {
                list.set(j, list.get(j) + list.get(j + 1));
            }
        }
        return list;
    }
    
    /*
     * 代码③
     */
    public List<Integer> getRow_3(int rowIndex) {
        Integer[] array = new Integer[rowIndex + 1];
        if (rowIndex < 0) return Arrays.asList(array);
        //填充0，每一轮循环的最后一位是 1 + 0 = 1
        Arrays.fill(array, 0);
        //第一位必然是1
        array[0] = 1;
        //迭代改变数组
        for (int i = 1; i <=rowIndex; ++i) {
            for (int j = i; j > 0 ; --j) {
                array[j] = array[j] + array[j - 1];
            }
        }
        return Arrays.asList(array);
    }
}
