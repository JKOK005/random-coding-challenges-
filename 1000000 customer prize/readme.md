##Redmart online coding challenge
### 1,000,000th Customer prize
### Full details of the challenge can be found in this [link](http://geeks.redmart.com/2015/10/26/1000000th-customer-prize-another-programming-challenge/)

### Compile and run
First compile the main function with its dependencies using
'''
javac CustomerPrize.java
'''

Then run the script with the input.txt piped in. 
'''
cat input.txt | java CustomerPrize
'''

Optionally, if the programme complains of the lack of heap space to run, add a flag
'''
cat input.txt | java -Xmg<RAM>g CustomerPrize
'''
replacing <RAM> with the amount of ram you wish to use. For my application, <RAM> = 5.


### Explaination of solution
This problem is similar to the knapsack problem which can be solved using dynamic programming.

We first parse each line of *input.txt* into an **Item** class. If the item's volume does not 
exceed the allowable tote's volume, we store that item in an ArrayList. If it exceeds, we reject that item
as a solution. 

We then construct an (M +1) * (N +1) matrix, with M = total volume of the tote and N = total size of ArrayList of items. The matrix is padded
with zeros. The rows of the matrix will represent the allowable volume limit, ranging from 1 - M and the colums, from 1 - N +1, will correspond
to a single candidate item. 

The columns correspond to all candidate items sorted by their weight. Hence, column (1) will correspond to the lightest
item and column (N +1) will correspond to the heaviest item. Sorting is done to facilitate easy collection of items once we have executed the 
bulk of the algorithm and should not be the bottle neck in running the code. We perform sorting on the weight property using the **WeightComparator**
class developed. 

Once the matrix is initialized, we fill it using a bottom up approach. Please refer to this [link](http://www.es.ele.tue.nl/education/5MC10/Solutions/knapsack.pdf)
for an understanding of the solution to the Knapsack problem.

Collection of the item is easy and done using a greedy approach. We start with the last row of the matrix, right most column. 
This entree represents the maximum price value that one can collect, given a full allowable volume and bring able to select all items. 
We sweep left until the value of the price decreases. In such a case, we then encounter the "lightest" item that gives us the maximum
price value that we can attain. We add that item into our Basket of items and subtract the item's volume from our initial volume.
We then refer to the suitable row for the remaining volume and repeat this process until:
	- We encounter an entree with price value of 0
	- We have considered all items in the candidate list. 

We not have a basket of all items we need! We sum up their ID to get the result XXXXXX. Add a __@redmart.com__ and send your mail.


### Dependencies
1. CustomerPrize.java 		- Main function which executes algorithm
2. Adapter.java 			- Adapter class to parse string to item class
3. Items.java 				- Item class with relevant method and attributes
4. WeightComparator.java 	- Custom comparator class for comparing Item weights
5. Printer.java 			- Utility class for printing data


### Closing 
This programme was pretty space consuming. The space complexity was ~O(M *N), a trade off to ensure that the time complexity is ~O(M *N) at most. 
Given M = 45*35*30 and N = 20,000 (max), with each matrix entree being an integer, the lowest memory required is 3.78 Gb! I had to allocate 5Gb for
this programme, which is quite a large amount to be used on Laptops. I often ran into lack of Heap memory errors thrown during runtime. 

Hence, if anyone has a suggestion on how I can better improve the memory performace, please feel free to PM me of leave a comment in this Repo. 

Thanks!









