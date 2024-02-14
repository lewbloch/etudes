package com.lewscanon.etude.algorithm;

public class FibonachoTest {

    @SuppressWarnings("unused")
    public void testFib() {
    }


    /**
     * Unit test.
     * @param value value to test.
     */
    @SuppressWarnings("unused")
    public boolean fibTest(char value) {
        Fibonacho fibber = new Fibonacho();
        System.out.printf(Fibonacho.OUTF, (int) value, fibber.fib(value));
        System.out.printf(Fibonacho.RECURF, Fibonacho.recursions.get());
        return true;
    }
}
