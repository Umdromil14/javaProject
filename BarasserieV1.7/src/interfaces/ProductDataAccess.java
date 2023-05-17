package interfaces;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import model.ProductProportion;

public interface ProductDataAccess {
    public List<ProductProportion> getAllProductsQuantity(Date startDate, Date endDate) throws SQLException;
}
