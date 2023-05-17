package main.interfaces;

import java.sql.Date;
import java.util.List;

import main.exception.DataAccessException;
import main.model.Invoice;

public interface InvoiceDataAccess {
    public List<Invoice> getInvoices(Integer id,Date start, Date end, String status) throws DataAccessException;
}
