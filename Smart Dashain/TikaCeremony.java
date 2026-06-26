class InvalidGuestCountException extends FestivalPlanningException {
    public InvalidGuestCountException(String message) {
        super(message);
    }
}

class BudgetExceededException extends FestivalPlanningException {
    public BudgetExceededException(String message) {
        super(message);
    }
}

public class TikaCeremony extends FestivalActivity {
    int expectedGuests;
    String mainFamilyElder;

    public TikaCeremony(double estimatedCost, int expectedGuests, String mainFamilyElder) {
        super("Tika Ceremony", estimatedCost);
        this.expectedGuests = expectedGuests;
        this.mainFamilyElder = mainFamilyElder;
    }

    void planActivity() throws FestivalPlanningException {
        if (expectedGuests < 5) {
            throw new InvalidGuestCountException("Not enough guests for a lively Tika! Is everyone on vacation?");
        }
        if (estimatedCost > 50000) {
            throw new BudgetExceededException("Tika budget too high! Is this for the whole village?");
        }
        System.out.println("Tika ceremony with " + mainFamilyElder + " planned successfully for " + expectedGuests + " guests!");
    }

    public static void main(String[] args) {
        TikaCeremony t1 = new TikaCeremony(15000, 12, "Buddha Prasad");
        TikaCeremony t2 = new TikaCeremony(8000, 3, "Krishna Bahadur");
        TikaCeremony t3 = new TikaCeremony(65000, 20, "Tara Devi");

        TikaCeremony[] ceremonies = { t1, t2, t3 };

        for (TikaCeremony t : ceremonies) {
            t.displayOverview();
            try {
                t.planActivity();
            } catch (InvalidGuestCountException e) {
                System.out.println("Guest Count Problem: " + e.getMessage());
            } catch (BudgetExceededException e) {
                System.out.println("Budget Problem: " + e.getMessage());
            } catch (FestivalPlanningException e) {
                System.out.println("Planning failed: " + e.getMessage());
            }
            System.out.println();
        }
    }
}
