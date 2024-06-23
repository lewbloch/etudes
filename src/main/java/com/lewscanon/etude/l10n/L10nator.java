/*
 * Copyright © 2024, Lewis S. Bloch. All rights reserved.
 */
package com.lewscanon.etude.l10n;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;

public class L10nator {
    public static void main(String... args) {
        final Locale defloc = Locale.getDefault();
        final DateTimeFormatter defaultFormatter = ofLocalizedDateTime(FormatStyle.LONG).localizedBy(defloc);
        final Locale french = Locale.FRANCE;
        final DateTimeFormatter frenchFormatter = ofLocalizedDateTime(FormatStyle.LONG).localizedBy(french);
        final Locale us = Locale.US;
        final DateTimeFormatter usFormatter = ofLocalizedDateTime(FormatStyle.LONG).localizedBy(us);

        final String SHOWIT = "Time per %s: %s\n";

        final ZonedDateTime rightNow = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        System.out.printf(defloc, SHOWIT, defloc.getDisplayName(), rightNow.format(defaultFormatter));
        System.out.printf(french, SHOWIT, french.getDisplayName(), rightNow.format(frenchFormatter));
        System.out.printf(us, SHOWIT, us.getDisplayName(), rightNow.format(usFormatter));
    }
}
