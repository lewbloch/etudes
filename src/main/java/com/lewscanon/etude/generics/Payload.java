/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.generics;

import java.lang.reflect.InvocationTargetException;

import static java.lang.String.format;

/**
 * A payload type that enforces its own base type at runtime.
 * The payload type must inherit from {@link Scraper}
 * @param <P> the payload base type.
 */
@SuppressWarnings("unused")
public class Payload<P extends Scraper<P>>  {
    private static final String ERRF = "%s: %s%n";

    private final Class<? extends P> ptype;

    private P payload;

    /**
     * Constructor that establishes the base type with a runtime type token of type {@link Class<P>}.
     *
     */
    public Payload(Class<? extends P> ptype) {
        if (ptype == null) {
            throw new NullPointerException(
                    format(ERRF, "Null type token", "Provide a type token"));
        }
        this.ptype = ptype;
    }

    /**
     * Payload accessor.
     * @return the payload.
     */
    public P getPayload() {
        return copy(payload);
    }

    /**
     * Payload mutator with defensive copy.
     * @param payload the payload to carry.
     */
    public void setPayload(P payload) {
        this.payload = copy(payload);
    }

    private P copy(P payload) {
        assert ptype != null;

        final String EXCF = "%s: %s%n payload {%s}%n";
        try {
            P copied = ptype.getConstructor().newInstance();
            copied.scrape(payload);
            return copied;
        }
        catch (InstantiationException
               | IllegalAccessException
               | InvocationTargetException
               | NoSuchMethodException exc) {
            throw new IllegalStateException(format(EXCF,
                exc.getClass().getSimpleName(), exc.getMessage(), payload),
                exc);
        }
    }
}
