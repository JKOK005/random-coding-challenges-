## LRU cache implementation
### Reference can be found in this [link from LeetCode](https://leetcode.com/problems/lru-cache/description/)

### Solution discussion
The cache was implemented using a hash set that maps a key to a NodeWrapper class. The approach is descriped [here](https://www.programcreek.com/2013/03/leetcode-lru-cache-java/).

As an add on, I included generic templating for key-value pairs for the cache. The key-values pairs that were tested out are
- Int -> Int
- Int -> String
- String -> Int
- String -> String

### Compile and run
Compile the main script and all dependencies
``` java
javac *.java -d .
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
java -cp ".;./junit-4.10.jar" org.junit.runner.JUnitCore TestLRUCache
```

### Complexity
Time complexity of 	
- Put : O(1), Addition of Key is done simply by inserting a new entry to the hash map or by invalidating an old entry if capacity is exceeded.
- Get : O(1), we simply search for the key in the hash map and retrieve the corresponding value in the NodeWrapper object
- Renewal of entry : O(1), the double linked list structure allows us to shift latest entries to the front of the list in constant time
- Deletion of old entry : O(1), simply remove the key and the NodeWrapper object pointed. In this process, ensure that the linked list is amended. 

Space Complexity 	: O(N), N is the capacity of the cache. 