package mikkelgaming.calorieburningcalculator;

/**
 * Defines activites and their metValue for the calorie calculation.
 */
public class Activity {
    public String title;
    public double metValue;

    /**
     * Constructor
     * @param title Name of the activity.
     * @param metValue Metabolic equivalent of task (Calories burned per minute of task/activity)
     */
    public Activity(String title, double metValue) {
        this.title = title;
        this.metValue = metValue;
    }
}
