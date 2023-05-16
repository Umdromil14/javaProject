package tools.DBOutput;

import java.sql.Date;

import controller.TableEntry;

public class Invoice {
    private String firstname;
    private double incluTVA;
    private int invoiceNumber;
    private Date payementDeadline;
    private String typeDoc;

    public Invoice(String firstname, double incluTVA, int invoiceNumber, Date payementDeadline, String type) {
        this.firstname = firstname;
        this.incluTVA = incluTVA;
        this.invoiceNumber = invoiceNumber;
        this.payementDeadline = payementDeadline;
        this.typeDoc = type;
    }

    public TableEntry toTableEntry() {
        return new TableEntry(
            firstname,
            Double.toString(incluTVA),
            Integer.toString(invoiceNumber),
            payementDeadline.toString(),
            typeDoc
        );
    }

}
