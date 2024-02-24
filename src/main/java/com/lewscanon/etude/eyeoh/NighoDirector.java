/*
 */
package com.lewscanon.etude.eyeoh;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NighoDirector {
    static void checkDirectory() {
        final String FNAME = "file.txt";
        final String EXISTS = "%6s:    exists? %b%n";
        final String ISADIR = "%6s: directory? %b%n";

        try {
            final Path path = Paths.get(FNAME);
            System.out.printf("Checking %s%n", path.toAbsolutePath());

            Files.delete(path);
            System.out.printf(EXISTS, "Before", Files.exists(path));
            System.out.printf(ISADIR, "", Files.isDirectory(path));

            Files.createDirectory(path);
            System.out.printf(EXISTS, "After", Files.exists(path));
            System.out.printf(ISADIR, "", Files.isDirectory(path));

            Files.delete(path);
            Files.createFile(path);
            System.out.printf(EXISTS, "After", Files.exists(path));
            System.out.printf(ISADIR, "", Files.isDirectory(path));
        }
        catch (IOException exc) {
            System.err.printf("pwd: %s%n", exc.getMessage());
        }
    }

    public static void main(String... args) {
        checkDirectory();
    }
}
