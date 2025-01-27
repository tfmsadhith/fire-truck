import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * ShiftRegisterTest
 * @author dcsslg
 * Description: set of tests for a shift register implementation
 */
public class ShiftRegisterTest {
    /**
     * Returns a shift register to test.
     * @param size
     * @param tap
     * @return a new shift register
     */
    ILFShiftRegister getRegister(int size, int tap) {
        return new ShiftRegister(size, tap);
    }

    /**
     * Tests shift with simple example.
     */
    @Test
    public void testShift1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.shift());
            System.out.println();
        }
    }

    @Test
    public void testShift2() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        r.setSeed(seed);
        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 };
        for (int i = 0; i < 10; i++) {
            System.out.println(r.shift());
        }
    }

    /**
     * Tests generate with simple example.
     */
    @Test
    public void testGenerate1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 6, 1, 7, 2, 2, 1, 6, 6, 2, 3 };
        for (int i = 0; i < 10; i++) {
            assertEquals("GenerateTest", expected[i], r.generate(3));
        }
    }

    @Test
    public void testGenerate2() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        r.setSeed(seed);
        int[] expected = {0,0,1,0,0,3,0,0,5,0};
        for (int i = 0; i < 10; i++) {
            assertEquals("GenerateTest", expected[i], r.generate(3));
        }
    }

    @Test
    public void testGenerate3() {
        ILFShiftRegister r = getRegister(3, 2);
        int[] seed = {1,1,1};
        r.setSeed(seed);
        int[] expected = {0,0,0,0,0,0,0,0,0,0};
        for (int i = 0; i < 10; i++) {
            assertEquals("GenerateTest", expected[i], r.generate(3));
        }
    }

    @Test
    public void testGenerate4() {
        ILFShiftRegister r = getRegister(8, 4);
        int[] seed = {1,1,1,0,1,1,1,0};
        r.setSeed(seed);
        int[] expected = {806, 90, 571, 915, 45, 285, 969, 534, 654, 996};
        for (int i = 0; i < 10; i++) {
            assertEquals("GenerateTest", expected[i], r.generate(10));
        }
    }



    /**
     * Tests register of length 1.
     */
    @Test
    public void testOneLength() {
        ILFShiftRegister r = getRegister(1, 0);
        int[] seed = { 1 };
        r.setSeed(seed);
        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.generate(3));
        }
    }

    /**
     * Tests with erroneous seed.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = { 1, 0, 0, 0, 1, 1, 0 };
        r.setSeed(seed);
        r.shift();
        r.generate(4);
    }
    /*
        The proper method for testing exceptions is to use IllegalArgumentException.class.

        Anyways, since this is an incorrect argument, an IllegalArgumentException should be thrown.

        1. https://stackoverflow.com/questions/15208544/when-should-an-illegalargumentexception-be-thrown
        2. https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html
     */
    @Test(expected = IllegalArgumentException.class)
    public void nonsenseTap1() {
        getRegister(2,4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonsenseTap2() {
        getRegister(2,2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void badSeed1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 2, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void badSeed2() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        r.setSeed(seed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void badSize1() {
        ILFShiftRegister r = getRegister(3, 7);
        int[] seed = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        r.setSeed(seed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void badSize2() {
        ILFShiftRegister r = getRegister(12, 7);
        int[] seed = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        r.setSeed(seed);
    }


    @Test(expected = IllegalArgumentException.class)
    public void badTap3() {
        ILFShiftRegister r = getRegister(3, 10);
        int[] seed = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        r.setSeed(seed);
    }
}
