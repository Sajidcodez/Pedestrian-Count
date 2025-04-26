import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedCountsRecordTest {
		private PedCountFileReader reader;
		private PedCountRecords records;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("testing class");
	}

	@BeforeEach
	void setUp() throws Exception {
		 reader = new PedCountFileReader();
	        records = new PedCountRecords();
	        List<PedCount> list = reader.readPedCounts("Brooklyn_Bridge_Automated_Pedestrian_Counts.csv");
	        for (PedCount p : list) {
	            records.addPedCount(p);
	    }
	}

	@Test
    void testFileLoads() {
        List<PedCount> pedCounts = reader.readPedCounts("Brooklyn_Bridge_Automated_Pedestrian_Counts.csv");
        assertTrue(!pedCounts.isEmpty(), "The list shouldnt be empty");
    }

    @Test
    void testRecordCountMatches() {
        int expectedSize = reader.readPedCounts("Brooklyn_Bridge_Automated_Pedestrian_Counts.csv").size();
        assertEquals(expectedSize, records.getNumRecords(), "Record count should match file data");
    }

    @Test
    void testGetTotalCountByDay() {
        int mondayTotal = records.getTotalCountByDay("MONDAY");
        assertTrue(mondayTotal >= 0, "Total pedestrian count for Monday should be a positive not a negative");
    }

    @Test
    void testGetAverageByDay() {
        double thursdayAverage = records.getAverageByDay("THURSDAY");
        assertTrue(thursdayAverage >= 0, "Average pedestrian count for Thrusday should be positive not a negative");
    }

    @Test
    void testGetWeatherTotalsToManhattan() {
        Map<String, Integer> weatherMap = records.getWeatherTotals(PedCountRecords.TO_MANHATTAN);
        assertNotNull(weatherMap, "Weather map for Manhattan should not be null");
        assertFalse(weatherMap.isEmpty(), "Weather map for Manhattan should not be empty");
    }

    @Test
    void testGetWeatherTotalsToBrooklyn() {
        Map<String, Integer> weatherMap = records.getWeatherTotals(PedCountRecords.TO_BROOKLYN);
        assertNotNull(weatherMap, "Weather map for Brooklyn should not be null");
        assertFalse(weatherMap.isEmpty(), "Weather map for Brooklyn should not be empty");
    }

    @Test
	void testInvalidFileNameReturnsEmptyList() {
	    List<PedCount> result = reader.readPedCounts("non existent_file.csv");
	    assertTrue(result.isEmpty(), "Reading from a non existent file should return an empty list");
	}
	}