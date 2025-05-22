package pt.ipleiria.estg.dei.ei.esoft.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    private int userId;
    private String username;
    private String password;
    private String email;
    private String document;
    private UserRole role;
    private final List<Ticket> tickets  = new ArrayList<>();
    private final List<Receipt> receipts = new ArrayList<>();

    public User(String username, String password, String email, String document) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.document = document;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }
    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public List<Receipt> getReceipts() {
        return Collections.unmodifiableList(receipts);
    }
    public void addReceipt(Receipt receipt) {
        this.receipts.add(receipt);
    }

}
