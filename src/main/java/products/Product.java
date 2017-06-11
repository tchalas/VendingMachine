package products;

/**
 * Created by tchalas on 9/06/17.
 */
public class Product {
    private double price = 1;
    private String name;
    public  double getPrice()
    {
        return this.price;
    }
    public String getName()
    {
        return this.name;
    }

    public Product(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

}
