package com.leetcode.algorithm.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

/*
 * 这题还是比较简单的，思路简单粗暴
 * 代码①, 1ms
 */

public class PascalsTriangle {
	/*
	 * 代码①
	 * 从第一层开始，一层层往list里添加子链表即可
	 */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        if (numRows < 1) return list;
        List<Integer> preSubList = new ArrayList<Integer>();
        preSubList.add(1);
        list.add(preSubList);
        // 循环几次就添加几个子链表，i从2开始，因为第1个子链表已经手动加进去了
        for (int i = 2; i <= numRows; ++i) {
            List<Integer> subList = new ArrayList<Integer>();
            subList.add(1);
            // 循环几次就添加几个数到子链表，j从1到i-1，因为第0个和第i个数是1
            for (int j = 1; j < i - 1; ++j) {
                subList.add(preSubList.get(j - 1) + preSubList.get(j));
            }
            subList.add(1);
            preSubList = subList;
            list.add(subList);
        }
        return list;
    }
}
