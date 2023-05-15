package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.localityDataAccess;

public class LocalityDBAccess implements localityDataAccess {
    private Connection connection;
    @Override
    public ArrayList<String> getCountries() throws SQLException {
        connection = SingletonConnection.getInstance();
        ArrayList<String> countries = new ArrayList<String>();
        String query = "SELECT DISTINCT name FROM country";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {
                    countries.add(rs.getString("name"));
                }
            }
        }

        return countries;
    }

    @Override
    public ArrayList<Integer> getPostalCode(String city) throws SQLException {
        connection = SingletonConnection.getInstance();
        ArrayList<Integer> postalCode = new ArrayList<Integer>();
        String query = "SELECT DISTINCT postalCode FROM city where name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, city);
            try (ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {
                    postalCode.add(rs.getInt("postalCode"));
                }
            }
        }
        return postalCode;
    }

    @Override
    public ArrayList<String> getCity(String country) throws SQLException {
        connection = SingletonConnection.getInstance();
        ArrayList<String> city = new ArrayList<String>();
        String query = "SELECT DISTINCT name FROM city where country = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, country);
            try (ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {
                    city.add(rs.getString("name"));
                }
            }
        }
        return city;
    }

    

}