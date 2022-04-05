package operations;
import java.util.Scanner;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Production {
    /**
     * System.out.println("\tProduction:");
        System.out.println("\t\t [7] - Enter a new book edition or new issue of a publication");
        System.out.println("\t\t [8] - Update, Delete a book edition or publication issue.");
        System.out.println("\t\t [9] - Enter/Update an article or chapter");
        System.out.println("\t\t [10] - Enter/Update text of an article");
        System.out.println("\t\t [11] - Find books and articles by topic, date, author's name");
        System.out.println("\t\t [12] - Enter payment for author or editor");
        System.out.println("\t\t [13] - Keep track of when each payment was claimed by its addressee");
        System.out.println();
     * 
     */

     /**
      * 
      * @param con is the object used to connect and send queries to mariadb
      * @param scan is the object used to get user input. 
      */

      public static boolean newPublication(Connection conn, Scanner inputReader){
        // System.out.println("Enter Publication ID:");
        int pubId;
        System.out.println("Enter Title for Publcation:");
        String pubTitle=inputReader.next();
        System.out.println("Enter the Date of Publication (dd-mm-yyyy):");
        String date=inputReader.next();
        System.out.println("Enter the Topics of Publication:");
        String pubTopics=inputReader.next();
        System.out.println("Enter the Periodicity of Publication:");
        String pubPeriodicity=inputReader.next();
        ResultSet rs = null;
        
        try{
           PreparedStatement stinsert=conn.prepareStatement("INSERT INTO Publication VALUES(?,?,?,?,?);");

            rs = stinsert.getGeneratedKeys();
            // if (rs != null && rs.next()) 
            //     pubId = rs.getInt(1);
            // else
            //     return false;
            System.out.print(rs.getInt(1));
            pubId=1;
            stinsert.setInt(1, pubId);
            stinsert.setString(2, pubTitle);
            stinsert.setDate(3,java.sql.Date.valueOf(date));
            stinsert.setString(4, pubTopics);
            stinsert.setString(5, pubPeriodicity);

           stinsert.executeQuery();

        } catch(Exception e){
           e.printStackTrace();
           return false;
        }
        return true;
    }
   /**  public static void createPublication(Connection con, Scanner scan ){
        //Title VARCHAR(128) NOT NULL,
        //Date DATE NOT NULL,
        //Topics VARCHAR(128),
        //Periodicity VARCHAR(128),

        //TODO: request more information from user( publication name, etc.)
        System.out.print("Enter the ID of Publication: "); //TODO: change this to get unused id from mariadb and use that instead of prompting.
        String id = scan.nextLine();

        System.out.print("Enter the Title of Publication: ");
        String title = scan.nextLine();

        System.out.print("Enter the Date of Publication: ");
        String date = scan.nextLine();
        
        System.out.print("Enter the Topics of Publication: "); //TODO: unsure how to send multiple topics to backend
        String topics = scan.nextLine();

        System.out.print("Enter the Periodicity of Publication: "); //TODO: create checks to prevent invalid values from being added.
        String periodicity = scan.nextLine();

        //TODO: send query to create publication to mariadb
        try(Statement stmt = con.createStatement()){
            String inputStatement = String.format("INSERT into Publication VALUES(%s, '%s', '%s', '%s', '%s')", id, title, date, topics, periodicity);
            stmt.executeUpdate(inputStatement);
            
            //TODO: output the result of the query to the commandline.
            System.out.println("Ok");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    **/

}
