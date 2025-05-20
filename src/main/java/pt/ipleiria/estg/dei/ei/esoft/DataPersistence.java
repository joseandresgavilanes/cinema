// DataPersistence.java
package pt.ipleiria.estg.dei.ei.esoft;

import pt.ipleiria.estg.dei.ei.esoft.models.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class DataPersistence {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String MOVIES_FILE   = "movies.json";
    private static final String SESSIONS_FILE = "sessions.json";
    private static final String ROOMS_FILE    = "rooms.json";
    private static final String PRODUCTS_FILE = "products.json";

    public static void saveMovies(List<Movie> movies) {
        saveList(movies, MOVIES_FILE, new TypeToken<List<Movie>>(){}.getType());
    }
    public static List<Movie> loadMovies() {
        return loadList(MOVIES_FILE, new TypeToken<List<Movie>>(){}.getType());
    }

    public static void saveSessions(List<Session> sessions) {
        saveList(sessions, SESSIONS_FILE, new TypeToken<List<Session>>(){}.getType());
    }
    public static List<Session> loadSessions() {
        return loadList(SESSIONS_FILE, new TypeToken<List<Session>>(){}.getType());
    }

    public static void saveRooms(List<Room> rooms) {
        saveList(rooms, ROOMS_FILE, new TypeToken<List<Room>>(){}.getType());
    }
    public static List<Room> loadRooms() {
        return loadList(ROOMS_FILE, new TypeToken<List<Room>>(){}.getType());
    }

    public static void saveProducts(List<Product> products) {
        saveList(products, PRODUCTS_FILE, new TypeToken<List<Product>>(){}.getType());
    }
    public static List<Product> loadProducts() {
        return loadList(PRODUCTS_FILE, new TypeToken<List<Product>>(){}.getType());
    }

    private static <T> void saveList(List<T> list, String fileName, Type type) {
        try (Writer w = new FileWriter(fileName)) {
            GSON.toJson(list, type, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <T> List<T> loadList(String fileName, Type type) {
        File f = new File(fileName);
        if (!f.exists()) return null;
        try (Reader r = new FileReader(f)) {
            return GSON.fromJson(r, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
