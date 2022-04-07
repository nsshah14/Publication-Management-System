package operations;
import java.util.Scanner;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Edit_Publish {
    /** 
    System.out.println("\t Additional Information")
    System.out.println("\t\t [1] - Enter basic information of a Editor");
    System.out.println("\t\t [2] - Enter basic information of a Staff Editor");
    System.out.println("\t\t [3] - Enter basic information of a Invited Author");

    System.out.println("\tEditing/Publishing Task:");
    System.out.println("\t\t [1] - Enter basic information on a new publication ");
    System.out.println("\t\t [2] - Update Publication Information ");
    System.out.println("\t\t [3] - Assign Editor(s) to Publication");
    System.out.println("\t\t [4] - Let each editor view the information on the publications he/she is responsible for");        
    System.out.println("\t\t [5] - Add Articles/Chapters to Publications ");
    System.out.println("\t\t [6] - Delete Articles/Chapters to Publications ");
    System.out.println();
    **/

    //TODO create methods for each task and operation above like the one in production.java  


    String[] editorcolumns={"EID", "Name", "Address", "Contact"};
      public static boolean newEditor(Connection conn, Scanner inputReader){

         System.out.println("Enter Editor name:");
         String name=inputReader.next();
         System.out.println("Enter Editor Address:");
         String add=inputReader.next();
         System.out.println("Enter Editor Contact:");
         String contact=inputReader.next();
        
         String query = "INSERT INTO Editor (Name, Address, Contact) VALUES(?,?,?);";
         try{
            PreparedStatement stinsert=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stinsert.setString(1, name);
            stinsert.setString(2, add);
            stinsert.setString(3, contact);
            stinsert.executeQuery();

            System.out.println("1 Row inserted!");

            System.out.println("\t\t [1] Enter information for New book");
            System.out.println("\t\t [2] Enter information for Periodic Publication (Articles, Magazines)");
            
            Scanner inputRead = new Scanner(System.in);  // Create a Scanner object
            String userInput = "";
            System.out.print("Input Command: ");
            userInput = inputRead.next();

            if(userInput.equals("1"))
            {
                String getEID = "SELECT EID FROM Editor ORDER BY EID DESC LIMIT 1";
                PreparedStatement getValueofID=conn.prepareStatement(getEID);
                ResultSet EID =  getValueofID.executeQuery();
                System.out.println(EID);
                EID.first();
                int EID=EID.getInt("EID");
                System.out.println(EID);

                System.out.println("Enter Editor Experience:");
                String experience=inputReader.next();

                String query = "INSERT INTO StaffEditor (EID, Experience) VALUES(?,?);";
         
                PreparedStatement stinsert=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                stinsert.setInt(1, EID);
                stinsert.setString(2, experience);
                stinsert.executeQuery();

                System.out.println("1 Row inserted!");

            }
             else if(userInput.equals("2"))
            {

            }
            else{
                System.out.println("Wrong Input Given");
            }


         } catch(Exception e){
            e.printStackTrace();
            return false;
         }
         return true;
      }
    
    // Enter basic information of a Staff Editor
    String[] staffeditorcolumns={"EID", "Experience"};
      public static boolean newStaffEditor(Connection conn, Scanner inputReader){

         System.out.println("Enter Editor ID:");
         int eid=inputReader.nextInt();
         System.out.println("Enter Editor Experience:");
         String experience=inputReader.next();
        
         String query = "INSERT INTO StaffEditor (EID, Experience) VALUES(?,?);";
         try{
            PreparedStatement stinsert=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stinsert.setInt(1, eid);
            stinsert.setString(2, experience);
            stinsert.executeQuery();

            System.out.println("1 Row inserted!");
         } catch(Exception e){
            e.printStackTrace();
            return false;
         }
         return true;
      }

    //Enter basic information of a Invited Author
    String[] inivitedautorcolumns={"EID", "Date_of_invitation"};
      public static boolean newInvitedAuthor(Connection conn, Scanner inputReader){

         System.out.println("Enter Editor ID:");
         int eid=inputReader.nextInt();
         System.out.println("Enter Editor Date_of_invitation:");
         String date=inputReader.next();
        
         String query = "INSERT INTO InvitedAuthor (EID, Date_of_invitation) VALUES(?,?);";
         try{
            PreparedStatement stinsert=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stinsert.setInt(1, eid);
            stinsert.setDate(2, java.sql.Date.valueOf(date));
            stinsert.executeQuery();

            System.out.println("1 Row inserted!");
         } catch(Exception e){
            e.printStackTrace();
            return false;
         }
         return true;
      }

    // Assign Editor to Publication

    String[] writePublicationcol={"PID", "EID"};
      public static boolean writePublication(Connection conn, Scanner inputReader){

         System.out.println("Enter PID:");
         int pid=inputReader.nextInt();
         System.out.println("Enter Editor ID:");
         int eid=inputReader.nextInt();
        
         String query = "INSERT INTO assignsAuthors (PID, EID) VALUES(?,?);";
         try{
            PreparedStatement stinsert=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stinsert.setInt(1, pid);
            stinsert.setInt(2, eid);
            stinsert.executeQuery();

            System.out.println("1 Row inserted!");
         } catch(Exception e){
            e.printStackTrace();
            return false;
         }
         return true;
      }

}
