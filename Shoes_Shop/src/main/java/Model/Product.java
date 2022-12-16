package Model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Product {
    private int productID;
    private String productName;
    private String productDescription;
    private double price;
    private int quaility;
    private int typeID;
    private String typeName;

    public Product() {
    }

    public Product(int productID, String productName, String productDescription, double price, int quaility, int typeID, String typeName) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.quaility = quaility;
        this.typeID = typeID;
        this.typeName = typeName;
    }

    public Product(String productName, String productDescription, double price, int quaility, int typeID) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.quaility = quaility;
        this.typeID = typeID;
    }
    public Product(String productName, String productDescription, double price,String typeName, int quaility ) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.quaility = quaility;
        this.typeName = typeName;
    }

    public Product(int productID, String productName, String productDescription, double price, int quaility, int typeID) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.quaility = quaility;
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public Product(String productName, String productDescription, double price, int quaility) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.quaility = quaility;
    }

    public Product(int productID, String productName, String productDescription, double price, int quaility) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.quaility = quaility;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

@NotEmpty(message = "product name is not empty")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @NotEmpty(message = "product description is not empty")
    @Length(min = 5, max = 100, message ="Leng of description from 5 to 100 character" )
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

//    @NotEmpty(message = "price is not empty")
//    @Min(value = 1000, message = "min price is 1 thounsand VND")
//    @Max(value = 1000000000, message = "max price is 1 billion VND")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    @NotEmpty(message = "quaility is not empty")
    public int getQuaility() {
        return quaility;
    }

    public void setQuaility(int quaility) {
        this.quaility = quaility;
    }

}
