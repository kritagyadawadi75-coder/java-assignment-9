import java.util.List;
import java.util.ArrayList;

class AuthenticationFailedException extends Exception {
    public AuthenticationFailedException(String message) {
        super(message);
    }
}

public class HotelRegistrationsDataSource extends TouristDataSource {

    public HotelRegistrationsDataSource() {
        super("Kathmandu Hotels Registrations");
    }

    List<String> fetchData() throws DataSourceAccessException {
        try {
            if (sourceName.contains("Hotels") && Math.random() < 0.2) {
                throw new AuthenticationFailedException("Hotel API authentication failed! Did someone forget the password again?");
            }
            List<String> data = new ArrayList<>();
            data.add("Hotel: Yak & Yeti, Guest: Ram Thapa, NP");
            data.add("Hotel: Annapurna, Guest: Alice Smith, AU");
            data.add("Hotel: Dwarika's, Guest: Sita Gurung, NP");
            return data;
        } catch (AuthenticationFailedException e) {
            throw new DataSourceAccessException("Failed to fetch hotel registration data", e);
        }
    }

    public static void main(String[] args) {
        HotelRegistrationsDataSource source = new HotelRegistrationsDataSource();

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
