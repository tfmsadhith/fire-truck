public class Guessing {
    // Your local variables here
    private int low = 0;
    private int high = 1000;
    private int mean;
    /**
     * Implement how your algorithm should make a guess here
     */
    public int guess() {
        this.mean = (low + high) / 2; // Calculate midpoint
        System.out.println(mean);
        return this.mean;
    }

    /**
     * Implement how your algorithm should update its guess here
     */
    public void update(int answer) {
        if (answer == -1) {
            this.low = this.mean + 1;
        } else if (answer == 1) {
            this.high = this.mean - 1;
        }
    }
}
