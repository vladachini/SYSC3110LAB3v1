import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//Testing Github editing
public class AddressBook extends DefaultListModel  {

    private DefaultListModel<BuddyInfo> buddies;

    private List<AddressBookView> views;

    public AddressBook () {
        buddies = new DefaultListModel<>();
        views = new ArrayList<>();
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

    public void addListeners(AddressBookView view){
        views.add(view);
    }
   public void removeListener(AddressBookView view){
        views.remove(view);
   }
   public void notifyViews(){
        for(AddressBookView v: views){
            v.update();
        }
   }

}
