## Execution
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

