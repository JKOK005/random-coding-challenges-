## Make change for coins
Solution to question 3 of practice problems

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
javac -cp ../junit-4.10.jar ChangeTester.java MakeChange.java
```

Execute the test using
```
java -cp .:../junit-4.10.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore ChangeTester
```

### Explaination
*Time complexity	: O(M*N)*
*Space complexity	: O(M*N)*

** Where M is the target sum and N is the unique no of coins **

The solution uses dynamic programming to solve and is similar to the knapsack problem. 
We start by designing a M*N array where each row represents the allowed weight limit and
columns represents the order of coins that we are allowed to pick up until. 

We start by sorting the coins out in ascending order. We then fill the array up from top-left
to bottom-right. For each entree arr[i][j], we consider the case where we either pick the coin
or do not pick it at all. We can only pick it if the value of the coin does not exceed the target
sum limit. In doing so, the value at that entree follows arr[i - coin_val][j]. If we do not pick, 
the entree then follows arr[i][j -1]. The entree arr[i][j] is then the sum of the results for pick
and no_pick.

We return the result of the last entree (bottom-right) as our answer.