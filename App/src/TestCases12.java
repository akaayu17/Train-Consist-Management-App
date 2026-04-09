import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UC12Test {

    // GoodsBogie model for testing
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }
    }


    @Test
    void testSafety_AllBogiesValid() {
        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open", "Coal"));
        goodsBogies.add(new GoodsBogie("Box", "Grain"));

        boolean isSafe = goodsBogies.stream()
                .allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));

        assertTrue(isSafe, "All bogies comply with safety rules; should be safe");
    }

    @Test
    void testSafety_CylindricalWithInvalidCargo() {
        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Coal")); // violation

        boolean isSafe = goodsBogies.stream()
                .allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));

        assertFalse(isSafe, "Cylindrical bogie carrying Coal should fail safety check");
    }

    @Test
    void testSafety_NonCylindricalBogiesAllowed() {
        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Open", "Coal"));
        goodsBogies.add(new GoodsBogie("Box", "Grain"));

        boolean isSafe = goodsBogies.stream()
                .allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));

        assertTrue(isSafe, "Non-cylindrical bogies can carry any cargo; should be safe");
    }

    @Test
    void testSafety_MixedBogiesWithViolation() {
        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Coal")); // violation
        goodsBogies.add(new GoodsBogie("Box", "Grain"));

        boolean isSafe = goodsBogies.stream()
                .allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));

        assertFalse(isSafe, "At least one cylindrical bogie violates rule; train should be unsafe");
    }

    @Test
    void testSafety_EmptyBogieList() {
        List<GoodsBogie> goodsBogies = new ArrayList<>();

        boolean isSafe = goodsBogies.stream()
                .allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));

        assertTrue(isSafe, "Empty list has no violations; should be safe");
    }
}