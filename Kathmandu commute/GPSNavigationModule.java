public class GPSNavigationModule implements NavigationService {

    public void navigate(String startPoint, String endPoint, RouteValidator validator) throws NavigationFailedException {
        System.out.println("Attempting to navigate from " + startPoint + " to " + endPoint + "...");

        if (startPoint.equalsIgnoreCase("Kalanki")) {
            throw new NavigationFailedException("GPS signal lost near Kalanki! Welcome to Kathmandu traffic!");
        }

        try {
            validator.isValidCommuteRoute(startPoint, endPoint, simulatedDistance(startPoint, endPoint));
        } catch (SameLocationException e) {
            throw new NavigationFailedException("Route validation failed!", e);
        } catch (InvalidRouteException e) {
            throw new NavigationFailedException("Route validation failed!", e);
        }

        System.out.println("Navigation successful! Estimated time: 20 minutes (or 2 hours depending on traffic).");
    }

    private double simulatedDistance(String start, String end) {
        return 5.0;
    }

    public static void main(String[] args) {
        GPSNavigationModule gps = new GPSNavigationModule();
        RouteValidator validator = new KathmanduTrafficValidator();

        String[][] testRoutes = {
            { "Thamel", "Patan" },
            { "Baneshwor", "Baneshwor" },
            { "Kalanki", "Balaju" }
        };

        for (String[] route : testRoutes) {
            try {
                gps.navigate(route[0], route[1], validator);
            } catch (NavigationFailedException e) {
                System.out.println("Navigation Failed: " + e.getMessage());
                if (e.getCause() != null) {
                    System.out.println("Caused by: " + e.getCause().getMessage());
                }
            }
            System.out.println();
        }
    }
}
