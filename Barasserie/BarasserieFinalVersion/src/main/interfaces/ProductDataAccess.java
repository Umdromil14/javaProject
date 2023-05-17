package main.interfaces;

import java.sql.Date;
import java.util.List;

import main.exception.DataAccessException;
import main.model.ProductProportion;

public interface ProductDataAccess {
    public List<ProductProportion> getAllProductsQuantity(Date startDate, Date endDate) throws DataAccessException;
}
