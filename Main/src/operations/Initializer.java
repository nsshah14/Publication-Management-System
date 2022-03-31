package operations;

import java.sql.*;
import java.util.Map;
import static java.util.Map.entry;

public class Initializer {
    public static void dropTables(Connection con){
        //TODO: add all the table names into the below array
        String[] tables = new String[]{"Distributor" };

        //runs the drop sql command for each of the tables listed int the tables array
        for(int i = 0; i< tables.length; i++){
            try{
                con.createStatement().executeUpdate("ALTER TABLE " + tables[i] + " NOCHECK CONSTRAINT ALL");
                con.createStatement().executeQuery("DROP TABLE " + tables[i] + ";");
                //con.createStatement().executeQuery("ALTER TABLE " + tables[i] + " CHECK CONSTRAINT ALL;");
            }catch(SQLException e){
                e.printStackTrace();
            }
        } 
    }

    public static void addDummyValues(Connection con){
        //TODO: create dummy values like below for the rest of the tables.
        //gives the insert statement for the distributor table
        String[] insertDistributor =  {
            "1, 'john', 'Wholesale', '2518 Avent Ferry Road', '+19848883402', 'Mary', 400",
            "2, 'Kevin', 'Wholesale', '2512 Avent Ferry Road', '+19842239405', 'Jake', 200",
            "3, 'Mark', 'Bookstore', '2520 Avent Ferry Road', '+191041239420', 'Lily', 64",
        };
        
        //TODO: for each list of insert statement connect them with the given table they belong to like below.
        //connects the insert statements with their respective tables. 
        Map<String, String[]> tableInsertStatements = Map.ofEntries(
            entry("Distributor", insertDistributor)
            //,entry("Account", insertAccount)
        );

        //Runs the insert statements for all the tables.
        for(Map.Entry<String,String[]> entry: tableInsertStatements.entrySet()){
            String tableName = entry.getKey();
            String[] insertStatements = entry.getValue();

            try(Statement stmt = con.createStatement()){
                for(int i = 0; i< insertStatements.length; i++){
                    stmt.executeUpdate("INSERT into " + tableName +
                    " VALUES(" + insertStatements[i] + ")");
                }

            }catch(SQLException e){
                e.printStackTrace();
            }
        } 
            
    }
    
    public static void createTables(Connection con){
        //TODO: create string sql statements like the one below but for all the tables. 
        String createDistributor = 
        "CREATE TABLE IF NOT EXISTS Distributor(" + 
            "distributorID INT,"+
            "Name VARCHAR(128) NOT NULL,"+
            "Type VARCHAR(128) NOT NULL,"+
            "Address VARCHAR(256) NOT NULL,"+
            "Phone VARCHAR(16) NOT NULL,"+
            "ContactPerson VARCHAR(128),"+
            "Balance INT NULL,"+
            "PRIMARY KEY(distributorID)"+
        ");";

        //TODO: add all the create table strings to the bellow array
        String[] createTableStatements = new String[]{createDistributor};
        
        //run the sql statements to create the tables. 
        for(int i = 0; i< createTableStatements.length; i++){
            try(Statement stmt = con.createStatement()){
                stmt.executeUpdate(createTableStatements[i]);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
