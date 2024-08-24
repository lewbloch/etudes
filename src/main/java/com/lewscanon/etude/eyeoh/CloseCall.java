/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.eyeoh;

import java.io.IOException;
import java.io.StringReader;

public class CloseCall {
    static final String README = "This is an initial";

    public static void main(String[] args) {
        try (final StringReader reader = new StringReader(README)) {
            try {
                char[] buffer = new char[1024];
                for (int kount;
                     (kount = reader.read(buffer, 0, buffer.length)) >= 0; ) {
                    System.out.print(new String(buffer, 0, kount));
                }
            }
            catch (IOException exc) {
                System.out.printf("%s%n", exc.getMessage());
            }
            System.out.println();
        }
    }
}
