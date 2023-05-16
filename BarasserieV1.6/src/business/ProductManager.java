package business;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import data.ProductDBAccess;
import interfaces.ProductDataAccess;
import tools.DBOutput.ProductProportion;

public class ProductManager {
    private ProductDataAccess dao;

    public ProductManager() {
        dao = new ProductDBAccess();
    }

    public List<ProductProportion> getAllProductsQuantity(Date startDate, Date endDate) throws SQLException {
        if (startDate.after(endDate)) {
            Date temp = startDate;
            startDate = endDate;
            endDate = temp;
        }

        return dao.getAllProductsQuantity(startDate, endDate);
    }
}