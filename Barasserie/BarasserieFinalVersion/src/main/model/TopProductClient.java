package main.model;

import main.controller.TableEntry;
import main.interfaces.tableEntryCreator;

public class TopProductClient implements tableEntryCreator {
    private String clientFirstname;
    private String clientLastname;
    private String productName;
    private int quantity;
    private boolean isEmpty;

    public TopProductClient() {
        this.isEmpty = true;
    }

    public void setTopProductClient(String clientFirstname, String clientLastname, String productName, int quantity) {
        this.clientFirstname = clientFirstname;
        this.clientLastname = clientLastname;
        this.productName = productName;
        this.quantity = quantity;
        this.isEmpty = false;
    }

    public String getClientFirstname() {
        return clientFirstname;
    }

    public String getClientLastname() {
        return clientLastname;
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

    @Override
    public TableEntry toTableEntry() {
        return new TableEntry(clientFirstname, clientLastname, productName, Integer.toString(quantity));
    }
}
