package tools.enumeration;

public enum Execution {
    TOP_PRODUCT_CLIENT(
        "Return the most sold product for a client",
        new String[] {"Client ID"},
        new String[] {"firstname", "lastname", "product", "quantity"}
    ),
    TOP_PRODUCT_CITY(
        "Return the most sold product for a city",
        new String[] {"City ID"},
        new String[] {"city", "product", "quantity"}
    ),
    INVOICE(
        "Return all the invoices according to the given parameters",
        new String[] {"Client ID (optional)", "City ID", "Status"},
        new String[] {"invoiceID", "date", "status", "product", "quantity"}
    ),
    READ_USER(
        "Return user information",
        new String[] {"User ID"},
        new String[] {"ID", "firstname", "lastname", "email", "street", "number", "postal", "city", "country"}
    ),
    UPDATE_USER(
        "Update user information",
        new String[] {"User ID"},
        new String[] {}
    ),
    DELETE_USER(
        "Update user information",
        new String[] {"User ID"},
        new String[] {}
    ); 
    
    private String description;
    private String[] inputs;
    private String[] outputs;

    Execution(String description, String[] inputs, String[] outputs) {
        this.description = description;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public String getDescription() {
        return description;
    }
    
    public String[] getInputs() {
        return inputs;
    }

    public String[] getOutputs() {
        return outputs;
    }
}
