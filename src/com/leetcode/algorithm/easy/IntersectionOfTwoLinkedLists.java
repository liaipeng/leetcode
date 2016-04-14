package com.leetcode.algorithm.easy;

/*
Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:

A:          a1 → a2
                   	↘
                     c1 → c2 → c3
                   	↗            
B:     b1 → b2 → b3
begin to intersect at node c1.

Notes:
If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

/*
 * 找出两个链表公共部分的开头
 * 只能用O(n)时间和O(1)空间，直接看答案，看到大家的时间复杂度其实都是O(n+m)
 * 
 * Discuss:
 * 代码①，2ms，先获取两链表的长度，然后长的链表先走，直到两链表剩余长度相同
 * 
 * 代码②，2ms，巧妙的把问题转换成判断链表是否存在环
 */

public class IntersectionOfTwoLinkedLists {
	/*
	 * 代码①
	 */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) return null;
        int lenA = length(headA);
        int lenB = length(headB);
        // 因为公共部分肯定是出现在末尾，先过滤前面多出来的结点，循环的时候注意减小长度
        while (lenA > lenB) {
            headA = headA.next;
            --lenA;
        }
        while (lenB > lenA) {
            headB = headB.next;
            --lenB;
        }
        // 这里直接判断节点是否相等，如果是判断节点的val是否相等，在最后一个结点会出现空指针异常
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;        
    }
    public int length(ListNode head) {
        int length = 0;
        while (null != head) {
            head = head.next;
            length++;
        }
        return length;
    } 
    
    /*
     * 代码②
     * 把问题转换成判断链表是否存在环
     */
    public ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) return null;
        ListNode currentA = headA;
        ListNode currentB = headB;
        /* 当链表A和链表B长度相等时：
         *			如果公共片段，while循环必然会找到公共结点
         *			如果不存在公共片段，A和B同时遍历到null，也跳出循环，return null
         * 当链表A和链表B长度不相等时，A和B都会遍历两次：
         * 			第一次遍历后A移动到B表头，B移动到A表头
         * 			因此，第二次遍历时，A和B都必然同时到达表尾，也就会找到公共结点或者同时遍历到null
         * 			或者可以理解成两条链表连接成：A+B和B+A两条。此时两条链表长度相等，若有公共部分肯定在链表末尾
         * 			可以套用当链表A和链表B长度相等的那种情况
         * 
         * 			相当于判断链表是否存在环
         */				
        while (currentA != currentB) {
            currentA = (null == currentA ? headB : currentA.next);
            currentB = (null == currentB ? headA : currentB.next);
        }
        return currentA;
    } 
}
