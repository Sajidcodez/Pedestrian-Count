import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

public class PedCountRecords {
    public static final String TO_MANHATTAN = "MANHATTAN";
    public static final String TO_BROOKLYN = "BROOKLYN";

    private Map<LocalDateTime, PedCount> records = new HashMap<>();

    public void addPedCount(PedCount pcount) {
        records.put(pcount.getDateTime(), pcount);
    }

    public Collection<PedCount> getAllRecords() {
        return records.values();
    }

    public int getNumRecords() {
        return records.size();
    }

    public int getTotalCountByDay(String day) {
        int total = 0;
        for (PedCount pcount : records.values()) {
            String dayofweek = pcount.getDateTime().getDayOfWeek().toString();
            if (dayofweek.equalsIgnoreCase(day)) {
                total = total + pcount.getTotalCount();
            }
        }
        return total;
    }

    public double getAverageByDay(String day) {
        int total = 0;
        int count = 0;
        for (PedCount pcount : records.values()) {
            String dayofweek = pcount.getDateTime().getDayOfWeek().toString();
            if (dayofweek.equalsIgnoreCase(day)) {
                total += pcount.getTotalCount();
                count++;
            }
        }
        if (count == 0) {
            return 0;
        } else {
            return (double) total / count;
        }
    }

    public Map<String, Integer> getWeatherTotals(String direction) {
        Map<String, Integer> weatherMap = new HashMap<>();
        for (PedCount pcount : records.values()) {
            String weather = pcount.getWeatherSummary();
            int count = 0;
            if (direction.equals(TO_MANHATTAN)) {
                count = pcount.getCountToManhattan();
            } else if (direction.equals(TO_BROOKLYN)) {
                count = pcount.getCountToBrooklyn();
            }
            weatherMap.put(weather, weatherMap.getOrDefault(weather, 0) + count);
        }
        return weatherMap;
    }
}