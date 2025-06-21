package pt.ipleiria.estg.dei.ei.esoft.models;

import java.util.Objects;

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

    @Override
    public String toString() {
        return name + " - " + capacity + " seats";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name)
                && Objects.equals(capacity, room.capacity)
                && Objects.equals(accessibility, room.accessibility)
                && soundSystem == room.soundSystem;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity, accessibility, soundSystem);
    }

}
