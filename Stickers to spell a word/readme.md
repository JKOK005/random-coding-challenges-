## Challenge: Stickers to spell a word

Description of the challenge can be found in this [link](https://leetcode.com/problems/stickers-to-spell-word/description/)

Results: Incomplete - Solution timed out. Need to optimize code further

### Solution 
Our approach uses memoization with recursion. Each time we select a sticker, we are only concerned with the remaining letters we need to fill up from the target
word. For a given substring of the target word, we can store the results in a hash map which maps string -> min no of stickers. The next time we encounter the same
substring, we can easily retrieve the results from the hash map. 

We start by asserting that the target string is achievable. This is done by checking the availability of all chars in the target string against the unique chars in 
the set of stickers we have. We can do so using a hash set. 

If we are able to form the target string using our set of stickers, we proceed to sort the target string in ascending alphabetical order. 

We then compute the minimum no of stickers to form the target string through this process:
- Assume that we select the ith sticker. 
- Hit the hash map for the string
- If present, return the min no of stickers needed
- If not present, we compute the remaining substring after removing all words from the sticker
	- If the remaining substring matches the original substring, we conclude that the sticker does not have any alphabets to reduce the substring. We ignore selection of this sticker.
	- If the remaining substring is smaller than the original, we recurse the process using the remaining substring as target

- Repeat this process for all stickers in given array. 

### Complexity
Time complexity 	: O(N * Prod(1 + n_i)) for all M)
- M represents the set of unique alphabets in the target string
- i the count for each unique alphabet
- N represents the array size of stickers

Space complexity 	: O(Prod(1 + n_i)) for all M)