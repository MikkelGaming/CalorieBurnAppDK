package mikkelgaming.calorieburningcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class HelloController {

    @FXML
    private Label resultLabel;
    @FXML
    private TextField timeField;
    @FXML
    private TextField weightField;
    @FXML
    private ChoiceBox activityChoiceBox;

    private final ArrayList<Activity> activities = new ArrayList<>();

    @FXML
    private void calculate()
    {
        // Math
        int time = safeParseInt(timeField.getText());
        int weight = safeParseInt(weightField.getText());
        double metValue = 0;

        // Get MET number
        String selectedActivity = (String) activityChoiceBox.getValue();
        for (Activity activity : activities)
        {
            if (activity.title.equals(selectedActivity))
            {
                metValue = activity.metValue;
            }
        }

        float preResult =(float) (time * weight * metValue) /60;
        int result = Math.round(preResult);
        resultLabel.setText("Du har forbrændt ca. " + result + " kcal.");
    }

    @FXML
    public void initialize()
    {
        // Make activities
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