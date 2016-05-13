package com.leetcode.algorithm.easy;

/*
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?
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
 * 判断链表是否回文，只用O(n)时间和O(1)空间完成
 * 用O(n)时间还好说，用O(1)空间实在想不到怎么做
 * 
 * 答案中算法有以下几种：
		1、遍历整个链表，将链表每个节点的值记录在数组中，再判断数组是不是一个回文数组，时间复杂度为O（n），
		但空间复杂度也为O（n），不满足空间复杂度要求。
		
		2、利用栈先进后出的性质，将链表前半段压入栈中，再逐个弹出与链表后半段比较。时间复杂度O（n），
		但仍然需要n/2的栈空间，空间复杂度为O（n）。
		
		3、反转链表法，将链表后半段原地翻转，再将前半段、后半段依次比较，判断是否相等，时间复杂度O（n），空间复杂度为O（1）满足题目要求。
	
	其中，寻找链表中间结点用到了快慢指针法：http://blog.sina.com.cn/s/blog_624ca80801011u6m.html
		原理是：快指针的移动速度是慢指针移动速度的2倍，因此当快指针到达链表尾时，慢指针到达中点。
		程序还要考虑链表结点个数的奇偶数因素，当快指针移动x次后到达表尾（1+2x），说明链表有奇数个结点，直接返回慢指针指向的数据即可。
		如果快指针是倒数第二个结点，说明链表结点个数是偶数，这时可以根据“规则”返回上中位数或下中位数或（上中位数+下中位数）的一半。
 * 
 * Discuss：
 * 代码①，2ms，代码简洁易懂
 * 
 * 代码②，2ms，与代码①相比，把逆转链表的操作放在快慢指针的遍历中，乍一看跟代码①几乎一样，实际上细节还是由差别的
 * 
 * 除了以上2个代码，还可以使用递归，但是递归其实会消耗O(n)的空间
 */

public class PalindromeLinkedList {
	/*
	 * 代码①
	 * 逆转链表的后半部分
	 * 然后两个指针一个从head开始，另一个从尾巴开始，同时往中间结点遍历
	 */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fast = head, slow = head;
        // 用快慢指针法找到中间结点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 其实这边可以不管是不是偶数个结点，slow都往下移动。因为如果是奇数个结点，中间那个是不用比较的
        if (fast.next != null) slow = slow.next;
        // 逆转链表后半部分
        slow = reverse(slow);
        
        while (slow != null) {
            if (head.val != slow.val) return false;
            head = head.next;
            slow = slow.next;
        }
        return true;
    }
    //逆转链表
    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    
    /*
     * 代码②
     * 代码①是逆转链表的后半部分
     * 而代码②是逆转链表的前半部分，然后两半部分的链表分别同时从中间往两边遍历
     */
    public boolean isPalindrome_2(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode firstHalfHead = head;
        ListNode secondHalfHead = head.next;
        ListNode prev = null;
        // head充当快指针，secondHalfHead充当慢指针。快慢指针遍历的同时，把链表前半部分逆转
        while (head.next != null && head.next.next != null) {
            head = head.next.next;
            prev = firstHalfHead;
            // 这边如果替换成 firstHalfHead = firstHalfHead.next; 会有2个test（其中之一[1,4,-1,-1,4,1]）无法通过，不知道是什么原因
            firstHalfHead = secondHalfHead;
            secondHalfHead = secondHalfHead.next;
            // 注意对比代码①和代码②逆转部分的代码是不一样的，代码①是先逆转next指向，然后到下一个结点；这边是先走到下一个结点，再逆转next指向
            firstHalfHead.next = prev;
        }
        // 如果是奇数个结点，中间那个不用判断，firstHalfHead往前走一步（相当于原链表往回走一步）
        if (head.next == null) firstHalfHead = firstHalfHead.next;
        
        while (secondHalfHead != null) {
            if (firstHalfHead.val != secondHalfHead.val) return false;
            firstHalfHead = firstHalfHead.next;
            secondHalfHead = secondHalfHead.next;
        }
        return true;
    }
}
