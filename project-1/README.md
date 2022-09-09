# Project 1: Player Cache with Serialization

* Author: Digno JR Teogalbo
* Class: CS321 Section 1
* Semester: Fall 2022

## Overview

This program takes serialized player objects, reads them, and
populates a cache with the deserialized player objects. This program
outputs the time elapsed to populate a cache, the amount of cache
references, hits, and ratio of references to hits.

## Reflection

The beginning of the project was very difficult to grasp my head
around what the project specified. Serialization and deserialization
was difficult for me to understand as I didn't know the details of
what a "Player" was and what kind of object I'd receive after
deserialization. After the project specifications were updated and
I was able to see the starter code, everything started to click for
me. I was able to follow the pseudocode and implement it very easily.

One issue I was finding was that my cache's hits were consistently
higher than expected. This made me revisit the logic and realize that
I was not removing the last entry if the number of entries was greater
than or equal to the cache's fixed size. Attempting the extra credit
was fairly straight-forward too, but looking at the CacheTest results
made me worry if I implemented it wrong. In small input sizes, the
LinkedHashMap cache ran slower than the LinkedList cache. But in large
input sizes (and cache sizes), the LinkedHashMap was wayyyyyy faster!

## Compiling and Using

Clone this project and change into the directory of the project.
From the directory containing all the source files, you can execute the
run-tests.sh script. This script will compile all class files and 
generate the data files, as well as run tests.

```
$ ./run-tests.sh
```

Otherwise you can manually compile all the class files through
```
$ javac *.java
```

Run the compiled CacheTest program using
```
$ java CacheTest
```

The console will output the usage for CacheTest:
```
Usage: java CacheTest <cache-size> <serialized-data-filename>
=============================================================
<cache-size>    The size of the cache.
<serialized-data-filename>      The name of the data file containing serialized Player objects that are being cached
```

## Results 

When executing the run-tests.sh script, the time elapsed matches 
closely with the expected time elapsed. This is expected as the data
values being generated and tested are the same for both my program
execution and the provided expected outputs. For the LinkedList Cache,
as the number of entries increases (size of the cache) the speed of 
the program decreases, despite the same number of players being
referenced. This is true for the LinkedHashMap Cache as well, but the
speed of the program does not decrease as quickly as the LinkedList 
Cache.

I believe that this is the result of the LinkedHashMap being quicker
to access data, whereas the LinkedList has an O(n) runtime for 
accessing, the LinkedHashMap has an O(1) runtime for accessing. So
while the LinkedList is generally quicker on smaller sized inputs and
smaller sized caches, the LinkedHashMap has a quicker runtime when
the cache and input sizes are larger.

## Sources used

* <https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html>

* <https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html>
* <https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html#LinkedHashMap-int-float-boolean>
    * for special constructor
* <https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html>
* <https://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html>
* <https://github.com/BoiseState/CS321-resources/tree/master/examples/serialization>
* <https://linuxhint.com/bash-test-command/>
    * for editing the run-tests script