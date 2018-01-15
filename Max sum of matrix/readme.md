## Max sum of matrix
### Hackarank challenge from PayPal

### Question
You are given a matrix of M x N dimensions. Each entry will contain an integer that can either be positive or negative. 

You are allowed to do a single operation infinite times on the matrix. The operation involves selecting 2 unique rows 
in 1 go and multiply all elements by -1. 

The objective is to get the maximum possible sum of the transformed matrix through apply as many operations as you want. 
The sum of the matrix is done by summing all M x N entries. 

### Solution discussion
The approach is not to get an understanding of the specific sequence of operations we need to apply. 

Instead, consider a matrix as such: [-2 -1 2 3 4]. The logical method would be to invert the signs of the negatives, 
by multiplying them by -1. We come to the conclusion that if the single row matrix has even negative numbers, we can
apply as many operations as we want to change all negative numbers to positves. The result is a fully positive row matrix
to which we can sum up all entries directly.

If the matrix contains odd number of negatives, such as [-3 -2 -1 0 1 2 3 4], we will choose to invert the signs of the -3 and -2
and leave the -1 alone. We conclude that the negative number we leave alone is the number that has the lowest absolute value among 
all negative numbers. We only need to leave 1 number alone as this will then turn the negative list to an even list, thereby allowing 
us to invert all their signs. 

Finally, consider the case of a matrix [-4 -3 -2 1 4]. We may have odd number of negatives but we should also consider the lowest positve number
(which is 1). We have to compare the lowest absolute odd no(|-2| = 2) with 1. Since 1 is lower, we will have to swap the signs of -2 -> 2 and 1 -> -1
to get the maximum sum. 

The problem now is that we are receiving an M x N matrix and not a 1 x N matrix. In order to get a row matrix, we sum up all columns 
in the input matrix. We then apply the logic mentioned above. 

### Compile and run
Compile the main script and all dependencies
``` java
javac Solutions.java -d .
```

Run the Solutions script in the package
``` java
cat input.txt | java Solutions
```

### Testing
We use JUnit testing framework for this problem

Compile the test class and the main script using
``` java
javac -cp junit-4.10.jar *.java
```

Execute JUnit test using (in windows syntax)
``` java
java -cp ".;./junit-4.10.jar" org.junit.runner.JUnitCore testSolution
```

### Complexity
Time complexity 	: O(M*N), M is the rows and N is the columns
Space Complexity 	: O(N) because we created an array of size N to store the sum vector of all columns