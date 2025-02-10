import java.util.Random;
import java.util.Arrays;

public class SortingTester {

    // Since we are not concerned with security, etc.
    private static final Random generator = new Random();

    // Predicate to check if an array is sorted.
    public static boolean isSorted(KeyValuePair[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // The first element is skipped, just a trick.
            if (arr[i - 1].getKey() > arr[i].getKey()) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkSort(ISort sorter, int size) {
        // Make a random array.
        KeyValuePair[] arr = new KeyValuePair[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new KeyValuePair(generator.nextInt(1729), 0);
        }
        sorter.sort(arr);
        return isSorted(arr);
    }

    public static boolean isStable(ISort sorter, int size) {
        // Note: Any sort performed on an array of size 0 or 1 is "vacuously" stable.
        if (size <= 1) {
            return true;
        }

        KeyValuePair[] arr = new KeyValuePair[size];

        for (int i = 0; i < size; i++) {
            arr[i] = new KeyValuePair(i % 3, i); // I just chose 0,1,2 repeating for simplicity, many ways of doing this.
        }

        sorter.sort(arr);

        // Stability checking.
        for (int i = 1; i < size; i++) {
            if ((arr[i - 1].getKey() == arr[i].getKey()) && (arr[i - 1].getValue() > arr[i].getValue())) {
                return false;
            }
        }

        return true;
    }

    // First I want to find the incorrect array.
    // This is Sorter B.

    public static boolean incorrectCheck(ISort sorter) {
        for (int i = 0; i < 400; i++) {
            int randomSize = generator.nextInt(100) + 1;
            KeyValuePair[] testArray = new KeyValuePair[randomSize];

            for (int j = 0; j < randomSize; j++) {
                testArray[j] = new KeyValuePair(generator.nextInt(100), j);
            }

            sorter.sort(testArray);

            if (!isSorted(testArray)) {
                System.out.println(Arrays.toString(testArray));
                return true;
            }
        }
        return false;
    }

    public static long[] evaluator(ISort sorter, int size) {
        // This report is where we keep some results.
        long[] report = new long[11];

        // First, I want a random array.
        KeyValuePair[] rando = new KeyValuePair[size];
        for (int i = 0; i < size; i++) {
            rando[i] = new KeyValuePair((int) (Math.random() * 1000), i);
        }
        report[0] = sorter.sort(rando);

        // Then, I want a reversed array.
        KeyValuePair[] reversed = new KeyValuePair[size];
        for (int i = 0; i < size; i++) reversed[i] = new KeyValuePair(size - i, i);
        report[1] = sorter.sort(reversed);

        // What about a fully sorted array.
        KeyValuePair[] sorted = new KeyValuePair[size];
        for (int i = 0; i < size; i++) {
            sorted[i] = new KeyValuePair(i, i);
        }
        report[2] = sorter.sort(sorted);

        // Now an array with a few swaps.
        KeyValuePair[] almost = new KeyValuePair[size];
        for (int i = 0; i < size; i++) {
            almost[i] = new KeyValuePair(i, i);
        }

        for (int i = 0; i < size - 1; i++) {
            if (Math.random() < 0.05) {
                KeyValuePair temp = almost[i];
                almost[i] = almost[i + 1];
                almost[i + 1] = temp;
            }
        }
        report[3] = sorter.sort(almost);

        // Test with a large array
        int largeArraySize = 200;
        KeyValuePair[] largeArray = new KeyValuePair[largeArraySize];
        for (int i = 0; i < largeArraySize; i++) {
            largeArray[i] = new KeyValuePair((int) (Math.random() * 1000), i);
        }
        report[4] = sorter.sort(largeArray); // Sorting the large random array

        // Test with a larger array
        int hugeArraySize = 400;
        KeyValuePair[] hugeArray = new KeyValuePair[hugeArraySize];
        for (int i = 0; i < hugeArraySize; i++) {
            hugeArray[i] = new KeyValuePair((int) (Math.random() * 1000), i);
        }
        report[5] = sorter.sort(hugeArray); // Sorting the large random array

        // 5 additional tests for powers of two starting from 8
        int[] powersOfTwo = {32, 64, 128, 256, 512};
        for (int i = 0; i < powersOfTwo.length; i++) {
            int powerOfTwoSize = powersOfTwo[i];

            // Random array
            KeyValuePair[] randoPowerOfTwo = new KeyValuePair[powerOfTwoSize];
            for (int j = 0; j < powerOfTwoSize; j++) {
                randoPowerOfTwo[j] = new KeyValuePair((int) (Math.random() * 1000), j);
            }
            report[6 + i] = sorter.sort(randoPowerOfTwo); // Adding the result for this power of two size
        }

        return report;
    }

    public static void main(String[] args) {
        // I just want to loop through these.
        ISort[] sorters = {
                new SorterA(),
                new SorterB(),
                new SorterC(),
                new SorterD(),
                new SorterE(),
                new SorterF()
        };

        int testSize = 1000;

        for (int i = 0; i < sorters.length; i++) {
            System.out.println("Sorter" + (char) ('A' + i) + " is being looked at.");

            boolean evilSort = incorrectCheck(sorters[i]);
            System.out.println("Is incorrect: " + evilSort);

            boolean stable = isStable(sorters[i], 1000);
            System.out.println("Is stable: " + stable);

            long[] report = evaluator(sorters[i], 100);
            System.out.println(Arrays.toString(report));
        }
    }
}

// RANDOM | REVERSED | SORTED | SLIGHTLY | LARGE | LARGER || 5 powers of 2.