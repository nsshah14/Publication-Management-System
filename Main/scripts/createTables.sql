CREATE TABLE IF NOT EXISTS Distributor(
    distributorID INT,
    Name VARCHAR(128) NOT NULL,
    Type VARCHAR(128) NOT NULL,
    Address VARCHAR(256) NOT NULL,
    Phone VARCHAR(16) NOT NULL,
    ContactPerson VARCHAR(128),
    Balance INT NULL,
    PRIMARY KEY(distributorID)
);
CREATE TABLE IF NOT EXISTS Editor(
    EID INT,
    Name VARCHAR(128) NOT NULL,
    Address VARCHAR(256) NOT NULL,
    Contact VARCHAR(16),
    PRIMARY KEY(EID )
);
CREATE TABLE IF NOT EXISTS StaffEditor(
    EID INT,
    Experience VARCHAR(128) NOT NULL,
    PRIMARY KEY(EID),
    FOREIGN KEY(EID) REFERENCES Editor(EID) ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS InvitedAuthor(
    EID INT,
    Date_of_invitation DATE NOT NULL,
    PRIMARY KEY(EID),
    FOREIGN KEY(EID) REFERENCES Editor(EID) ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS Payment(
    EID INT,
    Amount  INT,
    Date DATE NOT NULL,
    PRIMARY KEY(EID),
    FOREIGN KEY(EID) REFERENCES Editor(EID) ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS Publication(
    PublicationID INT,
    Title VARCHAR(128) NOT NULL,
    Date DATE NOT NULL,
    Topics VARCHAR(128),
    Periodicity VARCHAR(128) ,
    PRIMARY KEY(PublicationID)
);
CREATE TABLE IF NOT EXISTS Publisher(
    PID INT,
    Name VARCHAR(128) NOT NULL,
    Contact VARCHAR(16) NOT NULL,
    Address VARCHAR(256),
    PRIMARY KEY(PID)
);
CREATE TABLE IF NOT EXISTS Books(
    PublicationID INT,
    ISBN VARCHAR(128) NOT NULL,
    Edition VARCHAR(128) NOT NULL,
    FOREIGN KEY(PublicationID) REFERENCES Publication(PublicationID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS PeriodicPublication(
    PublicationID INT,
    Type VARCHAR(128) NOT NULL,
    Periodic_length INT NOT NULL,
    Issue_date DATE NOT NULL,
    FOREIGN KEY(PublicationID) REFERENCES Publication(PublicationID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS Chapters(
    PublicationID INT,
    ChapterID INT,
    Number_of_pages INT NOT NULL,
    PRIMARY KEY(PublicationID, ChapterID),
    FOREIGN KEY(PublicationID) REFERENCES Publication(PublicationID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS Articles(
    PublicationID INT,
    ArticleID INT,
    Description VARCHAR(128),
    Text VARCHAR(1000) NOT NULL,
    PRIMARY KEY(PublicationID, ArticleID),
    FOREIGN KEY(PublicationID) REFERENCES Publication(PublicationID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS Management(
    ManagementID INT,
    Name VARCHAR(128) NOT NULL,
    Department VARCHAR(128) NOT NULL,
    PRIMARY KEY(ManagementID )
);
CREATE TABLE IF NOT EXISTS MonthlyRevenueReport(
    ReportID INT,
    TotalShippingCost DECIMAL(9,2) NOT NULL,
    Date Date NOT NULL,
    TotalPrice DECIMAL(9,2) NOT NULL,
    NumberCopies INT NOT NULL,
    City VARCHAR(128) NOT NULL,
    PRIMARY KEY(ReportID )
);
CREATE TABLE IF NOT EXISTS MonthlyPaymentReport(
    ReportID INT,
    Payment DECIMAL(9,2) NOT NULL,
    Date Date NOT NULL,
    WorkType VARCHAR(128) NOT NULL,
    PRIMARY KEY(ReportID )
);
CREATE TABLE IF NOT EXISTS GenerateRevenueReport(
    ReportID INT,
    DistributorID INT,
    FOREIGN KEY(ReportID) REFERENCES MonthlyRevenueReport(ReportID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY(DistributorID) REFERENCES Distributor(DistributorID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS GeneratePaymentReport(
    ReportID INT,
    EID INT,
    FOREIGN KEY(ReportID) REFERENCES MonthlyPaymentReport(ReportID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY(EID) REFERENCES Editor(EID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS CollectRevenueReport(
    ManagementID INT,
    ReportID INT,
    FOREIGN KEY(ManagementID) REFERENCES Management(ManagementID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY(ReportID) REFERENCES MonthlyRevenueReport(ReportID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS CollectPaymentReport(
    ManagementID INT,
    ReportID INT,
    FOREIGN KEY(ManagementID) REFERENCES Management(ManagementID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY(ReportID) REFERENCES MonthlyPaymentReport(ReportID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS Orders(
    OrderID INT,
    Price Decimal(9,2) NOT NULL,
    ShippingCost Decimal(9,2) NOT NULL,
    NumCopies INT NOT NULL,
    Book VARCHAR(128) NOT NULL,
    Date DATE NOT NULL,
    PRIMARY KEY(OrderID)
);
CREATE TABLE IF NOT EXISTS PlacesOrder(
    DistributorID INT,
    OrderID INT,
    FOREIGN KEY(DistributorID) REFERENCES Distributor(DistributorID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY(OrderID) REFERENCES Orders(OrderID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS TransactionDetails(
    OrderID INT,
    DeliveryTime TIMESTAMP NOT NULL,
    DeliveryDate DATE NOT NULL,
    FOREIGN KEY(OrderID) REFERENCES Orders(OrderID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS Account(
    AccountNum INT,
    Date DATE NOT NULL,
    Balance DECIMAL(9,2) NOT NULL,
    PastOrders INT,
    PRIMARY KEY(AccountNum)
);
CREATE TABLE IF NOT EXISTS hasAccount(
    AccountNum INT,
    DistributorID INT NOT NULL,
    FOREIGN KEY(AccountNum) REFERENCES Account(AccountNum) 
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY(DistributorID) REFERENCES Distributor(DistributorID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS Consist(
    OrderID INT,
    PublicationID INT,
    FOREIGN KEY(OrderID) REFERENCES Orders(OrderID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY(PublicationID) REFERENCES Publication(PublicationID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS assignsAuthors(
    PID INT,
    EID INT,
    FOREIGN KEY(PID) REFERENCES Publisher(PID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY(EID) REFERENCES Editor(EID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS CreateUpdateDeletePublication(
    PID INT,
    PublicationID INT,
    FOREIGN KEY(PID) REFERENCES Publisher(PID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY(PublicationID) REFERENCES Publication(PublicationID) 
    on UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS writesPublication(
    PublicationID INT,
    EID INT,
    FOREIGN KEY(PublicationID) REFERENCES Publication(PublicationID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY(EID) REFERENCES Editor(EID) 
    ON UPDATE CASCADE
    ON DELETE CASCADE
);