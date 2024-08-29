/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.eyeoh;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.createDirectory;
import static java.nio.file.Files.createFile;
import static java.nio.file.Files.delete;
import static java.nio.file.Files.exists;
import static java.nio.file.Files.isDirectory;

public class NighoDirector {
    static void exemplifyNio() {
        final String FNAME = "file.txt";
        final String EXISTS = "%s %s\n exists? %b\n is dir? %b\n";

        try {
            final Path path = Paths.get(FNAME);
            System.out.printf("\n%s\n", path.toAbsolutePath());

            delete(path);
            System.out.printf(EXISTS, "delete", path, exists(path), isDirectory(path));

            createDirectory(path);
            System.out.printf(EXISTS, "createDirectory", path, exists(path), isDirectory(path));

            delete(path);
            System.out.printf(EXISTS, "delete", path, exists(path), isDirectory(path));
            createFile(path);
            System.out.printf(EXISTS, "createFile", path, exists(path), isDirectory(path));
        }
        catch (IOException exc) {
            System.err.printf("pwd: %s%n", exc.getMessage());
        }
    }

    public static void main(String... args) {
        exemplifyNio();
    }
}
