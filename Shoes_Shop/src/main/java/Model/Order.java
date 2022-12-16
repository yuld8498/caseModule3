package Model;

public class Order {
    private String userName;
    private String productName;
    private int productQuaility;

    public Order() {
    }

    public Order(String userName, String productName, int productQuaility) {
        this.userName = userName;
        this.productName = productName;
        this.productQuaility = productQuaility;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuaility() {
        return productQuaility;
    }

    public void setProductQuaility(int productQuaility) {
        this.productQuaility = productQuaility;
    }

}
