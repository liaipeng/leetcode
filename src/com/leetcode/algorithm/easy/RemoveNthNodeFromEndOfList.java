package com.leetcode.algorithm.easy;

/*
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
*/

/*
 * 给定一条链表，删除指定位置的结点。要求只能遍历一次
 * 刚好上一题做的是和滑动窗口有关的题目，这题直接就有思路了。
 * 
 * 代码①，1ms，用两个指针间隔n的指针进行遍历，
 *             相当于一个窗口大小为3的窗口往表尾滑动，当窗口前端front到达表尾，后端back下一个结点即为要删除的结点
 */

public class RemoveNthNodeFromEndOfList {
	/*
	 * 代码①
	 */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode front = head;
        ListNode back = head;
        while (n-- != 0) front = front.next;
        // 如果front为null，说明是删除表头
        if (null == front) return head.next;
        while (front.next != null) {
            front = front.next;
            back = back.next;
        }
        back.next = back.next.next;
        return head;
    }
}
