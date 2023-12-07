package view;
import javax.swing.*;

class LabelTextPanel extends JPanel {

    /**
     * Create a new JPanel with a given JLabel and JTextField
     * @param label JLabel
     * @param textField JTextField
     */
    LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}
