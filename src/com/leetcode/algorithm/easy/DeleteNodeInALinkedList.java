package com.leetcode.algorithm.easy;

/*
Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, 

the linked list should become 1 -> 2 -> 4 after calling your function.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class DeleteNodeInALinkedList {
    public static void deleteNode(ListNode node) {
    	if (node == null)
    		return;
    	else {
    		/*因为是单链表，没有办法取到要删除的结点的前一个结点。
    		 *所以，把思维逆转过来，把要删除的结点的后一个结点的value提到前一个结点，改成删除后面那个结点。*/
    		node.val = node.next.val;
    		node.next = node.next.next;
    	}
    }
    public static void main(String[] args) {
    	ListNode node1 = new ListNode(1);
    	ListNode node2 = new ListNode(2);
    	ListNode node3 = new ListNode(3);
    	ListNode node4 = new ListNode(4);
    	node1.next = node2;
    	node2.next = node3;
    	node3.next = node4;
    	node4.next = null;
    	
    	ListNode node = node1;
    	while (node != null) {
    		if (node.next != null)
    			System.out.print(node.val + " -> ");
    		else
    			System.out.println(node.val);
    		node = node.next;
    	}
    	
    	deleteNode(node3);
    	
    	node = node1;
    	while (node != null) {
    		if (node.next != null)
    			System.out.print(node.val + " -> ");
    		else
    			System.out.print(node.val);
    		node = node.next;
    	}
	}
}


