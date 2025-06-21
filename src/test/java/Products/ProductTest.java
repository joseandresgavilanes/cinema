package Products;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.ei.esoft.models.Product;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product("Combo1", "Hamburguer + fries", "combo", 5.99, "/images/combo1.png", 10);
    }

    @Test
    public void testInitialState() {
        assertAll("Constructor y getters",
                () -> assertEquals("Combo1", product.getName(),      "Nombre incorrecto"),
                () -> assertEquals("Hamburguer + fries", product.getDescription(), "Descripción incorrecta"),
                () -> assertEquals("combo", product.getCategory(),   "Categoría incorrecta"),
                () -> assertEquals(5.99, product.getPrice(), 1e-6,   "Precio incorrecto"),
                () -> assertEquals("/images/combo1.png", product.getPhoto(), "Ruta de imagen incorrecta")
        );
    }

    @Test
    public void testSetters() {
        product.setName("Snack1");
        product.setDescription("Patatas fritas");
        product.setCategory("snack");
        product.setPrice(2.49);
        product.setImagePath("/images/snack1.png");

        assertAll("Setters",
                () -> assertEquals("Snack1", product.getName()),
                () -> assertEquals("Patatas fritas", product.getDescription()),
                () -> assertEquals("snack", product.getCategory()),
                () -> assertEquals(2.49, product.getPrice(), 1e-6),
                () -> assertEquals("/images/snack1.png", product.getPhoto())
        );
    }

    @Test
    public void testToString() {
        assertEquals("Combo1", product.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Product copy = new Product("Combo1", "Hamburguer + fries", "combo", 5.99, "/images/combo1.png", 10);
        assertEquals(product, copy,     "Deberían ser iguales");
        assertEquals(product.hashCode(), copy.hashCode(), "Hash codes deben coincidir");

        Product diff = new Product("Combo2", "Hot dog", "snack", 3.49, "/images/hotdog.png", 10);
        assertNotEquals(product, diff,  "Deberían ser distintos");
    }
}