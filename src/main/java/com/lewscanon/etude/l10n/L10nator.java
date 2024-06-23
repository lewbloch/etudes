/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.l10n;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Set;

import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;
import static java.util.Locale.FRENCH;
import static java.util.Locale.JAPANESE;
import static java.util.Locale.UK;
import static java.util.Locale.US;

public class L10nator {
    record LocalFormatter(Locale locale, DateTimeFormatter formatter) {}
    static final Set<LocalFormatter> localFormatters = Set.of(
        new LocalFormatter(Locale.getDefault(), ofLocalizedDateTime(FormatStyle.LONG).localizedBy(Locale.getDefault())),
        new LocalFormatter(FRENCH, ofLocalizedDateTime(FormatStyle.LONG).localizedBy(FRENCH)),
        new LocalFormatter(JAPANESE, ofLocalizedDateTime(FormatStyle.LONG).localizedBy(JAPANESE)),
        new LocalFormatter(UK, ofLocalizedDateTime(FormatStyle.LONG).localizedBy(UK)),
        new LocalFormatter(US, ofLocalizedDateTime(FormatStyle.LONG).localizedBy(US))
    );

    public static void main(String... args) {
        final String SHOWIT = "Time per %s: %s\n";

        final ZonedDateTime rightNow = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        for (final LocalFormatter formatter : localFormatters) {
            System.out.printf(formatter.locale, SHOWIT, formatter.locale.getDisplayName(),
                              rightNow.format(formatter.formatter));
        }
    }
}
