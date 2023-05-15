package tools.DBOutput;

import java.sql.Date;

import controller.TableEntry;

public class Invoice {
    private String firstname;
    private double incluTVA;
    private int invoiceNumber;
    private Date payementDeadline;
    private Date creationDate;

    public Invoice(String firstname, double incluTVA, int invoiceNumber, Date payementDeadline, Date creationDate) {
        this.firstname = firstname;
        this.incluTVA = incluTVA;
        this.invoiceNumber = invoiceNumber;
        this.payementDeadline = payementDeadline;
        this.creationDate = creationDate;
    }

    public TableEntry toTableEntry() {
        return new TableEntry(
            firstname, 
            String.valueOf(incluTVA), 
            String.valueOf(invoiceNumber), 
            String.valueOf(payementDeadline), 
            String.valueOf(creationDate));
    }

}
