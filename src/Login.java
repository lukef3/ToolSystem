import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JPanel panel1;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JLabel lblDrillIcon;

    public Login() {
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        ImageIcon img = new ImageIcon("drill.png");
        setIconImage(img.getImage());
        setVisible(true);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(validLogin()){
                    JOptionPane.showMessageDialog(null, "Welcome " + txtUsername.getText());
                    txtUsername.setText("");
                    txtPassword.setText("");
                    MainMenu mainMenu = new MainMenu();
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Shutting down...... Goodbye :)");
                System.exit(0);
            }
        });
    }

    private boolean validLogin() {
        boolean result = true;
        String password = String.valueOf(txtPassword.getPassword());  //https://stackoverflow.com/questions/14162225/getting-text-from-password-field
        if (!txtUsername.getText().equals("")){
            if(!password.equals("")){

            }
            else{
                result = false;
                JOptionPane.showMessageDialog(null, "You must enter a password!", "Invalid", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            result = false;
            JOptionPane.showMessageDialog(null, "You must enter a username!", "Invalid", JOptionPane.ERROR_MESSAGE);
        }

       // if(txtUsername.getText().equals(""));
        return result;
    }


    public static void main(String[] args) {
        new Login();
    }
}
