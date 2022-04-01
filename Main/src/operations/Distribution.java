package operations;

import java.sql.PreparedStatement;
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
      boolean newDist(Connection conn, int id, String name, String type, String address, String phone, String cp, int bal){
         try{
            PreparedStatement stinsert=conn.prepareStatement("INSERT INTO Distributor VALUES(?,?,?,?,?,?,?);");


         } catch(Exception e){

            return false;
         }
         return true;
      }

      boolean updateDist(Connection conn, String condcolname, String updatecolname, Object cond, Object newval){
         try{
            PreparedStatement stupdate=conn.prepareStatement("UPDATE Distributor SET "+updatecolname+"=? WHERE "+condcolname+"=?;");
            

         }catch(Exception e){

            return false;
         }
         return true;
      }

      boolean deleteDist(Connection conn, String condcolname, String deletecolname, Object cond, Object newval){
         try{
            PreparedStatement stdelete=conn.prepareStatement("UPDATE Distributor SET "+deletecolname+"=? WHERE "+condcolname+"=?;");
            

         }catch(Exception e){

            return false;
         }
         return true;
      }
}
