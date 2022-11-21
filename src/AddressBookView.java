import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddressBookView extends JFrame  {

    private JFrame frame;
    private JMenu addrBookMenu, buddyInfoMenu;
    private JMenuBar menuBar;
    private JMenuItem operations;

    private JList buddies;

    private List selected;
    private AddressBook addressBook;

    private AddressBookController abc;
    public AddressBookView(AddressBook addressBook) {
        super("AdressBookGUI");
        frame = new JFrame("Address Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.getContentPane().setLayout(new FlowLayout());

        this.addressBook = addressBook;
        abc = new AddressBookController(addressBook, this);

        menuBar = new JMenuBar();

        addrBookMenu = new JMenu("Address Book");
        buddyInfoMenu = new JMenu("Buddy Info");

        //Creating Address book Menu
        menuBar.add(addrBookMenu);
        operations = new JMenuItem("Create a new AddressBook");
        operations.setActionCommand("Create AddressBook");
        operations.addActionListener(abc);
        addrBookMenu.add(operations);
        operations = new JMenuItem("Display BuddyInfos");
        operations.setActionCommand("Display");
        operations.addActionListener(abc);
        addrBookMenu.add(operations);

        //Creating buddyInfo Menu
        menuBar.add(buddyInfoMenu);
        operations = new JMenuItem("Add a Buddy");
        operations.setActionCommand("Add");
        operations.addActionListener(abc);
        buddyInfoMenu.add(operations);
        operations = new JMenuItem("Remove a Buddy");
        operations.setActionCommand("Remove");
        buddyInfoMenu.add(operations);


        buddies = new JList<>(addressBook.getBuddies());
        buddies.addListSelectionListener(abc);
        buddies.setVisible(false);

        addressBook.addListeners(this);
        operations.addActionListener(abc);
        frame.setJMenuBar(menuBar);
        frame.add(new JScrollPane(buddies));
        frame.setVisible(true);
    }

    public JList getBuddies(){
        return buddies;
    }


    public static void main(String[] args) {
        BuddyInfo buddy = new BuddyInfo("Tom","Carleton", 613);
        BuddyInfo buddy2 = new BuddyInfo("vlad","Carleton", 613);
        BuddyInfo buddy3 = new BuddyInfo("bob","Carleton", 613);
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.addBuddy(buddy2);
        addressBook.addBuddy(buddy3);
        AddressBookView ab1 = new AddressBookView(addressBook);
    }

    public void update(){

    }
}