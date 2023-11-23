package com.lewscanon.etude.algorithm;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.Map;

import static org.testng.Assert.*;

public class FibonachoTest {

    public void testFib() {
    }


    /**
     * Unit test.
     * @param value value to test.
     */
    public boolean fibTest(char value) {
        Fibonacho fibber = new Fibonacho();
        System.out.printf(Fibonacho.OUTF, (int) value, fibber.fib(value));
        System.out.printf(Fibonacho.RECURF, Fibonacho.recursions.get());
        return true;
    }

    /**
     * Unit test.
     */
    @Test
    public void fibarTest() {
//        final Map<Character, BigInteger> testValues =
//                Map.of(
//            0, 0,
//            1000,
//            new BigInteger("43466557686937456435688527675040625802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322471161642996440906533187938298969649928516003704476137795166849228875"),
//            900,
//            new BigInteger("54877108839480000051413673948383714443800519309123592724494953427039811201064341234954387521525390615504949092187441218246679104731442473022013980160407007017175697317900483275246652938800")
//        );
//
//        for (char value : testValues.keySet()) {
//            Assert.assertEquals();
//        }
    }
}