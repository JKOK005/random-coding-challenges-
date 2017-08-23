## Make change for coins
Solution to question 2 of practice problems

### Execution
To compile:
```
javac Permutation.java
```
Then execute the code using
```
java Permutation
```

## Unit testing
Compile the source file with jUnit using
```
javac -cp ../junit-4.10.jar TreeTester.java MinHeightTree.java Node.java
```

Execute the test using
```
java -cp .:../junit-4.10.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore TreeTester
```

### Explaination
*Time complexity	: O(N)*
*Space complexity	: O(N)*

** Where N is the no of entrees in the array **

A balanced binary tree is formed by adapting the logic of binary search on a sorted array.
The middle element of the array is selected and forms the root node. We perform recursive calls
to link the root's child nodes with the middle of the left and right block of the array. 