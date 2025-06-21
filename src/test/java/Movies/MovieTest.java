package Movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.ei.esoft.models.Genre;
import pt.ipleiria.estg.dei.ei.esoft.models.Language;
import pt.ipleiria.estg.dei.ei.esoft.models.Movie;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
    private Movie movie;

    @BeforeEach
    public void setUp() {
        movie = new Movie(
                "Matrix",
                "Classic science fiction",
                136,
                Genre.SCI_FI,
                Language.ENGLISH,
                "/images/matrix_movie.png"
        );
    }

    @Test
    public void testInitialState() {
        assertAll("Constructor y getters",
                () -> assertEquals("Matrix", movie.getTitle()),
                () -> assertEquals("Classic science fiction", movie.getDescription()),
                () -> assertEquals(136, movie.getDuration()),
                () -> assertEquals(Genre.SCI_FI, movie.getGenre()),
                () -> assertEquals(Language.ENGLISH, movie.getLanguage()),
                () -> assertEquals("/images/matrix_movie.png", movie.getImagePath())
        );
    }

    @Test
    public void testSetters() {
        movie.setTitle("Inception");
        movie.setDescription("Mind-bending thriller");
        movie.setDuration(148);
        movie.setGenre(Genre.THRILLER);
        movie.setLanguage(Language.PORTUGUESE);
        movie.setImagePath("/images/inception.png");

        assertAll("Setters",
                () -> assertEquals("Inception", movie.getTitle()),
                () -> assertEquals("Mind-bending thriller", movie.getDescription()),
                () -> assertEquals(148, movie.getDuration()),
                () -> assertEquals(Genre.THRILLER, movie.getGenre()),
                () -> assertEquals(Language.PORTUGUESE, movie.getLanguage()),
                () -> assertEquals("/images/inception.png", movie.getImagePath())
        );
    }

    @Test
    public void testToString() {
        assertEquals("Matrix", movie.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Movie copy = new Movie("Matrix", "Classic science fiction", 136, Genre.SCI_FI, Language.ENGLISH, "/images/matrix_movie.png");
        assertEquals(movie, copy, "Objects with same data should be equal");
        assertEquals(movie.hashCode(), copy.hashCode(), "Hash codes of equal objects should match");

        Movie diff = new Movie("Matrix Reloaded", "Secuela", 138, Genre.SCI_FI, Language.ENGLISH, "/images/matrix2.png");
        assertNotEquals(movie, diff, "Objects with different data should not be equal");
    }
}

