package com.leetcode.algorithm.easy;

import java.util.ArrayList;

/*
Reverse a singly linked list.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?
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
 * 逆转链表
 * 首先想到的是把链表的val逆转，代码如方法①，2ms，4.48% ，ORZ。。。
 * 
 * 方法②，回溯iterative solution，0ms
 * 
 * 方法③，递归recursive solution, 1ms, 5.35%
 */

public class ReverseLinkedList {
	//方法①
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ArrayList<Integer> al = new ArrayList<Integer>();
        ListNode point = head;
        al.add(point.val);
        while (point.next != null) {
            point = point.next;
            al.add(point.val);
        }
        point = head;
        for (int i = al.size() - 1; i >= 0; i--) {
            point.val = al.get(i);
            point = point.next;
        }
        return head;
    }
    
    /*
     *   方法②
     *   定义一个next指向head的原next，定义一个newHead作为新的head，记录head的原previous
     *   加上原来的head，可以理解成一共3个指针
     *   每一次循环，next移动到head的下一结点，head指向前一结点，也就是newHead，然后newHead指向head的位置，head移动到next，也就是下一结点的位置
     *   最后，head为null时，链表遍历完毕，此时的newHead指向了原链表的尾巴，也就是逆战后的链表的表头，return 表头。
     */ 
    public ListNode reverseList_2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = null;
        ListNode newHead = null;
        while (head != null) {
            next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
    /*
     *   方法③
     */ 
    public ListNode reverseList_3(ListNode head) {
        if (head == null || head.next == null) return head;
        //记录当前链表头的next
        ListNode next = head.next;
        //递归，逆转除了链表头的链表,返回新的链表头newHead
        ListNode newHead = reverseList(next);
        //当前链表头的next指向原链表头head，也就是现在的链表尾
        next.next = head;
        head.next = null;
        return newHead;
    }
    
    
}
