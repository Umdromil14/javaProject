<<<<<<< HEAD
package tools.DBOutput;

import controller.TableEntry;
import interfaces.tableEntryCreator;

public class City implements tableEntryCreator {
    private Integer id;
    private String name;
    private Integer postalCode;
    private String country;

    public City(Integer id, String name, Integer postalCode, String country) {
        this.id = id;
        this.name = name;
        this.postalCode = postalCode;
        this.country = country;
    }

    public City(String name, Integer postalCode, String country) {
        this(null, name, postalCode, country);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public TableEntry toTableEntry() {
        return new TableEntry(
            Integer.toString(id),
            name,
            Integer.toString(postalCode),
            country
        );
    }

    public void setName(String cityName) {
        this.name = cityName;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    @Override
    public String toString() {
        return name + " " + postalCode + ", " + country;
    }
}
=======
package tools.DBOutput;

import controller.TableEntry;
import interfaces.tableEntryCreator;

public class City implements tableEntryCreator {
    private Integer id;
    private String name;
    private Integer postalCode;
    private String country;

    public City(Integer id, String name, Integer postalCode, String country) {
        this.id = id;
        this.name = name;
        this.postalCode = postalCode;
        this.country = country;
    }

    public City(String name, Integer postalCode, String country) {
        this(null, name, postalCode, country);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public TableEntry toTableEntry() {
        return new TableEntry(
            Integer.toString(id),
            name,
            Integer.toString(postalCode),
            country
        );
    }

    public void setName(String cityName) {
        this.name = cityName;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    @Override
    public String toString() {
        return name + " " + postalCode + ", " + country;
    }
}
>>>>>>> a3552b73418bdd63b5a8a8ec3eefd92e53f2f519