CAR RENTAL APP - java CRUD application for renting cars

Technologies used:\
Java 8\
Spring Boot\
Spring Data\
JPA/Hibernate\
Thymeleaf\
Bootstrap\
HTML\
CSS\
JavaScript/JQuery\
MySQL/H2 Database\
Maven

You need an IDE and MySQL database (optional)* to start the application. MySQL database should be configured according to the application.properties file in the src/main/resources directory. In mysql folder you will find an SQL script (contains exemplary data) that you need to run in your database to populate the data. Otherwise you'll end up with an empty database. Once it's done you can run the application. In order to use the application you will need to log in to it. There are currently two accounts in the exemplary data (admin and user) that you can use to log in and play with the application to full extent. The login credentials are as follows:\
1) 
user: admin\
pass: admin\
2)
user: user\
pass: user

Have fun!

*if you don't have or don't want to use MySQL database you can copy two files -application.properties and data.sql from src/test/resources to src/main/resources and run the application. In this case the application will use H2 in memory database and you could play with the application normally. Just please remember that in such case any changes made to the data (create, update, delete) will not be visible once you restart the application. Moreover, login credentials to the applications would be different in that case:
1) admin\
user: janek\
pass: bomba
2) user\
user: dawidek\
pass: tromba