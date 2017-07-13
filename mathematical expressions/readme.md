## Parsing mathematical expressions

This code was written in Java and allows one to parse and evaluate a mathematical expression from System.In

A mathematical expression is evaluated according to standard rules
	- Brackets take precedence over everything
	- Multiplication and division precedes addition and subtraction

The code only accepts valid mathematical inputs and will flag any errors detected
For example, (* 5 + 3) is an invalid expression and will be flagged. 

### Compile and run
To compile in Windows:
First compile the .java main file and dependencies using
```
javac EqEval.java
```

Next, test the code with inputs from input.txt file
```
cmd /c --% java EqEval < input.txt
```

To compile in Linux:
First compile the .java main file and dependencies using
```
javac EqEval.java
```

Next, test the code with inputs from input.txt file
```
java EqEval < input.txt
```

### Supporting operators
This code supports the following operators
'(' 	& 	')' - Bracketing 
'*' 	& 	'/' - Multiplication and division
'+' 	& 	'-' - Addition and subtraction 


### Dependencies
EqEval.java 			-> Main class
OperandHandler.java 	-> Class that contains operand priorities and stack 
VariableHandler.java 	-> Class that contains variable stack 
Checker.java 			-> Base operand class for inheritance

### TODO
The code does not support negative numbers. For example, -5 will may result in a error or cause bad results.