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
        // Reference: https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Create the checkbox
        includeLabor = new JCheckBox(
                String.format("Include Labor (@$%.2f/hr)?", LABORCOST));

        // Create the TimeSelector to collect labor hours
        timeSelector = new TimeSelector("Pre/post production time: ");

        // Add a border around the panel
        setBorder(BorderFactory.createTitledBorder("Labor"));

        // Add widgets to the panel
        c.anchor = GridBagConstraints.LINE_START;
        
        // Include labor checkbox
        c.gridx = 0; c.gridy = 0;
        add(includeLabor, c);
        
        // Production time selector
        c.gridx = 0; c.gridy = 1;
        c.insets = new Insets(10,0,0,0);  //add top padding
        add(timeSelector, c);
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