import java.util.ArrayList;

public class AddressBook {

    private ArrayList<BuddyInfo> buddies;

    public AddressBook () {
        buddies = new ArrayList<BuddyInfo>();
    }
    public void removeBuddy(BuddyInfo buddy) {
        buddies.remove(buddy);
    }

    public void addBuddy(BuddyInfo buddy) {
        buddies.add(buddy);
    }
    public static void main(String[] args) {
        System.out.println("Address Book");
    }
}
