import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{

    private JPanel panel1;

    //Look at lab14 bike shop for JMenu

    public MainMenu(){
        setContentPane(panel1);
        setSize(400,400);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MainMenu();
    }
}
