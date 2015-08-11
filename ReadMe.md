# TODOManagementSystem 

<b>Abstract</b><br>
The TODOManagement System is a java based command line utility which can be used to maintain and create your TODO lists .
The Softwares used to create this are : 1) Java 2) SQLLite 3) Hibernate 4) JUnit 5) TravisCI 6) Maven.
SOFTWARES USED 
Java .
Hibernate .
SQLLite  - Backend .
Junit  - creating unit test cases .
Maven – to create build process .
TravisCI – to integrate the Unit tests .

<b>Functional Requirements</b><br>
Create a command line based utility to manage the TODO lists
And support for features like 
1.Create a list
2.Add items to a list
3.View the list
4.Modify /Edit the list
5.Mark the finished tasks as Done and they need to be moved to a RECENTLY FINISHED list.

<b>Softwares used </b><br>
Java .
Hibernate .
SQLLite  - Backend .
Junit  - creating unit test cases .
Maven – to create build process .
TravisCI – to integrate the Unit tests .


<b>Hibernate</b><br>
Why do we need to use this ?
Hibernate ORM is concerned with helping the application to achieve persistence.
What is persistence ?
Persistence simply means that we would like our application’s data to outlive the applications process. In Java terms, we would like the state of (some of) our objects to live beyond the scope of the JVM so that the same state is available later. 
How can we use it in the present context.

<b>Steps forward -22Jun2015</b><hr>

First complete the coding for the TODOManager class and the Services layer and the Domain layer .
Follow the TDD approach and test these three layers code .
Use stubs for mimicking the database which can be abstracted using a simple interface and later use a Hibernate based implementation for the integration of the DAO layer with the Front end code .
Need to test the usage of the TravisCI with the ant scripts as an interim approach. This needs to be later replaced with maven scripts later or vice versa .
The DAO layer includes the usage of Hibernate and the Database (SQLite at this point , might have to switch to MySQL or PostGres in case Hibernate does not support it .)

<b>Coding Approach - Timelines etc</b> <br>

As we are looking at a multi tiered , layered implementation, we would need to define the contracts between each of the layers using interfaces.
Finish the code and test cases for the Manager class , Services layer and the Domain layer.
Proceed with the TravisCI support implementation for the automatic code build support.

Hope to do this by the end of this week  and later proceed with study on the DAO layer .

<b>Coding the DAO layer</b><br>
Starting next week start the set up and coding of the DAO layer .(Hibernate tutorials)
As we have to follow TDD .. Understand about the DBUnit , Mockito and how to set up DBScripts for the test harnesses that we might need to create.

<b>Development Environment</b><br>
Start with Eclipse.. 
Need to migrate to IntelliJ IDE later.



[![Build Status](https://travis-ci.org/DebSreerekha/TODOManagementSystem.png)](https://travis-ci.org/[DebSreerekha]/[TODOManagementSystem])


