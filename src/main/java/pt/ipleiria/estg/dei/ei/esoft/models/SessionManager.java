package pt.ipleiria.estg.dei.ei.esoft.models;

public class SessionManager {
    private static User currentUser;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void clearSession() {
        currentUser = null;
    }

    public static User getLoggedUser() {
        return currentUser;
    }
}
