package midterm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * The Vendor Controller Class
 *
 * @author Roja Jayashree Karne
 */
@Named
@ApplicationScoped
public class VendorController {

    private List<Vendor> vendors;
    private Vendor thisVendor;

    public VendorController() {
        thisVendor = new Vendor(1, "", "", "");
        getVendorsFromDB();

    }

    /*
      @ getting the values from the database
     */
    private void getVendorsFromDB() {
        try (Connection conn = DBUtils.getConnection()) {
            vendors = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Vendors");
            while (rs.next()) {
                Vendor v = new Vendor(
                        rs.getInt("VendorId"),
                        rs.getString("Name"),
                        rs.getString("ContactName"),
                        rs.getString("PhoneNumber")
                );
                vendors.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendorController.class.getName()).log(Level.SEVERE, null, ex);
            vendors = new ArrayList<>();
        }
    }

    /**
     * list of vendors
     *
     * @return
     */
    public List<Vendor> getVendors() {
        return vendors;
    }

    /**
     * object return of vendor
     *
     * @return
     */
    public Vendor getThisVendor() {
        return thisVendor;
    }

    /**
     * value return by its id
     *
     * @param id
     * @return
     */
    public int getVendorByID(int id) {

        for (Vendor v : vendors) {
            if (v.getVendorId() == id) {
                return v.getVendorId();
            }
        }
        return 0;
    }

    /*
    @return the vendor name
     */
    public String getVendorName(int id) {

        for (Vendor v : vendors) {
            if (v.getVendorId() == id) {
                return v.getName();
            }
        }
        return null;
    }

    /**
     * get back vendor contact number
     *
     * @param id
     * @return
     */
    public String getVendorContact(int id) {
        for (Vendor v : vendors) {
            if (v.getVendorId() == id) {
                return v.getContactName();
            }
        }
        return null;
    }

    /**
     * get back vendor phone number
     *
     * @param id
     * @return
     */
    public String getVendorPhone(int id) {
        for (Vendor v : vendors) {
            if (v.getVendorId() == id) {
                return v.getPhNumber();
            }
        }
        return null;
    }

    /**
     * on click cancel returning back to home page
     *
     * @return
     */
    public String cancel() {
        return "index";
    }

    /**
     * getting back vendor class object
     *
     * @param id
     * @return
     */
    public Vendor getVendorById(int id) {
        for (Vendor v : vendors) {
            if (v.getVendorId() == id) {
                return v;
            }
        }
        return null;
    }

    /**
     * to add vendors into list
     *
     * @return
     */
    public String add() {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "INSERT INTO Vendors (VendorId, Name, ContactName, PhoneNumber) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, thisVendor.getVendorId());
            pstmt.setString(2, thisVendor.getName());
            pstmt.setString(3, thisVendor.getContactName());
            pstmt.setString(4, thisVendor.getPhNumber());
            pstmt.executeUpdate();
            //products.add(thisProduct);
            //thisProduct = new Product();
        } catch (SQLException ex) {
            Logger.getLogger(VendorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getVendorsFromDB();
        thisVendor = getVendorById(thisVendor.getVendorId());
        return "index";
    }

    /**
     * redirect to new page
     *
     * @return
     */
    public String addVendor() {
        thisVendor = new Vendor(1, "", "", "");
        return "addVendor";
    }
}
