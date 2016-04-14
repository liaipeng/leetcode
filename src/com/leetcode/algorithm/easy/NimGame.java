package com.leetcode.algorithm.easy;

/* You are playing the following Nim Game with your friend: 
 * There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. 
 * The one who removes the last stone will be the winner. You will take the first turn to remove the stones.
 * Both of you are very clever and have optimal strategies for the game. 
 * Write a function to determine whether you can win the game given the number of stones in the heap.
 * For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, 
 * the last stone will always be removed by your friend.
 */
/* Hint:
 * If there are 5 stones in the heap, could you figure out a way to remove the stones such that you will always be the winner?
 */
/*
令石头个数为K，从K=1开始考虑。每次均为A先取。

前三种情况：K=1，2，3的时候，A一次取完，故A胜。

K=4时，A无论取多少，取完之后K都在1-3之间，落回到前三种情况，此时相当于K=1~3而B先取的情况，故B一定胜。

K=5时，A只要取完之后，令K的值为【若A先取则B一定胜】的情况下的值，在此例中K为4，则此时的情况就相当于K=4且B先取，则A一定获胜。

同理，K=6时，令A取完之后K=4；K=7时，令A取完之后K=4，则A一定能获胜。

当K=8时，A无论怎样取，都只能使得K落在5、6、7之间，此时相当于，K=5~7且B先取，先取者胜，所以A无论如何无法取胜。

同理，K=12时，A无法使K在取一次后变成8，此时A也无法取胜。

故，每当K=4n（n为正整数）时，A无法取一次后将K变为【若A先取则B一定胜】的情况。此时B如果足够聪明，一定是可以稳操胜券的。
*/
public class NimGame {
    public boolean canWinNim(int n) {
        if (n%4 == 0) {
        	return false;
        }
        else
        	return true;
    }
}
