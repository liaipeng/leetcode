package com.leetcode.algorithm.easy;

/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/*
 * 最简单的，直接取两条链表的val，然后按照顺序修改链表的val，两条连在一起，但是显而易见效率很低，不试了。
 * 
 * 思路：l1,l2分别指向两个链表的表头，用结点作为新链表的表头，依次串联l1和l2中val比较小的值，直到其中一条链表结束，把另一条接到新链表结尾。
 * 		如代码① ，1ms
 * 
 * Discuss，代码②，递归，1ms，代码非常简洁
 */
public class MergeTwoSortedLists {
	/*
	 * 代码①
	 */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (null == l1 && null == l2) return null;
        if (null == l1) return l2;
        if (null == l2) return l1;
        ListNode head = new ListNode(-1);
        ListNode newHead = head;
        
        while (null != l1 && null != l2) {
            if (l1.val <= l2.val)  {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }
        if (null == l1) head.next = l2;
        if (null == l2) head.next = l1;
        
        return newHead.next;
    }
    
    /*
     * 代码②
     */
    public ListNode mergeTwoLists_2(ListNode l1, ListNode l2) {
    	/*
    	 * 递归到某一条链表为null时，返回另一条链表
    	 */
        if (null == l1 && null == l2) return null;
        if (null == l1) return l2;
        if (null == l2) return l1;
        
        /*
         * l1和l2中val小的结点作为表头，剩下的链表继续合成，合成结果接到该表头的后面
         */
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists_2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists_2(l1, l2.next);
            return l2;
        }
    }
}
