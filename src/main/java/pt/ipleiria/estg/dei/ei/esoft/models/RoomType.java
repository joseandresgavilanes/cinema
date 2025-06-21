package pt.ipleiria.estg.dei.ei.esoft.models;

public enum RoomType {
    STANDARD_2D(0.0),
    ENHANCED_SOUND(1.5),
    THREE_D(2.0),
    FOUR_D(3.0);

    private final double priceAdjustment;

    RoomType(double priceAdjustment) {
        this.priceAdjustment = priceAdjustment;
    }

    public double getPriceAdjustment() {
        return priceAdjustment;
    }
}
