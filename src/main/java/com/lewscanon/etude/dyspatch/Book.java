/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.dyspatch;

import java.util.*;

/** Book information. */
public class Book {
    private static final String TO_STR = """
    { author: "%s",
      title: "%s",
      genre: "%s",
      signatures: %s,
      dedications: %s
    }
    """;

    private final Author author;
    private final String title;
    private final Genre genre;

    private final Set<String> signatures = new HashSet<>();
    private final Set<String> dedications = new HashSet<>();

    public Book(Author author, String title, Genre genre) {
        this.author = Optional.of(author).orElseThrow();
        this.title = Optional.of(title).orElse("");
        this.genre = Optional.of(genre).orElse(Genre.GENERAL);
    }

    @Override
    public boolean equals(Object other) {
        return this == other ||
                (other instanceof Book book
                && Objects.equals(author, book.author)
                && Objects.equals(title, book.title));
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title);
    }

    @Override
    public String toString() {
        return String.format(TO_STR, author, title, genre, signatures, dedications);
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public Set<String> getSignatures() {
        return Collections.unmodifiableSet(signatures);
    }

    public Set<String> getDedications() {
        return Collections.unmodifiableSet(dedications);
    }

    /**
     * @param signature signature to add.
     * @return true if a new signature is added.
     */
    public boolean addSignature(String signature) {
        return signatures.add(signature);
    }

    /**
     * @param dedication dedication to add.
     * @return true if a new dedication is added.
     */
    public boolean addDedication(String dedication) {
        return dedications.add(dedication);
    }
}
