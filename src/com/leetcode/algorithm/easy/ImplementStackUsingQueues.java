package com.leetcode.algorithm.easy;

import java.util.LinkedList;
import java.util.Queue;

/*
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.

Notes:
You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, 
and is empty operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), 
as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
*/

/*
 * 232题 Implement Queue using Stacks 的姐妹篇
 * 思想也是一样的。可以从修改push方法和修改pop、top方法两种角度切入
 * 
 * 本来我是用两个队列来实现的（例如push的时候把queue的元素先放到辅助队列中，push完x再放回queue中）
 * 看了Discuss之后发现用一个队列就能实现了，被232题思维定式了
 * 
 * 代码①，时间跳动特别大，没什么参考价值, 修改push方法
 * 代码②，时间跳动特别大，没什么参考价值，修改pop和top方法
 */

public class ImplementStackUsingQueues {
	/*
	 * 代码①
	 * 只需修改push方法即可
	 * 把x push到队列末尾，然后再把x滚动到队列头
	 */
	private Queue<Integer> queue = new LinkedList<Integer>();
    // Push element x onto stack.
    public void push(int x) {
    	queue.offer(x);
        int size = queue.size();
    	while (size-- != 1) queue.offer(queue.poll());
    }
    // Removes the element on top of the stack.
    public void pop() {
        queue.poll();
    }

    // Get the top element.
    public int top() {
        return queue.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty();
    }
    
    
    
    /*
     * 代码②
     * push还是照样push，只不过取的时候先把其他的拿出来就行了
     * 相当于把最后那个数滚动到队列前面再pop和top
     */
    // Push element x onto stack.
    public void push_2(int x) {
        queue.offer(x);
    }
    // Removes the element on top of the stack.
    public void pop_2() {
        int size = queue.size();
        while (size-- != 1) queue.offer(queue.poll());
        queue.poll();
    }

    // Get the top element.
    public int top_2() {
        int size = queue.size();
        while (size-- != 1) queue.offer(queue.poll());
        int top = queue.peek();
        queue.offer(queue.poll());
        return top;
    }
    // Return whether the stack is empty.
    public boolean empty_2() {
        return queue.isEmpty();
    }
}
