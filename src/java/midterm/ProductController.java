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
 * The Product Controller Class
 *
 * @author Roja Jayashree KArne c0689497
 */
@Named
@ApplicationScoped

public class ProductController {

    private List<Product> products;
    private Product thisProduct;

    /**
     * Basic Constructor for Products - Retrieves from DB
     */
    public ProductController() {
        thisProduct = new Product();
        getlistfromDB();
    }

    /**
     * getting the list from the db
     */
    private void getlistfromDB() {
        try {
            Connection conn = DBUtils.getConnection();
            products = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Products");
            while (rs.next()) {
                Product p = new Product();
                p.setName(rs.getString("Name"));
                p.setProductId(rs.getInt("ProductId"));
                p.setVendorId(rs.getInt("VendorId"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            products = new ArrayList<>();
        }
    }

    /**
     * Retrieve the full list of Products
     *
     * @return the List of Products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Retrieve the Product Model used in Forms
     *
     * @return the Product Model used in Forms
     */
    public Product getThisProduct() {
        return thisProduct;
    }

    /**
     * Set the Product Model used in Forms
     *
     * @param thisProduct the Product Model used in Forms
     */
    public void setThisProduct(Product thisProduct) {
        this.thisProduct = thisProduct;
    }

    /**
     * Add a new Product to the Database and List
     *
     * @param vendorId
     * @param name
     * @return
     */
    public String add(int vendorId, String name) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "INSERT INTO Products (Name, VendorId) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, vendorId);
            pstmt.executeUpdate();
            //products.add(thisProduct);
            //thisProduct = new Product();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getlistfromDB();
        thisProduct = getProductByID(thisProduct.getProductId());
        return "index";
    }

    /**
     * get a Product from Database and List
     *
     * @param productid
     * @return
     */
    public Product getProductByID(int productid) {
        for (Product p : products) {
            if (p.getProductId() == productid) {
                return p;
            }
        }
        return null;
    }

    /**
     *
     * @param vendorid
     * @return product object
     */
    public Product getVendorByID(int vendorid) {
        for (Product p : products) {
            if (p.getVendorId() == vendorid) {
                return p;
            }
        }
        return null;
    }

    /**
     * delete any product
     *
     * @param id
     * @return
     */
    public String delete(int id) {

        try {
            if (thisProduct.getProductId() >= 0) {
                Connection conn = DBUtils.getConnection();
                String sql = "DELETE FROM Products WHERE ProductId = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getlistfromDB();
        return "index";
    }

    /**
     * on click edit assign values to class variables
     *
     * @param prodId
     * @param prodName
     * @param vendorId
     * @return
     */
    public String edit(int prodId, String prodName, int vendorId) {
        thisProduct.setProductId(prodId);
        thisProduct.setName(prodName);
        thisProduct.setVendorId(vendorId);
        return "editProduct";
    }

    /**
     * on click save in edit product
     *
     * @param prodName
     * @param vendorId
     * @param prodId
     * @return html page
     */
    public String save(String prodName, int vendorId, int prodId) {
        thisProduct.setVendorId(vendorId);
        try (Connection conn = DBUtils.getConnection()) {
            VendorController vc = new VendorController();
            if (thisProduct.getProductId() > 0) {
                String sql = "UPDATE Products SET Name = ?, VendorId = ? WHERE ProductId = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, prodName);
                pstmt.setInt(2, vendorId);
                pstmt.setInt(3, prodId);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getlistfromDB();
        thisProduct = getProductByID(thisProduct.getVendorId());
        return "index";
    }

    /**
     * On click cancel
     *
     * @return index page
     */
    public String cancel() {
        return "index";
    }

    /**
     * on click product add
     *
     * @return html page
     */
    public String addProduct() {
        return "addProduct";
    }
}
