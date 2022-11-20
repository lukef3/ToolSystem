import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame{

    private JPanel panel1;
    JMenu fileMenu, toolsMenu, rentMenu;
    JMenuItem item=null;

    public MainMenu(){
        setContentPane(panel1);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("drill.png")).getImage());
        setLocationRelativeTo(null);

        createFileMenu();
        createToolsMenu();
        createRentMenu();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        menuBar.add(rentMenu);

        setSize(800,600);
        setVisible(true);
    }

    private void createFileMenu(){

        fileMenu = new JMenu("File");

        String itemNames[] = {"New","Open","Save","Quit"};

        for(int i=0;i<itemNames.length;i++){
            item = new JMenuItem(itemNames[i]);
            item.addActionListener(this::actionPerformed);

            if(i==3)
                fileMenu.addSeparator();

            fileMenu.add(item);
        }
    }
    private void createToolsMenu(){

        toolsMenu = new JMenu("Tools");

        String itemNames[] = {"Add","Amend","Remove","View"};

        for(int i=0;i<itemNames.length;i++) {

            item = new JMenuItem(itemNames[i]);
            item.addActionListener(this::actionPerformed);
            toolsMenu.add(item);
        }
    }
    private void createRentMenu(){

        rentMenu = new JMenu("Rent");

        String itemNames[] = {"Add Rental","Return Rental","View Rental"};

        for(int i=0;i<itemNames.length;i++) {

            item = new JMenuItem(itemNames[i]);
            item.addActionListener(this::actionPerformed);
            rentMenu.add(item);
        }
    }

    public void actionPerformed(ActionEvent e) {
        int choice;
        if(e.getActionCommand().equals("New"))
            JOptionPane.showMessageDialog(null,"Creating a new file to store bicycle details",
                    "New File",JOptionPane.INFORMATION_MESSAGE);
        else if(e.getActionCommand().equals("Open"))
            JOptionPane.showMessageDialog(null,"Opening the file that stores bicycle details",
                    "Opening File",JOptionPane.INFORMATION_MESSAGE);
        else if(e.getActionCommand().equals("Save"))
            JOptionPane.showMessageDialog(null,"Saving the file that stores bicycle details",
                    "Saving File",JOptionPane.INFORMATION_MESSAGE);
        else if(e.getActionCommand().equals("Quit")) {
            choice = JOptionPane.showConfirmDialog(null, "Are you sure you wish to quit the application?",
                    "Exiting Application", JOptionPane.YES_NO_CANCEL_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null,"Saving the file that stores bicycle details before terminating",
                        "Saving File",JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }
        else if(e.getActionCommand().equals("Add"))
            JOptionPane.showMessageDialog(null,"Adding new bicycle details",
                    "Adding Bike",JOptionPane.INFORMATION_MESSAGE);
        else if(e.getActionCommand().equals("Amend"))
            JOptionPane.showMessageDialog(null,"Amending bicycle details",
                    "Amending Bike",JOptionPane.INFORMATION_MESSAGE);
        else if(e.getActionCommand().equals("Remove"))
            JOptionPane.showMessageDialog(null,"Removing bicycle details",
                    "Removing Bike",JOptionPane.INFORMATION_MESSAGE);
        else if(e.getActionCommand().equals("View"))
            JOptionPane.showMessageDialog(null,"Viewing bicycle details",
                    "Viewing Bike",JOptionPane.INFORMATION_MESSAGE);

        else if(e.getActionCommand().equals("Add Rental"))
            JOptionPane.showMessageDialog(null,"Adding rental...",
                    "Add Rental",JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
