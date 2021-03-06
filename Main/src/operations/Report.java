package operations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Report {
    /**
     * 
     * System.out.println("\tReports");
        
        
       
        
        System.out.println("\t\t [24] - Calculate total revenue (since inception) per city, per distributor, and per location");
        System.out.println("\t\t [25] - Calculate total payments to the editors and authors, per time period and per work type (book authorship, article authorship, or editorial work)");
        System.out.println();
        
        
        INSERT: 
        # distributor 1
        INSERT INTO Distributor(distributorID, Name, Type, Address, Phone, ContactPerson, Balance) VALUES (2001, 'BookSell', 'bookstore', '2200, A Street, NC', '9191234567', 'Jason', 215);
        # distributor 2
        INSERT INTO Distributor(distributorID, Name, Type, Address, Phone, ContactPerson, Balance) VALUES (2001, 'BookSell', 'bookstore', '2200, A Street, NC', '9191234567', 'Jason', 215);
        
        #Personel 1 - payment might need to be derived from orders
        INSERT INTO Editor(EID, Name, Address, Contact) VALUES (3001, 'John', '21 ABC St, NC 27', '3001@gmail.com');
        INSERT INTO StaffEditor(EID, Experience) VALUES (3001, '1 year');
        INSERT INTO Payment(EID, Amount, Date) VALUES (3001, 1000, '2020-04-01');
        
        #Personel 2
        INSERT INTO Editor(EID, Name, Address, Contact) VALUES (3002, 'Ethen', '21 ABC St, NC 27606', '3002@gmail.com');
        INSERT INTO StaffEditor(EID, Experience) VALUES (3002, '2 year');
        INSERT INTO Payment(EID, Amount, Date) VALUES (3002, 1000, '2020-04-01');
        
        #Personel 3
        INSERT INTO Editor(EID, Name, Address, Contact) VALUES (3003, 'Cathy', '3300 AAA St, NC, 27606', '3003@gmail.com');
        INSERT INTO InvitedAuthor(EID, Date_of_invitation) VALUES (3003, '2020-05-07');
        INSERT INTO Payment(EID, Amount, Date) VALUES (3003, 1200, '2020-04-01');
        
        
        #Publication 1- hold price from orders
        INSERT INTO Publication(PublicationID, Title, Date, Topics, Periodicity, Price, EID) VALUES(1001, 'Introduction to database', '2018-10-10', 'technology','N/A', 20, 3001);
        INSERT INTO Books(PublicationID, ISBN, Edition) VALUES(1001, '12345', '2ed');
        
        #Publication 2: 
        INSERT INTO Publication(PublicationID, Title, Date, Topics, Periodicity) VALUES(1002, 'Healthy Diet', '2020-02-24', 'health', 'weekly');
        INSERT INTO PeriodicPublication(PublicationID, Type, Periodic_length, Issue_date) VALUES(1002, 'magazine', 52, '2020-02-24');
        INSERT INTO Articles(PublicationID, ArticleID, Description, Text) VALUES(1002, 1, 'ABC', 'ABC');
        
        #Publication 3:
        INSERT INTO Publication(PublicationID, Title, Date, Topics, Periodicity) VALUES(1003, 'Animal Science', '2020-03-01', 'science', 'monthly');
        INSERT INTO PeriodicPublication(PublicationID, Type, Periodic_length, Issue_date) VALUES(1003, 'journal', 12, '2020-03-01');
        INSERT INTO Articles(PublicationID, ArticleID, Description, Text) VALUES(1003, 2, 'AAA', 'AAA');
        
        #Order 1: assuming amount is same as total payment in payment on demo datas
        INSERT INTO Orders(OrderID, Price, ShippingCost, NumCopies, PublicationID, Date) VALUES (4001, 20, 30, 30, 1001, '2020-01-02');
        INSERT INTO TransactionDetails(OrderID, DeliveryDate) VALUES(4001, '2020-01-15');
        INSERT INTO AddOrUpdateOrder(DistributorID, OrderID, Amount, Status) VALUES(2001, 4001, 630, 0);
        
        #Order 2: 
        INSERT INTO Orders(OrderID, Price, ShippingCost, NumCopies, PublicationID, Date) VALUES (4002, 20, 15, 10, 1001, '2020-02-05');
        INSERT INTO TransactionDetails(OrderID, DeliveryDate) VALUES(4002, '2020-02-15');
        INSERT INTO AddOrUpdateOrder(DistributorID, OrderID, Amount, Status) VALUES(2001, 4002, 0, 0);
        
        #Order 3: 
        INSERT INTO Orders(OrderID, Price, ShippingCost, NumCopies, PublicationID, Date) VALUES (4003, 10, 15, 10, 1003,'2020-02-10');
        INSERT INTO TransactionDetails(OrderID, DeliveryDate) VALUES(4003, '2020-02-25');
        INSERT INTO AddOrUpdateOrder(DistributorID, OrderID, Amount, Status) VALUES(2002, 4003, 115, 0);
        
        DONE
        System.out.println("\t\t [20] - Generate montly reports: number and total price of copies of each publication bought per distributor per month");
        System.out.println("\t\t [21] - Generate montly reports: total revenue of the publishing house");
        System.out.println("\t\t [22] - Generate montly reports: total expenses (i.e., shipping costs and salaries)");
        System.out.println("\t\t [23] - Calculate the total current number of distributors");
     * @return 
     */

	// System.out.println("\t\t [20] - Generate montly reports: number and total price of copies of each publication bought per distributor per month");  
	public static boolean monthlyPublication(Connection con, Scanner scan ){
		
        try(Statement stmt = con.createStatement()){	
        	//Insert TODO: add rows to Orders and AddOrUpdateOrder Tables
        	//SELECT YEAR(Date) AS Year, MONTH(Date) AS Month, DistributorID, PublicationID, SUM(NumCopies), SUM(Price*NumCopies) AS TotalPrice FROM Orders JOIN AddOrUpdateOrder ON AddOrUpdateOrder.OrderID = Orders.orderID GROUP BY Year, Month, DistributorID, PublicationID;
            String inputStatement = "SELECT YEAR(Date) AS Year, MONTH(Date) AS Month, DistributorID, PublicationID, SUM(NumCopies), SUM(Price) AS TotalPrice FROM Orders JOIN AddOrUpdateOrder ON AddOrUpdateOrder.OrderID = Orders.orderID GROUP BY Year, Month, DistributorID, PublicationID";     
            ResultSet rs = stmt.executeQuery(inputStatement);
            String prevDateVal = "";
            String prevDistIdVal = "";
            while(rs.next()) {
            	String date = rs.getString("Month") + "/" + rs.getString("Year");
            	String distId = rs.getString("DistributorID");
            	String pubId = rs.getString("PublicationID");
            	String sumCopies = rs.getString("SUM(NumCopies)");
            	String totalPrice = rs.getString("TotalPrice");
            	
            	if(!date.equals(prevDateVal)) {
            		System.out.println("Date: " + date);
            		prevDateVal = date;
            		prevDistIdVal = "";
            	}
            	
            	if(!distId.equals(prevDistIdVal)) {
            		System.out.println("\tDistributorID: " + distId);
            		prevDistIdVal = distId;
            	}
            	
            	System.out.println("\t\tPublicationID: " + pubId + " SumCopies: " + sumCopies + " TotalPrice: " + totalPrice);
            	
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
 
        try(Statement stmt = con.createStatement()){
        	 	
        	//SELECT YEAR(Date) AS Year, MONTH(Date) AS Month, SUM((Price*NumCopies) + ShippingCost) AS TotalRevenue FROM Orders JOIN AddOrUpdateOrder ON AddOrUpdateOrder.OrderID = Orders.orderID GROUP BY Year, Month;
        	
            String inputStatement = "SELECT YEAR(Date) AS Year, MONTH(Date) AS Month, SUM(Price + ShippingCost) AS TotalRevenue FROM Orders JOIN AddOrUpdateOrder ON Ad"
            		+ "dOrUpdateOrder.OrderID = Orders.orderID GROUP BY Year, Month";
            
            ResultSet rs = stmt.executeQuery(inputStatement);
            System.out.println(String.format("%s %20s", "Date:", "TotalPrice") );
            while(rs.next()){
                //grab id
            	String date = rs.getString("Month") + "/" + rs.getString("Year");
                String totalRevenue = rs.getString("TotalRevenue");
                System.out.println(String.format("%s %20s", date, totalRevenue));
             }
            System.out.println("Ok");
        }catch(SQLException e){
            e.printStackTrace();
            return false; 
        }
        return true;
	}
	
	
	//System.out.println("\t\t [22] - Generate montly reports: total expenses (i.e., shipping costs and salaries)");
	//TODO: look at sorting.
	public static boolean monthlyExpense(Connection con, Scanner scan ){

		//TODO: count distributor ids. 
        try(Statement stmt = con.createStatement()){
        	//SELECT YEAR(Date) AS Year, Month(Date) AS Month, SUM(Amount) FROM Payment GROUP BY Year, Month;
        	//SELECT YEAR(Date) AS Year, MONTH(Date) AS Month, SUM(ShippingCost) AS SumShipping FROM Orders GROUP BY Year, Month;
            
        	Map<String, Double> monthlyExpense = new HashMap<String, Double>();
        	
        	//get the monthly values for payment of author/editors
        	String inputStatement1 = "SELECT YEAR(Date) AS Year, Month(Date) AS Month, SUM(Amount) AS SumAmount FROM Payment GROUP BY Year, Month";
            ResultSet rs1 = stmt.executeQuery(inputStatement1);
            
        	while(rs1.next()) {
        		String date = rs1.getString("Month") + "/" + rs1.getString("Year");
        		double payment = rs1.getDouble("SumAmount");
        		monthlyExpense.put(date, payment);
        	}
        	
        	//get the monthly values for the shipping cost 
        	//SELECT YEAR(Date) AS Year, MONTH(Date) AS Month, SUM(ShippingCost) AS SumShipping FROM Orders JOIN AddOrUpdateOrder ON Orders.orderID = AddOrUpdateOrder.orderID GROUP BY Year, Month
        	String inputStatement2 = "SELECT YEAR(Date) AS Year, MONTH(Date) AS Month, SUM(ShippingCost) AS SumShipping FROM Orders JOIN AddOrUpdateOrder ON Orders.orderID = AddOrUpdateOrder.orderID GROUP BY Year, Month";
        	ResultSet rs2 = stmt.executeQuery(inputStatement2);
            
        	while(rs2.next()) {
        		String date = rs2.getString("Month") + "/" + rs2.getString("Year");
        		double shippingCost = rs2.getDouble("SumShipping");
        		if(monthlyExpense.get(date)== null) {
               		monthlyExpense.put(date, shippingCost);
        		}else {
        			Double totalMonthExpense = monthlyExpense.get(date) + shippingCost;
        			monthlyExpense.put(date,totalMonthExpense);
        		}
        	}
        	
        	Iterator<Map.Entry<String, Double>> hmIterator = monthlyExpense.entrySet().iterator();
        	System.out.println(String.format("%s %20s", "Date:" , "Expense:"));
        	while(hmIterator.hasNext()) {
        		Map.Entry<String, Double> mapElement = (Map.Entry<String, Double>)hmIterator.next();
        		System.out.println(String.format("%s %20s", mapElement.getKey(), mapElement.getValue()));
        	}		
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
            String inputStatement = "SELECT COUNT(distributorID) FROM Distributor";
            ResultSet rs = stmt.executeQuery(inputStatement);
            
            if(rs.next()) {
            	String numDist = rs.getString(1);
                System.out.println("Total Number of Distributors: " + numDist);
                System.out.println("Ok");
            }else {
            	System.out.println("Distributor table not present!!!");
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
        
	}
	
	
	//TODO
	//System.out.println("\t\t [24] - Calculate total revenue (since inception) per city, per distributor, and per location");
	public static boolean totalRevenuePerCity(Connection con, Scanner scan ){

		//SELECT Distributor.city,SUM((Price*NumCopies)+ShippingCost) As DistPay FROM Orders JOIN AddOrUpdateOrder ON AddOrUpdateOrder.OrderID = Orders.orderID JOIN Distributor ON Distributor.distributorID = AddOrUpdateOrder.DistributorID GROUP BY Distributor.city; 
        try(Statement stmt = con.createStatement()){
        	String inputStatement = "SELECT Distributor.city,SUM(Price+ShippingCost) As DistPay FROM Orders JOIN AddOrUpdateOrder ON AddOrUpdateOrder.OrderID = Orders.orderID JOIN Distributor ON Distributor.distributorID = AddOrUpdateOrder.DistributorID GROUP BY Distributor.city;";
        	ResultSet rs1 = stmt.executeQuery(inputStatement);
        	System.out.println(String.format("%s %20s", "City" , "Total Revenue"));
            while(rs1.next()) {
            	String city = rs1.getString("city");
            	String TotalRev = rs1.getString("DistPay");
            	System.out.println(String.format("%s %20s", city , TotalRev));
            }
        	System.out.println("Ok");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
	}
	
	//System.out.println("\t\t [24] - Calculate total revenue (since inception) per city, per distributor, and per location");
	public static boolean totalRevenuePerDistributor(Connection con, Scanner scan ){
		//SELECT Distributor.distributorID,SUM((Price*NumCopies)+ShippingCost) As DistPay FROM Orders JOIN AddOrUpdateOrder ON AddOrUpdateOrder.OrderID = Orders.orderID JOIN Distributor ON Distributor.distributorID = AddOrUpdateOrder.DistributorID GROUP BY Distributor.distributorID;
		
        try(Statement stmt = con.createStatement()){
            String inputStatement = "SELECT Distributor.distributorID,SUM(Price+ShippingCost) As DistPay FROM Orders JOIN AddOrUpdateOrder ON AddOrUpdateOrder.OrderID = Orders.orderID JOIN Distributor ON Distributor.distributorID = AddOrUpdateOrder.DistributorID GROUP BY Distributor.distributorID";
        	ResultSet rs1 = stmt.executeQuery(inputStatement);
        	System.out.println(String.format("%s %20s", "Distribution ID" , "Total Revenue"));
            while(rs1.next()) {
            	String distID = rs1.getString("distributorID");
            	String TotalRev = rs1.getString("DistPay");
            	System.out.println(String.format("%s %20s", distID , TotalRev));
            }
        	System.out.println("Ok");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
	}
		
		//System.out.println("\t\t [24] - Calculate total revenue (since inception) per city, per distributor, and per location");
	public static boolean totalRevenuePerLocation(Connection con, Scanner scan ){

		//SELECT Distributor.Address,(Price*NumCopies)+ShippingCost As DistPay FROM Orders JOIN AddOrUpdateOrder ON AddOrUpdateOrder.OrderID = Orders.orderID JOIN Distributor ON Distributor.distributorID = AddOrUpdateOrder.DistributorID GROUP BY Address;
        try(Statement stmt = con.createStatement()){
            String inputStatement = "SELECT Distributor.Address,SUM(Price+ShippingCost) As DistPay FROM Orders JOIN AddOrUpdateOrder ON AddOrUpdateOrder.OrderID = Orders.orderID JOIN Distributor ON Distributor.distributorID = AddOrUpdateOrder.DistributorID GROUP BY Address";
        	ResultSet rs1 = stmt.executeQuery(inputStatement);
        	System.out.println(String.format("%s %20s", "Address" , "Total Revenue"));
            while(rs1.next()) {
            	String addr = rs1.getString("Address");
            	String TotalRev = rs1.getString("DistPay");
            	System.out.println(String.format("%s %20s", addr , TotalRev));
            }
            
            System.out.println("Ok");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
	}	
	
	//System.out.println("\t\t [25] - Calculate total payments to the editors and authors, per time period and per work type (book authorship, article authorship, or editorial work)");
	public static boolean totalPaymentPerTime(Connection con, Scanner scan ){
		System.out.println("Enter Start date(yyyy-mm-dd): ");
		String startDate = scan.nextLine();
		System.out.println("Enter End date(yyyy-mm-dd): ");
		String endDate = scan.nextLine();
		
		//TODO: count distributor ids. 
        try(Statement stmt = con.createStatement()){
        
        	//SELECT SUM(Amount)  FROM Editor JOIN Payment ON Editor.EID = Payment.EID WHERE Date > '2020-05-01' AND Date < '2022-01-01';
        	String inputStatement = String.format("SELECT SUM(Amount) FROM Editor JOIN Payment ON Editor.EID = Payment.EID WHERE Date > '%s' AND Date < '%s'", startDate, endDate);
        	ResultSet rs1 = stmt.executeQuery(inputStatement);
            while(rs1.next()) {
            	String totPay = rs1.getString("SUM(Amount)");
            	System.out.println(String.format("Totals Amount: %s", totPay));
            }
            System.out.println("Ok");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
	}
	
	//TODO: Time period?? Work Type?
	//System.out.println("\t\t [25] - Calculate total payments to the editors and authors, per time period and per work type (book authorship, article authorship, or editorial work)");
	public static boolean totalPaymentPerWork(Connection con, Scanner scan ){

		//TODO: count distributor ids. 
        try(Statement stmt = con.createStatement()){
            
        	//SELECT SUM(Amount) FROM InvitedAuthor JOIN Payment ON InvitedAuthor.EID = Payment.EID;
        	String inputStatement1 = "SELECT SUM(Amount) FROM InvitedAuthor JOIN Payment ON InvitedAuthor.EID = Payment.EID";
        	String inputStatement2 = "SELECT SUM(Amount) FROM StaffEditor JOIN Payment ON StaffEditor.EID = Payment.EID";
     
        	ResultSet rs1 = stmt.executeQuery(inputStatement1);
        	ResultSet rs2 = stmt.executeQuery(inputStatement2);
        	
        	
			if(rs1.next()) {
				String totPayInvitedAuthor = rs1.getString("SUM(Amount)");
				System.out.println(String.format("Total Payment of InvitedAuthor: %s", totPayInvitedAuthor));
			}
			
			if(rs2.next()) {
				String totPayStaffEditor = rs2.getString("SUM(Amount)");
	        	System.out.println(String.format("Total Payment of StaffEditor: %s", totPayStaffEditor));
			}
        	
        	System.out.println("Ok");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
	}	
}
