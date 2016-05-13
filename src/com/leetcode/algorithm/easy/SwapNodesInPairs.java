package com.leetcode.algorithm.easy;

import com.leetcode.algorithm.easy.ListNode;

/*
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
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
 * 一对一对交换链表的节点，要求只能使用O(1)的空间，且不能修改节点的值
 * 
 * Discuss:
 * 代码①，0ms，用递归的方法，虽然代码很简洁，但是实际上是用了O(n)的空间，不符合题目要求。
 * 
 * 代码②，0ms，不用递归的方法，可以仅适用O(1)的空间解题。假表头的运用很巧妙，要多学习。
 */

public class SwapNodesInPairs {
	/*
	 * 代码①
	 */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = head.next; // 记录head.next
        head.next = swapPairs(head.next.next); // head.next指向swapPair后的head.next.next，递归求解
        node.next = head; // 原head.next指向head，也就是交换了第一个和第二个节点
        return node; // 第二个节点变为表头
    }
    
    /*
     * 代码②
     */
    public ListNode swapPairs_2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1); // 假表头
        dummy.next = head;
        ListNode current = dummy;
        // 假设链表是 -1 -> 1 -> 2 -> 3 -> 4 -> NULL
        // while循环就是交换过程，每3个节点为一个交换过程
        // 循环第一轮：调整 -1 -> 1 -> 2 这一段，交换后结果为 -1 -> 2 -> 1
        // 循环第二轮：调整1 -> 3 -> 4 这一段，交换后结果为 1 -> 4 -> 3。所以，假表头的运用至关重要
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;
            first.next = second.next;
            second.next = first;
            current.next = second;
            current = current.next.next;
        }
        return dummy.next;
    }
}
