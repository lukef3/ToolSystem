import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login extends JFrame{
    private JPanel panel1;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JLabel lblLogin;

    ArrayList<User> allUsers = new ArrayList<>();

    public Login() {
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("drill.png")).getImage());    //https://www.youtube.com/watch?v=cKmr-9Wc2u4
        setVisible(true);
        Toolkit toolkit = getToolkit();                                                         //https://www.youtube.com/watch?v=pbDbnmlFTS0
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);

        setSize(300, 400);

        User test = new User("test", "test");
        allUsers.add(test);

        User johnTest = new User("JohnBrosnan", "password");
        allUsers.add(johnTest);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(validLogin()){
                    JOptionPane.showMessageDialog(null, "Welcome " + txtUsername.getText());
                    txtUsername.setText("");
                    txtPassword.setText("");
                    dispose();
                    new MainMenu(allUsers);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Shutting down... Goodbye :)");
                System.exit(0);
            }
        });
    }

    private boolean validLogin() {
        boolean result = true, containsUsername = false, containsPassword = false;
        String password = String.valueOf(txtPassword.getPassword());  //https://stackoverflow.com/questions/14162225/getting-text-from-password-field
        if (!txtUsername.getText().equals("")){
            if(!password.equals("")){
                for (User user : allUsers){
                    if (txtUsername.getText().equals(user.getUsername())){
                        containsUsername = true;
                        break;
                    }
                }
                if (containsUsername){
                    for (User user1 : allUsers){
                        if (password.equals(user1.getPassword())){
                            containsPassword = true;
                            break;
                        }
                    }
                    if (containsPassword){
                        result = true;
                    }
                    else {
                        result = false;
                        JOptionPane.showMessageDialog(null, "Invalid Password", "Invalid", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    result = false;
                    JOptionPane.showMessageDialog(null, "Invalid Username", "Invalid", JOptionPane.ERROR_MESSAGE);
                }
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

    public void test() {

    }
}
