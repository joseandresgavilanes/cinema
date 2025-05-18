package pt.ipleiria.estg.dei.ei.esoft.models;

public class Room {
    private int roomId;
    private String name;
    private Integer capacity;
    private Boolean accessibility ;
    private SoundSystem soundSystem;

    public Room(String name, Integer capacity, Boolean accessibility, SoundSystem soundSystem) {
        this.name = name;
        this.capacity = capacity;
        this.accessibility = accessibility;
        this.soundSystem = soundSystem;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Boolean accessibility) {
        this.accessibility = accessibility;
    }

    public SoundSystem getSoundSystem() {
        return soundSystem;
    }

    public void setSoundSystem(SoundSystem soundSystem) {
        this.soundSystem = soundSystem;
    }
}
