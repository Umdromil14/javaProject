package data;
import java.sql.*;
import java.util.ArrayList;


import tools.*;
import tools.DBOutput.User;

public class DBClient implements DAODataBase {
    private static Connection connection ;
    private UserDBAccess userDBAccess;
    public DBClient() {
        userDBAccess = new UserDBAccess();
    }
    public void create(User bussinessEntity) throws SQLException {
        connection = SingletonConnection.getInstance();
       
        userDBAccess.addUser(bussinessEntity);
    }
    public void read() throws SQLException {
        //listing all the clients
        connection = SingletonConnection.getInstance();
        ArrayList<User> clients = getUsers();
        System.out.println(clients.size());

        System.out.println("read client");
    }
    public void update(User bussinessEntity,Integer idEntity,Integer idAdress) throws SQLException {
        connection = SingletonConnection.getInstance();
        updateAddress(bussinessEntity.getAddress(),idAdress);
        updateBussinessEntity(bussinessEntity,idEntity);
        updateCommunication(bussinessEntity.getEmail(),idEntity);
        System.out.println("update client");
    }

    //en last
    public void updateCommunication(String eMail,Integer idEntity) throws SQLException {
        String query = "UPDATE communication SET communicationDetails = ? WHERE entity = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, eMail);
        statement.setInt(2, idEntity);
        statement.executeUpdate();
    }
    //en premier
    public void updateAddress(Address address,int idAdress) throws SQLException {
        String query = "UPDATE address SET street = ? , number = ? , city = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, address.getStreet());
        statement.setInt(2, address.getNumber());
        statement.setInt(3,selectAddress(address));
        statement.setInt(4, idAdress);
        statement.executeUpdate();
    }
    //en second
    public void updateBussinessEntity(User bussinessEntity,int idEntity) throws SQLException {
        String query = "UPDATE bussiness_entity SET firstname = ? , lastname = ? , hashedPassword = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, bussinessEntity.getFirstname());
        statement.setString(2, bussinessEntity.getLastname());
        statement.setString(3, Utils.hashPassword(bussinessEntity.getPassword(),bussinessEntity.getSalt()));
        statement.setInt(4, idEntity);
        statement.executeUpdate();
    }

    public void delete(Integer id,Integer idAdress) throws SQLException {
        connection = SingletonConnection.getInstance();
        //first to delete is the communication with his id
        deleteUser(id,idAdress);
        //then the address with his id
        //and the user
        System.out.println("delete client");
    }
    public void deleteSomeUser(Integer id[],Integer idAdress[])
    {
     if (id.length == idAdress.length)
     {
         for (Integer iDelete : id) {
             try {
                 delete(id[iDelete],idAdress[iDelete]);
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
     }
     else
     {
         System.out.println("error");
     }
    }
    public void deleteUser (Integer id,Integer idAdress) throws SQLException{
        String query = "DELETE FROM communication WHERE entity = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        deleteBussinessEntity(id);
        deleteAddress(idAdress);
        
    }
    public void deleteAddress (Integer idAddress)throws SQLException{

        String query = "DELETE FROM address WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idAddress);
        statement.executeUpdate();
    }
    public void deleteBussinessEntity (Integer id) throws SQLException{
        String query = "DELETE FROM bussiness_entity WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();

    }
    public ArrayList<User> getUsers()throws SQLException
    {
        ArrayList<User> users = new ArrayList<User>();
        String query = "SELECT * FROM business_entity";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        if (rs == null) {
            throw new SQLException("No users found.");
        }
        while (rs.next()) {
            User user = 
            new User(
                rs.getInt("id"),
                rs.getInt("address"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("hashedPassword"),
                rs.getString("salt"));
            users.add(user);
        }
        return users;
    }
    public Integer selectAddress(Address address) throws SQLException
    {
        Integer cityId = null;
        String query = "SELECT id FROM city WHERE name = ? AND postalCode = ? AND country = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, address.getCity().getName());
        statement.setInt(2, address.getCity().getPostalCode());
        statement.setString(3, address.getCity().getCountry());

        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            cityId = rs.getInt("id");
        }
        return cityId;
    }
}