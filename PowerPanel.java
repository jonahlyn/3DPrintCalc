import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    // Printer power wattage input
    private JLabel printerWattageLabel;
    private JTextField printerWattage;

    // Power subtotal display label
    private JLabel powerSubtotalLabel;

    /**
     * Constructor
     */
    public PowerPanel(){
        // Set the layout
        setLayout(new GridLayout(7,1));

        // Print time input
        printHoursLabel = new JLabel("Print time in hours: ");
        printHours = new JTextField(10);

        // Electricity cost input
        powerCostLabel = new JLabel("Cost of electricity per kWh: ");
        powerCost = new JTextField(10);
        powerCost.setText(String.valueOf(COSTPERKWH)); // Set a default the user can change

        // Printer wattage input
        printerWattageLabel = new JLabel("Printer Watts: ");
        printerWattage = new JTextField(10);
        //Calculate the wattage of the printer and set it as a default value.
        printerWattage.setText(String.valueOf(VOLTS * AMPERES));

        // Power subtotal label to display power cost subtotal
        powerSubtotalLabel = new JLabel("");

        printHours.addActionListener(new WidgetListener());
        powerCost.addActionListener(new WidgetListener());
        printerWattage.addActionListener(new WidgetListener());

        // Add a border around the panel
        setBorder(BorderFactory.createTitledBorder("Power"));

        // Add widgets to panel
        add(printHoursLabel);
        add(printHours);
        add(powerCostLabel);
        add(powerCost);
        add(printerWattageLabel);
        add(printerWattage);
        add(powerSubtotalLabel);
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
                cost = 0.0,
                totalCost = 0.0;

        // Get print hours entered
        try {
            time = Double.parseDouble(printHours.getText());
        } catch(NumberFormatException e){
            time = 0.0;
        }

        // Get the printer wattage
        try {
            watts = Double.parseDouble(printerWattage.getText());
        } catch(NumberFormatException e) {
            watts = 0.0;
        }

        // Determine kWh usage
        kWh = (watts * time) / 1000;

        // Get the cost of electricity
        try {
            cost = Double.parseDouble(powerCost.getText());
        } catch(NumberFormatException e) {
            cost = 0.0;
        }

        // Calculate power cost
        totalCost = cost * kWh;

        return totalCost;
    }


    /**
     * Private inner class to display materials cost
     * when one of the options widgets changes.
     */
    private class WidgetListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            powerSubtotalLabel.setText(
                    String.format("Power cost: $%,.2f\n",
                            getPowerCost()));
        }
    }
}