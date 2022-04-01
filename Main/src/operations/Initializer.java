package operations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Map;
import static java.util.Map.entry;

public class Initializer {
    public static void dropTables(Connection con){
        //TODO: add all the table names into the below array        ---DONE---

        String s = new String();
        StringBuffer sb = new StringBuffer();
        try
        {
            FileReader fr = new FileReader(new File("scripts/dropTables.sql"));
            // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character
 
            BufferedReader br = new BufferedReader(fr);
 
            while((s = br.readLine()) != null)
                sb.append(s);
            br.close();
 
            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");
 
            Statement st = con.createStatement();
 
            for(int i = 0; i<inst.length; i++) {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if(!inst[i].trim().equals("")) {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>"+inst[i]);
                }
            }
        }
        catch(Exception e) {
            System.out.println("*** Error : "+e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
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
        //TODO: create string sql statements like the one below but for all the tables.     ---DONE---

        String s = new String();
        StringBuffer sb = new StringBuffer();
        try
        {
            FileReader fr = new FileReader(new File("scripts/createTables.sql"));
            // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character
 
            BufferedReader br = new BufferedReader(fr);
 
            while((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();
 
            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");
 
            Statement st = con.createStatement();
 
            for(int i = 0; i<inst.length; i++) {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if(!inst[i].trim().equals("")) {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>"+inst[i]);
                }
            }
   
        }
        catch(Exception e) {
            System.out.println("*** Error : "+e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        }
    }
}
