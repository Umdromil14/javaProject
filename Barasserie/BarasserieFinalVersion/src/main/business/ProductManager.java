package main.business;

import java.sql.Date;
import java.util.List;

import main.exception.DataAccessException;
import main.exception.NegativeNumberException;
import main.data.ProductDBAccess;
import main.interfaces.ProductDataAccess;
import main.model.ProductProportion;

public class ProductManager {
    private ProductDataAccess dao;

    public ProductManager() {
        dao = new ProductDBAccess();
    }

    public List<ProductProportion> getAllProductsQuantity(Date startDate, Date endDate) throws DataAccessException, NegativeNumberException {
        if (startDate.after(endDate)) {
            Date temp = startDate;
            startDate = endDate;
            endDate = temp;
        }

        List<ProductProportion> productsProportion = dao.getAllProductsQuantity(startDate, endDate);
        int totalQuantity = 0;
        for (ProductProportion product : productsProportion) {
            totalQuantity += product.getQuantity();
        }

        for (ProductProportion product : productsProportion) {
            product.setProportion(calculateProductProportion(product, totalQuantity));
        }

        return productsProportion;
    }

    public double calculateProductProportion(ProductProportion product, int totalQuantity) throws NegativeNumberException {
        if (totalQuantity < 0) {
            throw new NegativeNumberException("Total quantity is negative");
        }
        if (product.getQuantity() < 0) {
            throw new NegativeNumberException("Quantity of " + product.getName() + " is negative");
        }
        if (totalQuantity == 0) {
            return 0;
        }

        return (double) product.getQuantity() / totalQuantity;
    }
}