package interfaces;

import java.sql.Date;
import exception.DataAccessException;
import java.util.List;

import model.Invoice;

public interface InvoiceDataAccess {
    public List<Invoice> getInvoices(Integer id,Date start, Date end, String status) throws DataAccessException;
}
