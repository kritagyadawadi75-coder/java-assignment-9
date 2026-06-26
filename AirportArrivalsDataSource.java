import java.util.List;
import java.util.ArrayList;

class ConnectionLostException extends Exception {
    public ConnectionLostException(String message) {
        super(message);
    }
}

public class AirportArrivalsDataSource extends TouristDataSource {

    public AirportArrivalsDataSource() {
        super("Tribhuvan Airport Arrivals");
    }

    List<String> fetchData() throws DataSourceAccessException {
        try {
            if (sourceName.contains("Tribhuvan") && Math.random() < 0.3) {
                throw new ConnectionLostException("Airport data connection lost! Maybe a pigeon sat on the antenna?");
            }
            List<String> data = new ArrayList<>();
            data.add("Visitor: John Doe, USA");
            data.add("Visitor: Emily White, UK");
            data.add("Visitor: Ram Thapa, NP");
            return data;
        } catch (ConnectionLostException e) {
            throw new DataSourceAccessException("Failed to fetch airport data", e);
        }
    }

    public static void main(String[] args) {
        AirportArrivalsDataSource source = new AirportArrivalsDataSource();

        for (int i = 0; i < 5; i++) {
            try {
                List<String> data = source.fetchData();
                System.out.println("Fetched data: " + data);
            } catch (DataSourceAccessException e) {
                System.out.println("Data Source Error: " + e.getMessage());
                if (e.getCause() != null) {
                    System.out.println("Caused by: " + e.getCause().getMessage());
                }
            }
        }
    }
}
