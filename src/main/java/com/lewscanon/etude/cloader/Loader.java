package com.lewscanon.etude.cloader;

import java.util.List;

public class Loader {
    static <T> T choose(T a, T b) { return b; }
    public static void main(String[] args) {
        List<Integer> result = choose(List.of(4), List.of(1));
        System.out.println(result.getFirst().getClass().getSimpleName());
        System.out.println(result.getClass().getSimpleName());
    }
}