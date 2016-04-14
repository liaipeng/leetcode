package com.leetcode.algorithm.easy;

/*
Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.
*/

/*
 * 计算两个矩形总共占据的面积，就是两个矩形的面积减去重叠的面积，想了一下，情况好多种，太烦了，直接看答案了。。。
 * 虽然做好了准备，但是看了答案还是觉得自己太傻逼了
 * 
 * Discuss:
 * 代码①，4ms，先计算2个矩形的面积，然后减去重叠的面积
 * 
 * 代码②，4ms，优化代码①
 */
public class RectangleArea {
	/*
	 * 代码①
	 */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int twoRecArea = (C - A) * (D - B) + (G - E) * (H - F);
        // overlap
        int left = Math.max(A, E);
        int right = Math.min(C, G);
        int top = Math.min(D, H);
        int bottom = Math.max(B, F);
        return (left < right && bottom < top) ? (twoRecArea - (right - left) * (top - bottom)) : (twoRecArea);
    }
    
    /*
     * 代码②
     * 代码几乎与代码①一样，但是在计算right和top时，巧妙的利用max，避免最后还要判断两个矩形是否重叠
     * 判断left和right，top和bottom哪个大，把大的值赋给right，top，所以假如没有重叠，right-left或top-bottom中有一个会为0
     */
    public int computeArea_2(int A, int B, int C, int D, int E, int F, int G, int H) {
        int left = Math.max(A, E);
        int right = Math.max(Math.min(C, G), left);
        int bottom = Math.max(B, F);
        int top = Math.max(Math.min(D, H), bottom);
        return (C - A) * (D - B) + (G - E) * (H - F) - (right - left) * (top - bottom);
    }
}
