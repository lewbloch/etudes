/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.dyspatch;

import java.util.Objects;
import java.util.Optional;

/** Example author type. */
public class Author {
    private static final String TO_STR = """
    { name: "%s",
      personal: %s
    }
    """;

    private final String name;
    private final Personal personal;
    private final String represent;

    /**
     * Construct.
     * @param name name property.
     * @param personal Personal property.
     */
    public Author(String name, Personal personal) {
        this.name = Optional.of(name).orElse("oopsie");
        this.personal = personal;
        this.represent = String.format(TO_STR, this.name, this.personal);
    }

    /**
     * Add a signature to a book.
     * @param book Book to sign.
     * @return true if it's a new signature.
     */
    public boolean sign(Book book) {
        return book.addSignature(personal.signature());
    }

    /**
     * Add a dedication to a book.
     * @param book Book to dedicate.
     * @return true if it's a new dedication.
     */
    public boolean sign(Book book, String dedication) {
        return sign(book) && book.addDedication(dedication);
    }

    /**
     * Name accessor.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Personal accessor.
     * @return personal.
     */
    public Personal getPersonal() {
        return personal;
    }

    @Override
    public boolean equals(Object other) {
        return this == other ||
            (other instanceof Author author && Objects.equals(getName(), author.getName()));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public String toString() {
        return represent;
    }
}
