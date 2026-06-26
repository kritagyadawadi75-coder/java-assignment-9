class SameLocationException extends InvalidRouteException {
    public SameLocationException(String message) {
        super(message);
    }
}

public class KathmanduTrafficValidator implements RouteValidator {

    public boolean isValidCommuteRoute(String origin, String destination, double distanceKm) throws InvalidRouteException {
        if (origin.equalsIgnoreCase(destination)) {
            throw new SameLocationException("Origin and destination cannot be the same! Are you just spinning in circles, Damodar?");
        }
        if (distanceKm < 0.1 || distanceKm > 30) {
            throw new InvalidRouteException("Distance " + distanceKm + "km is unrealistic for Kathmandu commute!");
        }
        return true;
    }

    public static void main(String[] args) {
        KathmanduTrafficValidator validator = new KathmanduTrafficValidator();

        String[][] testRoutes = {
            { "Baneshwor", "Baneshwor", "5.0" },
            { "Thamel", "Patan", "6.5" },
            { "Kalanki", "Balaju", "0.05" },
            { "Koteshwor", "Bhaktapur", "45.0" }
        };

        for (String[] route : testRoutes) {
            String origin = route[0];
            String destination = route[1];
            double distance = Double.parseDouble(route[2]);

            try {
                boolean result = validator.isValidCommuteRoute(origin, destination, distance);
                System.out.println("Route from " + origin + " to " + destination + " is valid: " + result);
            } catch (SameLocationException e) {
                System.out.println("Same Location Problem: " + e.getMessage());
            } catch (InvalidRouteException e) {
                System.out.println("Invalid Route: " + e.getMessage());
            }
            System.out.println();
        }
    }
}
