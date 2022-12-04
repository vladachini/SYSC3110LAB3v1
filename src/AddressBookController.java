import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class AddressBookController implements ActionListener, ListSelectionListener, java.io.Serializable {
    private AddressBook addressBook;
    private AddressBookView view;
    private List selected;

    public  AddressBookController(AddressBook addressBook, AddressBookView view){
        this.addressBook = addressBook;
        this.view = view;

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if("Create AddressBook".equals(e.getActionCommand())){
            addressBook.clearList();
        } else if ("Display".equals(e.getActionCommand())) {
            view.getBuddies().setVisible(true);
        } else if ("Add".equals(e.getActionCommand())) {
            String buddyName= JOptionPane.showInputDialog("Please input the Buddy Name");
            String buddyAddress= JOptionPane.showInputDialog("Please input the Buddy Address");
            String buddyPhone= JOptionPane.showInputDialog("Please input the Buddy Phone Number");
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
        } else if ("Export".equals(e.getActionCommand())) {
            String filename= JOptionPane.showInputDialog("Enter Name of file to export to");
            addressBook.save(filename);
        }else if ("Import".equals(e.getActionCommand())) {
            String filename = JOptionPane.showInputDialog("Enter Name of file to import from");
            addressBook.importAddressBook(filename);
            view.update(addressBook);
        }  else if ("Export S".equals(e.getActionCommand())) {
            String filename= JOptionPane.showInputDialog("Enter Name of file to export to");
            addressBook.exportSerial(filename);
        }else if ("Import S".equals(e.getActionCommand())) {
            String filename = JOptionPane.showInputDialog("Enter Name of file to import from");
            addressBook.importSerial(filename);
            view.update(addressBook);
        }else if ("Import X".equals(e.getActionCommand())) {
            try {
                addressBook.importFromXmlFile("test.xml");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            view.update(addressBook);
        }else if ("Export X".equals(e.getActionCommand())) {
            //String filename = JOptionPane.showInputDialog("Enter Name of file to import from");
            addressBook.exportToXmlFile("testExp.xml");
            view.update(addressBook);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            selected =  view.getBuddies().getSelectedValuesList();
            System.out.println(selected);
        }
    }

}
