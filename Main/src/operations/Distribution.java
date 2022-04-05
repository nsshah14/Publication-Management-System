package operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;

public class Distribution {
    /**
     * 
     * CREATE TABLE IF NOT EXISTS Distributor(
            distributorID INT,
            Name VARCHAR(128) NOT NULL,
            Type VARCHAR(128) NOT NULL,
            Address VARCHAR(256) NOT NULL,
            Phone VARCHAR(16) NOT NULL,
            ContactPerson VARCHAR(128),
            Balance INT NULL,
            PRIMARY KEY(distributorID)
      );

     * //instructions for distribution
        System.out.println("\tDistribution");
        System.out.println("\t\t [14] - Enter new distributor");
        System.out.println("\t\t [15] - Update distributor information");
        System.out.println("\t\t [16] - Delete a distributor. ");
        System.out.println("\t\t [17] - Input orders from distributors, for a book edition or an issue of a publication per distributor, for a certain date.");
        System.out.println("\t\t [18] - Bill distributor for an order");
        System.out.println("\t\t [19] - Change outstanding balance of a distributor on receipt of a payment.");
        System.out.println();   
     */

     //TODO create methods for each task and operation above like the one in production.java  

     String[] columns={"distributorID", "Name", "Type", "Address", "Phone", "ContactPerson", "Balance"};
      public static boolean newDist(Connection conn, Scanner inputReader){
         System.out.println("Enter Distributor ID:");
         int distId=inputReader.nextInt();
         System.out.println("Enter Distributor name:");
         String name=inputReader.next();
         System.out.println("Enter Distributor type:");
         String type=inputReader.next();
         System.out.println("Enter Distributor Address:");
         String add=inputReader.next();
         System.out.println("Enter Distributor Phone:");
         String phone=inputReader.next();
         System.out.println("Enter Distributor Contact person:");
         String cp=inputReader.next();
         System.out.println("Enter balance:");
         int bal=inputReader.nextInt();

         try{
            PreparedStatement stinsert=conn.prepareStatement("INSERT INTO Distributor VALUES(?,?,?,?,?,?,?);");

            stinsert.setInt(1, distId);
            stinsert.setString(2, name);
            stinsert.setString(3, type);
            stinsert.setString(4, add);
            stinsert.setString(5, phone);
            stinsert.setString(6, cp);
            stinsert.setInt(7, bal);

            stinsert.executeQuery();

         } catch(Exception e){
            e.printStackTrace();
            return false;
         }
         return true;
      }

      boolean updateDist(Connection conn, String condcolname, String updatecolname, Object cond, Object newval){
         try{
            PreparedStatement stupdate=conn.prepareStatement("UPDATE Distributor SET "+updatecolname+"=? WHERE "+condcolname+"=?;");
            
            //todo

         } catch(Exception e){

            return false;
         }
         return true;
      }

      public static boolean deleteDist(Connection conn, Scanner inputreader){
         List<String> delcolnames=new ArrayList();
         List<Object> delcolvals=new ArrayList();

         System.out.println("enter number of conditions:");
         int n=inputreader.nextInt();
         for(int i=0;i<n;i++){
            System.out.println("Enter col name:");
            delcolnames.add(inputreader.next());
            System.out.println("Enter col value:");
            delcolvals.add(inputreader.next());
         }
         
         try{
            StringBuilder query=new StringBuilder("DELETE FROM Distributor where ");
            for(int i=0; i<delcolnames.size();i++){
               query.append(delcolnames.get(i) + "=? and ");
            }
            query.replace(query.length()-6,query.length()-1,";");
            // PreparedStatement stdelete=conn.prepareStatement(query);
            
            // for(int i=0;i<delcolvals.size();i++)
            //    stdelete.setObject(i, delcolvals.get(i));

            System.out.println(query.toString());
            // stdelete.executeQuery();

         }catch(Exception e){
            e.printStackTrace();
            return false;
         }
         return true;
      }

}
