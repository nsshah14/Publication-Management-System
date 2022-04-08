package operations;
import java.util.Scanner;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
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
        String pubTitle=inputReader.nextLine();
        System.out.println("Enter the Date of Publication (dd-mm-yyyy):");
        String date=inputReader.nextLine();
        System.out.println("Enter the Topics of Publication:");
        String pubTopics=inputReader.nextLine();
        System.out.println("Enter the Periodicity of Publication:");
        String pubPeriodicity=inputReader.nextLine();
        System.out.println("Enter the Price of Publication:");
        Double pubPrice=inputReader.nextDouble();
        ResultSet rs = null;
        String query = "INSERT INTO Publication (Title, Date,Topics,Periodicity,Price) VALUES(?,?,?,?,?);";

        try{
            PreparedStatement stinsert=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stinsert.setString(1, pubTitle);
            stinsert.setDate(2,java.sql.Date.valueOf(date));
            stinsert.setString(3, pubTopics);
            stinsert.setString(4, pubPeriodicity);
            stinsert.setDouble(5, pubPrice);
            stinsert.executeQuery();

            System.out.println("1 Row inserted!");

            System.out.println("\t\t [1] Enter information for New book");
            System.out.println("\t\t [2] Enter information for Periodic Publication (Articles, Magazines)");
            
            Scanner inputRead = new Scanner(System.in);  // Create a Scanner object
            String userInput = "";
            System.out.print("Input Command: ");
            userInput = inputRead.next();

            String getpublicationID = "SELECT PublicationID FROM Publication ORDER BY PublicationID DESC LIMIT 1";
            PreparedStatement getValueofID=conn.prepareStatement(getpublicationID);
            ResultSet publicationID =  getValueofID.executeQuery();
            publicationID.first();
            int PubID=publicationID.getInt("PublicationID");

            if(userInput.equals("1"))
            {
                System.out.println("Enter ISBN for Books:");
                String bookISBN=inputRead.next();
                System.out.println("Enter the Edition of Book:");
                inputRead.next();
                String bookEdition=inputRead.nextLine();   
                String book_query = "INSERT INTO Books (PublicationID, ISBN,Edition) VALUES(?,?,?);";
                PreparedStatement insertIntoBook=conn.prepareStatement(book_query);

                insertIntoBook.setInt(1, PubID);
                insertIntoBook.setString(2,bookISBN);
                insertIntoBook.setString(3, bookEdition);
                insertIntoBook.executeQuery();

                System.out.println("Inserted into Books Table");
                
                System.out.println("\t\t [1] Enter the Chapter Information in a Book:");
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
                    return false;
                }
            }
            else if(userInput.equals("2"))
            {
                System.out.println("Enter Type of Periodic Publication:");
                String type=inputRead.next();
                System.out.println("Enter the Periodic Length of Publication:");
                int periodicLength=inputRead.nextInt();  
                System.out.println("Enter the Issue Date of Publication:");
                String issueDate=inputRead.next();                  
                String periodic_publication_query = "INSERT INTO PeriodicPublication (PublicationID,Type,Periodic_length,Issue_date) VALUES(?,?,?,?);";
                PreparedStatement insertIntoPeriodicPublication=conn.prepareStatement(periodic_publication_query);

                insertIntoPeriodicPublication.setInt(1, PubID);
                insertIntoPeriodicPublication.setString(2,type);
                insertIntoPeriodicPublication.setInt(3, periodicLength);
                insertIntoPeriodicPublication.setDate(4,java.sql.Date.valueOf(issueDate));
                insertIntoPeriodicPublication.executeQuery();   
                System.out.println("Inserted into Periodic Publication Table");

                System.out.println("\t\t [1] Enter the Article Information in a Book:");
                System.out.print("Input Command: ");
                userInput = inputRead.next();

                if(userInput.equals("1"))
                {
                    System.out.println("Enter the Article ID:");
                    int articleID=inputRead.nextInt();  
                    System.out.println("Enter the Description of Article:");
                    String articleDesc=inputRead.next();  
                    System.out.println("Enter the Text of Article:");
                    String articleText=inputRead.nextLine();  
                    String article_query = "INSERT INTO Articles (PublicationID,ArticleID,Description,Text) VALUES(?,?,?,?);";
                    PreparedStatement insertArticles=conn.prepareStatement(article_query);
                    insertArticles.setInt(1, PubID);
                    insertArticles.setInt(2, articleID);
                    insertArticles.setString(3, articleDesc);
                    insertArticles.setString(4, articleText);
                    insertArticles.executeQuery();
                    System.out.println("Inserted into Articles Table");
                }
                else{
                    return false;
                }
                
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
    
    public static boolean updateProd(Connection conn, Scanner inputreader){

        List<String> condcolnames=new ArrayList();
         List<Object> condcolvals=new ArrayList();

         System.out.println("Enter number of conditions:");
         int n=inputreader.nextInt();
         for(int i=0;i<n;i++){
            System.out.println("Enter Column name:");
            condcolnames.add(inputreader.next());
            System.out.println("Enter column value:");
            condcolvals.add(inputreader.next());
         }

         System.out.println("Enter Column Name to update:");
         String toUpdateCol=inputreader.next();

         System.out.println("Enter new value:");
         String toUpdateVal=inputreader.next();


         try{
            StringBuilder query=new StringBuilder("UPDATE Publication SET "+toUpdateCol+"=? where ");
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

            stupdate.executeQuery();

            System.out.println("Rows Updated");
         } catch(Exception e){
            e.printStackTrace();
            return false;
         }
         return true;
     }

     public static boolean deleteProd(Connection conn, Scanner inputreader){
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
           StringBuilder query=new StringBuilder("DELETE FROM Publication where ");
           for(int i=0; i<delcolnames.size();i++){
              query.append(delcolnames.get(i) + "=? and ");
           }
           query.replace(query.length()-5,query.length()-1,";");
           PreparedStatement stdelete=conn.prepareStatement(query.toString());
           
           for(int i=0;i<delcolvals.size();i++)
              stdelete.setObject(i+1, delcolvals.get(i));

           // System.out.println(query.toString());
           stdelete.executeQuery();

           System.out.println("Rows Deleted");
        }catch(Exception e){
           e.printStackTrace();
           return false;
        }
        return true;
     }

     public static boolean updateArticles(Connection conn, Scanner inputreader){

        List<String> condcolnames=new ArrayList();
         List<Object> condcolvals=new ArrayList();

         System.out.println("Enter number of conditions:");
         int n=inputreader.nextInt();
         for(int i=0;i<n;i++){
            System.out.println("Enter Column name:");
            condcolnames.add(inputreader.next());
            System.out.println("Enter column value:");
            condcolvals.add(inputreader.next());
         }

         System.out.println("Enter Column Name to update:");
         String toUpdateCol=inputreader.next();
         inputreader.next();

         System.out.println("Enter new value:");
         String toUpdateVal=inputreader.next();


         try{
            StringBuilder query=new StringBuilder("UPDATE Articles SET "+toUpdateCol+"=? where ");
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

            stupdate.executeQuery();

            System.out.println("Rows Updated");
         } catch(Exception e){
            e.printStackTrace();
            return false;
         }
         return true;
     }
    
     public static boolean deleteArticles(Connection conn, Scanner inputreader){
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
           StringBuilder query=new StringBuilder("DELETE FROM Articles where ");
           for(int i=0; i<delcolnames.size();i++){
              query.append(delcolnames.get(i) + "=? and ");
           }
           query.replace(query.length()-5,query.length()-1,";");
           PreparedStatement stdelete=conn.prepareStatement(query.toString());
           
           for(int i=0;i<delcolvals.size();i++)
              stdelete.setObject(i+1, delcolvals.get(i));

           // System.out.println(query.toString());
           stdelete.executeQuery();

           System.out.println("Rows Deleted");
        }catch(Exception e){
           e.printStackTrace();
           return false;
        }
        return true;
     }

}
