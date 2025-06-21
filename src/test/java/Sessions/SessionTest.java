package Sessions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.ei.esoft.models.*;

import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {

    private Movie movie;
    private Room room;
    private Session session;

    @BeforeEach
    public void setUp() {
        movie = new Movie(
                "Matrix",
                "Classic science fiction",
                136,
                Genre.SCI_FI,
                Language.ENGLISH,
                "/images/matrix.png"
        );
        room = new Room(
                "Sala A",
                100,
                true,
                SoundSystem.SURROUND
        );
        session = new Session(
                "Morning Show",
                movie,
                room,
                "2025-07-01T10:00"
        );
    }

    @Test
    public void testInitialStateAndToString() {
        // 1) Constructor + getters + toString()
        assertAll("Constructor, getters y toString",  // agrupar con assertAll :contentReference[oaicite:0]{index=0}
                () -> assertEquals("Morning Show",                   session.getName()),
                () -> assertEquals(movie,                            session.getMovie()),
                () -> assertEquals(room,                             session.getRoom()),
                () -> assertEquals("2025-07-01T10:00",               session.getSchedule()),
                () -> assertEquals(0,                                session.getSessionId()),
                () -> assertEquals(
                        "Session{sessionId=0, name='Morning Show', movie=Matrix, room=Sala A, schedule='2025-07-01T10:00'}",
                        session.toString()
                )
        );
    }

    @Test
    public void testSetters() {
        // 2) Mutabilidad: cambiamos valores con setters
        session.setSessionId(42);
        session.setName("Evening Show");
        Movie m2 = new Movie("Inception", "Thriller", 148, Genre.THRILLER, Language.ENGLISH, "/images/inception.png");
        Room   r2 = new Room("Sala B", 80, false, SoundSystem.STEREO);
        session.setMovie(m2);
        session.setRoom(r2);
        session.setSchedule("2025-07-01T20:00");

        assertAll("Setters funcionan correctamente",
                () -> assertEquals(42,                             session.getSessionId()),
                () -> assertEquals("Evening Show",                 session.getName()),
                () -> assertEquals(m2,                             session.getMovie()),
                () -> assertEquals(r2,                             session.getRoom()),
                () -> assertEquals("2025-07-01T20:00",             session.getSchedule())
        );
    }

    @Test
    public void testCalculatePrice() {
        // 3) Lógica: calculatePrice añade el ajuste según el SoundSystem
        double base = TicketType.SENIOR.getBasePrice();
        // para SURROUND el ajuste es +1.0
        assertEquals(base + 1.0, session.calculatePrice(TicketType.SENIOR), 1e-6);
    }

    @Test
    public void testEqualsAndHashCode() {
        // 4) equals/hashCode basados en sessionId
        Session copy = new Session("Morning Show", movie, room, "2025-07-01T10:00");
        copy.setSessionId(session.getSessionId());
        assertEquals(session,     copy,           "Objetos con mismo sessionId deben ser iguales");
        assertEquals(session.hashCode(), copy.hashCode(), "hashCode debe coincidir para iguales");

        Session diff = new Session("Morning", movie, room, "2025-07-01T10:00");
        diff.setSessionId(session.getSessionId() + 1);
        assertNotEquals(session, diff, "sessionId distinto ⇒ objetos no iguales");
    }
}