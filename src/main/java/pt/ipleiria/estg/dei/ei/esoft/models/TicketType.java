package pt.ipleiria.estg.dei.ei.esoft.models;

public enum TicketType {
    STANDARD(6.0),
    STUDENT(4.5),
    SENIOR(3.5),
    VIP(10.0);

    private final double basePrice;

    TicketType(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase(); // Para mostrar "Standard", etc.
    }
}
