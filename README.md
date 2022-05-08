# csc540_project README

## Thing to remember when adding to database: 
* When you add an order add a transaction detail for given order.
* on order being submitted increment pastOrders value for order number
* ensure 1 publication id for 1 order.


## Project Structure 

There are 6 files in this program located in the main/src/operations folder with the following purposed:
* App.java: Main start of the program. Queries information from the user and routes the task/operations command 
* Initializer.java: Has methods to drop all tables, create all tables, add dummy values to the tables. (Functionality works but all tables haven't been added yet)
* Edit_Publish.java: holds the processing of each of the operation fir editing and publishing.(Needs to be implemented)
* Production.java: holds the processing of each of the tasks and operations for production.(Needs to be implemented)
* Distribution.java: holds the processing of each of the tasks and operations for distribution.(Needs to be implemented)
* Report.java holds the processing of each of the tasks and operations for report.(Needs to be implemented)

## Running Code

Install the latest version of Java on the local machine. 

If you have the Eclipse IDE installed: 

1. To run code import the code into an ide like eclipse as a java project.
2. In the file App.java change the user and password variable to your mariadb user and password.
3. Run the file App.java

Else:

1. Navigate to Project's Main folder
    ```
    cd ./Main 
    ```
2. To compile all the java files execute the following command:
    ```
    javac src/operations/*.java
    ```
3. In order to execute the java files use the following command:
    ```
    java -classpath src/:lib/mariadb-java-client-3.0.4.jar operations.App
    ```

## Development Help

* For an example of how to implement the sql queries take a look at the App.java and Production.java class for inserting a new publication.
* Within the App.java class the 7th case in the switch statment calls the createPublication method in the Production.java class. 
* The createPublication method queries more information from the user and then sends that information as a sql command to the database.
* **NOTE**: make sure not to not violate any constraints in the input. example: trying to add a publication with the same id as another publication in the database. 

## TODO

- [x] TODO: Do Report Queries - Assigned Prashan
- [x] TODO: Do Distribution Queries - Assigned Nisarg
- [x] TODO: Do Production Queries - Assigned Parth
- [x] TODO: Do Editing and Publishing Queries - Assigned Shakshi
- [x] TODO: Combine code and make sure there is not code issues from interaction between different peoples code. 
- [x] TODO: Try out the test demo data on our database(make sure that all information can be represented in the database. 

