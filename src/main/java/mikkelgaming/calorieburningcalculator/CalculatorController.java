package mikkelgaming.calorieburningcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

/**
 * Main Controller of the MVC model.
 */
public class CalculatorController {

    @FXML
    private Label resultLabel;
    @FXML
    private TextField timeField;
    @FXML
    private TextField weightField;
    @FXML
    private ChoiceBox activityChoiceBox;

    private final ArrayList<Activity> activities = new ArrayList<>();

    /**
     * Calculates the calories burned by the activity and inputs from the user.
     */
    @FXML
    private void calculate()
    {
        // Variable preparation and gets the input from the TextFields.
        int time = safeParseInt(timeField.getText());
        int weight = safeParseInt(weightField.getText());
        double metValue = 0;

        // Get MET number from the activity chosen in the ChoiceBox.
        String selectedActivity = (String) activityChoiceBox.getValue();
        for (Activity activity : activities)
        {
            // if the title of the activity matches the chosen activity use its metValue.
            if (activity.title.equals(selectedActivity))
            {
                metValue = activity.metValue;
            }
        }

        // Formats result to int and displays it in the result label.
        float preResult =(float) (time * weight * metValue) /60;
        int result = Math.round(preResult);
        if (result > 0)
        {
            resultLabel.setText("Du har forbrændt ca. " + result + " kcal.");
        }
    }

    /**
     * initialization of the controller.
     * Makes the activities.
     */
    @FXML
    public void initialize()
    {
        // Instantiates and adds activities to activity arraylist.
        activities.add(new Activity("Løb, roligt (9,5 km/t)", 9.8));
        activities.add(new Activity("Løb, moderat (12 km/t)", 11.5));
        activities.add(new Activity("Løb, hurtigt (13,5 km/t)", 12.3));
        activities.add(new Activity("Gang, roligt (3,2 km/t)", 2));
        activities.add(new Activity("Gang, moderat (4,8 km/t)", 3.5));
        activities.add(new Activity("Gang, hurtigt (5,5 km/t)", 4.3));
        activities.add(new Activity("Kombineret gang og løb", 6));
        activities.add(new Activity("Powerwalking", 6.3));
        activities.add(new Activity("Landevejscykling", 6.8));

        // Add activities to the activity ChoiceBox.
        for (Activity activity : activities)
        {
            activityChoiceBox.getItems().add(activity.title);
        }

        activityChoiceBox.getSelectionModel().select(0);
    }

    /**
     * Makes sure input is a whole numbers (Integers).
     * Gives error if it is not.
     * @param input String containing something that needs to be parsed to an int.
     * @return input as an int. 0 if an error occurred (NumberFormatException)
     */
    private int safeParseInt(String input)
    {
        try
        {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e)
        {
            resultLabel.setText("Indtast venligst et rigtigt nummer i all felter");
            return 0;
        }
    }
}