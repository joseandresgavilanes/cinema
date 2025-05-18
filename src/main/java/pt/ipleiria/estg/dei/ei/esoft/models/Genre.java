package pt.ipleiria.estg.dei.ei.esoft.models;

public enum Genre {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    FAMILY("Family"),
    FANTASY("Fantasy"),
    HISTORY("History"),
    HORROR("Horror"),
    MUSIC("Music"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    SCI_FI("Science Fiction"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name (for UI).
     */
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    /**
     * Parses a string into a Genre, ignoring case.
     * Accepts either the enum name (e.g. "ACTION") or its display name (e.g. "Action").
     *
     * @param text the input text
     * @return matching Genre
     * @throws IllegalArgumentException if no genre matches
     */
    public static Genre fromString(String text) {
        for (Genre g : Genre.values()) {
            if (g.name().equalsIgnoreCase(text) || g.displayName.equalsIgnoreCase(text)) {
                return g;
            }
        }
        throw new IllegalArgumentException("Invalid genre: " + text);
    }
}
