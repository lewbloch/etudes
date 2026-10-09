package com.lewscanon.etude.generics;

/**
 * "Scrape" the contents from a source object into this as a copy.
 * Adapt types to copy their contents or values into a custom type.
 * @param <T> the type to scrape from.
 */
public interface Scraper<T> {
    /**
     * Scrape the contents of the argument into oneself.
     * @param from the object to scrape.
     */
    void scrape(T from);
}
