package com.leetcode.algorithm.easy;

import java.util.HashSet;
import java.util.Set;

/*
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
*/

/*
 * 代码①，8ms，虽然是浪费了点空间，但是还是比较好理解的吧 - -
 * 
 * Discuss:
 * 代码②，8ms，简化代码①cube的部分，牛逼
 * 
 * 代码③，6ms，把遍历写成方法，也很牛逼，但是没有代码②牛逼
 */

public class ValidSudoku {
	/*
	 * 代码①
	 */
    public boolean isValidSudoku(char[][] board) {
        Set<Character> col = new HashSet<Character>();
        Set<Character> row = new HashSet<Character>();
        Set<Character> cube_1 = new HashSet<Character>();
        Set<Character> cube_2 = new HashSet<Character>();
        Set<Character> cube_3 = new HashSet<Character>();
        for (int i = 0; i < 9; ++i) {
        	row.clear();
        	col.clear();
        	if (i % 3 == 0) {
        		cube_1.clear();
        		cube_2.clear();
        		cube_3.clear();
        	}
        	for (int j = 0; j < 9; ++j) {
        	    // judge row
        		if (board[i][j] != '.' && !row.add(board[i][j])) return false;
        		// judge col
        		if (board[j][i] != '.' && !col.add(board[j][i])) return false;
        		// judge cube
        		if (j < 3 && board[i][j] != '.' && !cube_1.add(board[i][j])) return false;       			
        		if (j >= 3 && j < 6 && board[i][j] != '.' && !cube_2.add(board[i][j])) return false;         			
        		if (j >= 6 && board[i][j] != '.' && !cube_3.add(board[i][j])) return false; 
        	}
        }
        return true;
    }
    
    /*
     * 代码②
     * 注意学习cube部分i和j的使用
     */
    public boolean isValidSudoku_2(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            Set<Character> col = new HashSet<Character>();
            Set<Character> row = new HashSet<Character>();
            Set<Character> cube = new HashSet<Character>();      
        	for (int j = 0; j < 9; ++j) {
        	    // judge row
        		if (board[i][j] != '.' && !row.add(board[i][j])) return false;
        		// judge col
        		if (board[j][i] != '.' && !col.add(board[j][i])) return false;
        		// judge cube
        		// 用i控制每个cuba间的移动，从左到右，从上到下(rowIndex, colIndex)选择cube的起始坐标
        		int rowIndex = 3 * (i / 3);
        		int colIndex = 3 * (i % 3);
        		// 用j控制每个cube内的遍历：j从0->8，那么j/3从0->2，j/3每变化一次，j%3从0->2 一次，所以遍历完整个cube
        		if (board[rowIndex + j / 3][colIndex + j % 3] != '.' && !cube.add(board[rowIndex + j / 3][colIndex + j % 3])) return false;
        	}
        }
        return true;
    }
    
    /*
     * 代码③
     */
    public boolean isValidSudoku_3(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            // judge row
            if (!isParticallyValid(board, i, 0, i, 8)) return false;
            // judge col
            if (!isParticallyValid(board, 0, i, 8, i)) return false;
        }
        // judge cube
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (!isParticallyValid(board, i * 3, j * 3, i * 3 + 2, j * 3 + 2)) return false;
            }  
        }
        return true;
    }
    private boolean isParticallyValid(char[][] board, int x1, int y1, int x2, int y2) {
        Set<Character> set = new HashSet<Character>();
        for (int i = x1; i <= x2; ++i) {
            for (int j = y1; j <= y2; ++j) {
        		if (board[i][j] != '.' && !set.add(board[i][j])) return false;
            }
        }
        return true;
    }
}
