package com.leetcode.algorithm.easy;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
*/

/*
 * 代码①，遍历链表，取出不重复的val。再赋给原链表，13ms，0.3% ORZ
 * 
 * 代码②，1ms
 * 
 * 代码③，Discuss，相比之下代码②真是又臭又长 - -
 * 
 * 代码④，Discuss，递归, 2ms。 虽然效率比代码③低，但是代码非常简洁易懂，666666666。
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class RemoveDuplicatesFromSortedList {
	/*
	 * 代码①
	 */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        Set<Integer> set = new TreeSet<Integer>();
        ListNode point = head;
        ListNode pre = null;
        while (point != null) {
            set.add(point.val);
            point = point.next;
        }
        point = head;
        Iterator<Integer> it = set.iterator();
        while(it.hasNext()) {
            point.val = it.next().intValue();
            pre = point;
            point = point.next;
        }
        pre.next = null;
        return head;
    }
    
    /*
     * 代码②
     * 用point指向当前结点，nextPoint指向下一结点，当nextPont的val与point相等时，移动到下一结点，直到不相等，把point的next指向当前nextPoint
     */
    public ListNode deleteDuplicates_2(ListNode head) {
        if (null == head || null == head.next) return head;
        ListNode point = head;
        ListNode nextPoint = head.next;
        while (null != nextPoint) {
            if (point.val != nextPoint.val) {
                point.next = nextPoint;
                point = nextPoint;
            }
            nextPoint = nextPoint.next;
        }
        //处理类似链表为 1->1 这种情况
        point.next = null;
        return head;
    }
    
    /*
     * 代码③
     * 如果next的val不相等，移动到next；
     * 如果相等，把next指向next的next。
     */
    public ListNode deleteDuplicates_3(ListNode head) {
        if (null == head || null == head.next) return head;
        ListNode point = head;
        while (point.next != null) {
            if (point.val == point.next.val) {
                point.next = point.next.next;
            }
            else {
                point = point.next;
            }
        }
        return head;
    }
    
    /*
     * 代码④
     * 递归调用方法删除链表头之后的链表
     * 然后判断处理过后的链表的next与head的val是否相等，如果不相等，返回head，如果相等，丢弃表头。。返回next
     */
    public ListNode deleteDuplicates_4(ListNode head) {
        if (null == head || null == head.next) return head;
        head.next = deleteDuplicates_4(head.next);
        return head.val == head.next.val ? head.next : head;
    }
    
	public static void main(String[] args) {
		
	}
}
