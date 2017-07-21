/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.sqlite;

/**
 *
 * @author JianGe
 */
import tools.utilities.Logs;
import tools.sqlite.com.alexfu.sqlitequerybuilder.api.SQLiteQueryBuilder;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SQLiteJDBC {

    private Connection connection = null;
    private Statement stmt = null;
    private String databaseName = null;
    private StringBuffer sqlBuffer;
    private boolean operationSucessed = false;

    /**
     *
     * @param databaseName
     */
    public SQLiteJDBC(String databaseName) {
        this.sqlBuffer = new StringBuffer();
        this.databaseName = databaseName;
    }

    public void excute(String sql) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
//            Logs.e("Opened database \"" + databaseName + "\" successfully!");

            connection.setAutoCommit(false);
            stmt = connection.createStatement();

            stmt.executeUpdate(sql);
            stmt.close();

            connection.commit();
            connection.close();
        } catch (Exception e) {
            Logs.e(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
        }
    }

    /**
     *
     * @param sql
     */
    public void create(String sql) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);

            stmt = connection.createStatement();

            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch (Exception e) {
            Logs.e(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
        }
        Logs.e("Table created successfully");
    }

    /**
     *
     * @param tableName
     */
    public void createTable(String tableName, String tableItems) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + tableItems;
//        Logs.e(sql);
        //            String sql= SQLiteQueryBuilder.create().ifNotExists().table(tableName).
        excute(sql);
//        Logs.e("Table created successfully");
    }

    @SuppressWarnings("all")
    public void insertColumns(String tableName, Map<String, ArrayList<String>> valuesMap) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            connection.setAutoCommit(false);
//            Logs.e("Opened database \"" + databaseName + "\" successfully!");

            stmt = connection.createStatement();

            int totalColumns = valuesMap.size();

            String[] columns = new String[totalColumns];
            String[] rowValues = new String[totalColumns];
            //only once, improve efficiency
            int colmnsCount = 0;
            for (Map.Entry<String, ArrayList<String>> entry : valuesMap.entrySet()) {
                columns[colmnsCount] = entry.getKey();
                colmnsCount++;

            }

            int totalRows = valuesMap.get(columns[0]).size();

            for (int iRow = 0; iRow < totalRows; iRow++) {

                for (int iCol = 0; iCol < totalColumns; iCol++) {
                    rowValues[iCol] = valuesMap.get(columns[iCol]).get(iRow);
                }
                clearStringBuffer(sqlBuffer);

                sqlBuffer.append(SQLiteQueryBuilder.insert()
                        .into(tableName)
                        .columns(columns)
                        .values(rowValues)
                        .build());
//                Logs.e(sqlBuffer.toString());
                stmt.executeUpdate(sqlBuffer.toString());

            }

            stmt.close();
            connection.commit();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            Logs.e(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
        }

//        Logs.e("Records created successfully");
    }

    public void insertColumns(String tableName, String[] columns, ArrayList< ArrayList<String>> valueList) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            connection.setAutoCommit(false);
//            Logs.e("Opened database \"" + databaseName + "\" successfully!");

            stmt = connection.createStatement();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

            // make a back up
            String newTb = tableName + "bak_" + sdf.format(new java.util.Date());
            String copyTable = " CREATE TABLE " + newTb + " AS SELECT * FROM " + tableName;
            stmt.execute(copyTable);

            //delete old table items
            String deleteItems = "Delete from Review";
            stmt.execute(deleteItems);

            int totalRows = valueList.size();
            int totalColumns = columns.length;

            String[] rowValues = new String[totalColumns];

            for (int iRow = 0; iRow < totalRows; iRow++) {

//                Logs.e(iRow + "," + valueList.get(iRow).toString());
                for (int iColum = 0; iColum < totalColumns; iColum++) {
                    if (!valueList.get(iRow).get(0).equals("null")) {
                        rowValues[iColum] = valueList.get(iRow).get(iColum);
                    }

                }
                clearStringBuffer(sqlBuffer);

                sqlBuffer.append(SQLiteQueryBuilder.insert()
                        .into(tableName)
                        .columns(columns)
                        .values(rowValues)
                        .build());
//                Logs.e(sqlBuffer.toString());
                stmt.executeUpdate(sqlBuffer.toString());

            }

            stmt.close();
            connection.commit();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            Logs.e(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
        }

//        Logs.e("Records created successfully");
    }

    /**
     * need to be finished
     *
     * @param stock
     */
    public void insertColumn(String tableName, String columnName, ArrayList<String> valuesList) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            connection.setAutoCommit(false);
            Logs.e("Opened database \"" + databaseName + "\" successfully!");

            //insert table fisrt
            stmt = connection.createStatement();
            String sql = "alter table " + tableName + " add " + columnName + " text";
            stmt.executeUpdate(sql);
            connection.commit();

            //insert value
            for (int i = 0; i < valuesList.size(); i++) {
                sql = "UPDATE " + tableName
                        + " set " + columnName + " = " + valuesList.get(0).toString()
                        + " where ID=" + (i + 1) + " ;";
                stmt.executeUpdate(sql);
            }

            stmt.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            Logs.e(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
        }
    }

    public void select(String sql) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            connection.setAutoCommit(false);
            Logs.e("Opened database \"" + databaseName + "\" successfully!");

            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Logs.e("haha  " + rs.getString(1));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            Logs.e(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
        }
        Logs.e("Operation done successfully");
    }

//    public void selectAllIndex(StockSets allStockSets) {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
//            connection.setAutoCommit(false);
//            Logs.e("Opened database \"" + databaseName + "\" successfully!");
//
//            stmt = connection.createStatement();
//            Statement stmt2 = connection.createStatement();
//
//            String sql = SQLiteQueryBuilder.select("name").from("sqlite_master").where("type='table'").orderBy("name").build().toString();
//            ResultSet rsAllTableName = stmt.executeQuery(sql);
//
//            ArrayList<String> resultTableNames = new ArrayList<String>();
//
//            //1. get all tables
//            while (rsAllTableName.next()) {
//                resultTableNames.add(rsAllTableName.getString(1));
//                //2. create tables
//                allStockSets.add(new SingleStock());
//
//                //3. get the database creation sql sentence. 
//                sql = SQLiteQueryBuilder.select("sql").from("sqlite_master").where("type='table'").and("tbl_name=" + "'" + rsAllTableName.getString(1) + "'").build().toString();
//                ResultSet rsSingleTable = stmt2.executeQuery(sql);
//
//                ArrayList<String> curveNameList = getColumnName(rsSingleTable.getString(1));
//                while (rsSingleTable.next()) {
//                    //4. get curve name by parse sql sentence                
//                    for (int i = 0; i < curveNameList.size(); i++) {
//                        //5. create single curves
//                        if (curveNameList.get(i).contains("id")) {
//                            //throw id
//                            continue;
//                        }
//                        Logs.e("create sql is:    " + rsSingleTable.getString(1));
//                        allStockSets.add(new SingleStock());
//                    }
//                }
//                rsSingleTable.close();
//            }
//            rsAllTableName.close();
//            stmt.close();
//            connection.close();
//        } catch (Exception e) {
//            Logs.e(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
//        }
//        Logs.e("Operation done successfully");
//    }
    public ArrayList<String> selectColValueByName(String columnName, String tableName) {
        ArrayList<String> selectedColumnValue = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            connection.setAutoCommit(false);
            Logs.e("Opened database \"" + databaseName + "\" successfully!");

            stmt = connection.createStatement();

            String sql = SQLiteQueryBuilder.select(columnName).from(tableName).build().toString();
            Logs.e(sql);
            ResultSet rs = stmt.executeQuery(sql);
            Logs.e("run to here 1");
//            Logs.e(rs.getString(1).toString());
            while (rs.next()) {
                selectedColumnValue.add(rs.getString(1));
            }

            Logs.e("run to here 2");
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            Logs.e(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
        }
        Logs.e("Operation done successfully");
        return selectedColumnValue;

    }

    /**
     * Select a certain column with the first colum by column name
     *
     * @param columnName
     * @param tableName
     * @return
     */
    public ArrayList<ArrayList<String>> selectColumsWith1stByName(String columnName, String tableName) {
        ArrayList<ArrayList<String>> selectedColumnValue = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            connection.setAutoCommit(false);
//            Logs.e("Opened database \"" + databaseName + "\" successfully!");

            stmt = connection.createStatement();
            ArrayList<String> dates = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();

            //get the first column values
            String sql = SQLiteQueryBuilder.select("Date").from(tableName).build().toString();
//            Logs.e(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                dates.add(rs.getString(1));
            }

            //get the column values
            sql = SQLiteQueryBuilder.select(columnName).from(tableName).build().toString();
//            Logs.e(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                values.add(rs.getString(1));
            }

            selectedColumnValue.add(dates);
            selectedColumnValue.add(values);

            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            Logs.e(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
        }
//        Logs.e("Operation done successfully");
        return selectedColumnValue;

    }

    public Map<String, ArrayList<String>> selectColsValueByNameList(String tableName, ArrayList<String> columnNames) {
        Map<String, ArrayList<String>> selectedColumnValue = new LinkedHashMap<>();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            connection.setAutoCommit(false);
            Logs.e("Opened database \"" + databaseName + "\" successfully!");

            stmt = connection.createStatement();

            for (int idx = 0; idx < columnNames.size(); idx++) {
                String colName = columnNames.get(idx);
                ArrayList<String> values = new ArrayList<>();
                String sql = SQLiteQueryBuilder.select(colName).from(tableName).build().toString();
                Logs.e(sql);
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        values.add(rs.getString(1));
                    }
                }
                selectedColumnValue.put(colName, values);
            }

            stmt.close();
            connection.close();
            operationSucessed = true;
        } catch (Exception e) {
            operationSucessed = false;
            Logs.e(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
        }
        Logs.e("Operation done successfully");
        return selectedColumnValue;

    }

    public ArrayList< ArrayList<String>> selectAllColsValue(String tableName, String[] colName) {
        ArrayList< ArrayList<String>> valueSortByColum = new ArrayList<>();
        ArrayList< ArrayList<String>> valueSortByRow = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            connection.setAutoCommit(false);
            Logs.e("Opened database \"" + databaseName + "\" successfully!");

            stmt = connection.createStatement();

            for (int i = 0; i < colName.length; i++) {
                String string = colName[i];
                ArrayList<String> rowValues = new ArrayList<>();
                String sql = SQLiteQueryBuilder.select(string).from(tableName).build();
//                Logs.e(sql);
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        rowValues.add(rs.getString(1));

                    }
                } catch (Exception e) {

                }
                valueSortByColum.add(rowValues);
            }

            stmt.close();
            connection.close();
            operationSucessed = true;
        } catch (ClassNotFoundException | SQLException e) {

            operationSucessed = false;
            e.printStackTrace();
            Logs.e(e.getClass().getName() + ": " + e.getMessage());
            return null;
//            System.exit(0);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }

        }
        Logs.e("Operation done successfully");

        for (int row = 0; row < valueSortByColum.get(0).size(); row++) {
            ArrayList<String> rowList = new ArrayList<>();
            for (int colum = 0; colum < valueSortByColum.size(); colum++) {
                rowList.add(valueSortByColum.get(colum).get(row));

            }

            valueSortByRow.add(rowList);
        }

        return valueSortByRow;

    }

    public ArrayList<String> getColumnName(String commandString) {

        ArrayList<String> curveNames = new ArrayList<>();
//        Logs.e(commandString.split("\\(")[1].toString());
        String[] firstSplit = commandString.split("\\(")[1].split(",");

        String name;
        for (int i = 0; i < firstSplit.length; i++) {
            String aa = firstSplit[i].replaceAll(" +", " ");
            Logs.e(i + "  curve name is:" + aa);
            String[] bb = aa.split(" ");
            for (int j = 0; j < bb.length; j++) {
                if (!bb[j].isEmpty()) {
                    name = bb[j];
                    curveNames.add(name);
                    break;
                }

            }

//            Logs.e("bb " +1 +" is:" +bb[1]);
//            Logs.e("curve name is: " + curveNames.get(i));
        }
        return curveNames;
    }

    public void delete() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            connection.setAutoCommit(false);
            Logs.e("Opened database successfully");

            stmt = connection.createStatement();
            String sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
            stmt.executeUpdate(sql);
            connection.commit();

            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                float salary = rs.getFloat("salary");
                Logs.e("ID = " + id);
                Logs.e("NAME = " + name);
                Logs.e("AGE = " + age);
                Logs.e("ADDRESS = " + address);
                Logs.e("SALARY = " + salary);
                Logs.e("\n");
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
        }
        Logs.e("Operation done successfully");
    }

    public void clearStringBuffer(StringBuffer stringBuffer) {
        if (stringBuffer.length() >= 1) {
            stringBuffer.delete(0, stringBuffer.length());
        }
    }

    public boolean isOperationSucessed() {
        return operationSucessed;
    }

}
