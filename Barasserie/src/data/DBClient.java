package data;
import java.sql.*;
import java.util.ArrayList;


import tools.*;

public class DBClient implements DAODataBase {
    private static final String STARTING_STATUS = "regular";
    private static Connection connection ;
    public void create(BussinessEntity bussinessEntity) throws SQLException {
        connection = SingletonConnection.getInstance();
        addUser(bussinessEntity);
    }
    public void read() throws SQLException {
        //listing all the clients
        connection = SingletonConnection.getInstance();
        ArrayList<BussinessEntity> clients = getUsers();
        System.out.println(clients.size());

        System.out.println("read client");
    }
    public void update(BussinessEntity bussinessEntity,Integer idEntity,Integer idAdress) throws SQLException {
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
    public void updateBussinessEntity(BussinessEntity bussinessEntity,int idEntity) throws SQLException {
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
    public ArrayList<BussinessEntity> getUsers()throws SQLException
    {
        ArrayList<BussinessEntity> users = new ArrayList<BussinessEntity>();
        String query = "SELECT * FROM bussiness_entity";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        if (rs == null) {
            throw new SQLException("No users found.");
        }
        while (rs.next()) {
            BussinessEntity user = 
            new BussinessEntity(
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
    public int selectAddress(Address address) throws SQLException
    {
        String query = "SELECT id FROM city WHERE name = ? AND postalCode = ? AND country = ?";
        
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, address.getCity());
        statement.setInt(2, address.getPostalCode());
        statement.setString(3, address.getCountry());

        ResultSet rs = statement.executeQuery();
        rs = Utils.checkingResultSet(rs);
        if (rs == null) {
            throw new SQLException("Creating address failed, city not found.");
        }
        int cityId = rs.getInt("id");
        return cityId;
    }
    public int addAddress(Address address) throws SQLException {
        int cityId = selectAddress(address);

        String query = "INSERT INTO address (street, number, city) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, address.getStreet());
        statement.setInt(2, address.getNumber());
        statement.setInt(3, cityId);

        int affectedRow = statement.executeUpdate();
        if (affectedRow == 0) {
            throw new SQLException("Creating address failed, no rows affected.");
        }

        ResultSet generatedKeys = statement.getGeneratedKeys();
        int addressId;
        if (generatedKeys.next()) {
            addressId =  generatedKeys.getInt(1);
        } else {
            throw new SQLException("Creating address failed, no ID obtained.");
        }

        return addressId;
    }
    public void addEmail(int id, String email) throws SQLException {
        String query = "INSERT INTO communication (entity, type, communicationDetails) VALUES (?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, id);
        statement.setString(2, "email");
        statement.setString(3, email);

        statement.executeUpdate();
    }
    public void addUser(BussinessEntity bussinessEntity) throws SQLException {
        int addressId = addAddress(bussinessEntity.getAddress());

        String query = "INSERT INTO bussiness_entity " +
        "(address, tier, lastname, firstname, isClient, isSupplier, registrationDate, hashedPassword, salt) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, addressId);
        statement.setString(2, STARTING_STATUS);
        statement.setString(3, bussinessEntity.getLastname());
        statement.setString(4, bussinessEntity.getFirstname());
        statement.setBoolean(5, true);
        statement.setBoolean(6, false);
        statement.setDate(7, new Date(System.currentTimeMillis()));

        String salt = Utils.generateSalt();
        String hashedPassword = Utils.hashPassword(bussinessEntity.getPassword(), salt);
        statement.setString(8, hashedPassword);
        statement.setString(9, salt);

        int affectedRow = statement.executeUpdate();
        if (affectedRow == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            addEmail(generatedKeys.getInt(1), bussinessEntity.getEmail());
        } else {
            throw new SQLException("Creating user failed, no ID obtained.");
        }
        System.out.println("User added");
        connection.close();
    }
}
