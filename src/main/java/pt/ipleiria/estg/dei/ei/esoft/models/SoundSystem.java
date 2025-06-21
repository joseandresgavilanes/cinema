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
}
