public class BuddyInfo {
    private String name;
    private String address;
    private long phone_num;

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
    public BuddyInfo(String name, String address, long phone_num) {
        this.name = name;
        this.address = address;
        this.phone_num =phone_num;
    }

    public String getAddress() {
        return address;
    }

    public long getPhone_num() {
        return phone_num;
    }


    public String getName() {
        return name;
    }

    public String toString(){
        String info = "";
        info += name + " who lives on "+ address + " with phone number: "+ phone_num;
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

    public static void main(String[] args) {
        BuddyInfo b1 = new BuddyInfo("Homer");
        System.out.println("Hello " + b1.getName());
    }
}
