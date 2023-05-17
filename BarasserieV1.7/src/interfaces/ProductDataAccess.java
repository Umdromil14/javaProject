package interfaces;

import java.sql.Date;
import exception.DataAccessException;
import java.util.List;

import model.ProductProportion;

public interface ProductDataAccess {
    public List<ProductProportion> getAllProductsQuantity(Date startDate, Date endDate) throws DataAccessException;
}
