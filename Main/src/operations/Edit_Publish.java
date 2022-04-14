package operations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


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
          String name=inputReader.nextLine();
          System.out.println("Enter Editor Address:");
          String add=inputReader.nextLine();
          System.out.println("Enter Editor Contact:");
          String contact=inputReader.nextLine();
        
          String query = "INSERT INTO Editor (Name, Address, Contact) VALUES(?,?,?);";
         try{
             PreparedStatement stinsert=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

             stinsert.setString(1, name);
             stinsert.setString(2, add);
             stinsert.setString(3, contact);
             stinsert.executeQuery();

             System.out.println("1 Row inserted!");

            System.out.println("\t\t [1] Enter Staff Editor Information");
            System.out.println("\t\t [2] Enter Invited Author Information");
            
            String userInput = "";
            System.out.print("Input Command: ");
            userInput = inputReader.nextLine();
            
            String getEID = "SELECT EID FROM Editor ORDER BY EID DESC LIMIT 1";
                PreparedStatement getValueofID=conn.prepareStatement(getEID);
                ResultSet EID =  getValueofID.executeQuery();
                EID.first();
                int Eid=EID.getInt("EID");

                

            if(userInput.equals("1"))
            {
                System.out.println("Enter Editor Experience:");
                String experience=inputReader.nextLine();
                String staffquery = "INSERT INTO StaffEditor (EID, Experience) VALUES(?,?);";
         
                PreparedStatement stinsertstaff=conn.prepareStatement(staffquery, Statement.RETURN_GENERATED_KEYS);

                stinsertstaff.setInt(1, Eid);
                stinsertstaff.setString(2, experience);
                stinsertstaff.executeQuery();

               System.out.println("1 Row inserted!");

            }
             else if(userInput.equals("2"))
            {
                System.out.println("Enter Editor Date_of_invitation:");
                String date=inputReader.nextLine();
                String authorquery = "INSERT INTO InvitedAuthor (EID, Date_of_invitation) VALUES(?,?);";
            
                PreparedStatement authorstinsert=conn.prepareStatement(authorquery, Statement.RETURN_GENERATED_KEYS);

                authorstinsert.setInt(1, Eid);
                authorstinsert.setDate(2, java.sql.Date.valueOf(date));
                authorstinsert.executeQuery();

            System.out.println("1 Row inserted for Author!");
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
    
    
    // Assign Editor to Publication

    String[] assignsAuthor={"PID", "EID"};
      public static boolean assignsnewAuthors(Connection conn, Scanner inputReader){

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

      public static boolean editorviewPublication(Connection conn, Scanner inputReader){
         System.out.println("Enter Editor ID:");
         int eid=inputReader.nextInt();

         String query = "SELECT wp.EID, p.* FROM Publication p, writesPublication wp where p.PublicationID=wp.PublicationID and wp.EID="+eid+";";
         try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.isBeforeFirst()){
               System.out.println("No rows selected!!");
               return true;
            }
            while (rs.next()) {
               int EID=rs.getInt("EID");
               int PublicationID=rs.getInt("PublicationID");
               String Title=rs.getString("Title");
               Date Date=rs.getDate("Date");
               String Topics=rs.getString("Topics");
               String Periodicity=rs.getString("Periodicity");

               System.out.println(EID + ", " + PublicationID + ", " + Title +
                                 ", " + Date + ", " + Topics+", "+Periodicity);
            }
         } catch(Exception e){
            e.printStackTrace();
            return false;
         }
         return true;
      }

   public static boolean updateEditor(Connection conn, Scanner inputreader){

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
             StringBuilder query=new StringBuilder("UPDATE Editor SET "+toUpdateCol+"=? where ");
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


   

}
