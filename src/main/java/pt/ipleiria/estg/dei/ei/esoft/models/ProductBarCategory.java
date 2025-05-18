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

    /**
     * Returns the display name of the category.
     */
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    /**
     * Parses a string into a ProductBarCategory enum constant, ignoring case.
     * Accepts either the enum name (e.g., "DRINK") or its display name (e.g., "Drink").
     *
     * @param text the input text
     * @return the matching ProductBarCategory enum constant
     * @throws IllegalArgumentException if no matching category is found
     */
    public static ProductBarCategory fromString(String text) {
        for (ProductBarCategory category : ProductBarCategory.values()) {
            if (category.name().equalsIgnoreCase(text) || category.displayName.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid product bar category: " + text);
    }
}
