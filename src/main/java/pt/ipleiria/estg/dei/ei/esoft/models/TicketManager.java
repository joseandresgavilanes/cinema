package pt.ipleiria.estg.dei.ei.esoft.models;

import java.util.ArrayList;
import java.util.List;

public class TicketManager {
    private static final List<Ticket> tickets = new ArrayList<>();

    public static void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public static List<Ticket> getTickets() {
        return new ArrayList<>(tickets);
    }

    public static void clearTickets() {
        tickets.clear();
    }

    public static int getTotalTickets() {
        return tickets.size();
    }

    public static boolean isSeatTaken(Session session, String seat) {
        return tickets.stream().anyMatch(t -> t.getSession().equals(session) && t.getSeat().equals(seat));
    }
}
