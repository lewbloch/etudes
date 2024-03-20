/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.gof.observer;

/** Watcher of {@link Actor.Event}s coming from an {@link Actor<P>}.
 * The {@link Watcher} registers with an {@link Actor}
 * that emits {@link Actor.Event}s of base type {@code P}.
 * @param <P>
 */
public interface Watcher<P> {
    /**
     * Register with an {@link Actor}.
     * @param actor Whom to register with.
     */
    void register(Actor<P> actor);

    /**
     * Unregister with an {@link Actor}.
     * @param actor Whom to unregister with.
     */
    void unregister(Actor<P> actor);

    /**
     * Observe an {@link Actor.Event}.
     * @param event Whom to unregister with.
     */
    void observe(Actor.Event<P> event);
}
