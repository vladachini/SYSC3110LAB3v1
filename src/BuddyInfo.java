import java.io.*;

public class BuddyInfo implements java.io.Serializable{
    private String name;
    private String address;
    private String phone_num;

    public BuddyInfo() {
        name = "Bob";
        address = "123 Default St";
        phone_num = "123-456-7890";
    }
    public BuddyInfo(String name) {
        this.name = name;
        address = "123 Default St";
        phone_num = "123-456-7890";
    }
    public BuddyInfo(String name, String address, String phone_num) {
        this.name = name;
        this.address = address;
        this.phone_num =phone_num;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_num() {
        return phone_num;
    }


    public String getName() {
        return name;
    }

    public String toString(){
        String info = "";
        info += name + "#"+ address + "#"+ phone_num;
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }


        if (!(o instanceof BuddyInfo)) {
            return false;
        }

        BuddyInfo c = (BuddyInfo) o;

        // Compare the data members and return accordingly
        return c.getName().equals(this.name) && c.getAddress().equals(this.address) && c.getPhone_num() == this.phone_num;
    }

    public static BuddyInfo  importBuddyInfo(String buddy){
        String[] st = buddy.split("#");
        BuddyInfo newBuddy = new BuddyInfo(st[0],st[1],st[2]);
        return newBuddy;

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

    public static BuddyInfo importSerial(String filename){
        BuddyInfo buddy= null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            buddy = (BuddyInfo) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return null;
        }
        return buddy;
    }

    public String toXML(){
        String xmlFormat = "<buddy>\n";
        xmlFormat += "\t <name>"+this.name+"</name>\n";
        xmlFormat += "\t <address>"+this.address+"</address>\n";
        xmlFormat += "\t <phone>"+this.phone_num+"</phone>\n";
        xmlFormat += "</buddy>\n";
        return xmlFormat.indent(4);
    }


}
