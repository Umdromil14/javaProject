package interfaces;

import java.util.List;

import exception.DataAccessException;
import model.*;

public interface AddressDataAccess {
    public TopProductCity getTopProduct(int cityId) throws DataAccessException;

    public List<City> getAllCities() throws DataAccessException;

    public List<String> getCountries() throws DataAccessException;

    public List<Integer> getPostalCode(String city) throws DataAccessException;

    public List<String> getCity(String country) throws DataAccessException;

    public void deleteAddress(Integer idAddress) throws DataAccessException;

    public Integer getAddress(Address address) throws DataAccessException;
    
    public Integer addAddress(Address address) throws DataAccessException ;
}
