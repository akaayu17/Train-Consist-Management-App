import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * *:
 *
 * * MAIN CLASS - UseCase8TrainConsistMgmnt
 *
 * * Use Case 8: Filter Passenger Bogies Using Streams
 *
 * * Description:
 * * This class filters passenger bogies based on seating
 * * capacity using Java Stream API.
 *
 * * At this stage, the application:
 * * - Creates a List of bogies
 * * - Converts list into stremm
 * * - Applies filter condition
 * * - Collects filtered result
 * * - Displays qualifying bogies
 *
 * * This maps functional filtering using Streams,
 *
 * * @author Aayusha Kuikel
 * * @version 8.0
 * */


public class UC8 {

    public static void main(String[] args){
        System.out.println(" =====================================================");
        System.out.println("UC8 - Filter Passenger Bogies Using Streams ");
        System.out.println(" ====================================================\n");

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));
        System.out.println("All Bogies");
        for(Bogie b: bogies){
            System.out.println(b.name+" -> "+b.capacity);

        }

        System.out.println("Filtered Bogies (Capacity > 60)");

        bogies.stream().filter(bogie -> bogie.capacity>70).forEach(System.out::println);

    }
    public List<Bogie> filterBogies(List<Bogie> bogies) {
        return bogies.stream()
                .filter(b -> b.getCapacity() > 60)
                .collect(Collectors.toList());
    }


}