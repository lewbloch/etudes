/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.eyeoh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/** Two {@code try} blocks, only one has a {@code catch}. */
public class ReaderRector {
    static final String DFILE = "reader_rector2.txt";
    static final String DFILE_ERR = "Cannot find " + DFILE;

    static final URL inurl = ReaderRector.class.getResource(DFILE);
    static {
        if (inurl == null) {
            throw new IllegalStateException(DFILE_ERR);
        }
    }

    /**
     * Try it.
     * @param args ignored.
     */
    public static void main(String... args) {
        assert inurl != null : DFILE_ERR;

        try (final BufferedReader br =
                 new BufferedReader(new InputStreamReader(inurl.openStream()))) {
            try {
                for (String line; (line = br.readLine()) != null; ) {
                    System.out.println(line);
                }
            }
            catch (IOException exc) {
                System.out.printf("reading %s: %s%n",
                        exc.getClass().getSimpleName(), exc.getMessage());
            }
        }
        catch (IOException exc) {
            System.out.printf("opening %s: %s%n",
                exc.getClass().getSimpleName(), exc.getMessage());
        }
    }
}
