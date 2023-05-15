package controller;

public class TableEntry {
    private String column1, column2, column3, column4, column5, column6, column7, column8, column9, column10;

    public TableEntry(String... row) {
        for (int i = 0; i < row.length && i < 10; i++) {
            switch (i) {
                case 0: column1 = row[i]; break;
                case 1: column2 = row[i]; break;
                case 2: column3 = row[i]; break;
                case 3: column4 = row[i]; break;
                case 4: column5 = row[i]; break;
                case 5: column6 = row[i]; break;
                case 6: column7 = row[i]; break;
                case 7: column8 = row[i]; break;
                case 8: column9 = row[i]; break;
                case 9: column10 = row[i]; break;
            }
        }
    }

    public String getColumn1() {
        return column1;
    }

    public String getColumn2() {
        return column2;
    }

    public String getColumn3() {
        return column3;
    }

    public String getColumn4() {
        return column4;
    }

    public String getColumn5() {
        return column5;
    }

    public String getColumn6() {
        return column6;
    }

    public String getColumn7() {
        return column7;
    }

    public String getColumn8() {
        return column8;
    }

    public String getColumn9() {
        return column9;
    }

    public String getColumn10() {
        return column10;
    }
}
