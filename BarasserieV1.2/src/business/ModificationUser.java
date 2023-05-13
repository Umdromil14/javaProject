package business;

import java.sql.SQLException;
import java.util.ArrayList;

import data.GetComboBoxValues;
import tools.DBOutput.User;

public class ModificationUser {

    private GetComboBoxValues getComboBoxValues;
    public ModificationUser() {
        getComboBoxValues = new GetComboBoxValues();
    }

    public ArrayList<String> getCountries() throws SQLException {
        return getComboBoxValues.getCountries();
    }

    public ArrayList<Integer> getPostalCode(String city) throws SQLException {
        return getComboBoxValues.getPostalCode(city);
    }

    public ArrayList<String> getCity(String country) throws SQLException {
        return getComboBoxValues.getCity(country);
    }

    public User getUser(int id) throws SQLException {
        
        return getComboBoxValues.getUser(id);
    }
    
}
