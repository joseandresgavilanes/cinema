package pt.ipleiria.estg.dei.ei.esoft.models;

public enum Language {
    SPANISH("Spanish"),
    PORTUGUESE("Portuguese"),
    ENGLISH("English");

    private final String displayName;

    Language(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the language.
     */
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    /**
     * Parses a string into a Language enum constant, ignoring case.
     * Accepts either the enum name (e.g., "SPANISH") or its display name (e.g., "Spanish").
     *
     * @param text the input text
     * @return the matching Language enum constant
     * @throws IllegalArgumentException if no matching Language is found
     */
    public static Language fromString(String text) {
        for (Language lang : Language.values()) {
            if (lang.name().equalsIgnoreCase(text) || lang.displayName.equalsIgnoreCase(text)) {
                return lang;
            }
        }
        throw new IllegalArgumentException("Invalid language: " + text);
    }
}
