## Longest valid parenthesis
### Reference can be found in this [link from LeetCode](https://leetcode.com/problems/longest-valid-parentheses/description/)

### Results on submission
Passed: 226 / 226 test cases

### Solution discussion
This might seem like an innocent problem but its harder that you think. The problem is in designing an algorithm that accounts for 
continuous sequences of brackets ('()()()()' -> maxLength = 8) and discointinuous sequences of brackets ('()(()()' -> maxLength = 4)

We first initialize an boolean array of size N. We then use a stack that registers the index of all left brackets. 
Each time we encounter a right bracket, we pop the top of the stack for the index of the last left bracket. We then set 
the index of our array, for the current right bracket and the popped index for left bracket, to be TRUE. If the stack is empty,
we simply ignore this process. 

Doing so helps us initialize a linear map that registers all closed brackets. We then scan the map for the longest continuous sequence
of TRUE. This number is returned as the solution for the problem. 

### Compile and run
Compile the main script and all dependencies
``` java
javac Solutions.java -d .
```

Run the Solutions script in the package
``` java
java Solutions
```

### Testing
We use JUnit testing framework for this problem

Compile the test class and the main script using
``` java
javac -cp junit-4.10.jar *.java
```

Execute JUnit test using (in windows syntax)
``` java
java -cp ".;./junit-4.10.jar" org.junit.runner.JUnitCore TestLongestParenthesis
```

### Complexity
Time complexity 	: O(N), N is the length of input string
Space Complexity 	: O(N)