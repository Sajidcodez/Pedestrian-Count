import com.opencsv.*;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import java.util.*;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PedCountFileReader {
    public List<PedCount> readPedCounts(String fileName) {
        List<PedCount> counts = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");

        try {
            CSVParser parser = new CSVParserBuilder()
                    .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                    .build();

            CSVReader reader = new CSVReaderBuilder(new FileReader(fileName))
                    .withCSVParser(parser)
                    .withSkipLines(1)// skip header
                    .build();

            String[] line;
            while ((line = reader.readNext()) != null) {
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(line[0], formatter);
                    String site = line[1];
                    int totalCount = Integer.parseInt(line[2]);
                    int toManhattan = Integer.parseInt(line[3]);
                    int toBrooklyn = Integer.parseInt(line[4]);
                    String weather = line[5];
                    double tempF = Double.parseDouble(line[6]);
                    double precipitation = Double.parseDouble(line[7]);
                    double latitude = Double.parseDouble(line[8]);
                    double longitude = Double.parseDouble(line[9]);
                    boolean isHoliday = Boolean.parseBoolean(line[10]);

                    PedCount pc = new PedCount(dateTime, site, totalCount, toManhattan, toBrooklyn,
                            weather, tempF, precipitation, latitude, longitude, isHoliday);
                    counts.add(pc);
                    
                } catch (Exception e) {
                    System.err.println("Skipping a bad row in the file.");
                }
            }
            
        } catch (Exception e) {
            System.err.println("Problem reading the file but I couldn't even tell you what it is");
        }

        return counts;
    }
}

