package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.DBOutput.Invoice;

public class InvoiceDBAccess {
    private Connection connection;
    
    public List<Invoice> getInvoices (Integer id,Date start,Date end,String status) throws SQLException
    {
        List<Invoice> invoices = new ArrayList<>();
        StringBuilder query = new StringBuilder()
        .append("SELECT be.firstname, doc.inclVATTotal, doc.id as \"invoice_number\", doc.date,dt.code as type, doc.payementDeadline ")
        .append("from doc_status ds ")
        .append("join document doc on ds.code = doc.docStatus ")
        .append("join workflow wf on doc.workflow = wf.id ")
        .append("join business_entity be on wf.agent = be.id ")
        .append("join doc_type dt on doc.docType = dt.code ")
        .append("where ds.description = ? ")
        .append("and doc.date between ? and ?")
        .append("and dt.code = 'invoice' ");
        if (id != null)
        {
            query.append("and be.id = ?");
        }
        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {

            statement.setString(1, status);
            statement.setDate(2, start);
            statement.setDate(3, end);

            if (id != null)
            {
                statement.setInt(4, id);
            }
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    invoices.add(new Invoice(
                        resultSet.getString("firstname"),
                        resultSet.getDouble("inclVATTotal"),
                        resultSet.getInt("invoice_number"),
                        resultSet.getDate("date"),
                        resultSet.getDate("payementDeadline")
                    ));
                }
            }
        }
        return invoices;
    }
}
