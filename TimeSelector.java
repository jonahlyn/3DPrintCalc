import javax.swing.*;
import java.awt.*;


/**
 * Reference: https://stackoverflow.com/questions/36348941/java-unsafe-operations-with-combo-boxes
 */
public class TimeSelector extends JPanel {

    private final int HOURS = 24;
    private final int MINUTES = 60;
    private String[] hours = new String[HOURS];
    private String[] minutes = new String[MINUTES];
    private JLabel hoursBoxLabel;
    private JComboBox<String> hoursBox;
    private JLabel minutesBoxLabel;
    private JComboBox<String> minutesBox;

    /**
     * Constructor
     */
    public TimeSelector() {
        // Default layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Create the hours dropdown
        hoursBoxLabel = new JLabel("Hours: ");
        for(int i = 0; i < HOURS; i++){
            hours[i] = "" + i;
        }
        hoursBox = new JComboBox<>();
        hoursBox.setModel(new DefaultComboBoxModel<String>(hours));
        //hoursBox.setEditable(true);

        // Create the mintues dropdown
        minutesBoxLabel = new JLabel("Minutes: ");
        for(int j = 0; j < MINUTES; j++){
            minutes[j] = "" + j;
        }
        minutesBox = new JComboBox<>();
        minutesBox.setModel(new DefaultComboBoxModel<String>(minutes));
        //minutesBox.setEditable(true);

        // Add widgts to the panel
        c.gridx = 0;
        c.gridy = 0;
        add(hoursBoxLabel,c);

        c.gridx = 1;
        c.gridy = 0;
        add(hoursBox,c);

        c.gridx = 0;
        c.gridy = 1;
        add(minutesBoxLabel,c);

        c.gridx = 1;
        c.gridy = 1;
        add(minutesBox, c);
    }

    public int getHours(){
        return Integer.parseInt((String) hoursBox.getSelectedItem());
    }

    public int getMinutes(){
        return Integer.parseInt((String) minutesBox.getSelectedItem());
    }

}