import javax.swing.*;
import java.awt.*;

public class Rent extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton rentButton;
    private JButton doneButton;
    private JComboBox comboBox1;
    private JPanel panel1;

    public Rent(){
        setContentPane(panel1);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("drill.png")).getImage());
        setLocationRelativeTo(null);

        setSize(600,350);
        setVisible(true);

        
    }

    public static void main(String[] args) {
        new Rent();
    }
}
