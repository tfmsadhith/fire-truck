import java.util.Arrays;

class WiFi {

    /**
     * Implement your solution here
     */
    public static double computeDistance(int[] houses, int numOfAccessPoints) {
        Arrays.sort(houses);
        var size = houses.length;
        double bottom = 0;
        double top = houses[size - 1] - houses[0];

        while (top - bottom > 0.5) {
            double mid = (top + bottom) / 2;
            if (coverable(houses, numOfAccessPoints, mid)) {
                top = mid;
            } else {
                bottom = mid;
            }
        }

        return bottom;
    }

    /**
     * Implement your solution here
     * Just a Greedy Algorithm.
     */
    public static boolean coverable(int[] houses, int numOfAccessPoints, double distance) {
        if (houses.length == 0 || numOfAccessPoints <= 0) {
            return false;
        }

        Arrays.sort(houses);
        int size = houses.length;
        int count = 1;
        int bottom = 0;

        for (int i = 0; i < size; i++) {
            if (houses[i] - houses[bottom] > 2 * distance) {
                count++;
                if (count > numOfAccessPoints) {
                    return false;
                }
                bottom = i;
            }
        }
        return true;
    }


}
