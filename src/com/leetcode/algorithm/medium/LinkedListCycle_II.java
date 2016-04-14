package com.leetcode.algorithm.medium;

/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?
*/

/*
 * Linked List Cycle第二题
 * 本题除了要判断是否有环之外，还要找出环的开头
 * 也是很经典的题目，经典解法是在Linked List Cycle第一题使用快慢指针法找到有环时
 * 把快指针或慢指针移动到head，然后两个指针同时移动，每次移动一步，再次相遇时即为环的起点，理论依据：
 * 		http://blog.csdn.net/thestoryofsnow/article/details/6822576
 * 
 * 代码①，1ms
 */


public class LinkedListCycle_II {
	/*
	 * 代码①
	 */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // 确认有环存在，开始找环的起点的
            if (fast == slow) {
                fast = head; // 把fast重置回head，然后fast和slow同时移动，每次移动一步，直到相遇
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null; // 没有找到环
    }
}
