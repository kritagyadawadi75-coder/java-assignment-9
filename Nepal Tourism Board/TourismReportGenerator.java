import java.util.List;
import java.util.ArrayList;

public class TourismReportGenerator {

    static void generateOverallReport(List<TouristDataSource> dataSources, DataProcessor processor) {
        System.out.println("Generating overall tourism report...");

        for (TouristDataSource dataSource : dataSources) {
            try {
                List<String> rawData;
                try {
                    rawData = dataSource.fetchData();
                } catch (DataSourceAccessException e) {
                    System.out.println("Could not fetch data from " + dataSource.sourceName + ": " + e.getMessage() + ". Skipping this source.");
                    if (e.getCause() != null) {
                        System.out.println("Reason: " + e.getCause().getMessage());
                    }
                    continue;
                }

                try {
                    List<String> processedData = processor.process(rawData);
                    System.out.println("Processed data from " + dataSource.sourceName + ": " + processedData);
                } catch (DataProcessingException e) {
                    System.out.println("Error processing data from " + dataSource.sourceName + ": " + e.getMessage() + ". Skipping this data.");
                }
            } finally {
                System.out.println("Data handling from " + dataSource.sourceName + " completed.");
            }
        }
    }

    public static void main(String[] args) {
        List<TouristDataSource> dataSources = new ArrayList<>();
        dataSources.add(new AirportArrivalsDataSource());
        dataSources.add(new HotelRegistrationsDataSource());
        dataSources.add(new AirportArrivalsDataSource());
        dataSources.add(new HotelRegistrationsDataSource());

        DataProcessor processor = new UniqueVisitorCounter();

        generateOverallReport(dataSources, processor);
    }
}
