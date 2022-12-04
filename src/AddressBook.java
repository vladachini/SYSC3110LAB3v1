import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Testing Github editing
public class AddressBook extends DefaultListModel implements java.io.Serializable {

    private DefaultListModel<BuddyInfo> buddies;

    private List<AddressBookView> views;

    public AddressBook() {
        buddies = new DefaultListModel<>();
        views = new ArrayList<>();
    }

    public void removeBuddy(BuddyInfo buddy) {

        buddies.removeElement(buddy);


    }

    public void setBuddies(DefaultListModel<BuddyInfo> buddies) {
        this.buddies = buddies;
    }

    public void setViews(List<AddressBookView> views) {
        this.views = views;
    }

    public List<AddressBookView> getViews() {
        return views;
    }

    public void printBuddies() {
        for (int i = 0; i < buddies.size(); i++) {
            System.out.println(buddies.get(i));
        }
    }

    public void clearList() {
        buddies.clear();
       //buddies.removeRange(0, buddies.size() - 1);
    }

    public DefaultListModel<BuddyInfo> getBuddies() {
        return buddies;
    }

    public void addBuddy(BuddyInfo buddy) {
        buddies.addElement(buddy);
    }

    public void addListeners(AddressBookView view) {
        views.add(view);
    }

    public void removeListener(AddressBookView view) {
        views.remove(view);
    }

    public void notifyViews() {
        for (AddressBookView v : views) {
            v.update(this);
        }
    }

    public void save(String filename){
        FileOutputStream fos = null;
        File file;
        try {

            file = new File(filename);
            fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            if (!file.exists()) {
                file.createNewFile();
            }

            for (int i = 0; i < buddies.size(); i++) {
                //byte[] bytesArray = buddies.get(i).toString().getBytes();
                bw.write(buddies.get(i).toString());
                bw.newLine();
                bw.flush();
            }
            bw.close();

            System.out.println("File Written Successfully");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public void exportSerial(String filename){
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }




    public void importAddressBook(String filename) {
        BufferedReader buffer = null;
        this.clearList();
        try {
            buffer = new BufferedReader(new FileReader(filename));
            String line = buffer.readLine();
            while (line != null) {
                System.out.println(line);
                this.addBuddy(BuddyInfo.importBuddyInfo(line));
                line = buffer.readLine();
            }
            buffer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public  void importSerial(String filename){
        this.clearList();
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            AddressBook addressBook = (AddressBook) in.readObject();
            in.close();
            fileIn.close();
            addressBook.printBuddies();
            this.setBuddies(addressBook.getBuddies());
            this.setViews(addressBook.getViews());
            this.printBuddies();
        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            c.printStackTrace();

        }

    }

    public String toXML(){
        String xmlFormat = "<addressBook>\n";
        for(int i = 0; i < buddies.size(); i++){
            xmlFormat += "\t<buddyInfo> \n";
            xmlFormat += "" + buddies.get(i).toXML().indent(4);
            xmlFormat += "\t </buddyInfo> \n";
        }
//        for(int i = 0; i < views.size(); i++){
//            xmlFormat += "\t<views> \n";
//            xmlFormat += "" + views.get(i).indent(4);
//            xmlFormat += "\t </views> \n";
//        }

        xmlFormat += "</addressBook>\n";

        return xmlFormat;
    }

    public void exportToXmlFile(String filename){
        try  (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), "utf-8"))){

//            FileOutputStream fs = new FileOutputStream(filename);
//            OutputStreamWriter osw = new OutputStreamWriter(fs,"utf-8" );
//            Writer writer = new BufferedWriter(osw);

            writer.write(this.toXML());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void importFromXmlFile(String filename) throws Exception {
        File f = new File(filename);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser s = spf.newSAXParser();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> addressList = new ArrayList<>();
        ArrayList<String> phoneList = new ArrayList<>();
        boolean start = false;
        DefaultHandler dh = new DefaultHandler(){
            String info;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                super.startElement(uri, localName, qName, attributes);
                System.out.println("START: " +  qName);

            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                super.endElement(uri, localName, qName);
                System.out.println("END: " +  qName);
                if(qName.equals("name")) {
                    nameList.add(info);
                }
                if(qName.equals("address")) {
                    addressList.add(info);
                }
                if(qName.equals("phone")) {
                    phoneList.add(info);
                }

            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                super.characters(ch, start, length);
                info =  new String(ch,start,length);
                System.out.println("CHAR: " + info);


            }
        };
        s.parse(f,dh);
        System.out.println(nameList);
        System.out.println(addressList);
        System.out.println(phoneList);
        DefaultListModel<BuddyInfo> newBuddies = new DefaultListModel<>();
        for(int i =0; i< nameList.size();i++){
            newBuddies.addElement(new BuddyInfo(nameList.get(i), addressList.get(i), phoneList.get(i) ));
        }
        this.setBuddies(newBuddies);


    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBook that = (AddressBook) o;

        for(int i = 0; i< buddies.size(); i++){
            if(this.buddies.get(i).equals(that.getBuddies().get(i))){
                return false;
            }
        }


        return true;
    }

}
