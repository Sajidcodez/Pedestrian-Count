import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PedCountFileReader reader = new PedCountFileReader();
        PedCountRecords records = new PedCountRecords();

        String filename = "Brooklyn_Bridge_Automated_Pedestrian_Counts.csv";
        for (PedCount pcount : reader.readPedCounts(filename)) {
            records.addPedCount(pcount);
        }

        System.out.println("Loaded records: " + records.getNumRecords());

        String selection = "";
        while (!selection.equalsIgnoreCase("Q")) {
            System.out.println("\nMenu:");
            System.out.println("A - Get all pedestrian count records");
            System.out.println("B - Display total pedestrian counts by weather conditions");
            System.out.println("C - Display average pedestrian counts by day of week");
            System.out.println("Q - Quit");
            System.out.print("Enter your choice:");
            selection = in.nextLine().toUpperCase();

            switch (selection) {
                case "A":
                    for (PedCount pcount : records.getAllRecords()) {
                        System.out.println(pcount);
                    }
                    break;

                case "B":
                    System.out.print("Enter a direction please! Either type out MANHATTAN for pedestrian heading to the city or BROOKLYN for pedestrian heading to the best borough in NYC: ");
                    String input = in.nextLine().toUpperCase();
                    String direction = "";

                    if (input.equals("MANHATTAN")) {
                        direction = PedCountRecords.TO_MANHATTAN;
                    } else if(input.equals("BROOKLYN")) {
                        direction = PedCountRecords.TO_BROOKLYN;
                    } else {
                        System.out.println("Invalid direction. Use 'MANHATTAN' or 'BROOKLYN'.");
                        break;
                    }
                    Map<String, Integer> weatherMap = records.getWeatherTotals(direction);
                    if (weatherMap.isEmpty()) {
                        System.out.println("There's no data right now for that direction.");
                    } else {
                        showChart(weatherMap, "Pedestrians Traffic Heading to" + " " + direction + " by Weather");
                    }
                    break;

                case "C":
                    System.out.println("Average pedestrian counts by day:");
                    for (DayOfWeek day : DayOfWeek.values()) {
                        double average = records.getAverageByDay(day.toString());
                        double roundedAverage = Math.round(average * 10.0) / 10.0; // round to 1 decimal place
                        System.out.println(day + ": " + roundedAverage);
                    }
                    break;
                    
                case "Q":
                    System.out.println("Hasta La Vista!");
                    break;
                default : 
                	System.out.print("It's not that hard to follow instructions -_- ... Try again");
            }
        }
    }

    private static void showChart(Map<String, Integer> Data, String title) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        for (Map.Entry<String, Integer> entry : Data.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);
        chart.getTitle().setFont(new Font("SansSerif", Font.ITALIC, 20));

        ChartPanel panel = new ChartPanel(chart);
        JFrame frame = new JFrame(title);
        frame.setContentPane(panel);
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
