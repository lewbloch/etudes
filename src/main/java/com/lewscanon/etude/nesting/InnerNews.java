/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.nesting;

import java.util.Optional;

public class InnerNews {
    public class Contained {
        private final String name;
        private final String represent;

        public Contained(String name) {
            this.name = Optional.ofNullable(name).orElse("");
            this.represent = String.format("%s(\"%s\")", getClass().getSimpleName(), name);
        }

        @Override
        public String toString() {
            return represent;
        }
    }

    public static void main(String... args) {
        InnerNews.Contained contained = new InnerNews().new Contained("Anonymous outer");
        InnerNews news = new InnerNews();
        InnerNews.Contained explicit = news.new Contained("Explicit outer");
        System.out.println(contained);
        System.out.println(explicit);

        class OddChild extends InnerNews.Contained {
            public OddChild(String name) {
                new InnerNews().super(name);
            }
        }

        OddChild odd = new OddChild("Odd child");
        System.out.println(odd);
    }
}
