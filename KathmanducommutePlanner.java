public class CommutePlanner {

    static void planMyCommute(String origin, String destination, RouteValidator validator, NavigationService navigator) {
        System.out.println("Planning your commute from " + origin + " to " + destination + "...");
        try {
            navigator.navigate(origin, destination, validator);
        } catch (NavigationFailedException e) {
            Throwable cause = e.getCause();
            if (cause != null && cause instanceof SameLocationException) {
                System.out.println("Cannot plan: Invalid route details (same location)");
            } else if (cause != null && cause instanceof InvalidRouteException) {
                System.out.println("Cannot plan: Invalid route details");
            } else {
                System.out.println("Cannot plan: GPS issue");
            }
        } finally {
            System.out.println("Commute planning for " + origin + " to " + destination + " completed.");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RouteValidator validator = new KathmanduTrafficValidator();
        NavigationService navigator = new GPSNavigationModule();

        planMyCommute("Baneshwor", "Baneshwor", validator, navigator);
        planMyCommute("Thamel", "Patan", validator, navigator);
        planMyCommute("Kalanki", "Balaju", validator, navigator);
    }
}
