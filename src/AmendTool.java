import javax.swing.*;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("drill.png")).getImage());
        setLocationRelativeTo(null);

        setVisible(true);
        setSize(500,500);

        IDField.setText(String.valueOf(toolToAmend.getId()));
        toolTypeField.setText(toolToAmend.getToolType());
        manufacturerField.setText(toolToAmend.getToolManufacturer());
        descriptionField.setText(toolToAmend.getToolDesc());
        rateField.setText(String.valueOf(toolToAmend.getToolRate()));


    }
}

