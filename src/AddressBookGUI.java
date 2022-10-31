import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddressBookGUI extends JFrame implements ActionListener, ListSelectionListener {

    private JFrame frame;
    private JMenu addrBookMenu, buddyInfoMenu;
    private JMenuBar menuBar;
    private JMenuItem operations;

    private JList buddies;

    private List selected;
    private AddressBook addressBook;
    public AddressBookGUI(AddressBook addressBook) {
        super("AdressBookGUI");
        frame = new JFrame("Address Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.getContentPane().setLayout(new FlowLayout());

        this.addressBook = addressBook;

        menuBar = new JMenuBar();

        addrBookMenu = new JMenu("Address Book");
        buddyInfoMenu = new JMenu("Buddy Info");

        //Creating Address book Menu
        menuBar.add(addrBookMenu);
        operations = new JMenuItem("Create a new AddressBook");
        operations.setActionCommand("Create AddressBook");
        operations.addActionListener(this);
        addrBookMenu.add(operations);
        operations = new JMenuItem("Display BuddyInfos");
        operations.setActionCommand("Display");
        operations.addActionListener(this);
        addrBookMenu.add(operations);

        //Creating buddyInfo Menu
        menuBar.add(buddyInfoMenu);
        operations = new JMenuItem("Add a Buddy");
        operations.setActionCommand("Add");
        operations.addActionListener(this);
        buddyInfoMenu.add(operations);
        operations = new JMenuItem("Remove a Buddy");
        operations.setActionCommand("Remove");
        buddyInfoMenu.add(operations);


        buddies = new JList<>(addressBook.getBuddies());
        buddies.addListSelectionListener(this);
        buddies.setVisible(false);

        operations.addActionListener(this);
        frame.setJMenuBar(menuBar);
        frame.add(new JScrollPane(buddies));
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        BuddyInfo buddy = new BuddyInfo("Tom","Carleton", 613);
        BuddyInfo buddy2 = new BuddyInfo("vlad","Carleton", 613);
        BuddyInfo buddy3 = new BuddyInfo("bob","Carleton", 613);
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.addBuddy(buddy2);
        addressBook.addBuddy(buddy3);
        AddressBookGUI ab1 = new AddressBookGUI(addressBook);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("Create AddressBook".equals(e.getActionCommand())){
            addressBook.clearList();
        } else if ("Display".equals(e.getActionCommand())) {
            buddies.setVisible(true);
        } else if ("Add".equals(e.getActionCommand())) {
            String buddyName= JOptionPane.showInputDialog("Please input the Buddy Name");
            String buddyAddress= JOptionPane.showInputDialog("Please input the Buddy Address");
            long buddyPhone= Long.parseLong(JOptionPane.showInputDialog("Please input the Buddy Phone Number"));
            BuddyInfo newBuddy = new BuddyInfo(buddyName,buddyAddress,buddyPhone);
            addressBook.addBuddy(newBuddy);
            System.out.println(newBuddy);
        } else if ("Remove".equals(e.getActionCommand())) {
            if(selected.size() >=1){
                for(int i = 0; i <selected.size(); i++){
                    addressBook.removeBuddy((BuddyInfo)selected.get(i));
                }
            }
            System.out.println("Remove BuddyInfo");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            selected =  buddies.getSelectedValuesList();
            System.out.println(selected);
        }
    }
}