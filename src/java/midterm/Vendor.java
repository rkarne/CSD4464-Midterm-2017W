package midterm;

/**
 * The Vendor Model Class
 *
 * @author Roja JAyashree KArne
 */
public class Vendor {

    private int vendorId;
    private String name;
    private String contactName;
    private String phNumber;

    /**
     * constructor to call its is a paramterized
     *
     * @param vendorid
     * @param vendorName
     * @param vendorContact
     * @param vendorPhone
     */
    public Vendor(int vendorid, String vendorName, String vendorContact, String vendorPhone) {
        this.vendorId = vendorid;
        this.name = vendorName;
        this.contactName = vendorContact;
        this.phNumber = vendorPhone;
    }

    /**
     * @ setters and getters in the vendor
     */
    public int getVendorId() {
        return vendorId;
    }

    public String getName() {
        return name;
    }

    public String getContactName() {
        return contactName;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

}
