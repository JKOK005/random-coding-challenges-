## Leet code problem: [add two numbers](https://leetcode.com/problems/add-two-numbers/description/)

### Description
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

### Solution
Main solution found in Solution.java file with supporting packages

Idea is to traverse both lists sequentially. We create a new node with value = sum of nodes from both lists.
Pay attention to carry over values when the sum of 2 nodes exceed 10. 

If both linked lists are of equal length, then the addition process is simple. 

If lengths are unequal, we would need to perform additional add ons for the longer list if there is a carry over value. Else,
we simply link the newly constructed link list with the remainder of the longer list.

### Evaluation
Solution accepted to Leet code with 100% test case passes.
