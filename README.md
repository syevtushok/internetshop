#Internet shop.
Table of contents
* [What is it?](####what)
* [Purpose of it](#purpose)
* [Project structure](#structure)
* [What technologies used](#techn)
* [How to start](#start)

#### <a name="what">What is it?</a> 
This is a simple example of an internet shop implementation.
<hr>

#### <a name="purpose">Purpose of it</a>
<p id="purpose">Purpose of this project to demonstrate the skills of the applicant for the position Junior Java Developer.</p>
<hr>

#### <a name="purpose"></a>Project structure
The project has a clear structure. It has models, service and dao layers.
The project has two DAO implementation. The first implementation represents just work with Java Collection Framework. The second shows how java application interacts with a database.
The project has controllers for management requests from users.
Registration, login, and logout have full functionality. Users can add items to the basket, buy items and see a history of orders. Admin can create new items, delete them. 
Also, the admin can see all users.  By helping filters, users cannot see pages for admin, and admin cannot see pages for users. 
<hr>

#### <a name="techn"></a>Project structure
The technology used in this project:
* Java 8
* Maven 4.0.0
* MavenCheckstylePlugin 2.17
* JSTL 1.2
* Servlets 3.1.0
* Log4j 1.2.7
* MySQL 8.0.18
* Tomcat 9.0.30
* Bootstrap 4.0
<hr>

#### <a name="start">How to start</a>
* Open the project in your IDE. You can copy URL this repository or download it as zip.
* Configure Tomcat:
    * add artifact internetshop:war exploded;
    * add Java SDK (prefer version 11 or higher)
* Run init_db.sql from directory src/main/resources. This file creates tables for proper work java application.
* At src.main.java.Factory class use username and password for your DB to create a Connection.
* Change a path in src.main.java.resources.log4j.properties to properly write logs on the disc.
* Run application and you see
<hr>
