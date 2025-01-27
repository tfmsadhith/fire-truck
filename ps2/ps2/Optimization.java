/**
 * The Optimization class contains a static routine to find the maximum in an array that changes direction at most once.
 */
public class Optimization {

    /**
     * A set of test cases.
     */
    static int[][] testCases = {
//            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
//            {67, 65, 43, 42, 23, 17, 9, 100},
//            {4, -100, -80, 15, 20, 25, 30},
//            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83},
//            {-50, -20, -10, 0, -5, -15},
//            {-200, -150, -100, -50, -60, -70},
//            {73, 42, 13, 5, -17, -324},
//            {1, 3, 5, 7, 9, 12, 15, 18, 21, 25, 20, 17, 14, 11, 8, 6, 4, 2, 1, 0},
//            // Corner cases.
//            {},
//            {10},
//            {1,2},
//            {-10,3},
//            // Decreasing function case.
//            {10,9,8,7,6,7,8},
//            {1000,87,86},
//            {10,9,8,12931293},
//            // Increasing function case.
//            {10,9,6,4,5,3,10},
//            // Experiment.
    };

    /**
     * Returns the maximum item in the specified array of integers which changes direction at most once.
     *
     * @param dataArray an array of integers which changes direction at most once.
     * @return the maximum item in data Array
     * The sketch of the program is that if we start decreasing, then we can use a simple conditional to find
     * the maximum.
     * If we start increasing, then we can use binary search to find the peak.
     */
    public static int searchMax(int[] dataArray) {
        int size = dataArray.length;
        // Invalid input.
        if (size == 0) {
            return 0;
        }
        // Corner case when array length is 1.
        if (size == 1) {
            return dataArray[0];
        }
        // Corner case when array length is 2.
        if (size == 2) {
            if (dataArray[0] > dataArray[1]) {
                return dataArray[0];
            } else {
                return dataArray[1];
            }
        }

        // If the graph is decreasing, use a conditional.
        if (dataArray[0] > dataArray[1]) {
            if (dataArray[0] > dataArray[size - 1]) {
                return dataArray[0];
            } else {
                return dataArray[size - 1];
            }
        }

        // If the graph is increasing, use a binary search.
        int bottom = 0;
        int top = size - 1;

        while (bottom < top) {
            int mid = (bottom + top) / 2;
            System.out.println(bottom);
            System.out.println(top);

            // This is when we are at the peak.
            if (dataArray[mid] > dataArray[mid + 1] && dataArray[mid] > dataArray[mid - 1]) {
                return dataArray[mid];
            }

            if (dataArray[mid] < dataArray[mid + 1]) {
                bottom = mid + 1;
            } else {
                top = mid - 1;
            }
        }
        return dataArray[bottom];
    }

    /**
     * A routine to test the searchMax routine.
     */
    public static void main(String[] args) {
        for (int[] testCase : testCases) {
            System.out.println(searchMax(testCase));
        }
    }
}
