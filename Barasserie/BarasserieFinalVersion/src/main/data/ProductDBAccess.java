package main.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.exception.DataAccessException;
import main.interfaces.ProductDataAccess;
import main.model.ProductProportion;

public class ProductDBAccess implements ProductDataAccess {
    private Connection connection;

    @Override
    public List<ProductProportion> getAllProductsQuantity(Date startDate, Date endDate) throws DataAccessException {
        List<ProductProportion> products = new ArrayList<>();

        String query = new StringBuilder()
                .append("SELECT p.name, SUM(td.quantity) AS quantity ")
                .append("FROM product p ")
                .append("INNER JOIN transaction_detail td ON td.product = p.id ")
                .append("INNER JOIN document d ON d.docType = td.docType AND d.id = td.document ")
                .append("WHERE d.date BETWEEN ? AND ? ")
                .append("GROUP BY p.name ")
                .append("ORDER BY quantity DESC")
                .toString();

        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(new ProductProportion(
                            resultSet.getString("name"),
                            resultSet.getInt("quantity")));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("error while loading all the products");
        }

        return products;
    }
}
