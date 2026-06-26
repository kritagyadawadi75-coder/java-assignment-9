class FestivalPlanningException extends Exception {
    public FestivalPlanningException(String message) {
        super(message);
    }
}

public abstract class FestivalActivity {
    String activityName;
    double estimatedCost;

    public FestivalActivity(String activityName, double estimatedCost) {
        this.activityName = activityName;
        this.estimatedCost = estimatedCost;
    }

    abstract void planActivity() throws FestivalPlanningException;

    void displayOverview() {
        System.out.println("Activity: " + activityName + ", Estimated Cost: Rs. " + estimatedCost);
    }
}
