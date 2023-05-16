package business;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import data.InvoiceDBAccess;
import tools.DBOutput.Invoice;

public class InvoiceManager {
    private InvoiceDBAccess invoiceDBAccess;

    public InvoiceManager() {
        invoiceDBAccess = new InvoiceDBAccess();
    }

    public List<Invoice> getInvoices (Integer id,Date start,Date end,String status) throws SQLException {
        if (start.after(end)) {
            Date temp = start;
            start = end;
            end = temp;
        }
        return invoiceDBAccess.getInvoices(id, start, end, status);
    }
}