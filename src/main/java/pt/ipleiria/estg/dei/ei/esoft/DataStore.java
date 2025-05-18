package pt.ipleiria.estg.dei.ei.esoft;

import pt.ipleiria.estg.dei.ei.esoft.models.*;

import java.util.*;


public class DataStore {
    private static DataStore instance;

    private final List<Movie> movies = new ArrayList<>();
    private final List<Session> sessions = new ArrayList<>();
    private final List<Room> rooms = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();

    private DataStore() {
        loadDummyData();
    }

    public static synchronized DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    private void loadDummyData() {
        // —– Rooms —–
        Room r1 = new Room("Room 1", 50, true, SoundSystem.DOLBY_ATMOS);
        Room r2 = new Room("Room 2", 30, false, SoundSystem.STEREO);
        Room r3 = new Room("Room 3", 20, false, SoundSystem.DTS);
        Room r4 = new Room("Room 4", 100, true, SoundSystem.SURROUND);
        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);
        rooms.add(r4);

        // —– Movies —–
        Movie m1 = new Movie("Matrix", "Classic science fiction", 136, Genre.SCI_FI, Language.ENGLISH, "/images/matrix.jpg");
        Movie m2 = new Movie("Amelie", "French romantic comedy", 122, Genre.COMEDY, Language.ENGLISH, "/images/amelie.jpg");
        Movie m3 = new Movie("The Godfather", "Italian-American crime family drama", 175, Genre.DRAMA, Language.ENGLISH, "/images/godfather.jpg");
        Movie m4 = new Movie("Spirited Away", "Japanese animated fantasy adventure", 125, Genre.ANIMATION, Language.PORTUGUESE, "/images/spiritedaway.jpg");
        Movie m5 = new Movie("Parasite", "South Korean thriller about social inequality", 132, Genre.THRILLER, Language.PORTUGUESE, "/images/parasite.jpg");
        Movie m6 = new Movie("Coco", "Animated adventure inspired by the Day of the Dead", 105, Genre.ANIMATION, Language.SPANISH, "/images/coco.jpg");
        Movie m7 = new Movie("Interstellar", "Epic space travel and human survival", 169, Genre.SCI_FI, Language.ENGLISH, "/images/interstellar.jpg");
        movies.addAll(Arrays.asList(m1, m2, m3, m4, m5, m6, m7));

        // —– Sessions —–
        sessions.add(new Session("Matrix – Room 1 (18:00)", m1, r1, "2025-05-20 18:00"));
        sessions.add(new Session("Amelie – Room 2 (20:30)", m2, r2, "2025-05-20 20:30"));
        sessions.add(new Session("The Godfather – Room 3 (21:00)", m3, r3, "2025-05-21 21:00"));
        sessions.add(new Session("Spirited Away – Room 1 (16:00)", m4, r1, "2025-05-21 16:00"));
        sessions.add(new Session("Parasite – Room 4 (22:00)", m5, r4, "2025-05-22 22:00"));
        sessions.add(new Session("Coco – Room 2 (14:00)", m6, r2, "2025-05-22 14:00"));
        sessions.add(new Session("Interstellar – Room 4 (19:00)", m7, r4, "2025-05-23 19:00"));

        // —– Products —–
        products.add(new Product("Popcorn", "Salted popcorn", ProductBarCategory.SNACK.getDisplayName(), 2.50));
        products.add(new Product("Soda", "Carbonated soft drink", ProductBarCategory.DRINK.getDisplayName(), 1.75));
        products.add(new Product("Nachos", "Cheesy nachos", ProductBarCategory.FOOD.getDisplayName(), 3.00));
        products.add(new Product("Hot Dog", "Classic hot dog with mustard", ProductBarCategory.FOOD.getDisplayName(), 3.50));
        products.add(new Product("Iced Tea", "Refreshing lemon iced tea", ProductBarCategory.DRINK.getDisplayName(), 2.00));
        products.add(new Product("Candy", "Assorted sweets", ProductBarCategory.SNACK.getDisplayName(), 1.25));
    }

    public List<Movie> getMovies() { return Collections.unmodifiableList(movies); }
    public List<Session> getSessions() { return Collections.unmodifiableList(sessions); }
    public List<Room> getRooms() { return Collections.unmodifiableList(rooms); }
    public List<Product> getProducts() { return Collections.unmodifiableList(products); }

    public void addMovie(Movie m) { movies.add(m); }
    public void addSession(Session s) { sessions.add(s); }
    public void addRoom(Room r) { rooms.add(r); }
    public void addProduct(Product p) { products.add(p); }
}
