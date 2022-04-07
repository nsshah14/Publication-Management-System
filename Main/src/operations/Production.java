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
            PreparedStatement stinsert=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stinsert.setString(1, pubTitle);
            stinsert.setDate(2,java.sql.Date.valueOf(date));
            stinsert.setString(3, pubTopics);
            stinsert.setString(4, pubPeriodicity);
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
                String getpublicationID = "SELECT PublicationID FROM Publication ORDER BY PublicationID DESC LIMIT 1";
                PreparedStatement getValueofID=conn.prepareStatement(getpublicationID);
                ResultSet publicationID =  getValueofID.executeQuery();
                publicationID.first();
                int PubID=publicationID.getInt("PublicationID");
                System.out.println(PubID);
                System.out.println("Enter ISBN for Books:");
                String bookISBN=inputRead.next();
                System.out.println("Enter the Edition of Book:");
                String bookEdition=inputRead.next();   
                String book_query = "INSERT INTO Books (PublicationID, ISBN,Edition) VALUES(?,?,?);";
                PreparedStatement insertIntoBook=conn.prepareStatement(book_query);

                insertIntoBook.setInt(1, PubID);
                insertIntoBook.setString(2,bookISBN);
                insertIntoBook.setString(3, bookEdition);
                insertIntoBook.executeQuery();

                System.out.println("Inserted into Books Table");
                
                System.out.println("\t\t [1] Enter the Chapter Information in a Book:");
                System.out.println("\t\t Any other key to return to main menu");
                System.out.print("Input Command: ");
                userInput = inputRead.next();

                if(userInput.equals("1"))
                {
                    System.out.println("Enter the Chapter ID:");
                    int chapID=inputRead.nextInt();  
                    System.out.println("Enter the Number of Pages Chapters of Book:");
                    int numOfPages=inputRead.nextInt();  
                    String pages_query = "INSERT INTO Chapters (PublicationID,ChapterID,Number_of_pages) VALUES(?,?,?);";
                    PreparedStatement insertPagesIntoBook=conn.prepareStatement(pages_query);
                    insertPagesIntoBook.setInt(1, PubID);
                    insertPagesIntoBook.setInt(2, chapID);
                    insertPagesIntoBook.setInt(3, numOfPages);
                    insertPagesIntoBook.executeQuery();
                    System.out.println("Inserted into Chapters Table");
                }
                else{
                    return true;
                }
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
