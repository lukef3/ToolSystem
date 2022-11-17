import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{


    private JButton button1;

    public MainMenu(){
        JFrame mainFrame = new JFrame("Main Menu");
        mainFrame.setSize(400,400);
        mainFrame.setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {

    }
}
