/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.dyspatch;

public class DynamycDyspatch {
    static final String SIGNED = "%s %s sign \n%s\n";

    public static void main(String... args) {
        final Personal frankie = new Personal("Frankie is your hero", false, "Float like a bee!");
        final Personal jojo = new Personal("Love to you, jo-star", true, "Y'all get real, now.");

        final Author feckless = new GenreAuthor("Franklynn", frankie, Genre.UNCATEGORIZABLE);
        final Author famous = new GenreAuthor("Jolen", jojo, Genre.ROMANCE);

        final Book influencerBible =
                new Book(feckless, "How to Lose Friends and Irritate People", Genre.UNCATEGORIZABLE);
        final Book bestSeller = new Book(famous, "The Many Loves of Dobie Gillis", Genre.ROMANCE);

        boolean didSign = feckless.sign(influencerBible, "You love me!");
        System.out.printf(SIGNED, feckless, didSign? "did" : "did not", influencerBible);

        didSign = feckless.sign(bestSeller);
        System.out.printf(SIGNED, feckless, didSign? "did" : "did not", bestSeller);

        didSign = famous.sign(bestSeller, "You are the best!");
        System.out.printf(SIGNED, famous, didSign? "did" : "did not", bestSeller);

        didSign = famous.sign(bestSeller);
        System.out.printf(SIGNED, famous, didSign? "did again" : "did not again", bestSeller);
    }
}
