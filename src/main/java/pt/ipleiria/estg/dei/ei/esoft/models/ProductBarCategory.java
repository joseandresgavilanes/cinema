package pt.ipleiria.estg.dei.ei.esoft.models;

public enum ProductBarCategory {
    DRINK("Drink"),
    FOOD("Food"),
    SNACK("Snack"),
    DESSERT("Dessert");

    private final String displayName;

    ProductBarCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
