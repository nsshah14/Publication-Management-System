# Project narrative
This is the description of the main project for the course Database Management Systems. The details are likely to change over time, so please visit the online version again. 


You are to build a database system, WolfPubDb, for the WolfCity publishing house. The database system will be used by the management and editors of the publishing house, and should maintain information on at least the following: 

* publication: title, editor(s), type (book, journal, magazine), periodicity for periodic publication (weekly, monthly, etc), typical topics (e.g., general, sports, technology, etc);
* edition (1st, 2nd, etc), ISBN number, and publication date for a book;
* issue of a periodic publication: title, date of issue, articles in the issue;
* for a book or article: author(s), title, date of creation, text of the article;
* distributor: name, type (wholesale distributor, bookstore, library), street address, city, phone number, contact person, balance (how much is owed to the publishing house); 
* order (this is a request for a certain number of copies of a book or of an issue of a periodic publication to be produced by a certain date to meet a distributor's demand): distributor, title of book or issue, number of copies, date, price, shipping cost.

By talking to the managers and editors, we have elicited for you the following information about the publishing house. (Note that in working on this project, you might discover that not every bit of the information has to be explicitly captured in the database. Part of the modeling effort is to decide what to keep and what to discard. In doing your project, you will need to make additional assumptions as well as identify the potential inconsistencies and resolve them. Any reasonable assumptions are fine, but they must be documented in your reports. You can consult with the TA or instructor if you have questions about the assumptions.) 

* The publishing house publishes books, monthly journals, and weekly magazines. Each publication has one or more editors and caters to a certain audience, which is reflected by the selection of, for instance, articles for each issue of a periodic publication.
* Each issue of each periodic publication is produced on a certain date and consists of a sequence of articles written by journalists. 
* Both authors and editors get paid for their work, in certain amounts by certain dates. Staff authors and editors get paid periodically; invited editors and authors get paid for the (usually one-time) deliverables of their work.
* The publishing house sends its production to distributors, which include wholesale distributors, bookstores, and libraries. Distributors place orders for book editions and for issues of publications to be delivered by a certain date. Each distributor has an account with the publishing house; the information in the account includes the distributor's name and contact information, as well as the balance, which is how much the distributor owes the publishing house for outstanding and past orders. 
* The publishing house keeps information about all current and past orders from each distributor, and bills distributors periodically based on this information.
* The management of the publishing house collects and analyzes reports on various aspects of the production and distribution of the books, magazines, and journals, as well as of payments to the regular and invited editors and authors. The reports include summaries per month and since the inception of the publishing house, as well as by distributor and location.

## Tasks and Operations

The following are the four major kinds of tasks that need to be performed using your database. Each task potentially consists of a number of operations; an "operation" is something that corresponds to a separate action. For example, Editing and publishing is considered one task, which involves separate operations such as entering basic information on a new publication, updating information, and assigning editors to publications. 

Each student should write about the same number of application programs, but it is up to each team to decide how to allocate the overall effort. 

* **Editing and publishing.** Enter basic information on a new publication. Update information. Assign editor(s) to publication. Let each editor view the information on the publications he/she is responsible for. Edit table of contents of a publication, by adding/deleting articles (for periodic publications) or chapters/sections (for books). 
* **Production of a book edition or of an issue of a publication.** Enter a new book edition or new issue of a publication. Update, delete a book edition or publication issue. Enter/update an article or chapter: title, author's name, topic, and date. Enter/update text of an article. Find books and articles by topic, date, author's name. Enter payment for author or editor, and keep track of when each payment was claimed by its addressee. 
* **Distribution.** Enter new distributor; update distributor information; delete a distributor. Input orders from distributors, for a book edition or an issue of a publication per distributor, for a certain date. Bill distributor for an order; change outstanding balance of a distributor on receipt of a payment. 
* **Reports.** Generate montly reports: number and total price of copies of each publication bought per distributor per month; total revenue of the publishing house; total expenses (i.e., shipping costs and salaries). Calculate the total current number of distributors; calculate total revenue (since inception) per city, per distributor, and per location. Calculate total payments to the editors and authors, per time period and per work type (book authorship, article authorship, or editorial work). 

