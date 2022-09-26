public class BuddyInfo {
    private String name;
    private String address;
    private int phone_num;

    public BuddyInfo() {
        name = "Bob";
        address = "123 Default St";
        phone_num = 1234567890;
    }
    public BuddyInfo(String name) {
        this.name = name;
        address = "123 Default St";
        phone_num = 1234567890;
    }
    public BuddyInfo(String name, String address, int phone_num) {
        this.name = name;
        this.address = address;
        this.phone_num =phone_num;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone_num() {
        return phone_num;
    }


    public String getName() {
        return name;
    }





    public static void main(String[] args) {
        BuddyInfo b1 = new BuddyInfo("Homer");
        System.out.println("Hello " + b1.getName());
    }
}
