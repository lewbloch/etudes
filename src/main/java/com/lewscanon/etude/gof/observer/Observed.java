/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.gof.observer;

import java.util.HashSet;
import java.util.Set;

public class Observed implements Actor<String> {
    final Set<Watcher<String>> observers = new HashSet<>();

    @Override
    public void recognize(Watcher<String> watcher) {
        if (watcher != null) {
            observers.add(watcher);
        }
    }

    @Override
    public void unrecognize(Watcher<String> watcher) {
        if (watcher != null) {
            observers.remove(watcher);
        }
    }

    @Override
    public void alert(Event<String> event) {
        if (!observers.isEmpty()) {
            for (Watcher<String> observer : observers) {
                observer.observe(event);
            }
        }
    }
}
