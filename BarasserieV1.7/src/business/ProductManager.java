package business;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import data.ProductDBAccess;
import interfaces.ProductDataAccess;
import model.ProductProportion;

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

        List<ProductProportion> productsProportion = dao.getAllProductsQuantity(startDate, endDate);

        int totalQuantity = 0;
        for (ProductProportion product : productsProportion) {
            totalQuantity += product.getQuantity();
        }

        for (ProductProportion product : productsProportion) {
            product.setProportion(totalQuantity);
        }

        return productsProportion;
    }
}