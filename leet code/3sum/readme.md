## 3 sum coding challenge - Leet code
Score: Passed 312 / 313 test cases

Please refere to [link](https://leetcode.com/problems/3sum/description/)

### Approach 
First sort the given array. Then, maintain 2 pointers A,B. A will start at index 0 and comb the 
entire array. B will always be 1 index ahead of A. At each instance of the loop, the pointers represent
2 choosen numbers. We will then need to check if the 3rd number is present and equal to -(A+B).

We use a hash map for this. The array is first stored in a hash map for us to reference along the loop. 

### Complexity analysis
Time	: O(N^2)
Space	: O(N)

### Compile
Compile the code:
```
javac Solutions.java
```

Then run with input:
```
cat input.txt | java Solutions
```