
import java.util.*;
import java.util.stream.Collectors;
/**
* Use Case 9: Group Bogies by Type

* Description:
        * This class groups similar bogies together using
* Java Stream Collectors.groupingBy().

        * At this stage, the application:
        * - Creates a List of bogies
* - Streams the List
* - Groups bogies by name
* - Stores grouped data in a Map
* - Displays grouped structure

* This maps classification logic using groupingBy.

 @author Aayusha Kuikel
* @version 9.0
        */

public class UC9 {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println(" UC9 - Group Bogies by Type ");
        System.out.println("============================================");

        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 70));
        bogies.add(new Bogie("AC Chair", 60));


        System.out.println("\nAll Bogies:");
        for (Bogie b : bogies) {
            System.out.println(b);
        }


        Map<String, List<Bogie>> groupedBogies =
                bogies.stream()
                        .collect(Collectors.groupingBy(b -> b.name));


        System.out.println("\nGrouped Bogies:");
        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
