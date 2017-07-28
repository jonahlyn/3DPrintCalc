import javax.swing.*;
import java.awt.*;

/**
 * LaborPanel class allows the user to enter
 * options related to labor costs.
 */
public class LaborPanel extends JPanel {

    private final double LABORCOST = 7.50;  // labor cost hourly rate

    private JCheckBox includeLabor;
    private JLabel laborHoursLabel;
    private TimeSelector timeSelector;

    /**
     * Constructor
     */
    public LaborPanel(){
        // Set the layout
        setLayout(new GridLayout(4,1));

        // Create the checkbox
        includeLabor = new JCheckBox(
                String.format("Include Labor (@$%.2f/hr)?", LABORCOST));

        // Create the TimeSelector to collect labor hours
        laborHoursLabel = new JLabel("Pre/post production time: ");
        timeSelector = new TimeSelector();

        // Add a border around the panel
        setBorder(BorderFactory.createTitledBorder("Labor"));

        // Add widgets to the panel
        add(includeLabor);
        add(laborHoursLabel);
        add(timeSelector);
    }

    public double getLaborCost(){
        double laborCost = 0.0;
        double laborTime = 0.0;

        if(includeLabor.isSelected()){
            laborCost = LABORCOST * (timeSelector.getHours() + (timeSelector.getMinutes()/60.0));
        }

        return laborCost;
    }
}