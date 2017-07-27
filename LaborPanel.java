import javax.swing.*;
import java.awt.*;

/**
 * LaborPanel class allows the user to enter
 * options related to labor costs.
 */
public class LaborPanel extends JPanel {

    private final double LABORCOST = 15.0; // labor cost per hour in USD

    private JCheckBox includeLabor;
    private JLabel laborHoursLabel;
    private JTextField laborHours;

    /**
     * Constructor
     */
    public LaborPanel(){
        // Set the layout
        setLayout(new GridLayout(4,1));

        // Create the checkbox
        includeLabor = new JCheckBox("Include Labor Costs?");

        // Create the textfield
        laborHoursLabel = new JLabel("Pre and post processing time in hours: ");
        laborHours = new JTextField(10);

        // Add a border around the panel
        setBorder(BorderFactory.createTitledBorder("Labor"));

        // Add widgets to the panel
        add(includeLabor);
        add(laborHoursLabel);
        add(laborHours);
    }

    public double getLaborCost(){
        double laborCost = 0.0;

        if(includeLabor.isSelected()){
            laborCost = Double.parseDouble(laborHours.getText()) * LABORCOST;
        }

        return laborCost;
    }
}