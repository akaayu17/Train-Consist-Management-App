import java.util.ArrayList;
import java.util.List;

/**
 *
 * Use Case 12: Safety Compliance Check for Goods Bogies
 *
 * Description:
 * This class enforces domain safety rules on goods bogies.
 * - Creates goods bogie list
 * - Converts list into stream
 * - Applies safety validation rule
 * - Checks compliance using allMatch()
 * - Displays safety status
 *
 * This maps real-world cargo safety rules using Streams.
 *
 * Author: Aayusha Kuikel
 * Version: 12.0
 */
public class UC12 {

    // Goods Bogie model
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }

        @Override
        public String toString() {
            return type + " --> " + cargo;
        }
    }

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println(" UC12 - Safety Compliance Check for Goods Bogies ");
        System.out.println("============================================\n");

        // Create goods bogie List
        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open", "Coal"));
        goodsBogies.add(new GoodsBogie("Box", "Grain"));

        System.out.println("Goods Bogies in Train:");
        goodsBogies.forEach(System.out::println);

        // Safety validation: Cylindrical bogies must carry only Petroleum
        boolean isSafe = goodsBogies.stream()
                .allMatch(bogie -> !bogie.type.equals("Cylindrical") || bogie.cargo.equals("Petroleum"));

        System.out.println("\nSafety Compliance Check:");
        if (isSafe) {
            System.out.println("All goods bogies comply with safety rules. Train is SAFE.");
        } else {
            System.out.println("Safety violation detected! Train is UNSAFE.");
        }
    }
}