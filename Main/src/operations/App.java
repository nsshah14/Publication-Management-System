package operations;
import java.util.Scanner;
import java.sql.*;
import operations.Initializer;
import operations.Production;
import operations.Distribution;
import operations.Edit_Publish;
import operations.Report;

public class App {
    //database parameters Replace with your parameters
    static String user = "pthosan";
    static String password = "200401606";

    public static void main(String[] args) throws Exception {
        
        //connect to the database
        Class.forName("org.mariadb.jdbc.Driver"); 

        Connection con=DriverManager.getConnection("jdbc:mariadb://classdb2.csc.ncsu.edu:3306/" + user,user,password); 
        try{ 
            //INITIALIZE PROJECT
            //create scanner to read user input
            Scanner inputReader = new Scanner(System.in);  // Create a Scanner object
            String userInput = "";
                    
            //drop, and re-create all tables
            Initializer.dropTables(con);
            Initializer.createTables(con);

            System.out.println("\n\n+-------------------------------+\n|\tTABLES CREATED!!!\t|\n+-------------------------------+\n\n");
            Initializer.addDummyValues(con);

            //USER INPUT PROCESSING
            do {
                //Calls prompt method which gives the list of commands user could use.
                prompt();
                System.out.print("Input Command: ");
                userInput = inputReader.nextLine();  // Read user input
                
                //TODO: Replace the println's with calls to the different methods in the Distribution, Edit_Publish, Report, Production.java files.
                //check case 7 for example
                // input routing
                con.setAutoCommit(false);
                switch(userInput){
                	case "0" : break;
                    case "1" :  Savepoint prodInsert=con.setSavepoint("beforeProdInsert");
                                if(Production.newPublication(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(prodInsert);
                                break; //call createPublication method in Production.java file
                    case "2" : Savepoint editorInsert = con.setSavepoint("beforeEditorInsert");
                                if(Edit_Publish.newEditor(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(editorInsert);
                                break;
                    case "3" : Savepoint prodUpdate = con.setSavepoint("beforeProdUpdate");
                                if(Production.updateProd(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(prodUpdate);
                                break;
                    case "4" : Savepoint editorEditor =con.setSavepoint("beforeupdateEditor");
                                if(Edit_Publish.updateEditor(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(editorEditor);
                                break;
                    case "5" : Savepoint assignAuthor = con.setSavepoint("AfterEditorInsert");
                                if(Edit_Publish.assignsnewAuthors(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(assignAuthor);
                                break;
                    case "6" : Savepoint editorView = con.setSavepoint("editorviewPublication");
                                if(Edit_Publish.editorviewPublication(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(editorView);
                                break;
                    case "7" : Savepoint newProd = con.setSavepoint("beforeProdInsert");
                                if(Production.newPublication(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(newProd);
                                break;
                    case "8" : Savepoint chapterInsert = con.setSavepoint("beforeChapterInsert");
                                if(Production.newPublication(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(chapterInsert);
                                break;
                    case "9" : Savepoint articleDelete=con.setSavepoint("beforeArticledelete");
                                if(Production.deleteArticles(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(articleDelete);
                                break;
                    case "10" : Savepoint chapterDelete =con.setSavepoint("beforeChaptersedelete");
                                if(Production.deleteChapters(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(chapterDelete);
                                break;
                    case "11" : Savepoint addProd=con.setSavepoint("beforeProdInsert");
                                if(Production.newPublication(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(addProd);
                                break; //call createPublication method in Production.java file
                    case "12" : Savepoint updateProd = con.setSavepoint("beforeProdUpdate");
                                if(Production.updateProd(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(updateProd);
                                break;
                    case "13" : Savepoint deleteProd = con.setSavepoint("beforeProdDelete");
                                if(Production.deleteProd(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(deleteProd);
                                break;
                    case "14" : Savepoint updateArticle = con.setSavepoint("beforeArticleUpdate");
                                if(Production.updateArticles(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(updateArticle);
                                break;
                    case "15" : Savepoint updateChapters = con.setSavepoint("beforeChaptersUpdate");
                                if(Production.updateChapters(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(updateChapters);
                                break;
                    case "16" : Savepoint updateTextArticles = con.setSavepoint("beforeTextArticleUpdate");
                                if(Production.updateTextOfArticle(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(updateTextArticles);
                                break;
                    case "17" : Savepoint placeOrder=con.setSavepoint("beforeDistPlaceOrder");
                                if(Distribution.addOrderAndBillDist(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(placeOrder);
                                break;
                    case "18" : System.out.println("Unimplemented");
                                break;
                    case "19" : Savepoint distInsert=con.setSavepoint("beforeDistInsert");
                                if(Distribution.newDist(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(distInsert);
                                break;
                    case "20" : Savepoint distUpdate=con.setSavepoint("beforeDistupdate");
                                if(Distribution.updateDist(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(distUpdate);
                                break;
                    case "21" : Savepoint deldist=con.setSavepoint("beforeDistInsert");
                                if(Distribution.deleteDist(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(deldist);
                                break;
                    case "22" : System.out.println("Unimplemented");
                                break;
                    case "23" : System.out.println("Unimplemented");
                                break;
                    case "24" : System.out.println("Unimplemented");
                                break;
                    case "25" : con.setSavepoint("beforeDistInsert");
			                    if(Report.monthlyPublication(con,inputReader))
			                        con.commit();
			                    else
			                        con.rollback();
			                    break;	
                    case "26" : System.out.println("Unimplemented");
                                break;
                    case "27" : System.out.println("Unimplemented");
                                break;
                    case "28" : System.out.println("Unimplemented");
                                break;
                    case "29" : System.out.println("Unimplemented");
                                break;
                    case "30" : System.out.println("Unimplemented");
                                break;
                   

                    default:  System.out.println("Invalid Input");
                                break;
                    
                }
     
            } while(!userInput.equals("0"));

            //DE-INITIALIZE PROJECT
            
            //close sql connection and scanner object
            con.close();
            inputReader.close();
               
        }catch(Exception e){ 
            System.out.println(e);
        }finally{
            con.close();
        }
    }

    public static void prompt(){
        System.out.println();
        System.out.println("DBMS Group 22 Project");
        System.out.println("Instructions: Enter the number associated with the given operation to perform it.");
        System.out.println("\t\t [0] - Close");

        //instructions for editing/publishing
        System.out.println("\tEditing/Publishing:");
        System.out.println("\t\t [1] - Enter basic information on a new publication ");
        System.out.println("\t\t [2] - Enter basic information of a Editor");
        System.out.println("\t\t [3] - Update Publication Information ");
        System.out.println("\t\t [4] - Update Editor Information ");
        System.out.println("\t\t [5] - Assign Editor(s) to Publication");
        System.out.println("\t\t [6] - Let each editor view the information on the publications he/she is responsible for");        
        System.out.println("\t\t [7] - Add Articles to Publications ");
        System.out.println("\t\t [8] - Add Chapters to Publications ");
        System.out.println("\t\t [9] - Delete Articles to Publications ");
        System.out.println("\t\t [10] - Delete Chapters to Publications ");
        System.out.println();

        //instructions for production
        System.out.println("\tProduction:");
        System.out.println("\t\t [11] - Enter a new book edition or new issue of a publication");
        System.out.println("\t\t [12] - Update a book edition or publication issue.");
        System.out.println("\t\t [13] - Delete a book edition or publication issue.");
        System.out.println("\t\t [14] - Enter/Update an article");
        System.out.println("\t\t [15] - Enter/Update an chapter");        
        System.out.println("\t\t [16] - Enter/Update text of an article");
        System.out.println("\t\t [17] - Find books and articles by topic, date, author's name");
        System.out.println("\t\t [18] - Enter payment for author or editor");
        System.out.println("\t\t [19] - Keep track of when each payment was claimed by its addressee");
        System.out.println();
    
        //instructions for distribution
        System.out.println("\tDistribution");
        System.out.println("\t\t [20] - Enter new distributor");
        System.out.println("\t\t [21] - Update distributor information");
        System.out.println("\t\t [22] - Delete a distributor. ");
        System.out.println("\t\t [23] - Input orders from distributors, for a book edition or an issue of a publication per distributor, for a certain date.");
        System.out.println("\t\t [24] - Bill distributor for an order");
        System.out.println("\t\t [25] - Change outstanding balance of a distributor on receipt of a payment.");
        System.out.println();   
        
        //instructions for reports
        System.out.println("\tReports");
        System.out.println("\t\t [26] - Generate montly reports: number and total price of copies of each publication bought per distributor per month");
        System.out.println("\t\t [27] - Generate montly reports: total revenue of the publishing house");
        System.out.println("\t\t [28] - Generate montly reports: total expenses (i.e., shipping costs and salaries)");
        System.out.println("\t\t [29] - Calculate the total current number of distributors");
        System.out.println("\t\t [30] - Calculate total revenue (since inception) per city, per distributor, and per location");
        System.out.println("\t\t [31] - Calculate total payments to the editors and authors, per time period and per work type (book authorship, article authorship, or editorial work)");
        System.out.println();
        

    }

    
}
