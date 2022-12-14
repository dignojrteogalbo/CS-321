#!/bin/bash

if  test -e Player-List50.data  -a -e Player-List1000.data -a -e Player-List100000.data
then
	echo
	echo "Input data files found. Proceeding with the test!"
	echo
elif test -e PlayerGenerator.java -a -e Player.java 
then
	javac PlayerGenerator.java Player.java
	echo "Generating data files..."
	echo
	java PlayerGenerator 50 3.0 0 123
	java PlayerGenerator 1000 15.0 0 123
	java PlayerGenerator 100000 2500.0 0 123 
	echo
	echo "Finished generating data files!"
else
	echo "PlayerGenerator.java and Player.java files not found!"
fi

if test -e CacheTest.java -a -e Cache.java
then
	echo "CacheTest.java and Cache.java found, compiling class files..."
	javac CacheTest.java Cache.java

	if test -e CacheLinkedHashMap.java
	then
		echo "CacheLinkedHashMap.java found, compiling class file..."
		javac CacheLinkedHashMap.java
	fi

	for i in 1 2 3 4 5 6
	do
		cmd=$(cat test-cases/in$i)
		echo "======================================================================="
		echo "Running Test $i:" $cmd " "
		$cmd > out$i
		diff -w -I '^Time.*' out$i test-cases/out$i
		result=$?
		if test "$result" = 0
		then
			echo " ---- Test $i output matches! ---- "
		fi
	done

	echo "======================================================================="
else
	echo "CacheTest.java and Cache.java files not found!"
fi
