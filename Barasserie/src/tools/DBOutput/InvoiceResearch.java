package tools.DBOutput;

import java.sql.Date;

public class InvoiceResearch implements tableEntryCreator {
    private String clientName;
    private float totalRemaining;
    private Integer invoiceNumber;
    private Date limitDate;

    public InvoiceResearch(String clientName, float totalRemaining, Integer invoiceNumber, Date limitDate) {
        this.clientName = clientName;
        this.totalRemaining = totalRemaining;
        this.invoiceNumber = invoiceNumber;
        this.limitDate = limitDate;
    }
    public String getClientName() {
        return clientName;
    }
    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }
    public Date getLimitDate() {
        return limitDate;
    }
    public float getTotalRemaining() {
        return totalRemaining;
    }

    public TableEntry toTableEntry() {
        return new TableEntry(Integer.toString(invoiceNumber), clientName, limitDate.toString(), Float.toString(totalRemaining));
    }
}
