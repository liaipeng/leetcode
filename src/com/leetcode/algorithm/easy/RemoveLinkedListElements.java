package com.leetcode.algorithm.easy;

/*
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5
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
 * 代码①，2ms，这是我很直观的想法，遍历链表，把非val的值往前提，然后切断后面的结点
 * 
 * 代码②，2ms，递归，代码优雅直观
 * 
 * Discuss:
 * 代码③，2ms，遍历链表，把每个等于val的结点剔除
 */

public class RemoveLinkedListElements {
	/*
	 * 代码①
	 * 想法很直观，但是代码比较冗长
	 */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        // current记录新链表的遍历 nextNode记录旧链表的遍历 preNode记录current的前一结点
        ListNode current = head, nextNode = head, preNode = head;
        while (nextNode != null) {
            if (nextNode.val == val) {
                nextNode = nextNode.next;
                continue;
            }
            if (nextNode != null) {
                current.val = nextNode.val;
                nextNode = nextNode.next;
                preNode = current;
                current = current.next;
            }
        }
        preNode.next = null;
        return head.val == val ? head.next : head;
    }
    
    /*
     * 代码②
     */
    public ListNode removeElements_2(ListNode head, int val) {
        if (head == null) return head;
        head.next = removeElements_2(head.next, val);
        return head.val == val ? removeElements_2(head.next, val) : head;
    }
    
    /*
     * 代码③
     */
    public ListNode removeElements_3(ListNode head, int val) {
        if (head == null) return head;
        ListNode fakeHead = new ListNode(-1); //用fakeHead避免边界测试
        fakeHead.next = head;
        ListNode nextNode = head, current = fakeHead;
        while (nextNode != null) {
        	// 如果遍历到的结点值等于val，current把next指向此结点下一个结点
            if (nextNode.val == val) {
                current.next = nextNode.next;
            // 否则，current移动到下一个结点，等待下一次判断
            } else {
                current = current.next;
            }
            nextNode = nextNode.next;
        }
        return fakeHead.next;
    }
}
