import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

class TestCases11 {

    // Regex patterns for validation
    private final Pattern trainIdPattern = Pattern.compile("TRN-\\d{4}");
    private final Pattern cargoCodePattern = Pattern.compile("PET-[A-Z]{2}");




    @Test
    void testRegex_ValidTrainID() {
        String trainId = "TRN-1234";
        assertTrue(trainIdPattern.matcher(trainId).matches());
    }

    @Test
    void testRegex_InvalidTrainIDFormat() {
        String[] invalidIds = {"TRAIN12", "TRN12A", "1234-TRN"};
        for (String id : invalidIds) {
            assertFalse(trainIdPattern.matcher(id).matches());
        }
    }

    @Test
    void testRegex_TrainIDDigitLengthValidation() {
        assertFalse(trainIdPattern.matcher("TRN-123").matches());   // too short
        assertFalse(trainIdPattern.matcher("TRN-12345").matches()); // too long
    }

    @Test
    void testRegex_EmptyTrainIDHandling() {
        assertFalse(trainIdPattern.matcher("").matches());
    }

    @Test
    void testRegex_ExactTrainIDPatternMatch() {
        assertFalse(trainIdPattern.matcher("TRN-1234XYZ").matches()); // extra chars
    }



    @Test
    void testRegex_ValidCargoCode() {
        String cargoCode = "PET-AB";
        assertTrue(cargoCodePattern.matcher(cargoCode).matches());
    }

    @Test
    void testRegex_InvalidCargoCodeFormat() {
        String[] invalidCodes = {"PET-ab", "PET123", "AB-PET"};
        for (String code : invalidCodes) {
            assertFalse(cargoCodePattern.matcher(code).matches());
        }
    }

    @Test
    void testRegex_CargoCodeUppercaseValidation() {
        assertFalse(cargoCodePattern.matcher("PET-ab").matches());
        assertFalse(cargoCodePattern.matcher("PET-Ac").matches());
    }

    @Test
    void testRegex_EmptyCargoCodeHandling() {
        assertFalse(cargoCodePattern.matcher("").matches());
    }

    @Test
    void testRegex_ExactCargoCodePatternMatch() {
        assertFalse(cargoCodePattern.matcher("PET-AB12").matches()); // extra chars
    }


    @Test
    void testRegex_OriginalInputUnchanged() {
        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        boolean trainValid = trainIdPattern.matcher(trainId).matches();
        boolean cargoValid = cargoCodePattern.matcher(cargoCode).matches();

        assertTrue(trainValid);
        assertTrue(cargoValid);

        // Ensure original strings remain unchanged
        assertEquals("TRN-1234", trainId);
        assertEquals("PET-AB", cargoCode);
    }
}
