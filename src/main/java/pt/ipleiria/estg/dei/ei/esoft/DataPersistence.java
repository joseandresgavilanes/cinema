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

    private static final String MOVIES_FILE    = "movies.json";
    private static final String SESSIONS_FILE  = "sessions.json";
    private static final String ROOMS_FILE     = "rooms.json";
    private static final String PRODUCTS_FILE  = "products.json";
    private static final String USERS_FILE     = "users.json";
    private static final String RECEIPTS_FILE  = "receipts.json";

    // --- Movies ---
    public static void saveMovies(List<Movie> movies) {
        saveList(movies, MOVIES_FILE, new TypeToken<List<Movie>>(){}.getType());
    }
    public static List<Movie> loadMovies() {
        return loadList(MOVIES_FILE, new TypeToken<List<Movie>>(){}.getType());
    }

    // --- Sessions ---
    public static void saveSessions(List<Session> sessions) {
        saveList(sessions, SESSIONS_FILE, new TypeToken<List<Session>>(){}.getType());
    }
    public static List<Session> loadSessions() {
        return loadList(SESSIONS_FILE, new TypeToken<List<Session>>(){}.getType());
    }

    // --- Rooms ---
    public static void saveRooms(List<Room> rooms) {
        saveList(rooms, ROOMS_FILE, new TypeToken<List<Room>>(){}.getType());
    }
    public static List<Room> loadRooms() {
        return loadList(ROOMS_FILE, new TypeToken<List<Room>>(){}.getType());
    }

    // --- Products ---
    public static void saveProducts(List<Product> products) {
        saveList(products, PRODUCTS_FILE, new TypeToken<List<Product>>(){}.getType());
    }
    public static List<Product> loadProducts() {
        return loadList(PRODUCTS_FILE, new TypeToken<List<Product>>(){}.getType());
    }

    // --- Users ---
    public static void saveUsers(List<User> users) {
        saveList(users, USERS_FILE, new TypeToken<List<User>>(){}.getType());
    }
    public static List<User> loadUsers() {
        return loadList(USERS_FILE, new TypeToken<List<User>>(){}.getType());
    }

    // --- Receipts (nuevo) ---
    public static void saveReceipts(List<Receipt> receipts) {
        saveList(receipts, RECEIPTS_FILE, new TypeToken<List<Receipt>>(){}.getType());
    }
    public static List<Receipt> loadReceipts() {
        return loadList(RECEIPTS_FILE, new TypeToken<List<Receipt>>(){}.getType());
    }

    // --- Métodos genéricos de guardado y carga ---
    private static <T> void saveList(List<T> list, String fileName, Type type) {
        try (Writer w = new FileWriter(fileName)) {
            GSON.toJson(list, type, w);
        } catch (IOException e) {
            System.err.println("Error saving file: " + fileName);
            e.printStackTrace();
        }
    }

    private static <T> List<T> loadList(String fileName, Type type) {
        File f = new File(fileName);
        if (!f.exists()) {
            // No existe el archivo, retornamos null para que DataStore maneje la carga dummy
            return null;
        }
        try (Reader r = new FileReader(f)) {
            return GSON.fromJson(r, type);
        } catch (IOException e) {
            System.err.println("Error loading file: " + fileName);
            e.printStackTrace();
            return null;
        }
    }
}
