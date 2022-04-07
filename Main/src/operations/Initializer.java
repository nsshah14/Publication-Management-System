package operations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Map;
//import static java.util.Map.entry;

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

        String s = new String();
        StringBuffer sb = new StringBuffer();
        try
        {
            FileReader fr = new FileReader(new File("scripts/dummyValues.sql"));
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
            
    
    
    public static void createTables(Connection con){
        
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
