//Jonahlyn Gilstrap
//Assignment 7
//8/4/2017
//GreetingPanel.java

import javax.swing.*;


/**
 * Greeting Panel
 * Displays introductory text
 */
public class GreetingPanel extends JPanel {
    private JLabel greeting; // holds greeting text

    /**
     * Constructor
     */    
    public GreetingPanel(){
        greeting = new JLabel("Calculate the cost of creating a 3D printed object using a desktop FDM printer.");

        //Add the text to this panel.
        add(greeting);
    }
}