## Random challenge 
### Task: Given a set of numbers, get the smallest possible value that is large than a given input

### Approach
Solution would be to use dynamic programming. The algorithm is defined as such
1. Sort the values in ascending order

2. Construct a matrix (M +1) x (N +1) where
	a. M being the input value that the combination has to be larger or equal to
	b. N being the size of the set of numbers we choose from

3. Each entree in the matrix represents the minimum possible value that can be attained, given that
we select only numbers that are to the left of the chosen column.

4. Populate the matrix and get your final answer as the last entree.


### Description
Imagine we have a set of numbers {2,4,5} and we want to find the minimum sum of these numbers above 11. 

We construct our matrix as such:

|	0 	| 	2 	| 	 4 	| 	5 	|
|-------|-------|-------|-------|
|  >= 0	| 	2 	| 	2 	| 	2 	|
|  >= 1	| 	2 	| 	2 	| 	2 	|
|  >= 2	| 	2 	| 	2 	| 	2 	|
|  ...	|  ... 	|  ... 	|  ... 	|
| >= 10 |  	10	| 	10 	| 	10 	|
| >= 11 | 	12 	| 	12 	| 	11	|

Hence, the optimal value is 11.


### Compile
Run 
```java
javac GetSmallest.java
```

Then feed input.txt via
```
cat input.txt | java GetSmallest
```