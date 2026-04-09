import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *

* MAIN CLASS - UseCase10TrainConsistMgmnt

* Use Case 10: Count Total Seats in Train

* Description:
        * This class aggregates seating capacity of all bogies
* into a single total using Stream reduce().

        * At this stage, the application:
        * - Creates bogie list
* - Maps bogies to capacity
* - Reduces values into total
* - Displays total seat count

* This maps aggregation logic using reduce().

 @author Aayusha Kuikel
* @version 10.0
        */

public class UC10{

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println(" UC10 - Count Total Seats in Train");
        System.out.println("============================================");

        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 70));
        bogies.add(new Bogie("AC Chair", 60));


        System.out.println("\nBogies in Train:");
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



