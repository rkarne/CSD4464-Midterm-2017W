package midterm;

/**
 * The Product Model Class
 *
 * @author Roja JAyashree KArne
 */
public class Product {

    private int productId;
    private String name;
    private int vendorId;

    /**
     * Retrieve the Product ID
     *
     * @return the Product ID
     */
    Product(int id, String name, int vendorId) {
        this.productId = id;
        this.name = name;
        this.vendorId = vendorId;
    }

    Product() {

    }

    public int getProductId() {
        return productId;
    }

    /**
     * Set the Product ID
     *
     * @param productId the Product ID
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Retrieve the Product Name
     *
     * @return the Product Name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Product Name
     *
     * @param name the Product Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieve the Vendor ID
     *
     * @return the Vendor ID
     */
    public int getVendorId() {
        return vendorId;
    }

    /**
     * Set the Vendor ID
     *
     * @param vendorId the Vendor ID
     */
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

}
