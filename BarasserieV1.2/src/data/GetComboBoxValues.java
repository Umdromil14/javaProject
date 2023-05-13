package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tools.Address;
import tools.DBOutput.User;

public class GetComboBoxValues implements GetComboBoxValuesInterface {

    @Override
    public ArrayList<String> getCountries() throws SQLException {
        Connection connection = SingletonConnection.getInstance();
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
        Connection connection = SingletonConnection.getInstance();
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
        Connection connection = SingletonConnection.getInstance();
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

    @Override
    public User getUser(int id) throws SQLException {
        Connection connection = SingletonConnection.getInstance();
        User user = null;

        StringBuilder query = new StringBuilder().
        append("SELECT * From business_entity ")
        .append("INNER JOIN communication ON communication.entity = business_entity.id ")
        .append("INNER JOIN address ON address.id = business_entity.id ")
        .append("INNER JOIN city ON city.id = address.city ").
        append("WHERE business_entity.id = ?");
        
        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
         
            statement.setInt(1,id);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                user = new User();
                user.setAddress(
                    new Address(
                        rs.getString("street"), 
                        rs.getInt("number"),
                        rs.getString("city.name"), 
                        rs.getInt("postalCode"), 
                        rs.getString("country")));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.seteMail(rs.getString("communicationDetails"));
                
            }
        }

        return user;
    }

}