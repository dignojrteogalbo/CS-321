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

This section should tell the user how to compile your code.  It is
also appropriate to instruct the user how to use your code. Does your
program require user input? If so, what does your user need to know
about it to use it as quickly as possible?

## Results 

This section presents timing and other results of the experiments that 
you were asked to perform as part of the project.

## Sources used

* <https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html>

* <https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html>
* <https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html#LinkedHashMap-int-float-boolean>
    * for special constructor
* <https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html>
* <https://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html>
* <https://github.com/BoiseState/CS321-resources/tree/master/examples/serialization>

August 29, edited run-tests.sh, PlayerGenerate commands, added 0 for debug level... figured it out in lecture
all tests pass because seed was set correctly

August 31, changed how to handle arguments, decided to not do it