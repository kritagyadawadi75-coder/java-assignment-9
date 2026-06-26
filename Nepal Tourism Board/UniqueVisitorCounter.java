import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class EmptyDataException extends DataProcessingException {
    public EmptyDataException(String message) {
        super(message);
    }
}

public class UniqueVisitorCounter implements DataProcessor {

    public List<String> process(List<String> rawData) throws DataProcessingException {
        if (rawData.isEmpty()) {
            throw new EmptyDataException("No raw data to process! Did all tourists go missing?");
        }

        Set<String> uniqueNames = new HashSet<>();
        for (String entry : rawData) {
            String afterColon = entry.substring(entry.indexOf(":") + 1).trim();
            String name = afterColon.split(",")[0].trim();
            uniqueNames.add(name);
        }

        List<String> result = new ArrayList<>();
        result.add("Unique Visitors: " + uniqueNames.size());
        return result;
    }

    public static void main(String[] args) {
        UniqueVisitorCounter counter = new UniqueVisitorCounter();

        List<String> emptyData = new ArrayList<>();
        List<String> sampleData = new ArrayList<>();
        sampleData.add("Visitor: John Doe, USA");
        sampleData.add("Visitor: Emily White, UK");
        sampleData.add("Visitor: John Doe, USA");
        sampleData.add("Guest: Ram Thapa, NP");

        try {
            List<String> result = counter.process(emptyData);
            System.out.println(result);
        } catch (DataProcessingException e) {
            System.out.println("Processing Error: " + e.getMessage());
        }

        try {
            List<String> result = counter.process(sampleData);
            System.out.println(result);
        } catch (DataProcessingException e) {
            System.out.println("Processing Error: " + e.getMessage());
        }
    }
}
