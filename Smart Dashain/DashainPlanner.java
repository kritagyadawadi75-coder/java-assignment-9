import java.util.List;
import java.util.ArrayList;

public class DashainFestivalPlanner {

    static void executeFestivalPlan(List<FestivalActivity> activities) {
        for (FestivalActivity activity : activities) {
            activity.displayOverview();
            try {
                activity.planActivity();
            } catch (InvalidGuestCountException e) {
                System.out.println("Planning Warning (Guests): " + e.getMessage());
            } catch (BudgetExceededException e) {
                System.out.println("Planning Warning (Budget): " + e.getMessage());
            } catch (NoRouteException e) {
                System.out.println("Planning Warning (Routes): " + e.getMessage());
            } catch (FestivalPlanningException e) {
                System.out.println("General Planning Error: " + e.getMessage());
            } finally {
                System.out.println("Activity planning attempt for " + activity.activityName + " completed.");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<FestivalActivity> activities = new ArrayList<>();

        activities.add(new TikaCeremony(20000, 15, "Ram Bahadur"));
        activities.add(new TikaCeremony(60000, 25, "Sita Kumari"));
        activities.add(new TikaCeremony(18000, 3, "Hari Prasad"));

        List<String> routesA = new ArrayList<>();
        routesA.add("Lalitpur");
        routesA.add("Kirtipur");

        List<String> routesB = new ArrayList<>();

        activities.add(new DeusiBhailo(9000, routesA, 5));
        activities.add(new DeusiBhailo(4000, routesB, 6));
        activities.add(new DeusiBhailo(6000, routesA, 2));

        executeFestivalPlan(activities);
    }
}
