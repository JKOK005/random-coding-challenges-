## Reverse K Nodes 
### Reference can be found in this [link from LeetCode](https://leetcode.com/problems/reverse-nodes-in-k-group/description/)

### Solution discussion
To manage the need for constant space, we use a common param class that is used to store all global variables
We start by recursing till the end of the linked list. We maintain a counter to keep track of the counts of the list length.

We can thereafter obtain Q & R. Q is the quotient of the length / K, where K is the group size that we want and is cast to an integer. 
R is the remainder which can be calculated by: R = Len - Q * K. 

We start by recursing by R nodes. These nodes are at the end tail and will not be reversed since the chain will have a size smaller
than K. Once we have cleared R nodes, we will be at the tail end of the chain which will have to be reversed (and this length is a 
multiple of K). We maintain a pointer at this tail. This pointer will be the head of the newly reversed group. 

Gradually recurse up one level in the stack. In doing so, link the tail with the node behind it for K nodes. At the end of K nodes, we subtract
quotient by 1 and link the current node to the tail pointer. We then shift the tail pointer to the current node, representing the end of a new
chain which we have to recurse. Iterate this process till quotient = 0. Thereafter, return the very last tail node of the last chain as the solution. 

### Edge cases 
Consider the following cases