package main.model;

import java.text.DecimalFormat;

import main.controller.TableEntry;
import main.interfaces.tableEntryCreator;

public class ProductProportion implements tableEntryCreator {
    private String name;
    private int quantity;
    private double proportion;

    public ProductProportion(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        proportion = 0;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    @Override
    public TableEntry toTableEntry() {
        DecimalFormat decimalFormat = new DecimalFormat("0%");
        return new TableEntry(name, decimalFormat.format(proportion), Integer.toString(quantity));
    }
}
