package main.tools.enumeration;

public enum Execution {
    TOP_PRODUCT_CLIENT(
        "Return the most sold product for a client",
        "firstname", "lastname", "product", "quantity"
    ),
    TOP_PRODUCT_CITY(
        "Return the most sold product for a city",
        "city", "product", "quantity"
    ),
    INVOICE(
        "Return all the invoices according to the given parameters",
        "lastname", "incluTVA", "documentID", "creationDate", "payementDeadline"
    ),
    CITY(
        "Return all the cities",
        "ID", "city", "postal code", "country"
    ),
    USER(
        "Return user information",
        "ID", "firstname", "lastname", "email", "street", "number", "postal", "city", "country"
    ),
    PRODUCT_PROPORTION(
        "Return the proportion of a product sold in a city",
        "product", "proportion", "quantity"
    );
    
    private String description;
    private String[] outputs;

    Execution(String description, String... outputs) {
        this.description = description;
        this.outputs = outputs;
    }

    public String getDescription() {
        return description;
    }

    public String[] getOutputs() {
        return outputs;
    }
}
