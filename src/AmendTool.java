import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmendTool extends JFrame {
    private JTextField IDField;
    private JTextField manufacturerField;
    private JTextField descriptionField;
    private JButton amendButton;
    private JButton cancelButton;
    private JTextField rateField;
    private JComboBox statusComboBox;
    private JPanel panel1;
    private JComboBox toolTypeComboBox;

    public AmendTool(Tool toolToAmend){
        setContentPane(panel1);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("drill.png")).getImage());
        setSize(500,500);
        Toolkit toolkit = getToolkit();                                                         //https://www.youtube.com/watch?v=pbDbnmlFTS0
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);

        setVisible(true);

        statusComboBox.addItem("IN");
        statusComboBox.addItem("UNAVAILABLE");

        String[] toolTypes = {"Drill", "Cement Mixer", "Sander", "Rotavator", "Ladder", "Floor Saw", "Digger", "Welder", "Compactor"};
        for (int i = 0; i < toolTypes.length; i++){
            toolTypeComboBox.addItem(toolTypes[i]);
        }

        IDField.setText(String.valueOf(toolToAmend.getId()));
        toolTypeComboBox.setSelectedItem(toolToAmend.getToolType());
        manufacturerField.setText(toolToAmend.getToolManufacturer());
        descriptionField.setText(toolToAmend.getToolDesc());
        rateField.setText(String.valueOf(toolToAmend.getToolRate()));
        statusComboBox.setSelectedItem(toolToAmend.getToolStatus());

        if (toolToAmend.getToolStatus().equals("IN")) {
            statusComboBox.setSelectedItem("IN");
        }
        else
            statusComboBox.setSelectedItem("UNAVAILABLE");

        amendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (isValidManu(manufacturerField.getText())) {
                    if (isValidDesc(descriptionField.getText())) {
                        if (isValidRate(rateField.getText())) {
                            toolToAmend.setToolType(String.valueOf(toolTypeComboBox.getSelectedItem()));
                            toolToAmend.setToolManufacturer(manufacturerField.getText());
                            toolToAmend.setToolDesc(descriptionField.getText());
                            toolToAmend.setToolRate(Float.parseFloat(rateField.getText()));
                            toolToAmend.setToolStatus(statusComboBox.getSelectedItem().toString());
                            dispose();
                        }
                    }
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {        //test run
        new AmendTool(null);
    }

    //***************Validation************************

    public boolean isValidManu(String manufacturer){
        boolean result;
        if (!manufacturer.isEmpty()){
            result = true;
        }
        else {
            result = false;
            JOptionPane.showMessageDialog(null, "Tool manufacturer must be entered", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }

    public boolean isValidDesc(String description){
        boolean result;
        if (!description.isEmpty()){
            if (description.length() <= 20){
                result = true;
            }
            else {
                result = false;
                JOptionPane.showMessageDialog(null, "Tool description must not exceed 20 characters", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            result = false;
            JOptionPane.showMessageDialog(null, "Tool description must be entered", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public boolean isValidRate(String rate){
        boolean result = false;

        if (!rate.isEmpty()){
            for (int i = 0; i < rate.length(); i++){
                if (!Character.isDigit(rate.charAt(i))){
                    result = false;
                    JOptionPane.showMessageDialog(null, "Invalid Rate", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                else {
                    result = true;
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Rate must be entered", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }

}

