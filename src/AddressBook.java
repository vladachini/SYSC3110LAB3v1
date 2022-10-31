import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//Testing Github editing
public class AddressBook extends DefaultListModel  {

    private DefaultListModel<BuddyInfo> buddies;

    public AddressBook () {
        buddies = new DefaultListModel<>();
    }
    public void removeBuddy(BuddyInfo buddy) {

        buddies.removeElement(buddy);

    }
    public void printBuddies(){
        for (int i =0; i< buddies.size(); i++){
            System.out.println(buddies.get(i).getName());
        }
    }
    public void clearList(){
        buddies.removeRange(0, buddies.size()-1);
    }

    public DefaultListModel<BuddyInfo> getBuddies(){
        return buddies;
    }

    public void addBuddy(BuddyInfo buddy) {
        buddies.addElement(buddy);
    }
    public static void main(String[] args) {
        System.out.println("Address Book");
        BuddyInfo buddy = new BuddyInfo("Tom","Carleton", 613);
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.printBuddies();
        //addressBook.removeBuddy(0);

    }


}
