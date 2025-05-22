package pt.ipleiria.estg.dei.ei.esoft.models;


public class SessionManager {
    private static User currentUser;

    /** Registra el usuario que acaba de hacer login */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /** Devuelve el usuario logeado, o null si no hay ninguno */
    public static User getCurrentUser() {
        return currentUser;
    }

    /** Booleano de conveniencia */
    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    /** Limpia la sesión (logout) */
    public static void clearSession() {
        currentUser = null;
    }
}
