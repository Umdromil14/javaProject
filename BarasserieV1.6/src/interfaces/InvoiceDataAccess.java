package interfaces;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import tools.DBOutput.Invoice;

public interface InvoiceDataAccess {
    public List<Invoice> getInvoices(Integer id,Date start, Date end, String status) throws SQLException;
}
