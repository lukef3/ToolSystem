import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.*;

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
        setIconImage(new ImageIcon(getClass().getResource("drill.png")).getImage());
        setSize(600,350);
        Toolkit toolkit = getToolkit();                                                         //https://www.youtube.com/watch?v=pbDbnmlFTS0
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);


        setVisible(true);

        ArrayList<Tool> rentableTools = getRentableTools(allTools);

        for (int i = 0; i < rentableTools.size(); i++){
            toolComboBox.addItem(rentableTools.get(i).getId() + "       " + rentableTools.get(i).getToolManufacturer() + ", " + rentableTools.get(i).getToolType());
        }

        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isValidCustName(custName.getText())){
                    if (isValidEmail(email.getText())){
                        if (isValidPhone(phone.getText())){
                            if (isValidEircode(eircode.getText())){
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
                        }
                    }
                }
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
        new Rent(null, null);       //testing
    }


    //validation************************

    public boolean isValidCustName(String custName) {
        boolean result = false;
        if (!custName.isEmpty()) {
            for (int i = 0; i < custName.length(); i++) {
                if (!Character.isLetter(custName.charAt(i))) {
                    result = false;
                    JOptionPane.showMessageDialog(null, "Invalid customer name entered", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                } else {
                    result = true;
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Please enter customer name", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public boolean isValidEmail(String email){
        boolean result;

        String emailPattern = "^(.+)@(\\S+)$";              //https://mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
        Pattern pattern = Pattern.compile(emailPattern);

        if (!email.isEmpty()){
            if (pattern.matcher(email).matches()){
                result = true;
            }
            else {
                result = false;
                JOptionPane.showMessageDialog(null, "Invalid Email", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            result =false;
            JOptionPane.showMessageDialog(null, "Please enter an Email", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }

    public boolean isValidPhone(String phone){
        boolean result = true;

        if (!phone.isEmpty()){
            if (phone.charAt(0) == '+' || Character.isDigit(phone.charAt(0))){
                for (int i = 1; i <phone.length(); i++){
                    if (!Character.isDigit(phone.charAt(i))){
                        result = false;
                        JOptionPane.showMessageDialog(null, "Invalid phone number", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
            }
            else {
                result =false;
                JOptionPane.showMessageDialog(null, "Invalid phone number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            result =false;
            JOptionPane.showMessageDialog(null, "Please enter a phone number", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }
    public boolean isValidEircode(String eircode){
        boolean result;

        String eircodePattern = "^([AC-FHKNPRTV-Y]{1}[0-9]{2}|D6W)[ ]?[0-9AC-FHKNPRTV-Y]{4}$";          //https://regex101.com/library/mS4fO6
        Pattern pattern = Pattern.compile(eircodePattern);

        if (!eircode.isEmpty()){
            if (pattern.matcher(eircode).matches()){
                result = true;
            }
            else {
                result =false;
                JOptionPane.showMessageDialog(null, "Invalid Eircode", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            result =false;
            JOptionPane.showMessageDialog(null, "Please enter an Eircode", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }
}
