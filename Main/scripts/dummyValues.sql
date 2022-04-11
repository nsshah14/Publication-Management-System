INSERT INTO Distributor VALUES(1, 'john', 'Wholesale', '2518 Avent Ferry Road', '+19848883402', 'Mary', 400);
INSERT INTO Distributor VALUES(2, 'Kevin', 'Wholesale', '2512 Avent Ferry Road', '+19842239405', 'Jake', 200);
INSERT INTO Distributor VALUES(3, 'Mark', 'Bookstore', '2520 Avent Ferry Road', '+191041239420', 'Lily', 64);
INSERT INTO Distributor VALUES(4, 'Steve', 'Library', '2514 Avent Ferry Road', '+19843483402', 'Mary', 400);
INSERT INTO Distributor VALUES(5, 'Mike', 'Library', '2517 Avent Ferry Road', '+19863245402', 'Lucy', 772);

INSERT INTO Distributor VALUES(2001, 'BookSell', 'Bookstore', '2200, A Street, NC', '+1919234567', 'Jason', 215);
INSERT INTO Distributor VALUES(2002, 'BookDist', 'Wholesaler', '2200, B Street, NC', '+19291234568', 'Alex', 0);


INSERT INTO Editor VALUES(1, 'Johnny', '2512 Gorman Street Road', '+19848883402');
INSERT INTO Editor VALUES(2, 'Johnny', '2522 Gorman Street Road', '+19842239405');
INSERT INTO Editor VALUES(3, 'Mark', '2520 Gorman Street Road', '+191041239420');
INSERT INTO Editor VALUES(4, 'Steve', '2514 Gorman Street Road', '+19843483402');
INSERT INTO Editor VALUES(5, 'Mike', '2517 Gorman Street Road', '+19863245402');

INSERT INTO Editor VALUES(3001, 'John', '21 ABC St, NC 27', '+19891234567');
INSERT INTO Editor VALUES(3002, 'Ethen', '21 ABC St, NC 27606', '+19491234567');
INSERT INTO Editor VALUES(3003, 'Cathy', '3300 AAA St, NC 27606', '+19591234567');


INSERT INTO StaffEditor VALUES(1, '2 Years');
INSERT INTO StaffEditor VALUES(2, '3 Years');
INSERT INTO StaffEditor VALUES(3, '4 Years');

INSERT INTO StaffEditor VALUES(3001, '4 Years');
INSERT INTO StaffEditor VALUES(3002, '5 Years');


INSERT INTO InvitedAuthor VALUES(4, '2022-01-07');
INSERT INTO InvitedAuthor VALUES(5, '2022-03-02');
INSERT INTO InvitedAuthor VALUES(3003, '2022-04-02');


INSERT INTO Payment VALUES(1, 102, '2022-01-21');
INSERT INTO Payment VALUES(2, 942, '2022-02-04');
INSERT INTO Payment VALUES(3, 1200, '2022-03-19');
INSERT INTO Payment VALUES(4, 512, '2021-01-13');
INSERT INTO Payment VALUES(5, 765, '2021-01-26');

INSERT INTO Payment VALUES(3001,1000,'2020-04-01');
INSERT INTO Payment VALUES(3002,1000,'2020-04-01');
INSERT INTO Payment VALUES(3003,1200,'2020-04-01');


INSERT INTO Publisher VALUES(1, "Angel", "+19848883402", "2512 Kensington Park Road");
INSERT INTO Publisher VALUES(2, "Paul", "+19842239405", "2122 Kensington Park Road");
INSERT INTO Publisher VALUES(3, "Suzane", "+191041239420", "2620 Kensington Park Road");
INSERT INTO Publisher VALUES(4, "Maria", "+19841298765", "2524 Kensington Park Road");
INSERT INTO Publisher VALUES(5, "Michael", "+19863245402", "2519 Kensington Park Road");
INSERT INTO Publication VALUES(1, "Introduction to Computing", '2020-01-21', "Science", "",12.45,1);
INSERT INTO Publication VALUES(2, "Introduction to Databases", '2020-01-21', "Science", "",10.97,2);
INSERT INTO Publication VALUES(3, "Autobiography of Sachin Tendulkar", '2019-03-19', "Sports", "Yearly",4.67,2);
INSERT INTO Publication VALUES(4, "Mathematics Edition 1", '2021-02-13', "Mathematics", "Monthly",9.43,3);
INSERT INTO Publication VALUES(5, "Marvel Comics", '2021-01-26', "Fictional", "Weekly",8.34,4);

INSERT INTO Publication VALUES(1001,"introduction to database",'2018-10-10',"technology","",100,3001);
INSERT INTO Publication VALUES(1002,"Healthy Diet",'2020-02-24',"health","Weekly",200,3002);
INSERT INTO Publication VALUES(1003,"Animal Science",'2020-03-01',"Science","Monthly",200,2);


INSERT INTO Books VALUES(1, "978-3-16-148410-0", "1st Edition");
INSERT INTO Books VALUES(2, "978-3-16-148410-1", "2nd Edition");

INSERT INTO Books VALUES(1001, "12345", "2ed");

INSERT INTO PeriodicPublication VALUES(3, "Journal", 2, '2020-10-05');
INSERT INTO PeriodicPublication VALUES(4, "Magazine", 5, '2020-06-21');
INSERT INTO PeriodicPublication VALUES(5, "Journal", 1, '2020-03-03');

INSERT INTO PeriodicPublication VALUES(1002, "Magazine", 1, '2020-02-24');
INSERT INTO PeriodicPublication VALUES(1002, "Journal", 2, '2020-03-01');


INSERT INTO Chapters VALUES(1, 1, 400);
INSERT INTO Chapters VALUES(1, 2, 500);
INSERT INTO Chapters VALUES(1, 3, 600);
INSERT INTO Chapters VALUES(2, 4, 40);
INSERT INTO Chapters VALUES(2, 5, 45);
INSERT INTO Articles VALUES(3, 1, "ScienceDescription", "ScienceText");
INSERT INTO Articles VALUES(3, 2, "MathDescription", "MathText");
INSERT INTO Articles VALUES(4, 3, "ReadingDescription", "ReadingText");
INSERT INTO Articles VALUES(5, 4, "ComputerScienceDescription", "ComputerScienceText");

INSERT INTO Articles VALUES(1002, 5, "", "ABC");

INSERT INTO Management VALUES(1, "John", "Financial");
INSERT INTO Management VALUES(2, "Steve", "Distribution");
INSERT INTO Management VALUES(3, "Mike", "Financial");
INSERT INTO Orders VALUES(1,100.00, 100.00, 1, 1, '2022-01-21');
INSERT INTO Orders VALUES(2,200.00, 200.00, 2, 2, '2022-01-22');
INSERT INTO Orders VALUES(3,300.00, 300.00, 3, 3,'2022-01-23');
INSERT INTO Orders VALUES(4,400.00, 400.00, 4, 4, '2022-01-24');

INSERT INTO Orders VALUES(4001,20.00, 30.00, 30, 1001, '2020-01-02');
INSERT INTO Orders VALUES(4002,20.00, 15.00, 10, 1001, '2020-02-05');
INSERT INTO Orders VALUES(4003,10.00, 15.00, 10, 1003, '2020-02-10');


INSERT INTO TransactionDetails VALUES(1, '2022-01-21');
INSERT INTO TransactionDetails VALUES(2, '2022-01-21');
INSERT INTO TransactionDetails VALUES(3, '2022-04-21');

INSERT INTO TransactionDetails VALUES(4001, '2020-01-15');
INSERT INTO TransactionDetails VALUES(4002, '2020-02-15');
INSERT INTO TransactionDetails VALUES(4003, '2020-02-25');



INSERT INTO Account VALUES(1, '2022-04-21', 200.02, 27);
INSERT INTO Account VALUES(2, '2022-05-21', 100.02, 1);
INSERT INTO MonthlyRevenueReport VALUES(1, 231.40, '1989-05-14', 20.00, 40, "Cary");
INSERT INTO MonthlyRevenueReport VALUES(2, 2541.40, '1989-05-14', 65.00, 30, "Durham");
INSERT INTO MonthlyRevenueReport VALUES(3, 2674.40, '1989-05-14', 48.00, 70, "Charlette");
INSERT INTO MonthlyRevenueReport VALUES(4, 2021.40, '1989-05-14', 70.00, 40, "Greensboro");
INSERT INTO MonthlyPaymentReport VALUES(1, 1203.41, '2021-03-20', "Book Authorship");
INSERT INTO MonthlyPaymentReport VALUES(2, 921.41, '2021-03-20', "Article Authorship");
INSERT INTO MonthlyPaymentReport VALUES(3, 1203.41, '2021-03-05', "Editorial Authorship");
INSERT INTO MonthlyPaymentReport VALUES(4, 1203.41, '2021-03-20', "Article Authorship");
INSERT INTO GenerateRevenueReport VALUES(1,1);
INSERT INTO GenerateRevenueReport VALUES(1,2);
INSERT INTO GenerateRevenueReport VALUES(2,1);
INSERT INTO GenerateRevenueReport VALUES(2,3);
INSERT INTO GeneratePaymentReport VALUES(1,1);
INSERT INTO GeneratePaymentReport VALUES(1,2);
INSERT INTO GeneratePaymentReport VALUES(2,1);
INSERT INTO GeneratePaymentReport VALUES(2,3);
INSERT INTO CollectRevenueReport VALUES(1,1);
INSERT INTO CollectRevenueReport VALUES(2,3);
INSERT INTO CollectPaymentReport VALUES(1,1);
INSERT INTO CollectPaymentReport VALUES(2,3);
INSERT INTO AddOrUpdateOrder VALUES(1,1,256.76,1);
INSERT INTO AddOrUpdateOrder VALUES(1,2,134.2,0);
INSERT INTO AddOrUpdateOrder VALUES(2,1,457.2,1);
INSERT INTO AddOrUpdateOrder VALUES(2,2,3458,0);

INSERT INTO AddOrUpdateOrder VALUES(2001,4001,630,1);
INSERT INTO AddOrUpdateOrder VALUES(2001,4002,250,1);
INSERT INTO AddOrUpdateOrder VALUES(2002,4003,115,1);


INSERT INTO hasAccount VALUES(1, 1);
INSERT INTO hasAccount VALUES(2, 2);
INSERT INTO hasAccount VALUES(1, 1);
INSERT INTO hasAccount VALUES(2, 3);
INSERT INTO assignsAuthors VALUES(1, 1);
INSERT INTO assignsAuthors VALUES(1, 3);
INSERT INTO CreateUpdateDeletePublication VALUES(1, 1);
INSERT INTO CreateUpdateDeletePublication VALUES(1, 3);
INSERT INTO writesPublication VALUES(1, 1);
INSERT INTO writesPublication VALUES(1, 3);
INSERT INTO Consist VALUES(1, 1);
INSERT INTO Consist VALUES(2, 1);

INSERT INTO Consist VALUES (4001,1001);
INSERT INTO Consist VALUES (4002,1001);
INSERT INTO Consist VALUES (4003,1003);
