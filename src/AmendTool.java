import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmendTool extends JFrame {
    private JTextField IDField;
    private JTextField toolTypeField;
    private JTextField manufacturerField;
    private JTextField descriptionField;
    private JButton amendButton;
    private JButton cancelButton;
    private JTextField rateField;
    private JComboBox statusComboBox;
    private JPanel panel1;

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

        IDField.setText(String.valueOf(toolToAmend.getId()));
        toolTypeField.setText(toolToAmend.getToolType());
        manufacturerField.setText(toolToAmend.getToolManufacturer());
        descriptionField.setText(toolToAmend.getToolDesc());
        rateField.setText(String.valueOf(toolToAmend.getToolRate()));

        if (toolToAmend.getToolStatus().equals("IN")) {
            statusComboBox.setSelectedItem("IN");
        }
        else
            statusComboBox.setSelectedItem("UNAVAILABLE");

        amendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toolToAmend.setToolType(toolTypeField.getText());
                toolToAmend.setToolManufacturer(manufacturerField.getText());
                toolToAmend.setToolDesc(descriptionField.getText());
                toolToAmend.setToolRate(Float.parseFloat(rateField.getText()));
                toolToAmend.setToolStatus(statusComboBox.getSelectedItem().toString());
                dispose();
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
}

