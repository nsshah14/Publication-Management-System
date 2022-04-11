package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

         System.out.println("Enter Distributor name:");
         String name=inputReader.nextLine();
         System.out.println("Enter Distributor type:");
         String type=inputReader.nextLine();
         System.out.println("Enter Distributor Address:");
         String add=inputReader.nextLine();
         System.out.println("Enter Distributor Phone:");
         String phone=inputReader.nextLine();
         System.out.println("Enter Distributor Contact person:");
         String cp=inputReader.nextLine();
         System.out.println("Enter balance:");
         int bal=inputReader.nextInt();

         String query = "INSERT INTO Distributor (Name, Type, Address, Phone, ContactPerson, Balance) VALUES(?,?,?,?,?,?);";
         try{
            PreparedStatement stinsert=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stinsert.setString(1, name);
            stinsert.setString(2, type);
            stinsert.setString(3, add);
            stinsert.setString(4, phone);
            stinsert.setString(5, cp);
            stinsert.setInt(6, bal);

            stinsert.executeQuery();

            System.out.println("1 Row inserted!");
         } catch(Exception e){
            e.printStackTrace();
            return false;
         }
         return true;
      }

      public static boolean updateDist(Connection conn, Scanner inputreader){

         List<String> condcolnames=new ArrayList<String>();
          List<Object> condcolvals=new ArrayList<Object>();

          System.out.println("Enter number of conditions:");
          int n=inputreader.nextInt();
          inputreader.nextLine();

          for(int i=0;i<n;i++){
             System.out.println("Enter Column name:");
             condcolnames.add(inputreader.nextLine());
             System.out.println("Enter column value:");
             condcolvals.add(inputreader.nextLine());
          }

          System.out.println("Enter Column Name to update:");
          String toUpdateCol=inputreader.nextLine();

          System.out.println("Enter new value:");
          String toUpdateVal=inputreader.nextLine();


          try{
             StringBuilder query=new StringBuilder("UPDATE Distributor SET "+toUpdateCol+"=? where ");
             for(int i=0; i<condcolnames.size();i++){
                query.append(condcolnames.get(i) + "=? and ");
             }
             query.replace(query.length()-5,query.length()-1,";");

            //  System.out.println(query.toString());
             PreparedStatement stupdate=conn.prepareStatement(query.toString());
             stupdate.setObject(1,toUpdateVal);
             for(int i=0; i<condcolnames.size();i++){
                stupdate.setObject(i+2,condcolvals.get(i));
             }

             int rowCount = stupdate.executeUpdate();

             System.out.println(rowCount+" rows Updated");
          } catch(Exception e){
             e.printStackTrace();
             return false;
          }
          return true;
      }

    public static boolean deleteDist(Connection conn, Scanner inputreader){
        List<String> delcolnames=new ArrayList<String>();
        List<Object> delcolvals=new ArrayList<Object>();

        System.out.println("enter number of conditions:");
        int n=inputreader.nextInt();
        inputreader.nextLine();

        for(int i=0;i<n;i++){
        System.out.println("Enter col name:");
        delcolnames.add(inputreader.nextLine());
        System.out.println("Enter col value:");
        delcolvals.add(inputreader.nextLine());
        }
         
        try{
            StringBuilder query=new StringBuilder("DELETE FROM Distributor where ");
            for(int i=0; i<delcolnames.size();i++){
                query.append(delcolnames.get(i) + "=? and ");
            }
            query.replace(query.length()-5,query.length()-1,";");
            PreparedStatement stdelete=conn.prepareStatement(query.toString());
            
            for(int i=0;i<delcolvals.size();i++)
                stdelete.setObject(i+1, delcolvals.get(i));

            stdelete.executeQuery();

            System.out.println("Rows Deleted");
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
      }

    public static boolean addOrderAndBillDist(Connection conn, Scanner inputreader){

        System.out.println("Enter Distributor ID:");
        int did=inputreader.nextInt();
        System.out.println("Enter Publication ID:");
        int pid=inputreader.nextInt();
        try{
            Statement checkDist=conn.createStatement();
            ResultSet rs1=checkDist.executeQuery("Select * from Distributor where distributorID="+did);
            Statement checkPub=conn.createStatement();
            ResultSet rs2=checkPub.executeQuery("Select * from Publication where PublicationID="+pid);
            
            if(rs1.next() && rs2.next()){
                double price=rs2.getFloat("Price");
                System.out.println("Enter the quantity:");
                int quantity=inputreader.nextInt();
                double final_amount=price*quantity;
                double sc=0.1*final_amount;

                LocalDate date=LocalDate.now();
                LocalDate delivery=date.plusDays(14);


                String query="INSERT into Orders (Price, ShippingCost, numCopies, PublicationID, Date) Values (?,?,?,?,?)";
                PreparedStatement insertOrder=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
                insertOrder.setDouble(1, final_amount);
                insertOrder.setDouble(2, sc);
                insertOrder.setInt(3, quantity);
                insertOrder.setInt(4, pid);
                insertOrder.setObject(5, date);
                insertOrder.executeQuery();

                // FOR RETRIVAL OF DATE
                // LocalDate ld = myResultSet.getObject( … , LocalDate.class ) ;

                ResultSet rs = insertOrder.getGeneratedKeys();
                int orderId = 0;
                if(rs.next()){
                    orderId = rs.getInt(1);
                }
                insertOrder.executeQuery();

                String addToRelation="Insert into AddOrUpdateOrder values ("+did+","+orderId+","+(final_amount+sc)+","+"0);";
                Statement insertPlaceOrder=conn.createStatement();
                insertPlaceOrder.executeQuery(addToRelation);

                double updatedBal=rs1.getFloat("Balance")+final_amount+sc;

                String queryUpdateDist="UPDATE Distributor set Balance="+updatedBal+" where distributorID="+rs1.getInt("distributorID")+";";
                Statement updateDist=conn.createStatement();
                updateDist.executeQuery(queryUpdateDist);

                String queryInsert="Insert INTO TransactionDetails(OrderID, DeliveryDate) Values (?,?);";
                PreparedStatement insertTransDetails=conn.prepareStatement(queryInsert);
                insertTransDetails.setInt(1, orderId);
                insertTransDetails.setObject(2, delivery);

                insertTransDetails.executeQuery();

                System.out.println("Distributor placed order!!");
            }
            else{
                System.out.println("Distributor or Publication not present!!!");
                return false;
            }
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean changeBalOnPayment(Connection conn,Scanner inputreader){
        System.out.println("Enter Distributor ID:");
        int did=inputreader.nextInt();
        System.out.println("Enter Order ID:");
        int oid=inputreader.nextInt();
        System.out.println("Enter the Amount Paid:");
        float paidAmount=inputreader.nextFloat();
        StringBuilder updateDist=new StringBuilder("UPDATE Distributor set Balance=Balance-"+paidAmount+" where distributorID="+did);
        StringBuilder updateorderreceipt=new StringBuilder("UPDATE AddOrUpdateOrder set Status=1 where distributorID="+did+" and orderID="+oid);
        try{
            StringBuilder querydistorder=new StringBuilder("Select * from Distributor d,Orders o,AddOrUpdateOrder a where d.distributorID = a.distributorID and o.orderID = a.orderID and d.distributorID ="+did+" and o.OrderID = "+oid);
            ResultSet getdist=conn.createStatement().executeQuery(querydistorder.toString());
            if(getdist.next()){
                if(getdist.getInt("Status")==0){
                    conn.createStatement().executeUpdate(updateDist.toString());
                    conn.createStatement().executeUpdate(updateorderreceipt.toString());

                    System.out.println("Distributor Balance Updated");
                } else{
                    System.out.println("Order payment already done!!");
                }
            } else{
                System.out.println("Distributor / Order not present");
            }

        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
