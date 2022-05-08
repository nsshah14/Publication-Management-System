package operations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
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
        System.out.println("Enter Title for Publcation:");
        String pubTitle=inputReader.nextLine();
        System.out.println("Enter the Date of Publication (yyyy-mm-dd):");
        String date=inputReader.nextLine();
        System.out.println("Enter the Topics of Publication:");
        String pubTopics=inputReader.nextLine();
        System.out.println("Enter the Periodicity of Publication:");
        String pubPeriodicity=inputReader.nextLine();
        System.out.println("Enter the Price of Publication:");
        Double pubPrice=inputReader.nextDouble();
        System.out.println("Enter the Editor ID for Publication:");
        int pubEID=inputReader.nextInt();
        ResultSet rs = null;
        String query = "INSERT INTO Publication (Title, Date,Topics,Periodicity,Price,EID) VALUES(?,?,?,?,?,?);";
        String insWritesPublication="INSERT INTO writesPublication (PublicationID,EID) VALUES(?,?);";
        String matchEID = "SELECT * from Editor where EID = "+pubEID+";";
        // int flag = 0;
        int EID = 0;
        try{
            ResultSet rs1=conn.createStatement().executeQuery(matchEID);
            if(rs1.next()){
                EID = rs1.getInt(1);
            }
            else{   
                System.out.println("Enter the Editor First as it's not present");
                // flag=1;
                return false;
            }
        }
        catch(Exception e){
            System.out.println("Enter the Editor First as it's not present");
            return false;
         }
        // if(flag==1)
        // {
        //     System.out.println("Enter 0 to return to Prompt");
        //     int returnToPrompt = inputReader.nextInt();
        //     if(returnToPrompt==0)
        //     {
        //         return false;
        //     }
        // }
        try{
            PreparedStatement stinsert=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            rs = stinsert.getGeneratedKeys();
        
            stinsert.setString(1, pubTitle);
            stinsert.setDate(2,java.sql.Date.valueOf(date));
            stinsert.setString(3, pubTopics);
            stinsert.setString(4, pubPeriodicity);
            stinsert.setDouble(5, pubPrice);
            stinsert.setInt(6,pubEID);
            stinsert.executeQuery();

            System.out.println("1 Row inserted!");

            System.out.println("\t\t [1] Enter information for New book");
            System.out.println("\t\t [2] Enter information for Periodic Publication (Articles, Magazines)");
            
            Scanner inputRead = new Scanner(System.in);  // Create a Scanner object
            String userInput = "";
            System.out.print("Input Command: ");
            userInput = inputRead.nextLine();

            String getpublicationID = "SELECT PublicationID FROM Publication ORDER BY PublicationID DESC LIMIT 1";
            PreparedStatement getValueofID=conn.prepareStatement(getpublicationID);
            ResultSet publicationID =  getValueofID.executeQuery();
            publicationID.first();
            int PubID=publicationID.getInt("PublicationID");

            PreparedStatement iwp=conn.prepareStatement(insWritesPublication);
            iwp.setInt(1, PubID);
            iwp.setInt(2, EID);
            iwp.executeQuery();

            if(userInput.equals("1"))
            {
                System.out.println("Enter ISBN for Books:");
                String bookISBN=inputRead.nextLine();
                System.out.println("Enter the Edition of Book:");
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
                userInput = inputRead.nextLine();

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
                String type=inputRead.nextLine();
                System.out.println("Enter the Periodic Length of Publication:");
                int periodicLength=inputRead.nextInt();  
                inputRead.nextLine();

                System.out.println("Enter the Issue Date of Publication:");
                String issueDate=inputRead.nextLine();                  
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
                userInput = inputRead.nextLine();

                if(userInput.equals("1"))
                {
                    System.out.println("Enter the Article ID:");
                    int articleID=inputRead.nextInt();  
                    inputRead.nextLine();
                    System.out.println("Enter the Description of Article:");
                    String articleDesc=inputRead.nextLine();  
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
            StringBuilder query=new StringBuilder("UPDATE Publication SET `"+toUpdateCol+"`=? where ");
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
         inputreader.nextLine();

         System.out.println("Enter new value:");
         String toUpdateVal=inputreader.nextLine();


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

     public static boolean updateChapters(Connection conn, Scanner inputreader){

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
            StringBuilder query=new StringBuilder("UPDATE Chapters SET "+toUpdateCol+"=? where ");
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

     public static boolean deleteChapters(Connection conn, Scanner inputreader){
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
           StringBuilder query=new StringBuilder("DELETE FROM Chapters where ");
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

     public static boolean updateTextOfArticle(Connection conn, Scanner inputreader){

       
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

         System.out.println("Enter new value:");
         String toUpdateVal=inputreader.nextLine();


         try{
            StringBuilder query=new StringBuilder("UPDATE Articles SET Text=? where ");
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

     public static boolean findBooksByTopic (Connection conn, Scanner inputreader){
        System.out.println("Enter Topic to find the Book for:");
        String inputTopic=inputreader.nextLine();
        ResultSet rs2;
        // try{
        //     String query = "SELECT PublicationID from Publication where Topics like '"+inputTopic+"%';";
        //     rs=conn.createStatement().executeQuery(query);
        //     rs.next();
        //     pubID = rs.getInt(1);
        // } catch(Exception e){
        //     e.printStackTrace();
        //     return false;
        // }

        // try{
        //     String queryFromBooks = "SELECT PublicationID from Books where PublicationID ="+pubID+";";
        //      rs1=conn.createStatement().executeQuery(queryFromBooks);
        // } catch(Exception e){
        //     e.printStackTrace();
        //     return false;
        // }
      
        try{

        // while(rs1.next())
        // {
            String getBooksByTopic = "SELECT * from Books b,Publication p where p.PublicationID = b.PublicationID and p.Topics like '%"+inputTopic+"%';";
            rs2=conn.createStatement().executeQuery(getBooksByTopic);
            if(!rs2.isBeforeFirst()){
                System.out.println("No Books found for the specified Topic!");
                return true;
            }
            while(rs2.next()){
                System.out.println();
                System.out.println("-------Result for Book Searched by Topic-------");
                System.out.println("Title of Book: "+rs2.getString("Title"));
                System.out.println("Date of Publication of Book: "+rs2.getString("Date"));
                System.out.println("Topic of Book: "+rs2.getString("Topics"));
                System.out.println("Periodicity of Book: "+rs2.getString("Periodicity"));
                System.out.println("Price of Book: "+rs2.getString("Price"));
                System.out.println("Edition of Book: "+rs2.getString("Edition"));
                System.out.println("ISBN of Book: "+rs2.getString("ISBN"));
            }
    
        // }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
     }

     public static boolean findBooksByDate (Connection conn, Scanner inputreader){
        System.out.println("Enter Date to find the Book for:");
        String inputDate=inputreader.nextLine();
        ResultSet rs2;
        // try{
        //     String query = "SELECT PublicationID from Publication where Date = '"+inputDate+"%';";
        //     rs=conn.createStatement().executeQuery(query);
        //     if(rs.next())
        //         pubID = rs.getInt(1);
        //     else{
        //         System.out.println("No Books Found!!");
        //         return true;
        //     }
        // } catch(Exception e){
        //     e.printStackTrace();
        //     return false;
        // }

        // try{
        //     String queryFromBooks = "SELECT PublicationID from Books where PublicationID ="+pubID+";";
        //      rs1=conn.createStatement().executeQuery(queryFromBooks);
        // } catch(Exception e){
        //     e.printStackTrace();
        //     return false;
        // }
      
        try{

            // while(rs1.next())
            // {
                String getBooksByDate = "SELECT * from Books b,Publication p where p.PublicationID = b.PublicationID and p.Date = '"+inputDate+"';";
                rs2=conn.createStatement().executeQuery(getBooksByDate);
                if(!rs2.isBeforeFirst()){
                    System.out.println("No books found for this date!");
                    return true;
                }
                while(rs2.next()){
                    System.out.println();
                    System.out.println("-------Result for Book Searched by Date-------");
                    System.out.println("Title of Book: "+rs2.getString("Title"));
                    System.out.println("Date of Publication of Book: "+rs2.getString("Date"));
                    System.out.println("Topic of Book: "+rs2.getString("Topics"));
                    System.out.println("Periodicity of Book: "+rs2.getString("Periodicity"));
                    System.out.println("Price of Book: "+rs2.getString("Price"));
                    System.out.println("Edition of Book: "+rs2.getString("Edition"));
                    System.out.println("ISBN of Book: "+rs2.getString("ISBN"));
                }
            // }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
     }

     public static boolean findBooksByAuthorName (Connection conn, Scanner inputreader){
        System.out.println("Enter Author's name to find the Book for:");
        String inputName=inputreader.nextLine();
        ResultSet rs2;
        // try{            
        //     String findidbyauthor = "SELECT EID from Editor where Name like '"+inputName+"%';";
        //     rs3=conn.createStatement().executeQuery(findidbyauthor);
        //     rs3.next();
        //     editorID = rs3.getInt(1);
        // }
        // catch(Exception e){
        //     e.printStackTrace();
        //     return false;
        // }
        // try{
        //     String query = "SELECT PublicationID from Publication where EID ="+editorID+";";
        //     rs=conn.createStatement().executeQuery(query);
        //     rs.next();
        //     pubID = rs.getInt(1);
        // } catch(Exception e){
        //     e.printStackTrace();
        //     return false;
        // }

        // try{
        //     String queryFromBooks = "SELECT PublicationID from Books where PublicationID ="+pubID+";";
        //      rs1=conn.createStatement().executeQuery(queryFromBooks);
        // } catch(Exception e){
        //     e.printStackTrace();
        //     return false;
        // }
      
        try{

        // while(rs1.next())
        // {
            String getBooksByAuthorName = "SELECT * from Books b,Publication p,Editor e where p.PublicationID = b.PublicationID and p.EID = e.EID and e.Name like '"+inputName+"%';";
            rs2=conn.createStatement().executeQuery(getBooksByAuthorName);
            if(!rs2.isBeforeFirst()){
                System.out.println("No Books found for the specified Author!");
                return true;
            }
            while(rs2.next()){
                System.out.println();
                System.out.println("-------Result for Book Searched by Author Name-------");
                System.out.println("Title of Book: "+rs2.getString("Title"));
                System.out.println("Date of Publication of Book: "+rs2.getString("Date"));
                System.out.println("Topic of Book: "+rs2.getString("Topics"));
                System.out.println("Periodicity of Book: "+rs2.getString("Periodicity"));
                System.out.println("Price of Book: "+rs2.getString("Price"));
                System.out.println("Edition of Book: "+rs2.getString("Edition"));
                System.out.println("ISBN of Book: "+rs2.getString("ISBN"));
            }
        // }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
     }

     public static boolean findArticlesbyTopic (Connection conn, Scanner inputreader){
        System.out.println("Enter Topic to find the Articles for:");
        String inputTopic=inputreader.nextLine();
        ResultSet rs2;
        // try{
        //     String query = "SELECT PublicationID from Publication where Topics like '"+inputTopic+"%';";
        //     rs=conn.createStatement().executeQuery(query);
        //     rs.next();
        //     pubID = rs.getInt(1);
        // } catch(Exception e){
        //     e.printStackTrace();
        //     return false;
        // }

        // try{
        //     String queryFromArticles = "SELECT PublicationID from Articles where PublicationID ="+pubID+";";
        //      rs1=conn.createStatement().executeQuery(queryFromArticles);
        // } catch(Exception e){
        //     e.printStackTrace();
        //     return false;
        // }
      
        try{

        // while(rs1.next())
        // {
            String getArticlesByTopic = "SELECT * from Articles a,Publication p where p.PublicationID = a.PublicationID and p.Topics like '%"+inputTopic+"%';";
            rs2=conn.createStatement().executeQuery(getArticlesByTopic);
            if(!rs2.isBeforeFirst()){
                System.out.println("No Articles found for the specified topics:");
                return true;
            }
            while(rs2.next()){
                System.out.println();
                System.out.println("-------Result for Articles Searched by Topic-------");
                System.out.println("Title of Article: "+rs2.getString("Title"));
                System.out.println("Date of Publication of Article: "+rs2.getString("Date"));
                System.out.println("Topic of Article: "+rs2.getString("Topics"));
                System.out.println("Periodicity of Article: "+rs2.getString("Periodicity"));
                System.out.println("Price of Article: "+rs2.getString("Price"));
                System.out.println("Description of Article: "+rs2.getString("Description"));
                System.out.println("Text of Article: "+rs2.getString("Text"));
            }
        // }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
     }

     public static boolean findArticlesbyDate (Connection conn, Scanner inputreader){
        System.out.println("Enter Date to find the Articles for:");
        String inputDate=inputreader.nextLine();
        ResultSet rs2;
        // try{
        //     String query = "SELECT PublicationID from Publication where Date like '"+inputDate+"%';";
        //     rs=conn.createStatement().executeQuery(query);
        //     rs.next();
        //     pubID = rs.getInt(1);
        // } catch(Exception e){
        //     e.printStackTrace();
        //     return false;
        // }

        // try{
        //     String queryFromArticles = "SELECT PublicationID from Articles where PublicationID ="+pubID+";";
        //      rs1=conn.createStatement().executeQuery(queryFromArticles);
        // } catch(Exception e){
        //     e.printStackTrace();
        //     return false;
        // }
      
        try{

        // while(rs1.next())
        // {
            String getArticlesByDate = "SELECT * from Articles a,Publication p where p.PublicationID = a.PublicationID and p.Date = '"+inputDate+"';";
            rs2=conn.createStatement().executeQuery(getArticlesByDate);
            if(!rs2.isBeforeFirst()){
                System.out.println("No Article found for the specified Date!");
                return true;
            }
            while(rs2.next()){
                System.out.println();
                System.out.println("-------Result for Article Searched by Date-------");
                System.out.println("Title of Article: "+rs2.getString("Title"));
                System.out.println("Date of Publication of Article: "+rs2.getString("Date"));
                System.out.println("Topic of Article: "+rs2.getString("Topics"));
                System.out.println("Periodicity of Article: "+rs2.getString("Periodicity"));
                System.out.println("Price of Article: "+rs2.getString("Price"));
                System.out.println("Description of Article: "+rs2.getString("Description"));
                System.out.println("Text of Article: "+rs2.getString("Text"));
            }
        // }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
     }

     public static boolean findArticlesByAuthorName (Connection conn, Scanner inputreader){
        System.out.println("Enter Author's name to find the Articles for:");
        String inputName=inputreader.nextLine();
        ResultSet rs2;
        // try{            
        //     String findidbyauthor = "SELECT EID from Editor where Name like '"+inputName+"%';";
        //     rs3=conn.createStatement().executeQuery(findidbyauthor);
        //     rs3.next();
        //     editorID = rs3.getInt(1);
        // }
        // catch(Exception e){
        //     e.printStackTrace();
        //     return false;
        // }
        // try{
        //     String query = "SELECT PublicationID from Publication where EID ="+editorID+";";
        //     rs=conn.createStatement().executeQuery(query);
        //     rs.next();
        //     pubID = rs.getInt(1);
        // } catch(Exception e){
        //     e.printStackTrace();
        //     return false;
        // }

        // try{
        //     String queryFromArticles = "SELECT PublicationID from Articles where PublicationID ="+pubID+";";
        //      rs1=conn.createStatement().executeQuery(queryFromArticles);
        // } catch(Exception e){
        //     e.printStackTrace();
        //     return false;
        // }
      
        try{

        // while(rs1.next())
        // {
            String getArticlesByAuthorName = "SELECT * from Articles a,Publication p,Editor e where p.PublicationID = a.PublicationID and p.EID = e.EID and e.Name like '"+inputName+"%';";
            rs2=conn.createStatement().executeQuery(getArticlesByAuthorName);
            if(!rs2.isBeforeFirst()){
                System.out.println("No Article found for the given Author name!");
                return true;
            }
            while(rs2.next()){
                System.out.println();
                System.out.println("-------Result for Article Searched by Author Name-------");
                System.out.println("Title of Article: "+rs2.getString("Title"));
                System.out.println("Date of Publication of Article: "+rs2.getString("Date"));
                System.out.println("Topic of Article: "+rs2.getString("Topics"));
                System.out.println("Periodicity of Article: "+rs2.getString("Periodicity"));
                System.out.println("Price of Article: "+rs2.getString("Price"));
                System.out.println("Description of Article: "+rs2.getString("Description"));
                System.out.println("Text of Article: "+rs2.getString("Text"));
            }
        // }
        }
    catch(Exception e){
        e.printStackTrace();
        return false;
    }
        return true;
    }
    public static boolean editorPayment(Connection conn,Scanner inputreader){
        try{
            System.out.println("Enter Editor ID:");
            int eid=inputreader.nextInt();

            ResultSet getEditor=conn.createStatement().executeQuery("SELECT * FROM Editor where EID="+eid);

            if(getEditor.next()){
                System.out.println("Enter Amount Paid to Editor:");
                float amt=inputreader.nextFloat();

                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String currentTime = sdf.format(date);
                
                // System.out.println("SELECT * FROM Payment where EID="+eid+" and Date=`"+currentTime+"`;");
                ResultSet geteidanddate=conn.createStatement().executeQuery("SELECT * FROM Payment where EID="+eid+" and Date='"+currentTime+"';");

                if(geteidanddate.next()){
                    System.out.println("Can't enter Transaction for the specified EID as 1 Transaction is already record today!");
                    return false;
                } else{
                    PreparedStatement ps=conn.prepareStatement("Insert into Payment(EID,Amount,Date) values (?,?,?)");

                    ps.setInt(1,eid);
                    ps.setFloat(2, amt);
                    ps.setDate(3, java.sql.Date.valueOf(currentTime));

                    ps.executeQuery();

                    System.out.println("Payment for Editor successful!!");
                }
        
            } else{
                System.out.println("Editor not present!! Please Enter Editor details first to continue");
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    public static boolean claimPayment(Connection conn,Scanner inputreader) {
    	try{
            System.out.println("Enter Editor/Author ID:");
            int eid=inputreader.nextInt();
            inputreader.nextLine();
            System.out.println("Enter Payment Send Date(yyyy-mm-dd):");
            String sentDate=inputreader.next();
            inputreader.nextLine();
            System.out.println("Enter Payment Claim Date(yyyy-mm-dd)");
            String claimDate=inputreader.next();
            inputreader.nextLine();
            
            String query = "UPDATE Payment SET DateClaimed = ? WHERE EID = ? AND Date = ?";
            PreparedStatement updateOrder = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            updateOrder.setObject(1, claimDate);
            updateOrder.setInt(2, eid);
            updateOrder.setObject(3, sentDate);
            int i= updateOrder.executeUpdate();
            if (i>0) {
            	System.out.println("Editor/Author ID: "+ eid+ " claimed payment!");
            }else {
            	System.out.println("Failed to add values check if data sent is correct");
            } 
            
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true; 
    } 
    
    
    public static boolean trackPayment(Connection conn,Scanner inputreader) {
    	try(Statement stmt = conn.createStatement()){
    		 String query= "SELECT Editor.EID, Name, Amount, Date, DateClaimed FROM Payment JOIN Editor ON Payment.EID = Editor.EID WHERE DateClaimed IS NOT NULL ORDER BY DateClaimed";
             ResultSet rs = stmt.executeQuery(query);
             
             System.out.println(String.format("%s %20s %20s %20s %20s", "EID", "Name", "Amount", "Send Date", "Claim Date") );
             while (rs.next()) {
            	 String eid = rs.getString("EID");
            	 String name = rs.getString("Name");
            	 String amount = rs.getString("Amount");
            	 String sentDate = rs.getString("Date");
            	 String claimDate = rs.getString("DateClaimed");
            	 System.out.println(String.format("%s %20s %20s %20s %20s", eid, name, amount, sentDate, claimDate) );
             }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true; 
    }
    public static boolean getOrders(Connection conn,Scanner inputreader){
        try{
            System.out.println("Enter Order ID:");
            int oid=inputreader.nextInt();
            String query = "Select * from Orders where OrderID="+oid+";";
            ResultSet rs=conn.createStatement().executeQuery(query);
            System.out.println("--------------ORDERS--------------");
            while(rs.next()){
                System.out.println("Order ID:"+rs.getInt(1));
                System.out.println("Price:"+rs.getFloat(2));
                System.out.println("Shipping Cost:"+rs.getFloat(3));
                System.out.println("Number of Copies:"+rs.getInt(4));
                System.out.println("Publication ID:"+rs.getInt(5));
                System.out.println("Order Date:"+rs.getString(6));
            }            
        } catch(Exception e){
            System.out.println("Error occured");
            return false;
        }
        return true;
    }
}
