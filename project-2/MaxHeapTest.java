import java.util.NoSuchElementException;

public class MaxHeapTest {
    private static final int A = 1;
    private static final int B = 2;
    private static final int C = -2;
    private static final int D = -1;

    private int passes = 0;
    private int fails = 0;
    private int total = 0;

    enum Result {
        Pass, Fail, NoSuchElementException, MatchingValue, NoException, HeapException, IndexOutOfBoundsException
    }

    private static final Integer[] LIST_EMPTY = {};
    private static final Integer[] LIST_A = { A };
    private static final Integer[] LIST_B = { B };
    private static final Integer[] LIST_BA = { B, A };

    public static void main(String[] args) {
        MaxHeapTest tester = new MaxHeapTest();
        tester.testEmptyMaxHeap(emptyHeap, "EmptyHeap", LIST_EMPTY);
        tester.testEmptyMaxHeap(A_extractMax_emptyHeap, "A_extractMax_emptyHeap", LIST_EMPTY);
        tester.testSingleElementMaxHeap(emptyHeap_insert_A, "EmptyHeap_insert_A", LIST_A);
        tester.testSingleElementMaxHeap(A_increaseKey0B_B, "A_increaseKey0B_B", LIST_B);
        tester.testTwoElementMaxHeap(A_insertB_BA, "A_insertB_BA", LIST_BA);
        System.out.printf("Tests Passed: %d\nTests Failed: %d\nTotal Tests: %d\n", tester.passes, tester.fails, tester.total);
    }

    private void printTest(String test, boolean result) {
        total++;

        if (result) {
            passes++;
        } else {
            fails++;
        }

        System.out.printf("%-46s\t%s\n", test, (result ? "   PASS" : "***FAIL***"));
    }

    private void testEmptyMaxHeap(Scenario<Integer> scenario, String scenarioName, Integer[] contents) {
        System.out.printf("TEST EMPTY %s ===============\n", scenarioName);

        try {
            printTest(scenarioName + "_testIsEmpty", testIsEmpty(scenario.build(), true, Result.MatchingValue));
            printTest(scenarioName + "_testHeapify", testHeapify(scenario.build(), 0, Result.NoException));
            printTest(scenarioName + "_testExtractMax", testExtractMax(scenario.build(), null, Result.NoSuchElementException));
            printTest(scenarioName + "_testInsert", testInsert(scenario.build(), A, Result.NoException));
            printTest(scenarioName + "_testIncreaseKey0A", testIncreaseKey(scenario.build(), 0, A, Result.NoSuchElementException));
            printTest(scenarioName + "_testGet0", testGet(scenario.build(), 0, null, Result.NoSuchElementException));
        } catch (Exception err) {
            System.out.println("UNABLE TO COMPLETE TEST");
            err.printStackTrace();
        }

        System.out.println("=====================================================================");
    }

    private void testSingleElementMaxHeap(Scenario<Integer> scenario, String scenarioName, Integer[] contents) {
        System.out.printf("TEST SINGLE ELEMENT %s ===============\n", scenarioName);

        try {
            printTest(scenarioName + "_testIsEmpty", testIsEmpty(scenario.build(), false, Result.MatchingValue));
            printTest(scenarioName + "_testHeapifyNeg1", testHeapify(scenario.build(), -1, Result.IndexOutOfBoundsException));
            printTest(scenarioName + "_testHeapify0", testHeapify(scenario.build(), 0, Result.NoException));
            printTest(scenarioName + "_testHeapify1", testHeapify(scenario.build(), 1, Result.NoException));
            printTest(scenarioName + "_testExtractMax", testExtractMax(scenario.build(), contents[0], Result.MatchingValue));
            printTest(scenarioName + "_testInsertA", testInsert(scenario.build(), A, Result.NoException));
            printTest(scenarioName + "_testInsertB", testInsert(scenario.build(), B, Result.NoException));
            printTest(scenarioName + "_testIncreaseKey0A", testIncreaseKey(scenario.build(), 0, A, (contents[0].compareTo(A) > 0) ? Result.HeapException : Result.NoException));
            printTest(scenarioName + "_testIncreaseKey0B", testIncreaseKey(scenario.build(), 0, B, (contents[0].compareTo(B) > 0) ? Result.HeapException : Result.NoException));
            printTest(scenarioName + "_testGetNeg1", testGet(scenario.build(), -1, null, Result.IndexOutOfBoundsException));
            printTest(scenarioName + "_testGet0", testGet(scenario.build(), 0, contents[0], Result.MatchingValue));
            printTest(scenarioName + "_testGet1", testGet(scenario.build(), 1, null, Result.IndexOutOfBoundsException));
        } catch (Exception err) {
            System.out.println("UNABLE TO COMPLETE TEST");
            err.printStackTrace();
        }

        System.out.println("=====================================================================");
    }

    private void testTwoElementMaxHeap(Scenario<Integer> scenario, String scenarioName, Integer[] contents) {
        System.out.printf("TEST TWO ELEMENT %s ===============\n", scenarioName);

        try {
            printTest(scenarioName + "_testIsEmpty", testIsEmpty(scenario.build(), false, Result.MatchingValue));
            printTest(scenarioName + "_testHeapifyNeg1", testHeapify(scenario.build(), -1, Result.IndexOutOfBoundsException));
            printTest(scenarioName + "_testHeapify0", testHeapify(scenario.build(), 0, Result.NoException));
            printTest(scenarioName + "_testHeapify1", testHeapify(scenario.build(), 1, Result.NoException));
            printTest(scenarioName + "_testHeapify2", testHeapify(scenario.build(), 2, Result.NoException));
            printTest(scenarioName + "_testExtractMax", testExtractMax(scenario.build(), contents[0], Result.MatchingValue));
            printTest(scenarioName + "_testInsertA", testInsert(scenario.build(), A, Result.NoException));
            printTest(scenarioName + "_testInsertB", testInsert(scenario.build(), B, Result.NoException));
            printTest(scenarioName + "_testIncreaseKey0A", testIncreaseKey(scenario.build(), 0, A, (contents[0].compareTo(A) > 0) ? Result.HeapException : Result.NoException));
            printTest(scenarioName + "_testIncreaseKey0B", testIncreaseKey(scenario.build(), 0, B, (contents[0].compareTo(B) > 0) ? Result.HeapException : Result.NoException));
            printTest(scenarioName + "_testIncreaseKey1A", testIncreaseKey(scenario.build(), 1, A, (contents[1].compareTo(A) > 0) ? Result.HeapException : Result.NoException));
            printTest(scenarioName + "_testIncreaseKey1B", testIncreaseKey(scenario.build(), 1, B, (contents[1].compareTo(B) > 0) ? Result.HeapException : Result.NoException));
            printTest(scenarioName + "_testGetNeg1", testGet(scenario.build(), -1, null, Result.IndexOutOfBoundsException));
            printTest(scenarioName + "_testGet0", testGet(scenario.build(), 0, contents[0], Result.MatchingValue));
            printTest(scenarioName + "_testGet1", testGet(scenario.build(), 1, contents[1], Result.MatchingValue));
            printTest(scenarioName + "_testGet2", testGet(scenario.build(), 2, null, Result.IndexOutOfBoundsException));
        } catch (Exception err) {
            System.out.println("UNABLE TO COMPLETE TEST");
            err.printStackTrace();
        }

        System.out.println("=====================================================================");
    }


    private boolean testHeapify(MaxHeap<Integer> maxHeap, int index, Result expectedResult) {
        Result result;

        try {
            maxHeap.heapify(index);
            result = Result.NoException;
        } catch (IndexOutOfBoundsException err) {
            result = Result.IndexOutOfBoundsException;
        } catch (Exception err) {
            System.out.printf("%s caught unexpected %s\n", "testHeapify", err.toString());
            err.printStackTrace();
            result = Result.Fail;
        }

        return result.equals(expectedResult);
    }

    private Boolean testIsEmpty(MaxHeap<Integer> scenario, boolean expectedValue, Result expectedResult) {
        Result result;

        try {
            if (scenario.isEmpty() == expectedValue) {
                result = Result.MatchingValue;
            } else {
                result = Result.Fail;
            }
        } catch (Exception err) {
            System.out.printf("%s caught unexpected %s\n", "testIsEmpty", err.toString());
            err.printStackTrace();
            result = Result.Fail;
        }

        return result.equals(expectedResult);
    }

    private boolean testExtractMax(MaxHeap<Integer> maxHeap, Integer expectedValue, Result expectedResult) {
        Result result;

        try {
            Integer max = maxHeap.extractMax();
            if (max.equals(expectedValue)) {
                result = Result.MatchingValue;
            } else {
                result = Result.Fail;
            }
        } catch (NoSuchElementException err) {
            result = Result.NoSuchElementException;
        } catch (Exception err) {
            System.out.printf("%s caught unexpected %s\n", "testExtractMax", err.toString());
            err.printStackTrace();
            result = Result.Fail;
        }

        return result.equals(expectedResult);
    }

    private boolean testInsert(MaxHeap<Integer> maxHeap, int element, Result expectedResult) {
        Result result;

        try {
            maxHeap.insert(element);
            result = Result.NoException;
        } catch (Exception err) {
            System.out.printf("%s caught unexpected %s\n", "testInsert", err.toString());
            err.printStackTrace();
            result = Result.Fail;
        }

        return result.equals(expectedResult);
    }

    private boolean testIncreaseKey(MaxHeap<Integer> maxHeap, int index, Integer value, Result expectedResult) {
        Result result;

        try {
            maxHeap.increaseKey(index, value);
            result = Result.NoException;
        } catch (IndexOutOfBoundsException err) {
            result = Result.IndexOutOfBoundsException;
        } catch (NoSuchElementException err) {
            result = Result.NoSuchElementException;
        } catch (HeapException err) {
            result = Result.HeapException;
        } catch (Exception err) {
            System.out.printf("%s caught unexpected %s\n", "testIncreaseKey", err.toString());
            err.printStackTrace();
            result = Result.Fail;
        }

        return result.equals(expectedResult);
    }

    private boolean testGet(MaxHeap<Integer> maxHeap, int index, Integer value, Result expectedResult) {
        Result result;

        try {
            Integer item = maxHeap.get(index);
            if (item.equals(value)) {
                result = Result.MatchingValue;
            } else {
                result = Result.Fail;
            }
        } catch (IndexOutOfBoundsException err) {
            result = Result.IndexOutOfBoundsException;
        } catch (NoSuchElementException err) {
            result = Result.NoSuchElementException;
        } catch (Exception err) {
            System.out.printf("%s caught unexpected %s\n", "testIncreaseKey", err.toString());
            err.printStackTrace();
            result = Result.Fail;
        }

        return result.equals(expectedResult);
    }

    private static Scenario<Integer> emptyHeap = () -> {
        return new MaxHeap<Integer>();
    };

    private static Scenario<Integer> emptyHeap_insert_A = () -> {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.insert(A);
        return maxHeap;
    };

    private static Scenario<Integer> A_extractMax_emptyHeap = () -> {
        MaxHeap<Integer> maxHeap = emptyHeap_insert_A.build();
        maxHeap.extractMax();
        return maxHeap;
    };

    private static Scenario<Integer> A_increaseKey0B_B = () -> {
        MaxHeap<Integer> maxHeap = emptyHeap_insert_A.build();

        try {
            maxHeap.increaseKey(0, B);
        } catch (Exception err) {
            
        }

        return maxHeap;
    };

    private static Scenario<Integer> A_insertB_BA = () -> {
        MaxHeap<Integer> maxHeap = emptyHeap_insert_A.build();
        maxHeap.insert(B);
        return maxHeap;
    };

}

interface Scenario<T extends Comparable<T>> {
    MaxHeap<T> build();
}
