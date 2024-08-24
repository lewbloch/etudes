/** Copyright © 2024, Lewis S. Bloch. All rights reserved.*/
package com.lewscanon.etude.dyspatch;

public class GenreAuthor extends Author {
    private static final String TO_STR = """
    { name: "%s",
      personal: %s,
      genre: "%s"
    }
    """;

    private final Genre genre;
    private final String represent;

    /**
     * Construct.
     * @param name      property.
     * @param personal  property.
     * @param genre     property.
     */
    public GenreAuthor(String name, Personal personal, Genre genre) {
        super(name, personal);
        this.genre = genre;
        this.represent = String.format(TO_STR, getName(), getPersonal(), this.genre);
    }

    /**
     * Genre accessor.
     * @return genre.
     */
    public Genre getGenre() {
        return genre;
    }

    @Override
    public boolean sign(Book book) {
        return genre.equals(book.getGenre()) && super.sign(book);
    }

    @Override
    public boolean sign(Book book, String dedication) {
        return genre.equals(book.getGenre()) && super.sign(book, dedication);
    }

    @Override
    public String toString() {
        return represent;
    }
}
