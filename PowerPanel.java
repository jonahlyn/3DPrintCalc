//Jonahlyn Gilstrap
//Assignment 7
//8/4/2017
//PowerPanel.java

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

    // Print hours selector
    private TimeSelector timeSelector;

    // Electricity cost input
    private JLabel powerCostLabel;
    private JTextField powerCost;

    // Printer power wattage input
    private JLabel printerWattageLabel;
    private JTextField printerWattage;

    /**
     * Constructor
     */
    public PowerPanel(){
        // Set the layout
        // Reference: https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Print hours selector
        timeSelector = new TimeSelector("Time to print: ");

        // Electricity cost input
        powerCostLabel = new JLabel("Cost of electricity per kWh: ");
        powerCost = new JTextField(10);
        powerCost.setText(String.valueOf(COSTPERKWH)); // Set a default the user can change

        // Printer wattage input
        printerWattageLabel = new JLabel("Printer Watts: ");
        printerWattage = new JTextField(10);
        //Calculate the wattage of the printer and set it as a default value.
        printerWattage.setText(String.valueOf(VOLTS * AMPERES));

        // Add a border around the panel
        setBorder(BorderFactory.createTitledBorder("Power"));

        // Add widgets to panel
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10,0,0,0);  //top padding
        
        // Print time selector
        c.gridx = 0; c.gridy = 0;        
        c.weightx = 1.0;
        add(timeSelector, c);
        
        // Electricity cost
        c.gridx = 0; c.gridy = 1;
        add(powerCostLabel, c);
        
        c.gridx = 0; c.gridy = 2;        
        add(powerCost, c);
        
        // Printer wattage
        c.gridx = 0; c.gridy = 3;        
        add(printerWattageLabel, c);
        
        c.gridx = 0; c.gridy = 4;        
        add(printerWattage, c);       
    }

    /**
     * getPowerCost method
     * Reference: https://energy.gov/energysaver/estimating-appliance-and-home-electronic-energy-use
     * @return The cost of running the printer.
     */
    public double getPowerCost(){
        int hours, minutes;
        double  time = 0.0,
                watts = 0.0,
                kWh = 0.0,
                cost = 0.0,
                totalCost = 0.0;

        // Get print hours entered
        try {
            hours = timeSelector.getHours();
            minutes = timeSelector.getMinutes();
            time = (double) hours + (minutes/60.0);
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

}