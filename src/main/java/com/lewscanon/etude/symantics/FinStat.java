/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.symantics;

public class FinStat {
    public static void main(String[] args) {
        for (String arg : new String[]
    {"lark", "subway", "literal", "\uD834\uDD1E\uD834\uDD1A\uD834\uDD07 Sing it!"}) {
            System.out.printf(FMT, "foo", arg, foo(arg));
        }
    }

    static final String FMT = "%s(\"%s\") => \"%s\"\n";

    @SuppressWarnings("FinalStaticMethod")
    final
    static String foo(String argue) {
        return argue == null? ""
            : new StringBuilder(argue).reverse().toString();
    }
}
