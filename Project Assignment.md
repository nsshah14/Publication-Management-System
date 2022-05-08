# Project assignment

This is the description of the task for the main project for CSC 540. The details may change over time, so please visit the online version again. The project will be based on the case study presented in the narrative. Your main task is to design and develop a database system. In order to do this systematically, you will need to perform all and only the subtasks described in terms of the three project reports. (Please note that you will not receive extra credit if you do more than is required; if in doubt, please see the instructor or TAs before the due date for each report.)

## Implementation Plan

The way this project is envisioned you will carry out the following steps. 

1. Understand the problem. 
2. Create the local E/R diagrams (see item 7 in project report 1). 
3. Create the local relational schemas (see item 9 in project report 1). 
4. Create the database schema (see item 1 in project report 2). 
5. Input the schema into the DBMS and populate the base relations (see item 3 in project report 2). 
6. Design SQL queries and updates to realize the operations in the narrative, and test the queries (see item 4 in project report 2). 
7. For each operation, create application code to exercise the operations appropriately (see items 2 and 3 in project report 3). Your application-code language must be different from SQL and must include functionalities for sending SQL commands to a database-management system and for receiving responses from the DBMS. Java with its JDBC functionalities is one example of permitted application-code language.
8. Test by hand. 
9. Give a demo to the TA and the instructor (see item 5 in project report 3). 

## Organization
The project is recommended to be a team effort, each team consisting ideally of (and never more than) 4 students. Please choose your project partners by the announced date. There is no extra credit for forming a team of fewer than 4 people, but such teams are allowed. In such cases, the number of application programs required for the assignment is scaled down, but the remaining tasks are not scaled down. 

The team will have the following functional roles. Every member will have to do some database design and application programming, so the roles can be rotated among the members. Each team member will write the same number of application programs. The prime and backup application programmers will do this first. 

**Software Engineer (prime and backup)**
Responsible for designing the software architecture, including transaction logic. 
**Database Designer / Administrator (prime and backup)**
Responsible for designing E/R diagrams and relational schemas, based on the informal specifications given in the narrative. Also responsible for database-schema maintenance and analysis of query execution. Develops advanced transactions in collaboration with the software engineer. 
**Application Programmer (prime and backup)**
Responsible for designing and implementing the actual applications (operations) that use the designed database. 
**Test Plan Engineer (prime and backup)**
Ensures code quality by designing and implementing test plans for the applications in the project. Also responsible for documentation. 

## Grading
The programming project will be implemented in a system and described in reports. The reports will include E/R diagrams, relational schemas and their justifications, physical design and its justification, description of the queries and transactions (embedded SQL and program logic). The grading for the project will be totaled over four modules as follows. Some of these categories correspond to specific items in project reports (as listed). The others are spread over several items. 
