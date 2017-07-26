import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MaterialsPanel extends JPanel {

    // Material costs per 1kg spool
    public final double PLA = 25.00;
    public final double GLOW_PLA = 25.00;
    public final double WOOD_PLA = 35.00;

    // Material options
    private JRadioButton pla;
    private JRadioButton glowPla;
    private JRadioButton woodPla;

    private ButtonGroup plaGroup;

    // Object weight text field
    private JLabel objWeightLabel;
    private JTextField objWeight;

    //Display cost in a label
    private JLabel costInfo;


    /*
     * Constructor
     */
    public MaterialsPanel() {

        // GridLayout with four rows and one column.
        setLayout(new GridLayout(6,1));

        // Create the radio button options
        pla = new JRadioButton(String.format("PLA ($%.0f)", PLA), true);
        glowPla = new JRadioButton(String.format("Glow in the Dark PLA ($%.0f)", GLOW_PLA));
        woodPla = new JRadioButton(String.format("Wood PLA ($%.0f)", WOOD_PLA));

        // Group the radio buttons.
        plaGroup = new ButtonGroup();
        plaGroup.add(pla);
        plaGroup.add(glowPla);
        plaGroup.add(woodPla);

        // Create the object weight text entry
        objWeightLabel = new JLabel("Object weight in grams: ");
        objWeight = new JTextField(10);

        // Create the label for showing cost for materials
        costInfo = new JLabel("");

        // Add action listeners to all widgets to perform calculation
        // automatically on change.
        pla.addActionListener(new WidgetListener());
        glowPla.addActionListener(new WidgetListener());
        woodPla.addActionListener(new WidgetListener());
        objWeight.addActionListener(new WidgetListener());

        // Add a border around the panel.
        setBorder(BorderFactory.createTitledBorder("Materials"));

        // Add text field to panel
        add(objWeightLabel);
        add(objWeight);

        // Add radio buttons to panel.
        add(pla);
        add(glowPla);
        add(woodPla);

        // Add the cost info label to the panel
        add(costInfo);
    }

    /**
     * getMaterialsCost method
     * @return The cost of the print using the selected material
     */
    public double getMaterialCost() {
        double weight = 0.0,
                costPerKg = 0.0,
                costPerGram = 0.0,
                totalCost = 0.0;

        // Get the object weight value entered (in grams)
        try {
            weight = Double.parseDouble(objWeight.getText());
        } catch (NumberFormatException e) {
            weight = 0.0;
        }

        // Get the selected material cost
        if(pla.isSelected()){
            costPerKg = PLA;
        } else if (glowPla.isSelected()){
            costPerKg = GLOW_PLA;
        } else if(woodPla.isSelected()){
            costPerKg = WOOD_PLA;
        }

        // Determine the cost per gram of the material
        // 1kg = 1000 grams
        costPerGram = costPerKg / 1000;

        // calculate the material cost
        totalCost = weight * costPerGram;

        return totalCost;
    }

    /**
     * Private inner class to display materials cost
     * when one of the options widgets changes.
     */
    private class WidgetListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            costInfo.setText(
                    String.format("Materials cost: $%,.2f\n",
                            getMaterialCost()));
        }
    }
}