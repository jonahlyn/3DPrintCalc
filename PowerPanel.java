import javax.swing.*;
import java.awt.*;


/**
 PowerPanel class shows options
 related to cost of electricty
 */

public class PowerPanel extends JPanel {

    // Amperes of the Einstart-S 3D Printer
    public final double AMPERES = 5.0;
    public final double VOLTS = 120;

    // Cost of electricity
    public final double COSTPERKWH = 0.1472299; // PNM Block 3 cost per kWh in USD

    // Print hours text input
    private JLabel printHoursLabel;
    private JTextField printHours;

    // Electricity cost input
    private JLabel powerCostLabel;
    private JTextField powerCost;

    /**
     * Constructor
     */
    public PowerPanel(){
        // Set the layout
        setLayout(new GridLayout(4,1));

        // Create the input areas
        printHoursLabel = new JLabel("Print time in hours: ");
        printHours = new JTextField(10);

        powerCostLabel = new JLabel("Cost of electricity: ");
        powerCost = new JTextField(10);

        // Add a border around the panel
        setBorder(BorderFactory.createTitledBorder("Power"));

        // Add widgets to panel
        add(printHoursLabel);
        add(printHours);

        add(powerCostLabel);
        add(powerCost);
    }

    /**
     * getPowerCost method
     * Reference: https://energy.gov/energysaver/estimating-appliance-and-home-electronic-energy-use
     * @return The cost of running the printer.
     */
    public double getPowerCost(){
        double  time = 0.0,
                watts = 0.0,
                kWh = 0.0,
                totalCost = 0.0;

        //Calculate the wattage of the printer
        watts = VOLTS * AMPERES;

        // Get print hours entered
        try {
            time = Double.parseDouble(printHours.getText());
        } catch(NumberFormatException e){
            time = 0.0;
        }

        // Determine kWh usage
        kWh = (watts * time) / 1000;

        // Get the cost of electricity
        //costOfElectricity = Double.parseDouble(powerCost.getText());

        // Calculate power cost
        totalCost = COSTPERKWH * kWh;

        return totalCost;
    }
}