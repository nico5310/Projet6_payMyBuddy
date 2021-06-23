# PayMyBuddy
OpenClassRooms training project 6 

### Resume
PayMyBuddy is a project on creating a prototype application for customers to transfer money to manage their finances or pay their friends.

### Synthesis
- Database design.
- Design of the Java DAL layer (with the management of commits / rollback transactions).
- Design of the web interface for the project prototype based on the models submitted.

### Needs / Features
- New users must be able to register with a unique email ID
- Users must be able to log in from their accounts in the database.
- After login, users can add people to their lists from their email address (if the person already exists in the database).
- A user can deposit money into their account in our app.
- From the available balance, users can make payments to any other user registered on the app.
- At any time, users can transfer money to their bank account.
- With each transaction, we charge a percentage of 0.5% to monetize the application.

## Techniques
- Java Jdk 11
- Hibernate 5.4.31
- MySQL 8.0
- Spring Boot
- Spring Boot Security
- Application port 8080
- MySQL port 3306
- paymybuddy database for application
- paymybuddytest database for test

![Diagram class] (src/main/resources/Diagclass.png)
![Model MPD] (src/main/resources/ModelMPD.png)
