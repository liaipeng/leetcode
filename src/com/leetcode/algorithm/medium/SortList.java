package com.leetcode.algorithm.medium;


/*
Sort a linked list in O(n log n) time using constant space complexity.
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
 * 用O(n log n)的时间复杂度和O(1)的空间复杂度对一个链表进行排列
 * 
 * Discuss：
 * 
 * 题目没有想象中简单。。
 * 以下是思路，就是先两两合并相邻节点，进而以合并好的小链表为单位，再两两合并，以此类推，直到2的level次方大于n停止
 * 	Assume the length of list is n, which is unknown at the beginning.
		
		  level log(n)         [ A0, A2, A3, ... , An-2, An-1 ]
		  ...
		  level 2        [ A0, A1, A2,  A3 ], [A4, A5, A6, A7] ,... , [ ..., An-2, An-1 ]
		  level 1        [ A0, A1], [A2,  A3 ], [A4, A5], [A6, A7] ,... , [An-2, An-1]
		  level 0        [ A0 ], [ A1] , [ A2 ],  [ A3 ],... , [ An-2 ], [ An-1 ]
		At each level, each group only contains at maximum 2^level elements. Merge-sort theses groups pair by pair. 
		Then level ++. Stop until 2^level > n. Assume the original input is :
		  level 0        5, 3, 6, 1, 4, 2, 7
		  
		After level 0, we got the length of the list and the list become:
		  level 1        3, 5,   1, 6,    2, 4,    7
		  
		Now each group contains 2 elements. After level 1, the list become:
		  level 2        1, 3, 5, 6,    2, 4, 7
		  
		Now each group contains 2^2 = 4 elements. After level 2, the list become:
		  level 3        1, 2, 3, 4, 5, 6, 7
		  
		Now, 2^3 > 7, stop.
		
		Time complexity: In each level, each node is visited by at maximum twice. 
		And there are log(n) level. Thus the time complexity is O(2n* log n ) => O( n* log n )
 * 
 * 代码①，7ms，采用以上思想，将虽然时间复杂度符合要求，但是因为用到了递归，实际上空间复杂度是不满足的~，需要O(logn)，
 * 但是代码思路清晰，很直观易懂
 * 
 * 代码②，18ms，为了符合题目的要求，使用常数的空间，本代码不使用递归，所以相比代码①显得很冗长晦涩，时间效率上也更不理想
 */

public class SortList {
	/*
	 * 代码①
	 */
	// 合并链表n1和n2，合并的结果按照升序排列
    private ListNode merge(ListNode n1, ListNode n2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        // 对比两条链表，把小的那个节点摘下来，接到cur后面
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                cur.next = n1;
                n1 = n1.next;
            } else {
                cur.next = n2;
                n2 = n2.next;
            }
            cur = cur.next;
        }
        // 把剩下的节点连到cur后面
        cur.next = (n1 != null) ? n1 : n2;
        
        return dummyHead.next;
    }
    // 递归对中拆分原链表，然后调用merge方法合并两部分的链表，回溯的过程就是题目下方的思路
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null, slow = head, fast = head;
        // 对中拆分链表
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        // 分别sort两条链表
        ListNode n1 = sortList(head);
        ListNode n2 = sortList(slow);
        // 合并两条链表
        return merge(n1, n2);
    }
    
    
    /*
     * 代码②
     */
    // 辅助类：记录合并好的链表段的头和尾，就是把合并好的链表段当做一个整体
    class ListNodePair {
        ListNode head;
        ListNode tail;
        ListNodePair(ListNode h, ListNode t) { head = h; tail = t; }
    }
    // 合并两个链表段
    private ListNodePair merge_2(ListNode first, ListNode second) {
        ListNode fakeHead = new ListNode(-1);
        ListNode tail = fakeHead;
        
        while (first != null && second != null) {
            if (first.val < second.val) {
                tail.next = first;
                first = first.next;
            } else {
                tail.next = second;
                second = second.next;
            }
            tail = tail.next;
        }
        
        tail.next = (first != null) ? first : second;
        while (tail.next != null) tail = tail.next;
        return new ListNodePair(fakeHead.next, tail);
    }
    // 根据lenOfList的长度来截取链表段
    private ListNode getListTail(ListNode head, int lenOfList) {
        while (lenOfList > 1 && head.next != null) {
            head = head.next;
            lenOfList--;
        }
        return head;
    }
    // 按照子链表长度为lenOfList，进行两两合并
    private ListNode mergeLayer(ListNode head, int lenOfList) {
        ListNode fakeHead = new ListNode(-1);
        ListNode mergeTail = fakeHead; // 串起这一层排好序的子链表
        
        while (head != null) {
            ListNode first = head;
            ListNode firstTail = getListTail(first, lenOfList); // 得到第一条链表
            ListNode second = firstTail.next;
            if (second == null) { // 加入second是null，说明剩下的只有一条资料表了，直接串起
                mergeTail.next = head;
                break;
            }
            ListNode secondTail = getListTail(second, lenOfList); // 得到第二条链表
            head = secondTail.next; // 摘掉前面的节点
            firstTail.next = null;
            secondTail.next = null;
            ListNodePair pair = merge_2(first, second); // 合并两条子链表
            
            mergeTail.next = pair.head; // 串起合并后的链表
            mergeTail = pair.tail; // 移动到表尾
        }
        
        return fakeHead.next;
    }
    
    public ListNode sortList_2(ListNode head) {
        if (head == null || head.next == null) return head;
        int len = 0;
        for (ListNode runner = head; runner != null; runner = runner.next) len++; // 计算链表的长度
        int lenOfList = 1; // 要截取的子链表段的长度，初始为1
        while (lenOfList < len) { // 如果lenOfList大于len，表示已经合并好了整条链表
            head = mergeLayer(head, lenOfList); // 按照子链表长度为lenOfList，进行两两合并
            lenOfList <<= 1; // 呈2倍2倍增长
        }
        return head;
    }
}
