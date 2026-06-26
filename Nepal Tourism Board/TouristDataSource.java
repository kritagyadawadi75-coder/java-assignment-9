import java.util.List;

class DataSourceAccessException extends Exception {
    public DataSourceAccessException(String message) {
        super(message);
    }

    public DataSourceAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}

public abstract class TouristDataSource {
    String sourceName;

    public TouristDataSource(String sourceName) {
        this.sourceName = sourceName;
    }

    abstract List<String> fetchData() throws DataSourceAccessException;
}
