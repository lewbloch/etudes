/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.collexion;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public class IntersectGenerian {
    public static <E> Collection<E> intersect(Collection<E> left, Collection<E> right) {
        final Collection<E> common = new TreeSet<>(left);
        @SuppressWarnings("unused")
        final boolean wasChanged = common.retainAll(right);
        return Collections.unmodifiableCollection(common);
    }

    public static void main(String... args) {
        List<String> left = List.of("foo", "bar", "foo", "baz", "oof", "raz", "baz", "matazz");
        List<String> right = List.of("baR", "foo", "baz", "ooph", "raz", "baz", "matazzz");
        Collection<String> common = intersect(left, right);
        System.out.printf("  left = %s%n right = %s%ncommon = %s%n%n", left, right, common);

        List<Character> lefch = List.of('f', 'b', 'f', 'b', 'z', 'o', 'r', 'b', 'm');
        List<Character> rigch = List.of('R', 'o', 'z', 'h', 'z', 'z', 'z');
        Collection<Character> comch = intersect(lefch, rigch);
        System.out.printf(" lefch = %s%n rigch = %s%n comch = %s%n%n", lefch, rigch, comch);

        List<Foo> leffoo = List.of(
                new Foo('b', "bar"),
                new Foo('b', "baz"),
                new Foo('b', "baz"),
                new Foo('f', "foo"),
                new Foo('f', "foo"),
                new Foo('m', "matazz"),
                new Foo('m', "matazzz"),
                new Foo('o', "raz"),
                new Foo('z', "matazz"),
                new Foo('z', "oof"),
                new Foo('z', "raz"),
                new Foo()
        );
        List<Foo> rigfoo = List.of(
                new Foo('R', "baR"),
                new Foo('h', "ooph"),
                new Foo('m', "matazz"),
                new Foo('o', "foo"),
                new Foo('z', "baz"),
                new Foo('z', "baz"),
                new Foo('z', "matazzz"),
                new Foo('z', "oof"),
                new Foo()
        );
        Collection<Foo> comfoo = intersect(leffoo, rigfoo);
        System.out.printf("leffoo = %s%nrigfoo = %s%ncomfoo = %s%n", leffoo, rigfoo, comfoo);
    }
}

class Foo implements Comparable<Foo> {
    static final String FMT = "{seq: %d, signif: \"%s\"}";
    final int seq;
    final String signif;
    final String repre;

    public Foo(int seq, String signif) {
        this.seq = seq;
        this.signif = signif == null ? "" : signif;
        this.repre = String.format(FMT, seq, signif);
    }

    public Foo() {
        this(-1, "");
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq, signif);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Foo other
                && this.seq == other.seq
                && this.signif.equals(other.signif);
    }

    @Override
    public String toString() {
        return repre;
    }

    @Override
    public int compareTo(Foo augend) {
        return augend == null ? 1
                : this.seq != augend.seq ? this.seq - augend.seq
                        : this.signif.compareTo(augend.signif);
    }
}
