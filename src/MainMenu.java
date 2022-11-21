import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class MainMenu extends JFrame{

    private JPanel panel1;
    JMenu fileMenu, toolsMenu, rentMenu;
    JMenuItem item=null;
    ArrayList<Tool> allTools = new ArrayList<>();

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
            addTool();
        else if(e.getActionCommand().equals("Amend"))
            JOptionPane.showMessageDialog(null,"Amending bicycle details",
                    "Amending Bike",JOptionPane.INFORMATION_MESSAGE);
        else if(e.getActionCommand().equals("Remove"))
            JOptionPane.showMessageDialog(null,"Removing bicycle details",
                    "Removing Bike",JOptionPane.INFORMATION_MESSAGE);
        else if(e.getActionCommand().equals("View"))
            viewTools(allTools);


        else if(e.getActionCommand().equals("Add Rental"))
            JOptionPane.showMessageDialog(null,"Adding rental...",
                    "Add Rental",JOptionPane.INFORMATION_MESSAGE);
    }

    public void addTool(){
        String toolType, toolManufacturer, toolDesc;
        Float toolRate;

        toolType = JOptionPane.showInputDialog("Please enter tool type:");
        toolManufacturer = JOptionPane.showInputDialog("Please enter the manufacturer of the tool:");
        toolDesc = JOptionPane.showInputDialog("Please enter the tool's description");
        toolRate = Float.parseFloat(JOptionPane.showInputDialog("Please enter the tool rate:"));

        Tool tool = new Tool(toolType, toolManufacturer, toolRate, toolDesc);
        allTools.add(tool);
    }

    public void viewTools(ArrayList<Tool> allTools){
        String allToolData = "";
        Tool tool;

        Iterator<Tool> iterator = allTools.iterator();

        while (iterator.hasNext()){
            tool = iterator.next();
            if(tool != null){
                allToolData += tool + "\n\n\n";
            }
        }
        JOptionPane.showMessageDialog(null, allToolData, "List of all tools", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
