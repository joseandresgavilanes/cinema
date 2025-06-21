package pt.ipleiria.estg.dei.ei.esoft.models;

public enum SoundSystem {
    MONO("Mono"),
    STEREO("Stereo"),
    DOLBY_ATMOS("Dolby Atmos"),
    DTS("DTS"),
    SURROUND("Surround 5.1");

    private final String displayName;

    SoundSystem(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static SoundSystem fromString(String text) {
        for (SoundSystem system : SoundSystem.values()) {
            if (system.name().equalsIgnoreCase(text) || system.displayName.equalsIgnoreCase(text)) {
                return system;
            }
        }
        throw new IllegalArgumentException("Invalid sound system: " + text);
    }
}
