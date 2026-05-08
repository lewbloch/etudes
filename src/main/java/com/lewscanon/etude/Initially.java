package com.lewscanon.etude;

public class Initially {
    private static final String FMT = "\"%s\": {\"initiali\": %d, \"initials\": \"%s\"}";
    private static final String FMT_NULL = "\"%s\": {\"initiali\": %d, initials: %s}";

    private final int initiali;
    private final String initials;

    private final String represent;

    public Initially(int initiali, String initials) {
        this.initiali = initiali;
        this.initials = initials;
        if (this.initials == null) {
            represent = String.format(FMT_NULL, getClass().getSimpleName(), getInitiali(), null);
            return;
        }
        represent = String.format(FMT, getClass().getSimpleName(), getInitiali(), getInitials());
    }

    public Initially(int initiali) {
        this(initiali, null);
    }

    public Initially() {
        this(0);
    }

    @Override
    public String toString() {
        return represent;
    }

    final
    public int getInitiali() {
        return initiali;
    }

    final
    public String getInitials() {
        return initials;
    }

    public static void main(String... args) {
        final String FORM = "%s -> %s\n";

        System.out.printf(FORM, "new Initially()", new Initially());
        System.out.printf(FORM, "new Initially(0)", new Initially(0));
        System.out.printf(FORM, "new Initially(1)", new Initially(1));
        System.out.printf(FORM, "new Initially(0, null)", new Initially(0, null));
        System.out.printf(FORM, "new Initially(0, \"null\")", new Initially(0, "null"));
        System.out.printf(FORM, "new Initially(1, null)", new Initially(1, null));
        System.out.printf(FORM, "new Initially(1, \"null\")", new Initially(1, "null"));
        System.out.printf(FORM, "new Initially(0, \"\")", new Initially(0, ""));
        System.out.printf(FORM, "new Initially(17, \"\")", new Initially(17, ""));
    }
}
