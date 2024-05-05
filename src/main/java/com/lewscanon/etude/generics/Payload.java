/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.generics;

//import java.lang.reflect.InvocationTargetException;
//
//import static java.lang.String.format;
//
///**
// * A payload type that enforces its own base type at runtime.
// * The payload type must inherit from {@link Scraper}
// * @param <P> the payload base type.
// */
@SuppressWarnings("unused")
public class Payload<P> /* implements Scraper<P> */ {
//    private static final String ERRF = "%s: %s%n";
//
//    private final Class<P> ptype;
//
//    private P payload;
//
//    /**
//     * Constructor that establishes the base type with a runtime type token of type {@link Class<P>}.
//     *
//     */
//    public Payload(Class<P> ptype) {
//        if (ptype == null) {
//            throw new NullPointerException(format(ERRF, "Null type token", "Provide a type token"));
//        }
//        this.ptype = ptype;
//    }
//
//    /**
//     * Payload accessor.
//     * @return the payload.
//     */
//    public P getPayload() {
//        return payload;
//    }
//
//    /**
//     * Payload mutator with defensive copy.
//     * @param payload the payload to carry.
//     */
//    public void setPayload(P payload) {
//        this.payload = copy(payload);
//    }
//
//    private P copy(P payload) {
//        assert ptype != null;
//
//        final String EXCF = "%s: %s%n payload {%s}%n";
//        try {
//            P copied = ptype.getDeclaredConstructor().newInstance();
//            copied.scrape(payload);
//            return copied;
//        }
//        catch (InstantiationException
//               | IllegalAccessException
//               | InvocationTargetException
//               | NoSuchMethodException exc) {
//            throw new IllegalStateException(format(EXCF,
//                exc.getClass().getSimpleName(), exc.getMessage(), payload),
//                exc);
//        }
//    }
}

///**
// * "Scrape" the contents from a source object into this as a copy.
// * Adapt types to copy their contents or values into a custom type.
// * @param <T> the type to scrape from.
// */
//interface Scraper<T> {
//    /**
//     * Scrape the contents of the argument into oneself.
//     * @param from the object to scrape.
//     */
//    void scrape(T from);
//}
