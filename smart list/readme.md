## SmartList - Storing any primitive typed value in a list

This is a simple attempt to design a class that allows you to store any kind of 
primitive values and read from them. 

The class has 2 functionalities
1. Set a value based on the template that the user specified
2. Print value to console 

### Description

The user can initialize a list of *Commons* class which is the common interface that all *SmartList* 
class implements. The user then populates each entree to the list with a *SmartList* object of his type. 

Example:

```java
SmartList<String> lst_a 	= new SmartList<String>();
lst_a.set("Hello");

SmartList<Integer> lst_b 	= new SmartList<Integer>();
lst_b.set(100);

Commons[] common 	= new Commons[2];
common[0] 	= (Commons) lst_a;
common[1] 	= (Commons) lst_b;

// Printing out the values
for(int i =0; i < common.length; i++){
			common[i].print();
}
```

Expected output:
```
Hello
100
```