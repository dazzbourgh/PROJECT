package borisevich.emailgenerator.model;

/**
 * Created by Leonid on 06.09.2016.
 */
public class Address {
    private int address_id;
    private String address;
    private String name;

    public Address(int address_id, String address, String name){
        this.address_id = address_id;
        this.address = address;
        this.name = name;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
