## Make change for coins
Solution to question 1 of practice problems

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
javac -cp ../junit-4.10.jar PermutationTester.java Permutation.java
```

Execute the test using
```
java -cp .:../junit-4.10.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore PermutationTester
```

### Explaination
*Time complexity	: O(M + N)*
*Space complexity	: O(M + N)*

** Where M, N are the length of the 2 strings**

We can check for a permutation if the exact no of characters matches for each string. We start by looking
at the lengths. If there is a length mismatch then the strings are not permutations of each other. Else, we 
take the first string and store its elements in a hash map. Each new character will map to a value of 1 and 
each repeated character will increment the counter's value. 

We then match the characters in the 2nd string to the hash map. Each time we encounter a hit, we decrement the 
value by 1. If the value is < 0 or there is a miss, then we will return a false. Else, we return a true to say
that the 2 strings are a permutation of each other. 

** Alternative solution **
We can sort the strings based on char Ascii values and compare them character wise. This reduces the space
complexity to O(1) but increases the time complexity to O(N*logN + M*logM) for the sorting process.