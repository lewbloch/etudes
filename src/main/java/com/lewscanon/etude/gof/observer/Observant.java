/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.gof.observer;

import java.util.Objects;

public record Observant(Actor<String> actor) implements Watcher<String>, AutoCloseable {
    /**
     * Constructor, injecting the {@link Actor}.
     *
     * @param actor the {@link Actor} to observe, non-{@code null}.
     */
    public Observant(Actor<String> actor) {
        this.actor = Objects.requireNonNull(actor, "actor must be non-null");
        register(this.actor);
    }

    @Override
    public void close() {
        assert actor != null;
        unregister(actor);
    }

    @Override
    public void observe(Actor.Event<String> event) {
        if (event != null) {
            System.out.printf("%s%n", event.payload());
        }
    }

    @Override
    public void register(Actor<String> actor) {
        assert actor != null;
        actor.recognize(this);
    }

    @Override
    public void unregister(Actor<String> actor) {
        assert actor != null;
        actor.unrecognize(this);
    }
}
