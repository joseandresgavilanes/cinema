package pt.ipleiria.estg.dei.ei.esoft.models;

import java.util.Date;

public class Ticket {
    private Float price;
    private Session session;
    private String seat;
    private Date date;
    private String hour;

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Float getPrice() {
        return price;
    }

    public Session getSession() {
        return session;
    }

    public String getSeat() {
        return seat;
    }

    public Date getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }
}
