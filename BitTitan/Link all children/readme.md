## BitTitan coding challenge - Link all siblings

### Explanation
We start by analyzing the root layer with only a single node. In this case, the root's Right will be null. 

We then move deeper by 1 layer, where We expand the roots children into a vector and link each child to his immediate neighbor in the vector till the last child. We then recursively iterate through this process, going 1 additional layer deeper till we encounter a case where the vector is null. This means that there are no more children to link and the algorithm terminates. 

### Complexity analysis
For N nodes in the tree
Time complexity: O(N) 

Space complexity: O(N) - It depends on the structure of the tree. The max space taken depends on the widest width of a given layer. Since this isn't a binary tree, we can't be sure which is the widest width but we can bound the space usage by O(N).

### Compile and execution
First compile the Solution.java class using

```
javac Solution.java
```

Then execute the code using

```
java Solution
```

To compile the Test.java test, run

```
javac Test.java
```

Then execute using

```
java Test
```

You should see the expected output:

```
0 ->
15 -> 16 -> 17 -> 18 -> 19 -> 20 -> 21 -> 22 ->
0 -> 1 -> 2 -> 200 -> 201 -> 202 -> 400 -> 401 -> 402 -> 600 -> 601 -> 602 ->
```

Which prints a tree of 3 layers deep, with only alternate nodes on the 2nd layer having children. 