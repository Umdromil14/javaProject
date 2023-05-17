package main.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.exception.DataAccessException;
import main.interfaces.InvoiceDataAccess;
import main.model.Invoice;

public class InvoiceDBAccess implements InvoiceDataAccess {
    private Connection connection;
    
    @Override
    public List<Invoice> getInvoices(Integer id, Date start, Date end, String status) throws DataAccessException {
        List<Invoice> invoices = new ArrayList<>();

        StringBuilder query = new StringBuilder()
            .append("SELECT be.firstname, doc.inclVATTotal, doc.id as invoiceNumber, doc.date, doc.docType as type, doc.payementDeadline ")
            .append("FROM doc_status ds ")
            .append("INNER JOIN document doc on ds.code = doc.docStatus ")
            .append("INNER JOIN workflow wf on doc.workflow = wf.id ")
            .append("INNER JOIN business_entity be on wf.agent = be.id ")
            .append("where ds.description = ? ")
            .append("and doc.date between ? and ? ")
            .append("and doc.docType = 'invoice' ");

        if (id != null) {
            query.append("and be.id = ?");
        }
        
        connection = SingletonConnection.getInstance();
        
        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            statement.setString(1, status);
            statement.setDate(2, start);
            statement.setDate(3, end);

            if (id != null) {
                statement.setInt(4, id);
            }
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    invoices.add(new Invoice(
                        resultSet.getString("firstname"),
                        resultSet.getDouble("inclVATTotal"),
                        resultSet.getInt("invoiceNumber"),
                        resultSet.getDate("date"),
                        resultSet.getDate("payementDeadline")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("error while loading all the invoices");
        }
        
        return invoices;
    }
}
