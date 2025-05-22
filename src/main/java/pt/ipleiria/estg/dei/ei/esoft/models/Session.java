package pt.ipleiria.estg.dei.ei.esoft.models;

public class Session {
    private int sessionId;
    private String name;
    private Movie movie;
    private Room room;
    private String schedule;


    public Session(String name, Movie movie, Room room, String schedule) {
        this.name = name;
        this.movie = movie;
        this.room = room;
        this.schedule = schedule;
    }

    public double calculatePrice(TicketType ticketType) {
        double base = ticketType.getBasePrice();
        double adjustment = getSoundSystemAdjustment(room.getSoundSystem());
        return base + adjustment;
    }

    private double getSoundSystemAdjustment(SoundSystem soundSystem) {
        switch (soundSystem) {
            case MONO:
                return 0.0;
            case STEREO:
                return 0.5;
            case SURROUND:
                return 1.0;
            case DTS:
                return 1.5;
            case DOLBY_ATMOS:
                return 2.0;
            default:
                return 0.0;
        }
    }

    public Movie getMovie() {
        return movie;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
