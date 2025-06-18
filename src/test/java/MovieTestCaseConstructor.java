import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.ei.esoft.models.Genre;
import pt.ipleiria.estg.dei.ei.esoft.models.Language;
import pt.ipleiria.estg.dei.ei.esoft.models.Movie;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTestCaseConstructor {
    Movie movie;
    @BeforeEach
    public void setUp() {
        movie = new Movie("Matrix", "Classic science fiction", 136, Genre.SCI_FI, Language.ENGLISH, "/images/matrix_movie.png");

    }

    @Test
    public void testGetTitle() {

        assertEquals("Matrix", movie.getTitle());
    }

    @Test
    public void testGetDescription() {

        assertEquals("Classic science fiction", movie.getDescription());
    }

    @Test
    public void testGetDuration() {

        assertEquals(136, movie.getDuration());
    }

    @Test
    public void getGenre() {
        assertEquals(Genre.SCI_FI, movie.getGenre());
    }

    @Test
    public void getLanguage() {
        assertEquals(Language.ENGLISH, movie.getLanguage());
    }

    @Test
    public void getImagePath() {
        assertEquals("/images/matrix_movie.png", movie.getImagePath());
    }


}
