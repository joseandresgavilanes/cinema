package Rooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.ei.esoft.models.Room;
import pt.ipleiria.estg.dei.ei.esoft.models.SoundSystem;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    private Room room;

    @BeforeEach
    public void setUp() {
        room = new Room(
                "Sala A",
                100,
                true,
                SoundSystem.SURROUND
        );
    }

    @Test
    public void testInitialState() {
        assertAll("Constructor y getters",
                () -> assertEquals("Sala A", room.getName(),        "Nombre incorrecto"),
                () -> assertEquals(100,    room.getCapacity(),      "Capacidad incorrecta"),
                () -> assertTrue(          room.getAccessibility(), "Accesibilidad incorrecta"),
                () -> assertEquals(SoundSystem.SURROUND, room.getSoundSystem(), "Sistema de sonido incorrecto")
        );
    }

    @Test
    public void testSetters() {
        room.setName("Sala B");
        room.setCapacity(75);
        room.setAccessibility(false);
        room.setSoundSystem(SoundSystem.STEREO);

        assertAll("Setters",
                () -> assertEquals("Sala B",    room.getName()),
                () -> assertEquals(75,          room.getCapacity()),
                () -> assertFalse(              room.getAccessibility()),
                () -> assertEquals(SoundSystem.STEREO, room.getSoundSystem())
        );
    }

    @Test
    public void testToString() {
        assertEquals("Sala A - 100 seats", room.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Room copy = new Room("Sala A", 100, true, SoundSystem.SURROUND);
        assertEquals(room, copy,          "Deberían ser iguales");
        assertEquals(room.hashCode(), copy.hashCode(), "Hash codes deben coincidir");

        Room diff = new Room("Sala A", 80, true, SoundSystem.SURROUND);
        assertNotEquals(room, diff,      "Capacidades distintas no deben ser iguales");
    }
}