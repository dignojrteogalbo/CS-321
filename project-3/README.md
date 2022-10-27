# Project 3: Experiments with Hashing

* Author: Digno JR Teogalbo
* Class: CS321 Section 1
* Semester: Fall 2022

## Overview

This program will test the differences between two open addressing
hashing algorithms: linear probing and double hashing. And provide
the results when inserting n elements until the desired load factor
is reached.

## Reflection

This project was very easy to approach compared to project 2. I was
able to see how to design the Hashtable and the HashObject, and I was
able to easily design the testing program for the Hashtable. Inheriting
a parent Hashtable class and just overriding a hash function enabled me
to quickly write the two different hashing types. I found difficulty in
making sure that the run-tests.sh file had the correct new line endings
when transferring my code between Windows, Debian with WSL, and MacOS.

I found myself wondering how the load factor worked. But as I read and
re-read the project specifications, I realized I just overlooked past
where it outlined how the load factor worked. Talking with Amit Jain
helped me understand how my code was working, especially the probing
count as I had to explain and understand my own thought process. Its
much more important to understand why my code works rather than having
it work without knowing.

## Compiling and Using

To compile, run:

`$ javac *.java`

in the project directory.

To use the HashtableTest, run:

`$ java HashtableTest`

to see the usage instructions.

To use the TwinPrimeGenerator, run:

`$ java TwinPrimeGenerator`

to see the usage instructions.

To run the tests for the HashtableTest, run:

`$ ./run-test.sh`

## Results 

### Results for Random Integers
| Hashing Method | Load Factor | Avg. Num. of Probes |
| :---: | :---: | :---: |
| Linear Probing | 0.5 | 1.51 |
| Double Hashing | 0.5 | 1.39 |
| Linear Probing | 0.6 | 1.75 |
| Double Hashing | 0.6 | 1.52 |
| Linear Probing | 0.7 | 2.18 |
| Double Hashing | 0.7 | 1.73 |
| Linear Probing | 0.8 | 2.98 |
| Double Hashing | 0.8 | 2.02 |
| Linear Probing | 0.9 | 5.58 |
| Double Hashing | 0.9 | 2.54 |
| Linear Probing | 0.95 | 11.47 |
| Double Hashing | 0.95 | 3.18 |
| Linear Probing | 0.99 | 35.46 |
| Double Hashing | 0.99 | 4.65 |

### Results for Date Longs
| Hashing Method | Load Factor | Avg. Num. of Probes |
| :---: | :---: | :---: |
| Linear Probing | 0.5 | 1.08 |
| Double Hashing | 0.5 | 1.13 |
| Linear Probing | 0.6 | 1.09 |
| Double Hashing | 0.6 | 1.16 |
| Linear Probing | 0.7 | 1.09 |
| Double Hashing | 0.7 | 1.23 |
| Linear Probing | 0.8 | 1.15 |
| Double Hashing | 0.8 | 1.45 |
| Linear Probing | 0.9 | 1.47 |
| Double Hashing | 0.9 | 2.16 |
| Linear Probing | 0.95 | 1.86 |
| Double Hashing | 0.95 | 2.80 |
| Linear Probing | 0.99 | 3.54 |
| Double Hashing | 0.99 | 4.38 |

### Results for Word-List
| Hashing Method | Load Factor | Avg. Num. of Probes |
| :---: | :---: | :---: |
| Linear Probing | 0.5 | 1.60 |
| Double Hashing | 0.5 | 1.39 |
| Linear Probing | 0.6 | 2.15 |
| Double Hashing | 0.6 | 1.53 |
| Linear Probing | 0.7 | 3.60 |
| Double Hashing | 0.7 | 1.72 |
| Linear Probing | 0.8 | 6.71 |
| Double Hashing | 0.8 | 2.02 |
| Linear Probing | 0.9 | 19.81 |
| Double Hashing | 0.9 | 2.57 |
| Linear Probing | 0.95 | 110.59 |
| Double Hashing | 0.95 | 3.19 |
| Linear Probing | 0.99 | 471.67 |
| Double Hashing | 0.99 | 4.70 |

## Sources used

<https://www.geeksforgeeks.org/prime-numbers/>
