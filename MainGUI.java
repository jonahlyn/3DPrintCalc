import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/*
 * Main GUI for application
 */
public class MainGUI extends JFrame{

    // Window size parameters
    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 400;

    // Options panels
    private GreetingPanel greetingPanel;   // instructions
    private MaterialsPanel materialsPanel; // materials
    private PowerPanel powerPanel;         // power
    private LaborPanel laborPanel;         // labor

    // Buttons
    private JPanel buttonPanel;
    private JButton calcButton;
    private JButton exitButton;

    /*
     * Constructor
     */
    public MainGUI(){

        // Display a title
        setTitle("3D Printing Calculator");

        // Set the window size
        //setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // Specify close action
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a layout manager
        setLayout(new BorderLayout());

        // Create panels
        greetingPanel = new GreetingPanel();
        materialsPanel = new MaterialsPanel();
        powerPanel = new PowerPanel();
        laborPanel = new LaborPanel();
        buildButtonPanel();

        // Add panels to content pane.

        // Introduction text
        add(greetingPanel, BorderLayout.NORTH);

        // Materials Settings
        add(materialsPanel, BorderLayout.WEST);

        // Power Settings
        add(powerPanel, BorderLayout.CENTER);

        // Labor Settings
        add(laborPanel, BorderLayout.EAST);

        // Buttons
        add(buttonPanel, BorderLayout.SOUTH);

        // Pack contents of window
        // resizes to make sure everything fits within it
        pack();
        setVisible(true);
    }

    /**
     * buildButtonPanel method
     * Builds the button panel.
     * @param args
     */
    private void buildButtonPanel(){
        // Create a panel for the buttons.
        buttonPanel = new JPanel();

        // Create the buttons
        calcButton = new JButton("Calculate");
        exitButton = new JButton("Exit");

        // Register the event listeners
        calcButton.addActionListener(new CalcButtonListener());
        exitButton.addActionListener(new ExitButtonListener());

        // Add the buttons to the button panel.
        buttonPanel.add(calcButton);
        buttonPanel.add(exitButton);
    }


    /**
     * Private inner class that handles the click on the Calculate button.
     */
    private class CalcButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            double materialCost, powerCost, laborCost, subtotal;

            // Calculate the different types of costs
            materialCost = materialsPanel.getMaterialCost();
            powerCost = powerPanel.getPowerCost();
            laborCost = laborPanel.getLaborCost();

            // Calculate the subtotal.
            subtotal = materialCost + powerCost + laborCost;

            // Display the charges.
            JOptionPane.showMessageDialog(null,
                    String.format("Subtotal: $%,.2f\n", subtotal));
        }
    }


    /**
     * Private inner class that handles the click event on the Exit button.
     */
    private class ExitButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }


    /*
     * Main method.
     */
    public static void main(String[] args) {
        new MainGUI();
    }
}