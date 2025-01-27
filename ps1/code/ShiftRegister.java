///////////////////////////////////
// This is the main shift register class.
// Notice that it implements the ILFShiftRegister interface.
// You will need to fill in the functionality.
///////////////////////////////////

/**
 * class ShiftRegister
 * @author
 * Description: implements the ILFShiftRegister interface.
 */
public class ShiftRegister implements ILFShiftRegister {
    ///////////////////////////////////
    // Create your class variables here
    ///////////////////////////////////
    public int[] register;
    public int tap;
    public int size;

    ///////////////////////////////////
    // Create your constructor here:
    ///////////////////////////////////
    ShiftRegister(int size, int tap) {
        // Needs to check if size and tap is valid.
        if ((size <= 0) || (tap < 0) || (tap >= size)) {
            throw new IllegalArgumentException("Check size and tap.");
        } else {
            this.size = size;
            this.tap = tap;
        }
    }

    ///////////////////////////////////
    // Create your class methods here:
    ///////////////////////////////////
    /**
     * setSeed
     * @param seed
     * Description: Checks if the given seed is valid, and then copies it.
     */
    @Override
    public void setSeed(int[] seed) {
        for (int j : seed) {
            // System.out.println(j);
            if (j != 0 && j != 1 || seed.length > size) {
                throw new IllegalArgumentException("Seed can only have 0s or 1s and must have smaller or equal in length to the size.");
            }
        }

        // Most significant bit has to be in the leftmost position.
        int[] new_register = new int[seed.length];

        for (int i = 0; i < new_register.length; i++) {
            new_register[i] = seed[(seed.length - 1) - i];
        }

        this.register = new_register;
    }

    /**
     * shift
     * @return
     * Description:
     */
    @Override
    public int shift() {
        // Recall that the array is flipped.
        int temp_tap = this.register.length - 1 - tap;
        int linear_function = this.register[0] ^ this.register[temp_tap];
        for (int i = 0; i < this.register.length - 1; i++) {
            this.register[i] = this.register[i + 1];

        }
        register[register.length - 1] = linear_function;
        return linear_function;
    }

    /**
     * generate
     * @param k
     * @return
     * Description:
     */
    @Override
    public int generate(int k) {
        //k is a non-negative integer lesser than or equals to 32.
        if (k > 32 || k < 0) {
            throw new IllegalArgumentException("k is a non-negative integer lesser than or equals to 32.");
        } else {
            int store = 0;
            for (int i = 0; i < k; i++) {
                store += (int) (shift() * Math.pow(2, k - i - 1));
            }
            return store;
        }
    }

    /**
     * Returns the integer representation for a binary int array.
     * @param array
     * @return
     */
    private int toDecimal(int[] array) {
        // TODO:
        return 0;
    }
}
