import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Rent extends JFrame{
    private JTextField custName;
    private JTextField email;
    private JTextField phone;
    private JTextField eircode;
    private JButton rentButton;
    private JButton doneButton;
    private JComboBox toolComboBox;
    private JPanel panel1;

    public Rent(ArrayList<Tool> allTools, ArrayList<Rental> allRentals){
        setContentPane(panel1);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("drill.png")).getImage());
        Toolkit toolkit = getToolkit();                                                         //https://www.youtube.com/watch?v=pbDbnmlFTS0
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);

        setSize(600,350);
        setVisible(true);

        ArrayList<Tool> rentableTools = getRentableTools(allTools);

        for (int i = 0; i < rentableTools.size(); i++){
            toolComboBox.addItem(rentableTools.get(i).getId() + "   -----    " + rentableTools.get(i).getToolManufacturer() + ", " + rentableTools.get(i).getToolType());
        }

        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rental rental = new Rental(custName.getText(), email.getText(), Integer.parseInt(phone.getText()), eircode.getText(), getToolIDFromComboBox(toolComboBox.getSelectedItem().toString()));
                allRentals.add(rental);
                Tool toolToChangeStatus;
                for (Tool tool : allTools) {
                    if (tool.getId() == getToolIDFromComboBox(toolComboBox.getSelectedItem().toString())) {
                        toolToChangeStatus = tool;
                        toolToChangeStatus.setToolStatus("OUT");
                    }
                }
                updateRentableItems(allTools);
            }
        });
    }

    public int getToolIDFromComboBox(String comboBoxString){
        StringBuilder ID = new StringBuilder();

        for (int i = 0; i < 4; i++){
            if (Character.isDigit(comboBoxString.charAt(i))){
                ID.append(comboBoxString.charAt(i));
            }
        }

        return Integer.parseInt(ID.toString());
    }

    public void updateRentableItems(ArrayList<Tool> allTools){
        toolComboBox.removeAllItems();
        ArrayList<Tool> rentableTools = getRentableTools(allTools);
        for (int i = 0; i < rentableTools.size(); i++){
            toolComboBox.addItem(rentableTools.get(i).getId() + " - " + rentableTools.get(i).getToolType() + " - " + rentableTools.get(i).getToolManufacturer());
        }
    }

    public ArrayList<Tool> getRentableTools(ArrayList<Tool> allTools){
        ArrayList<Tool> rentableTools = new ArrayList<>();
        for (Tool tool : allTools) {
            if (tool.getToolStatus().equals("IN")) {
                rentableTools.add(tool);
            }
        }
        return rentableTools;
    }

    public static void main(String[] args) {
        new Rent(null, null);
    }
}
