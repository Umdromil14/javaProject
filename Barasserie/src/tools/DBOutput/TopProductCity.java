package tools.DBOutput;

public class TopProductCity implements tableEntryCreator {
    private String city;
    private String productName;
    private int quantity;

    public TopProductCity(String city, String productName, int quantity) {
        this.city = city;
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getCity() {
        return city;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public TableEntry toTableEntry() {
        return new TableEntry(city, productName, Integer.toString(quantity));
    }
}