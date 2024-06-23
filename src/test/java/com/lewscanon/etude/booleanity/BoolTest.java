/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.booleanity;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.lewscanon.etude.booleanity.Bool.FALSE;
import static com.lewscanon.etude.booleanity.Bool.TRUE;

import static org.testng.Assert.assertEquals;

public class BoolTest {
    private static final String BFMT = "%s %s %s expect %s\n";
    private static final String UFMT = "%s %s expect %s\n";

    final List<TruthScene> binaryTruths = List.of(
            new TruthScene("NAND", Bool::nand, List.of(
                    new Truth(FALSE, FALSE, TRUE),
                    new Truth(FALSE, TRUE, TRUE),
                    new Truth(TRUE, FALSE, TRUE),
                    new Truth(TRUE, TRUE, FALSE))
            ),
            new TruthScene("AND", Bool::and, List.of(
                    new Truth(FALSE, FALSE, FALSE),
                    new Truth(FALSE, TRUE, FALSE),
                    new Truth(TRUE, FALSE, FALSE),
                    new Truth(TRUE, TRUE, TRUE))
            ),
            new TruthScene("OR", Bool::or, List.of(
                    new Truth(FALSE, FALSE, FALSE),
                    new Truth(FALSE, TRUE, TRUE),
                    new Truth(TRUE, FALSE, TRUE),
                    new Truth(TRUE, TRUE, TRUE))
            ),
            new TruthScene("ERGO", Bool::ergo, List.of(
                    new Truth(FALSE, FALSE, TRUE),
                    new Truth(FALSE, TRUE, TRUE),
                    new Truth(TRUE, FALSE, FALSE),
                    new Truth(TRUE, TRUE, TRUE))
            ),
            new TruthScene("EQV", Bool::eqv, List.of(
                    new Truth(FALSE, FALSE, TRUE),
                    new Truth(FALSE, TRUE, FALSE),
                    new Truth(TRUE, FALSE, FALSE),
                    new Truth(TRUE, TRUE, TRUE)))
    );
    final List<UntruthScene> unaryTruths = List.of(
            new UntruthScene("NOT", Bool::not, List.of(
                    new Untruth(FALSE, TRUE),
                    new Untruth(TRUE, FALSE)))
    );

    @DataProvider(name = "truths")
    public Iterator<TruthScene> nandTruths() {
        return binaryTruths.iterator();
    }

    @DataProvider(name = "untruths")
    public Iterator<UntruthScene> untruths() {
        return unaryTruths.iterator();
    }

    @Test(dataProvider = "truths")
    public void testbinary(TruthScene scene) {
        for (Truth truth : scene.truths) {
            System.out.printf(BFMT, scene.name, truth.p, truth.q, truth.result);
            assertEquals(scene.op.apply(truth.p, truth.q), truth.result);
        }
    }

    @Test(dataProvider = "untruths")
    public void testUnary(UntruthScene scene) {
        for (Untruth truth : scene.untruths) {
            System.out.printf(String.format(UFMT, scene.name, truth.p, truth.result));
            assertEquals(scene.op.apply(truth.p), truth.result);
        }
    }

    public record Truth(Bool p, Bool q, Bool result) {
    }

    public record TruthScene(String name, BiFunction<Bool, Bool, Bool> op,
                             List<Truth> truths) {
    }

    public record Untruth(Bool p, Bool result) {
    }

    public record UntruthScene(String name, Function<Bool, Bool> op,
                               List<Untruth> untruths) {
    }
}
