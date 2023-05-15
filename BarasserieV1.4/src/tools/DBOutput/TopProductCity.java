package tools.DBOutput;

import controller.TableEntry;
import interfaces.tableEntryCreator;

public class TopProductCity implements tableEntryCreator {
    private String city;
    private String productName;
    private int quantity;
    private boolean isEmpty;

    public TopProductCity() {
        this.isEmpty = true;
    }

    public void setTopProductCity(String city, String productName, int quantity) {
        this.city = city;
        this.productName = productName;
        this.quantity = quantity;
        this.isEmpty = false;
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

    public boolean isEmpty() {
        return isEmpty;
    }

    public TableEntry toTableEntry() {
        return new TableEntry(city, productName, Integer.toString(quantity));
    }
}