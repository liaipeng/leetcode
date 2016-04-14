package com.leetcode.algorithm.easy;

import java.util.Stack;

/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
*/

/*
 * 设计一个栈，这个栈除了可以实现基本的push、pop、和top操作外，还可以以O(1)时间取得栈中最小元素
 * 
 * 题目不够严谨，没有说如果栈为空要返回什么，所以默认当成除了push操作外不可能出现栈为空的情况
 * 
 * 代码①，9ms，常规解法，使用两条栈实现，并用一个私有变量minNum记录栈中最小值
 * 
 * Discuss：
 * 代码②，10ms，只用一条栈实现，栈中存储的是x和min的差值，很巧妙的解法
 * 
 * 代码③，9ms，只用一条栈实现，每次min值改变的时候，在新的min值入栈之前，把旧的min值入栈。也就是说每个新的min值栈底下一层都有一个旧的min值
 */

public class MinStack {
	/*
	 * 代码①
	 */
    private Stack<Integer> stack = new Stack<Integer>();
    private int minNum;
    // push的时候判断x的值是否比minNum小
    public void push(int x) {
        minNum = stack.isEmpty() ? x : Math.min(minNum, x);
        stack.push(x);
    }
    // 若pop的值是最小值minNum且栈不为空，那么要重新找出栈中剩余元素的最小值，用辅助栈来实现
    public void pop() {
        if (stack.pop() == minNum && !stack.isEmpty()) {
            minNum = stack.peek();
            Stack<Integer> assist = new Stack<Integer>();
            while (!stack.isEmpty()) {
                int tempNum = stack.pop();
                if (minNum > tempNum) minNum = tempNum;
                assist.push(tempNum);
            }
            while (!assist.isEmpty()) {
                stack.push(assist.pop());
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minNum; // 直接返回私有变量minNum
    }
   
    
    
    /*
     * 代码②
     * 栈中存储x与min2的差值，所以栈和min都用long或者Long，否则会超出int的范围
     * 栈中所有负值都是曾经或现在的最小值
     */
    private Stack<Long> stack2 = new Stack<Long>(); // 栈的类型是Long
    long min2; // min的类型也是long
    
    public void push_2(int x) {
        if (stack2.isEmpty()) min2 = x;
        stack2.push(x - min2);
        if (x - min2 < 0) min2 = x; // 如果x - min2 < 0 说明x比min2小
    }

    public void pop_2() {
        long diff = stack2.pop();
        // 如果diff小于0，说明pop掉的是最小值，所以让min2 = min2 - diff，重新获取最小值
        if (diff < 0) min2 = min2 - diff; 
    }

    public int top_2() {
        long diff = stack2.peek();
        // 因为栈中存的是差值，所以取的时候，如果diff小于0，返回最小值；如果不为0，返回最小值 + 差值
        return diff < 0 ? (int)min2 : (int)(min2 + diff); 
    }

    public int getMin_2() {
        return (int)min2;
    }
    
    
    
    /*
     * 代码③
     * 只用一条栈实现，每次min值改变的时候，在新的min值入栈之前，把旧的min值入栈。也就是说每个新的min值栈底下一层都有一个旧的min值
     */
    private Stack<Integer> stack3 = new Stack<Integer>();
    int min3;
    
    public void push_3(int x) {
        if (stack3.isEmpty()) min3 = x;
        // 核心部分：如果x <= min3，把旧的min3先入栈，然后min3变为x；之后再让x入栈
        // 与代码②不同，这边必须用<=不能用<，因为有可能出现一个num是等于min3的，如果此时条件是x<min3，那么旧min3（=min3）不会入栈
        // 此时在调用pop_3的时候会出错
        if (x <= min3) {
            stack3.push(min3);
            min3 = x;
        }
        stack3.push(x);
    }

    public void pop_3() {
    	// pop出来的数如果等于最小值，那么把该数底下的那个数也pop出来（也就是min3恢复成旧的min3值）
        int num = stack3.pop();
        if (num == min3) {
            min3 = stack3.pop();
        }
    }

    public int top_3() {
        return stack3.peek();
    }

    public int getMin_3() {
        return min3;
    }
}
