package tools.DBOutput;

public class TopProductClient implements tableEntryCreator {
    private String clientFirstname;
    private String clientLastname;
    private String productName;
    private int quantity;

    public TopProductClient(String clientFirstname, String clientLastname, String productName, int quantity) {
        this.clientFirstname = clientFirstname;
        this.clientLastname = clientLastname;
        this.productName = productName;
        this.quantity = quantity;
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

    public TableEntry toTableEntry() {
        return new TableEntry(clientFirstname, clientLastname, productName, Integer.toString(quantity));
    }
}
