package operations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Savepoint;
import java.util.Scanner;

public class App {
    //database parameters Replace with your parameters
    static String user = "psengo";
    static String password = "csc540";

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
                    case "17" : Savepoint findBooksByTopic=con.setSavepoint("beforefindBooksByTopic");
                                if(Production.findBooksByTopic(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(findBooksByTopic);
                                break;
                    case "18" : Savepoint findBooksByDate=con.setSavepoint("beforefindBooksByDate");
                                if(Production.findBooksByDate(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(findBooksByDate);
                                break;
                    case "19" : Savepoint findBooksByAuthorName=con.setSavepoint("beforefindBooksByAuthorName");
                                if(Production.findBooksByAuthorName(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(findBooksByAuthorName);
                                break;
                    case "20" :Savepoint findArticlesByTopic=con.setSavepoint("beforefindArticlessByTopic");
                                if(Production.findArticlesbyTopic(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(findArticlesByTopic);
                                break;
                    case "21" : Savepoint findArticlesByDate=con.setSavepoint("beforefindArticlessByDate");
                                if(Production.findArticlesbyDate(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(findArticlesByDate);
                                break;
                    case "22" : Savepoint findArticleByAuthorName=con.setSavepoint("beforefindArticlessByAuthor");
                                if(Production.findArticlesByAuthorName(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(findArticleByAuthorName);
                                break;
                    case "23" : Savepoint editPay=con.setSavepoint("beforeEditorPayment");
                                if(Production.editorPayment(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(editPay);
                                break;
                    case "24" : System.out.println("Unimplemented");
                                break;
                    case "25" :  Savepoint distInsert=con.setSavepoint("beforeDistInsert");
                                if(Distribution.newDist(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(distInsert);
                                break;  
                    case "26" :  Savepoint distUpdate=con.setSavepoint("beforeDistupdate");
                                if(Distribution.updateDist(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(distUpdate);
                                break;
                    case "27" : Savepoint deldist=con.setSavepoint("beforeDistInsert");
                                if(Distribution.deleteDist(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(deldist);
                                break;
                    case "28" :Savepoint addDistOrder=con.setSavepoint("beforeDistAddOrder");
                                if(Distribution.addOrderAndBillDist(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(addDistOrder);
                                break;
                    case "29" :Savepoint addDistOrderBill=con.setSavepoint("beforeDistAddOrder");
                                if(Distribution.addOrderAndBillDist(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(addDistOrderBill);
                                break;
                    case "30" : Savepoint updateDistReceipt=con.setSavepoint("beforeDistPayment");
                                if(Distribution.changeBalOnPayment(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(updateDistReceipt);
                                break;
                    case "31" : Savepoint monthlyPub= con.setSavepoint("beforeGetPubReport");
                                if(Report.monthlyPublication(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback(monthlyPub);
                                break;

                    case "32" : Savepoint monthlyRev= con.setSavepoint("beforeGetRevReport");
			                    if(Report.monthlyRevenue(con,inputReader))
			                        con.commit();
			                    else
			                        con.rollback(monthlyRev);
			                    break;
                
                    case "33" : Savepoint monthlyExp = con.setSavepoint("beforeGetExpReport");
			                    if(Report.monthlyExpense(con,inputReader))
			                        con.commit();
			                    else
			                        con.rollback(monthlyExp);
			                    break;
                  
                    case "34" : Savepoint distCount= con.setSavepoint("beforeDistCountGet");
			                    if(Report.totalDistributor(con,inputReader))
			                        con.commit();
			                    else
			                        con.rollback(distCount);
			                    break;
                    case "35" : Savepoint revPerCity = con.setSavepoint("beforeGetRevPerCity");
			                    if(Report.totalRevenuePerCity(con,inputReader))
			                        con.commit();
			                    else
			                        con.rollback(revPerCity);
			                    break;
                    case "36" : Savepoint revPerDist = con.setSavepoint("beforeGetRevPerDist");
			                    if(Report.totalRevenuePerDistributor(con,inputReader))
			                        con.commit();
			                    else
			                        con.rollback(revPerDist);
			                    break;
                    case "37" : Savepoint revPerLoc = con.setSavepoint("beforeGetRevPerLocation");
			                    if(Report.totalRevenuePerLocation(con,inputReader))
			                        con.commit();
			                    else
			                        con.rollback(revPerLoc);
			                    break;
                    	
                    case "38" : Savepoint payPerDate = con.setSavepoint("beforeGetPayPerDate");
			                    if(Report.totalPaymentPerTime(con,inputReader))
			                        con.commit();
			                    else
			                        con.rollback(payPerDate);
			                    break;
                    case "39" : System.out.println("Unimplemented");	
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
        System.out.println("\t\t [17] - Find books by topic");
        System.out.println("\t\t [18] - Find books by date");
        System.out.println("\t\t [19] - Find books by author's name");
        System.out.println("\t\t [20] - Find articles by topic");
        System.out.println("\t\t [21] - Find articles by date");
        System.out.println("\t\t [22] - Find articles by author's name");
        System.out.println("\t\t [23] - Enter payment for author or editor");
        System.out.println("\t\t [24] - Keep track of when each payment was claimed by its addressee");
        System.out.println();
    
        //instructions for distribution
        System.out.println("\tDistribution");
        System.out.println("\t\t [25] - Enter new distributor");
        System.out.println("\t\t [26] - Update distributor information");
        System.out.println("\t\t [27] - Delete a distributor. ");
        System.out.println("\t\t [28] - Input orders from distributors, for a book edition or an issue of a publication per distributor, for a certain date.");
        System.out.println("\t\t [29] - Bill distributor for an order");
        System.out.println("\t\t [30] - Change outstanding balance of a distributor on receipt of a payment.");
        System.out.println();   
        
        //instructions for reports
        System.out.println("\tReports");
        System.out.println("\t\t [31] - Generate montly reports: number and total price of copies of each publication bought per distributor per month");
        System.out.println("\t\t [32] - Generate montly reports: total revenue of the publishing house");
        System.out.println("\t\t [33] - Generate montly reports: total expenses (i.e., shipping costs and salaries)");
        System.out.println("\t\t [34] - Calculate the total current number of distributors");
        System.out.println("\t\t [35] - Calculate total revenue (since inception) per city");
        System.out.println("\t\t [36] - Calculate total revenue (since inception) per distributor");
        System.out.println("\t\t [37] - Calculate total revenue (since inception) per location");
        System.out.println("\t\t [38] - Calculate total payments to the editors and authors, per time period ");
        System.out.println("\t\t [39] - Calculate total payments to the editors and authors, per work type (book authorship, article authorship, or editorial work)");
        System.out.println();
        

    }

    
}

