import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/*
 * Main GUI for application
 */
public class MainGUI extends JFrame{

    // Options panels
    private GreetingPanel greetingPanel;   // instructions
    private MaterialsPanel materialsPanel; // materials
    private PowerPanel powerPanel;         // power

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

        // Specify close action
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a layout manager
        setLayout(new BorderLayout());

        // Create panels
        greetingPanel = new GreetingPanel();
        materialsPanel = new MaterialsPanel();
        powerPanel = new PowerPanel();
        buildButtonPanel();

        // Add panels to content pane.
        add(greetingPanel, BorderLayout.NORTH);
        add(materialsPanel, BorderLayout.WEST);
        add(powerPanel, BorderLayout.CENTER);
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

        // Add the buttons to the button panel.
        buttonPanel.add(calcButton);
        buttonPanel.add(exitButton);
    }


    private class CalcButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            double materialCost, powerCost, subtotal;

            // Calculate the different types of costs
            materialCost = materialsPanel.getMaterialCost();
            powerCost = powerPanel.getPowerCost();

            // Calculate the subtotal.
            subtotal = materialCost + powerCost;

            // Display the charges.
            JOptionPane.showMessageDialog(null,
                    String.format("Materials cost: $%,.2f\n", subtotal));
        }
    }


    /*
     * Main method.
     */
    public static void main(String[] args) {
        new MainGUI();
    }
}