# Project #: Stardew Valley (Priority Queues)

* Author: Digno JR Teogalbo
* Class: CS321 Section 1
* Semester: Fall 2022

## Overview

This program simulates a day (or multiple days) in the life of Stardew
Valley. The simulation will randomly generate tasks and perform them,
then display the results in energy used, money gained, and whether you
die/pass out.

## Reflection

Getting started was the most difficult part, but what helped was going
through the provided classes and interfaces. Implementing the pseudocode
was easy to do but I found it difficult making it fit into what I needed it
 to do. The parent, left, and right methods are examples of this as the
 pseudocode based it on the index starting at 1 whereas I used an index
 starting at 0, so I had to tweak it in order for it to work. Making the
 MaxHeap generic was also difficult as I had issues getting a generic array
 but I created a comparable array first and cast it to the generic type. One
 issue I had was about the heapify method, where I had keys swapping spots in
 the wrong places. I talked to Kaden in class about it and we were able to
 find out it was due to our indexing that was causing the issues.

I wish there was more information in the interfaces and the MaxHeap but I
enjoyed the freedom it allowed me. I really wanted to attempt the multiplayer
extra credit but I ran out of time. After I submit I'll try to implement
the multiplayer and the generic PriorityQueue. I did not enjoy creating
tests for the MaxHeap because at first I wanted to test that the heapify and
buildMaxHeap changed the array in the ways I expected. But I shifted my view
of the testing to be more basic, as in whether or not the methods returned
the correct values and threw the correct exceptions.

## Compiling and Using

Clone the project directory into a folder, then change directories into the project directory.

In the project directory run the command to compile all Java files.

```$ javac *.java```

After all Java files compile, run the command to execute the MyLifeInStarDew program and view the program usage.

```$ java MyLifeInStarDew```

To run the tester for MyLifeInStarDew, execute the command to view the test results.

```$ ./run-tests.sh```

To run the tester for MaxHeap, execute the command to view the test results.

```$ java MaxHeapTest```

## Results

This section presents timing and other results of the experiments that
you were asked to perform as part of the project.

## Sources used

<https://stackoverflow.com/questions/15981319/casting-array-of-objects-to-array-of-comparable>
<https://www.geeksforgeeks.org/max-heap-in-java/>
