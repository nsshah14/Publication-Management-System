package operations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.ArrayList;

public class Report {
    /**
     * 
     * System.out.println("\tReports");
        System.out.println("\t\t [20] - Generate montly reports: number and total price of copies of each publication bought per distributor per month");
        System.out.println("\t\t [21] - Generate montly reports: total revenue of the publishing house");
        System.out.println("\t\t [22] - Generate montly reports: total expenses (i.e., shipping costs and salaries)");
        System.out.println("\t\t [23] - Calculate the total current number of distributors");
        System.out.println("\t\t [24] - Calculate total revenue (since inception) per city, per distributor, and per location");
        System.out.println("\t\t [25] - Calculate total payments to the editors and authors, per time period and per work type (book authorship, article authorship, or editorial work)");
        System.out.println();
     * @return 
     */

	// System.out.println("\t\t [20] - Generate montly reports: number and total price of copies of each publication bought per distributor per month");  
	public static boolean monthlyPublication(Connection con, Scanner scan ){
		//get the publication, distributor, and month
		System.out.print("Enter Publication ID: "); 
        String publicationID = scan.nextLine();
        
        System.out.print("Enter Distributor ID: "); 
        String distributorID = scan.nextLine();

        System.out.print("Enter Month number: "); 
        String month = scan.nextLine();

		//orders, places orders, consists, transactionDetails 
        //TODO: merge places orders,transactionDetails and consists by orderID. 
        //TODO: extract all orderids by specified publication id distributorid and month of transaction.
        //TODO: aggregate the price and num copies of the orderids. 
        try(Statement stmt = con.createStatement()){
        	ArrayList<Integer> OrderIDs = new ArrayList<Integer>();
        	
        	
        	//"SELECT SUM(Price), SUM(NumCopies) FROM Orders WHERE Orders.OrderID IN (SELECT PlacesOrder.OrderID FROM PlacesOrder JOIN TransactionDetails ON PlacesOrder.OrderID = TransactionDetails.OrderID JOIN Consist ON Consist.OrderID = TransactionDetails.OrderID WHERE Consist.PublicationID = 1 AND PlacesOrder.DistributorID = 2 AND MONTH(TransactionDetails.DeliveryDate)= 1);"
        	
            String inputStatement = String.format("SELECT SUM(Price), SUM(NumCopies) FROM Orders "
            		+ "WHERE Orders.OrderID IN "
            		+ "(SELECT PlacesOrder.OrderID FROM PlacesOrder "
            		+ "JOIN TransactionDetails ON PlacesOrder.OrderID = TransactionDetails.OrderID "
            		+ "JOIN Consist ON Consist.OrderID = TransactionDetails.OrderID "
            		+ "WHERE Consist.PublicationID = %s "
            		+ "AND PlacesOrder.DistributorID = %s "
            		+ "AND MONTH(TransactionDetails.DeliveryDate)= %s)", publicationID, distributorID, month);
            
            ResultSet rs = stmt.executeQuery(inputStatement);
            while(rs.next()){
                //grab ids
                System.out.println("SUM MONTHLY Price: " + rs.getString(1));
                System.out.println("SUM MONTHLY Copies: " + rs.getString(2));
             }
            System.out.println("Ok");
        }catch(SQLException e){
            e.printStackTrace();
            return false; 
        }
        return true;
	}
	
	//System.out.println("\t\t [21] - Generate montly reports: total revenue of the publishing house");
	
	public static boolean monthlyRevenue(Connection con, Scanner scan ){

		//get the month
        System.out.print("Enter Month number: "); 
        String month = scan.nextLine();

		//orders, places orders, consists, transactionDetails 
        //TODO: merge places orders,transactionDetails and consists by orderID. 
        //TODO: extract all orderids by specified  month of transaction.
        //TODO: aggregate the price  of the orderids. 
        try(Statement stmt = con.createStatement()){
        	ArrayList<Integer> OrderIDs = new ArrayList<Integer>();
        	
        	
        	//"SELECT SUM(Price), SUM(NumCopies) FROM Orders WHERE Orders.OrderID IN (SELECT PlacesOrder.OrderID FROM PlacesOrder JOIN TransactionDetails ON PlacesOrder.OrderID = TransactionDetails.OrderID JOIN Consist ON Consist.OrderID = TransactionDetails.OrderID WHERE Consist.PublicationID = 1 AND PlacesOrder.DistributorID = 2 AND MONTH(TransactionDetails.DeliveryDate)= 1);"
        	
            String inputStatement = String.format("SELECT SUM(Price) FROM Orders "
            		+ "WHERE Orders.OrderID IN "
            		+ "(SELECT PlacesOrder.OrderID FROM PlacesOrder "
            		+ "JOIN TransactionDetails ON PlacesOrder.OrderID = TransactionDetails.OrderID "
            		+ "JOIN Consist ON Consist.OrderID = TransactionDetails.OrderID "
            		+ "WHERE MONTH(TransactionDetails.DeliveryDate)= %s)", month);
            
            ResultSet rs = stmt.executeQuery(inputStatement);
            while(rs.next()){
                //grab ids
                System.out.println("SUM MONTHLY Price: " + rs.getString(1));
             }
            System.out.println("Ok");
        }catch(SQLException e){
            e.printStackTrace();
            return false; 
        }
        return true;
	}
	
	
	//System.out.println("\t\t [22] - Generate montly reports: total expenses (i.e., shipping costs and salaries)");
	public static boolean monthlyExpense(Connection con, Scanner scan ){

		//TODO: count distributor ids. 
        try(Statement stmt = con.createStatement()){
            //String inputStatement = String.format("INSERT into Publication VALUES(%s, '%s', '%s', '%s', '%s')", id, title, date, topics, periodicity);
            //stmt.executeUpdate(inputStatement);
            
            System.out.println("Ok");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
	}
	
	//System.out.println("\t\t [23] - Calculate the total current number of distributors");
	public static boolean totalDistributor(Connection con, Scanner scan ){

		//TODO: count distributor ids. 
        try(Statement stmt = con.createStatement()){
            //String inputStatement = String.format("INSERT into Publication VALUES(%s, '%s', '%s', '%s', '%s')", id, title, date, topics, periodicity);
            //stmt.executeUpdate(inputStatement);
            
            System.out.println("Ok");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
        
	}
	
	//System.out.println("\t\t [24] - Calculate total revenue (since inception) per city, per distributor, and per location");
	public static boolean totalRevenue(Connection con, Scanner scan ){

		//TODO: count distributor ids. 
        try(Statement stmt = con.createStatement()){
            //String inputStatement = String.format("INSERT into Publication VALUES(%s, '%s', '%s', '%s', '%s')", id, title, date, topics, periodicity);
            //stmt.executeUpdate(inputStatement);
            
            System.out.println("Ok");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
	}
	
	//System.out.println("\t\t [25] - Calculate total payments to the editors and authors, per time period and per work type (book authorship, article authorship, or editorial work)");
	public static boolean totalPayment(Connection con, Scanner scan ){

		//TODO: count distributor ids. 
        try(Statement stmt = con.createStatement()){
            //String inputStatement = String.format("INSERT into Publication VALUES(%s, '%s', '%s', '%s', '%s')", id, title, date, topics, periodicity);
            //stmt.executeUpdate(inputStatement);
            
            System.out.println("Ok");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
	}	
}
