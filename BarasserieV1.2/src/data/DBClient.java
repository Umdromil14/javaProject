package data;
import java.sql.*;
import java.util.ArrayList;


import tools.*;

public class DBClient implements DAODataBase {
    private static final String STARTING_STATUS = "regular";
    private static Connection connection ;
    public void create(BusinessEntity bussinessEntity) throws SQLException {
        connection = SingletonConnection.getInstance();
        addUser(bussinessEntity);
    }
    public void read() throws SQLException {
        //listing all the clients
        connection = SingletonConnection.getInstance();
        ArrayList<BusinessEntity> clients = getUsers();
        System.out.println(clients.size());

        System.out.println("read client");
    }
    public void update(BusinessEntity bussinessEntity,Integer idEntity,Integer idAdress) throws SQLException {
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
    public void updateBussinessEntity(BusinessEntity bussinessEntity,int idEntity) throws SQLException {
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
    public ArrayList<BusinessEntity> getUsers()throws SQLException
    {
        ArrayList<BusinessEntity> users = new ArrayList<BusinessEntity>();
        String query = "SELECT * FROM business_entity";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        if (rs == null) {
            throw new SQLException("No users found.");
        }
        while (rs.next()) {
            BusinessEntity user = 
            new BusinessEntity(
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
}