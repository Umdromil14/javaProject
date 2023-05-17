package model;

import java.text.DecimalFormat;

import controller.TableEntry;
import interfaces.tableEntryCreator;

public class ProductProportion implements tableEntryCreator {
    private String name;
    private int quantity;
    private double proportion;

    public ProductProportion(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProportion(int totalQuantity) {
        this.proportion = (double) quantity / totalQuantity;
    }

    @Override
    public TableEntry toTableEntry() {
        DecimalFormat decimalFormat = new DecimalFormat("0%");
        return new TableEntry(name, decimalFormat.format(proportion), Integer.toString(quantity));
    }
}
