import java.util.List;
import java.util.ArrayList;

class NoRouteException extends FestivalPlanningException {
    public NoRouteException(String message) {
        super(message);
    }
}

public class DeusiBhailo extends FestivalActivity {
    List<String> plannedRoutes;
    int numberOfPerformers;

    public DeusiBhailo(double estimatedCost, List<String> plannedRoutes, int numberOfPerformers) {
        super("Deusi Bhailo Program", estimatedCost);
        this.plannedRoutes = plannedRoutes;
        this.numberOfPerformers = numberOfPerformers;
    }

    void planActivity() throws FestivalPlanningException {
        if (plannedRoutes.isEmpty()) {
            throw new NoRouteException("No routes planned for Deusi Bhailo! Are we just singing in the living room?");
        }
        if (numberOfPerformers < 3) {
            throw new FestivalPlanningException("Need at least 3 performers for a proper Deusi Bhailo!");
        }
        System.out.println("Deusi Bhailo program with " + numberOfPerformers + " performers planned for " + plannedRoutes.size() + " routes!");
    }

    public static void main(String[] args) {
        List<String> routes1 = new ArrayList<>();
        routes1.add("Baneshwor");
        routes1.add("Koteshwor");
        routes1.add("Bhaktapur Durbar Square");

        List<String> routes2 = new ArrayList<>();

        DeusiBhailo d1 = new DeusiBhailo(10000, routes1, 6);
        DeusiBhailo d2 = new DeusiBhailo(5000, routes2, 4);
        DeusiBhailo d3 = new DeusiBhailo(7000, routes1, 2);

        DeusiBhailo[] programs = { d1, d2, d3 };

        for (DeusiBhailo d : programs) {
            d.displayOverview();
            try {
                d.planActivity();
            } catch (NoRouteException e) {
                System.out.println("Route Problem: " + e.getMessage());
            } catch (FestivalPlanningException e) {
                System.out.println("Planning failed: " + e.getMessage());
            }
            System.out.println();
        }
    }
}
