import java.util.ArrayList;
//Testing Github editing
public class AddressBook {

    private ArrayList<BuddyInfo> buddies;

    public AddressBook () {
        buddies = new ArrayList<BuddyInfo>();
    }
    public BuddyInfo removeBuddy(int index) {
        if(index >= 0 && index < buddies.size()){
            return buddies.remove(index);
        }
        return null;
    }
    public void printBuddies(){
        for (BuddyInfo buddy : buddies){
            System.out.println(buddy.getName());
        }
    }

    public void addBuddy(BuddyInfo buddy) {
        buddies.add(buddy);
    }
    public static void main(String[] args) {
        System.out.println("Address Book");
        BuddyInfo buddy = new BuddyInfo("Tom","Carleton", 613);
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.printBuddies();
        addressBook.removeBuddy(0);

    }
}
