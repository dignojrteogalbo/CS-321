#!/bin/sh

echo
echo "Compiling the source code"
echo
javac *.java

for load in 0.5 0.6 0.7 0.8 0.9
do
	debugLevel=1

	echo "Running java HashtableTest dataSource = 3 loadFactor = $load "
	java HashtableTest 3 $load $debugLevel  >> /dev/null

	echo
	diff linear-dump.txt test-cases/word-list-$load-linear-dump.txt > diff-linear-$load.out
	if test "$?" = 0
	then
		echo "Test PASSED for linear probing and load = $load !!!!"
	else
		echo "==> Test FAILED for linear probing load = $load !!!!!!!! "
		echo "       Check the file diff-linear-$load.out for differences"
	fi

	diff double-dump.txt test-cases/word-list-$load-double-dump.txt > diff-double-$load.out
	if test "$?" = 0
	then
		echo "Test PASSED for double probing and load = $load !!!!"
	else
		echo "==> Test FAILED for double probing load = $load !!!!!!!! "
		echo "       Check the file diff-double-$load.out for differences"
	fi
	echo
done

