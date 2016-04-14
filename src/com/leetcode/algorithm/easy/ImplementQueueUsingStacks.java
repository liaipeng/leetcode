package com.leetcode.algorithm.easy;

import java.util.Stack;

/*
Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, 
and is empty operations are valid.

Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), 
as long as you use only standard operations of a stack.

You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
*/

/*
 * 使用栈来实现队列，吃饱了撑的。
 * 代码①，111ms，28.62%
 * 
 * 代码②，107ms，45%。把代码①中的push改为: （也就是不用数组存放临时数据，改用临时栈，效率比用数组高一点）
 *  Stack<Integer> temp = new Stack<Integer>();
    while(!stack.empty()){
        temp.push(stack.pop());
    }
    stack.push(x);
    while(!temp.empty()){
        queue.push(temp.pop());
    }
 */

public class ImplementQueueUsingStacks {
    private Stack<Integer> stack = new Stack<Integer>();
    
    /*
     * 代码①
     * 修改stack的push方法，每次push先把栈内的元素全部取出存入数组，把x放进去后，把元素反序放回栈内。
     * pop、peak和empty不变。
     */
    // Push element x to the back of queue.
    public void push(int x) {
        if (stack.isEmpty()) stack.push(x);
        else {
            int[] array = new int[stack.size()];
            int i = 0;
            //先把栈内所有数取出来
            while (!stack.isEmpty()) {
            	array[i++] = stack.pop();
            }
            //放x到栈底
            stack.push(x);
            //重新放回刚才取出来的数，因为栈是先进后出，所以放回去的时候要反着放
            for (i = array.length - 1; i >= 0; --i)
            	stack.push(array[i]);
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
    	stack.pop();
    }

    // Get the front element.
    public int peek() {
        return stack.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
    	ImplementQueueUsingStacks queen = new ImplementQueueUsingStacks();
    	queen.push(1);
    	
//    	System.out.println(stack.size());
//    	
//    	int peakNum = queen.peek();
//    	System.out.println(peakNum);
    	
    	queen.pop();
    	System.out.println(queen.empty());
    	
//    	peakNum = queen.peek();
//    	System.out.println(peakNum);
	}
}
