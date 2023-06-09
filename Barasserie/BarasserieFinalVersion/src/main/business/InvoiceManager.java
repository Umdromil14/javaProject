package main.business;

import java.sql.Date;
import java.util.List;

import main.data.InvoiceDBAccess;
import main.exception.DataAccessException;
import main.model.Invoice;

public class InvoiceManager {
    private InvoiceDBAccess invoiceDBAccess;

    public InvoiceManager() {
        invoiceDBAccess = new InvoiceDBAccess();
    }

    public List<Invoice> getInvoices (Integer id,Date start,Date end,String status) throws DataAccessException {
        if (start.after(end)) {
            Date temp = start;
            start = end;
            end = temp;
        }
        return invoiceDBAccess.getInvoices(id, start, end, status);
    }
}