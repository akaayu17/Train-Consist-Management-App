import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TestCases10 {

    @Test
    void testFilter_CapacityGreaterThanThreshold() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56)
        );
        int threshold = 60;

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.getCapacity() > threshold)
                .toList();

        assertEquals(1, filtered.size());
        assertEquals("Sleeper", filtered.get(0).getName());
    }

    @Test
    void testFilter_CapacityEqualToThreshold() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56)
        );
        int threshold = 56;

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.getCapacity() == threshold)
                .toList();

        assertEquals(1, filtered.size());
        assertEquals("AC Chair", filtered.get(0).getName());
    }

    @Test
    void testFilter_CapacityLessThanThreshold() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56)
        );
        int threshold = 60;

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.getCapacity() < threshold)
                .toList();

        assertEquals(1, filtered.size());
        assertEquals("AC Chair", filtered.get(0).getName());
    }

    @Test
    void testFilter_MultipleBogiesMatching() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("Sleeper", 70)
        );
        int threshold = 60;

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.getCapacity() > threshold)
                .toList();

        assertEquals(2, filtered.size());
    }

    @Test
    void testFilter_NoBogiesMatching() {
        List<Bogie> bogies = List.of(
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24)
        );
        int threshold = 60;

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.getCapacity() > threshold)
                .toList();

        assertTrue(filtered.isEmpty());
    }

    @Test
    void testFilter_AllBogiesMatching() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56)
        );
        int threshold = 50;

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.getCapacity() > threshold)
                .toList();

        assertEquals(bogies.size(), filtered.size());
    }

    @Test
    void testFilter_EmptyBogieList() {
        List<Bogie> bogies = new ArrayList<>();
        int threshold = 50;

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.getCapacity() > threshold)
                .toList();

        assertTrue(filtered.isEmpty());
    }

    @Test
    void testFilter_OriginalListUnchanged() {
        List<Bogie> bogies = new ArrayList<>(List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56)
        ));
        List<Bogie> copy = new ArrayList<>(bogies);

        bogies.stream().filter(b -> b.getCapacity() > 50).toList();

        assertEquals(copy.size(), bogies.size());
        for (int i = 0; i < bogies.size(); i++) {
            assertEquals(copy.get(i).getName(), bogies.get(i).getName());
            assertEquals(copy.get(i).getCapacity(), bogies.get(i).getCapacity());
        }
    }

    // -----------------------------
    // UC10 Reduce/Aggregation Tests
    // -----------------------------

    @Test
    void testReduce_TotalSeatCalculation() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24),
                new Bogie("Sleeper", 70),
                new Bogie("AC Chair", 60)
        );

        int totalCapacity = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        assertEquals(72 + 56 + 24 + 70 + 60, totalCapacity);
    }

    @Test
    void testReduce_MultipleBogiesAggregation() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("Sleeper", 70),
                new Bogie("AC Chair", 56)
        );

        int totalCapacity = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        assertEquals(72 + 70 + 56, totalCapacity);
    }

    @Test
    void testReduce_SingleBogieCapacity() {
        List<Bogie> bogies = List.of(
                new Bogie("First Class", 24)
        );

        int totalCapacity = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        assertEquals(24, totalCapacity);
    }

    @Test
    void testReduce_EmptyBogieList() {
        List<Bogie> bogies = new ArrayList<>();

        int totalCapacity = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        assertEquals(0, totalCapacity);
    }

    @Test
    void testReduce_CorrectCapacityExtraction() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56)
        );

        List<Integer> capacities = bogies.stream()
                .map(Bogie::getCapacity)
                .toList();

        assertEquals(List.of(72, 56), capacities);
    }

    @Test
    void testReduce_AllBogiesIncluded() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24)
        );

        int totalCapacity = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        int expectedSum = bogies.stream()
                .mapToInt(Bogie::getCapacity)
                .sum();

        assertEquals(expectedSum, totalCapacity);
    }

    @Test
    void testReduce_OriginalListUnchanged() {
        List<Bogie> bogies = new ArrayList<>(List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56)
        ));

        List<Bogie> copy = new ArrayList<>(bogies);

        bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        assertEquals(copy.size(), bogies.size());
        for (int i = 0; i < bogies.size(); i++) {
            assertEquals(copy.get(i).getName(), bogies.get(i).getName());
            assertEquals(copy.get(i).getCapacity(), bogies.get(i).getCapacity());
        }
    }
}
