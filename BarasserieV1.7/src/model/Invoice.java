package model;

import java.sql.Date;

import controller.TableEntry;
import interfaces.tableEntryCreator;

public class Invoice implements tableEntryCreator {
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

    @Override
    public TableEntry toTableEntry() {
        return new TableEntry(
            firstname, 
            String.valueOf(incluTVA), 
            String.valueOf(invoiceNumber), 
            String.valueOf(payementDeadline), 
            String.valueOf(creationDate));
    }

}
