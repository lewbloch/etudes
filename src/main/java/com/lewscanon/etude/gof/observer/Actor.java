/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.gof.observer;

/**
 * Actor that generates events and alerts {@link Watcher}s.
 * @param <P> Event payload type.
 */
public interface Actor<P> {
    /**
     * Event with payload.
     * @param <P> Payload type.
     */
    record Event<P>(P payload) {}

    /**
     * Recognize a {@link Watcher}.
     *
     * @param watcher watcher to register.
     */
    void recognize(Watcher<P> watcher);

    /**
     * Unecognize a {@link Watcher}.
     *
     * @param watcher watcher to unregister.
     */
    void unrecognize(Watcher<P> watcher);

    /**
     * Alert all recognized {@link Watcher}s to an event.
     * @param event event to alert the {@link Watcher}s to.
     */
    void alert(Event<P> event);
}
