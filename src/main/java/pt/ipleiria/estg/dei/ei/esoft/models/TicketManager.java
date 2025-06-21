package pt.ipleiria.estg.dei.ei.esoft.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    public static List<Ticket> buyTickets(Session session,
                                          Map<TicketType, Integer> ticketMap,
                                          List<String> selectedSeats,
                                          User user,
                                          String paymentMethod) throws IllegalArgumentException {
        int totalRequested = ticketMap.values().stream().mapToInt(Integer::intValue).sum();

        if (totalRequested != selectedSeats.size()) {
            throw new IllegalArgumentException("La cantidad de tickets no coincide con los asientos seleccionados.");
        }

        List<Ticket> createdTickets = new ArrayList<>();
        Iterator<String> seatIterator = selectedSeats.iterator();

        for (Map.Entry<TicketType, Integer> entry : ticketMap.entrySet()) {
            TicketType type = entry.getKey();
            int quantity = entry.getValue();

            for (int i = 0; i < quantity; i++) {
                if (!seatIterator.hasNext()) {
                    throw new IllegalArgumentException("Faltan asientos.");
                }

                String seat = seatIterator.next();

                if (isSeatTaken(session, seat)) {
                    throw new IllegalArgumentException("El asiento " + seat + " ya está ocupado.");
                }

                Ticket ticket = new Ticket(
                        user.getUsername(),
                        user.getDocument(),
                        paymentMethod,
                        seat,
                        type,
                        session
                );

                user.addTicket(ticket);            // Asocia al usuario
                TicketManager.addTicket(ticket);   // Guarda globalmente
                createdTickets.add(ticket);
            }
        }

        return createdTickets;
    }

}
