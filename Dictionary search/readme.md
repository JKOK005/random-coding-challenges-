## Dictionary search

### Objective
To construct a Trie data structure that can store all words in the dictionary. Each entree will 
be a single word in an input.txt file 

Once the data structure has been constructed, the next step would be to make UI which allows the user
to perform intelligent search. This process involves:

1. User keys in the first few letters of a word and submits
2. A search of the trie is conducted starting with the root node and returns the top N entees starting with the inputs the user keys in
3. If there are no valid entrees, the system will flag this to the user

### Compile and run
First, compile the main java binaries using 
```java
javc DictionaryGui.java
```

Then launch the application using 
```java
java DictionaryGui
```

Exit the application using `ctrl c`

### Explaination
A trie is constructed from an input of all words existing in the dictionary. This dictionary is found in the 
**input.txt** file and its contents are obtained from [link](http://www.gwicks.net/dictionaries.htm).

To avoid constantly reloading the dictionary every time the user launches the application, we store an already
constructed trie in the **store** directory in `.ser` format. The main application first checks the existence of
this file and loads the data if it exists. If not, a new trie is constructed and will be stored in the directory.

Navigation along the trie is determined by the initial key words that the user specifies. If the user specifies a 
query string "wo", the programme will navigate to the node that starts with 'wo' and will search for the top N words
starting from that node. This information is then printed out in console. 

### Expected output
1. Querying a string that has many possible combinations 

```
Please enter your search keys: 
> af

Search results: 
> afar afraid afresh africa african africans afrikaans afrikaner afrikaners afro
```

2. Querying a string that has few combinations
```
Please enter your search keys: 
> dragon

Search results: 
> dragon dragons dragonflies dragonfly
```

3. Querying an invalid string and receiving an error message
```
Please enter your search keys: 
> qweqweqwe

No such keys found.
```