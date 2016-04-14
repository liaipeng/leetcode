package com.leetcode.algorithm.medium;

/*
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

/*
 * 判断链表是否有环
 * 很经典的问题，经典解法就是用快慢指针法（弗洛伊德判圈法，龟兔赛跑法）
 * 
 * 代码①，1ms
 * 
 * Discuss：
 * 代码②，1ms，不一样的写法而已
 */

public class LinkedListCycle {
	/*
	 * 代码①
	 */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (true) {
            if (fast == null || fast.next == null) return false; // 若快指针到了遍历到了结尾，无环
            if (fast.val == slow.val) return true; // 若快指针追上了慢指针，有环。这里可以直接用fast == slow代替
            fast = fast.next.next; // 快指针每次走2步
            slow = slow.next; // 慢指针每次走1步
        }
    }
    /*
     * 代码②
     */
    public boolean hasCycle_2(ListNode head) {
        if (head == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;           
            if (fast == slow) return true; // 这种写法，这边必须是用直接比较，不能fast.val == slow.val，会空指针
        }
        return false;
    }
}
