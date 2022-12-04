import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {

    private AddressBook addressBook;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        addressBook = new AddressBook();
        addressBook.addBuddy(new BuddyInfo("Tom","Carleton", "613-123-4569"));
        addressBook.addBuddy(new BuddyInfo("vlad","Carleton", "613-345-4320"));
        addressBook.addBuddy(new BuddyInfo("bob","Carleton", "613-343-4530"));
    }

    @org.junit.jupiter.api.Test
    void importAndExportTest(){
        addressBook.save("test.txt");
        AddressBook addressBook2 = new AddressBook();
        addressBook2.importAddressBook("test.txt");
        assertTrue(addressBook.equals(addressBook2));

    }
    @org.junit.jupiter.api.Test
    void importAndExportSerialTest()  {
        addressBook.exportSerial("test.ser");
        AddressBook addressBook2 = new AddressBook();
        addressBook2.importSerial("test.ser");
        assertTrue(addressBook.equals(addressBook2));

    }
    @org.junit.jupiter.api.Test
    void importAndExportXMLTest() throws Exception {
        addressBook.exportToXmlFile("testExp.xml");
        AddressBook addressBook2 = new AddressBook();
        addressBook2.importFromXmlFile("testExp.xml");
        assertTrue(addressBook.equals(addressBook2));

    }
}