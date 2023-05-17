package business;

import java.sql.SQLException;
import java.util.ArrayList;

import data.GetComboBoxValues;
import tools.BusinessEntity;

public class ModificationUser implements ModificationUserInterface{

    private GetComboBoxValues getComboBoxValues;
    public ModificationUser() {
        getComboBoxValues = new GetComboBoxValues();
    }
    @Override
    public ArrayList<String> getCountries() throws SQLException {
        return getComboBoxValues.getCountries();
    }

    @Override
    public ArrayList<Integer> getPostalCode(String city) throws SQLException {
        return getComboBoxValues.getPostalCode(city);
    }

    @Override
    public ArrayList<String> getCity(String country) throws SQLException {
        return getComboBoxValues.getCity(country);
    }

    @Override
    public BusinessEntity getUser(int id) throws SQLException {
        
        return getComboBoxValues.getUser(id);
    }
    
}
