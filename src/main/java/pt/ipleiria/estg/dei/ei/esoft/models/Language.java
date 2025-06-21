package pt.ipleiria.estg.dei.ei.esoft.models;

public enum Language {
    SPANISH("Spanish"),
    PORTUGUESE("Portuguese"),
    ENGLISH("English");

    private final String displayName;

    Language(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
